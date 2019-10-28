package com.car.rental.service.rent;

import com.car.rental.dto.rent.CheaperRentDTO;
import com.car.rental.dto.rent.CheaperRentFilterDTO;
import com.car.rental.model.orm.Tariffs;
import com.car.rental.model.repository.TariffsRepository;
import com.car.rental.service.AbstractMessage;
import com.car.rental.validation.CheaperRentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Service
public class CheaperRentService extends AbstractMessage {

    @Autowired
    private TariffsRepository tariffsRepository;
    @Autowired
    private CheaperRentValidation cheaperRentValidation;


    /**
     * Search the vehicle with the best price for rent.
     * @param filter
     * @return {@link CheaperRentDTO}
     * @throws IOException
     */
    public CheaperRentDTO cheaperRent(CheaperRentFilterDTO filter) throws IOException {
        this.cheaperRentValidation.validateSearchRequest(filter);

        List<Tariffs> allTariffs = this.tariffsRepository.findByActivedTrue();
        if ( Objects.isNull(allTariffs) || allTariffs.isEmpty() ) {
            return null;
        }

        allTariffs.sort( Tariffs::compareTo );
        Tariffs tariffs = this.getBetterTariffs( allTariffs );

        CheaperRentDTO cheaperRent = mapper.map(tariffs,  CheaperRentDTO.class);
        cheaperRent.setAmountOfDaily( this.calculateDaily( filter ).intValue() );
        cheaperRent.setAmount( this.calculateDailyValue(filter, tariffs) );

        return cheaperRent;
    }

    /**
     * Search the list for an item with the same values ​​and the category ID greater than the first item in the list.
     * @param tariffs
     * @return {@link Tariffs}
     */
    private Tariffs getBetterTariffs(List<Tariffs> tariffs) {
        Tariffs firstTariffs = tariffs.get(0);

        Tariffs tariffsBetter = tariffs.stream().filter( t ->
                ( (t.getWeekday().compareTo(firstTariffs.getWeekday()) == 0
                        && t.getWeekendDay().compareTo(firstTariffs.getWeekendDay()) == 0
                        && t.getWeekdayLoyalty().compareTo(firstTariffs.getWeekdayLoyalty()) == 0
                        && t.getWeekendDayLoyalty().compareTo(firstTariffs.getWeekendDayLoyalty()) == 0)
                        && t.getAutomobile().getCategory().getId().compareTo(firstTariffs.getAutomobile().getCategory().getId()) > 0
                )
        ).findAny().orElse(null);

        return ( Objects.isNull(tariffsBetter) ? firstTariffs : tariffsBetter);
    }

    /**
     * Calculates the total daily rate.
     * @param filter
     * @param tariffs
     * @return
     */
    private BigDecimal calculateDailyValue(CheaperRentFilterDTO filter, Tariffs tariffs){
        Integer amountOfDaily = this.calculateDaily(filter).intValue();
        Integer weekend = this.calculateWeekendDays(filter, amountOfDaily);

        BigDecimal weekday = ( filter.getLoyaltyProgram() ? tariffs.getWeekdayLoyalty() : tariffs.getWeekday() );
        BigDecimal weekendDay = ( filter.getLoyaltyProgram() ? tariffs.getWeekendDayLoyalty() : tariffs.getWeekendDay() );

        BigDecimal totalWeekday = weekday.multiply( new BigDecimal(amountOfDaily - weekend) );
        BigDecimal totalWeekendDay = weekendDay.multiply( new BigDecimal(weekend) );

        return totalWeekday.add(totalWeekendDay);
    }

    /**
     * Calculates the number of days that are weekend.
     * @param filter
     * @param amountOfDaily
     * @return
     */
    private Integer calculateWeekendDays(CheaperRentFilterDTO filter, Integer amountOfDaily){
        int weekend = 0;
        for (int i = 0; i <= amountOfDaily; i++) {
            DayOfWeek dayOfWeek = filter.getStartDate().plusDays(i).getDayOfWeek();
            if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
                weekend++;
            }
        }

        return weekend;
    }

    /**
     * Calculate a daily amount.
     *
     * @param filter
     * @return
     */
    private Long calculateDaily(CheaperRentFilterDTO filter){
        return ChronoUnit.DAYS.between(filter.getStartDate(), filter.getEndDate());
    }

}
