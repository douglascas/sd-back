package com.car.rental.model.repository;

import com.car.rental.model.orm.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TariffsRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private TariffsRepository tariffsRepository;


    @Test
    public void whenFindByAutomobileIdAndActivedTrue_thenReturnRariffs(){
        Tariffs tariffs = this.builderTariffs().get(0);

        this.entityManager.persistAndFlush( tariffs.getAutomobile() );
        this.entityManager.persistAndFlush( tariffs );

        Tariffs found = this.tariffsRepository.findByAutomobileIdAndActivedTrue(tariffs.getAutomobile().getId());

        assertThat(found.getAutomobile().getId()).isEqualTo(tariffs.getAutomobile().getId());
    }

    @Test
    public void whenFindByActivedTrueAndId_thenReturnRariffs(){
        Tariffs tariffs = this.builderTariffs().get(0);

        this.entityManager.persistAndFlush( tariffs.getAutomobile() );
        this.entityManager.persistAndFlush( tariffs );

        Tariffs found = this.tariffsRepository.findByActivedTrueAndId(tariffs.getId());

        assertThat(found.getId()).isEqualTo(tariffs.getId());
    }

    @Test
    public void whenFindByActivedTrueAndId_thenReturnNull(){
        Tariffs tariffs = this.builderTariffs().get(0);
        tariffs.setActived( Boolean.FALSE );

        this.entityManager.persistAndFlush( tariffs.getAutomobile() );
        this.entityManager.persistAndFlush(tariffs);

        Tariffs found = this.tariffsRepository.findByActivedTrueAndId(tariffs.getId());

        assertThat(found).isNull();;
    }

    private List<Tariffs> builderTariffs(){
        Tariffs tariffsKiaEclipse = new Tariffs();
        tariffsKiaEclipse.setActived( Boolean.TRUE );
        tariffsKiaEclipse.setAutomobile( this.buiderAutomobile().get(0) );
        tariffsKiaEclipse.setWeekday( new BigDecimal(95.4) );
        tariffsKiaEclipse.setWeekendDay( new BigDecimal(93.2) );
        tariffsKiaEclipse.setWeekdayLoyalty( new BigDecimal(87.4) );
        tariffsKiaEclipse.setWeekendDayLoyalty( new BigDecimal(85.2)  );

        Tariffs tariffsKiaPicanto = new Tariffs();
        tariffsKiaPicanto.setActived( Boolean.TRUE );
        tariffsKiaPicanto.setAutomobile( this.buiderAutomobile().get(1) );
        tariffsKiaPicanto.setWeekday( new BigDecimal(75.4) );
        tariffsKiaPicanto.setWeekendDay( new BigDecimal(73.2) );
        tariffsKiaPicanto.setWeekdayLoyalty( new BigDecimal(77.4) );
        tariffsKiaPicanto.setWeekendDayLoyalty( new BigDecimal(75.2)  );

        Tariffs tariffsKiaSorento = new Tariffs();
        tariffsKiaSorento.setActived( Boolean.TRUE );
        tariffsKiaSorento.setAutomobile( this.buiderAutomobile().get(2) );
        tariffsKiaSorento.setWeekday( new BigDecimal(105.4) );
        tariffsKiaSorento.setWeekendDay( new BigDecimal(103.2) );
        tariffsKiaSorento.setWeekdayLoyalty( new BigDecimal(97.4) );
        tariffsKiaSorento.setWeekendDayLoyalty( new BigDecimal(95.2)  );

        Tariffs tariffsFiatMobi = new Tariffs();
        tariffsFiatMobi.setActived( Boolean.TRUE );
        tariffsFiatMobi.setAutomobile( this.buiderAutomobile().get(3) );
        tariffsFiatMobi.setWeekday( new BigDecimal(45.4) );
        tariffsFiatMobi.setWeekendDay( new BigDecimal(43.2) );
        tariffsFiatMobi.setWeekdayLoyalty( new BigDecimal(47.4) );
        tariffsFiatMobi.setWeekendDayLoyalty( new BigDecimal(45.2)  );

        Tariffs tariffsFiatPalio = new Tariffs();
        tariffsFiatPalio.setActived( Boolean.TRUE );
        tariffsFiatPalio.setAutomobile( this.buiderAutomobile().get(4) );
        tariffsFiatPalio.setWeekday( new BigDecimal(55.4) );
        tariffsFiatPalio.setWeekendDay( new BigDecimal(53.2) );
        tariffsFiatPalio.setWeekdayLoyalty( new BigDecimal(57.4) );
        tariffsFiatPalio.setWeekendDayLoyalty( new BigDecimal(55.2)  );

        Tariffs tariffsFiatStilo = new Tariffs();
        tariffsFiatStilo.setActived( Boolean.TRUE );
        tariffsFiatStilo.setAutomobile( this.buiderAutomobile().get(5) );
        tariffsFiatStilo.setWeekday( new BigDecimal(65.4) );
        tariffsFiatStilo.setWeekendDay( new BigDecimal(63.2) );
        tariffsFiatStilo.setWeekdayLoyalty( new BigDecimal(67.4) );
        tariffsFiatStilo.setWeekendDayLoyalty( new BigDecimal(65.2)  );

        return Arrays.asList(tariffsKiaEclipse, tariffsKiaPicanto, tariffsKiaSorento, tariffsFiatMobi, tariffsFiatPalio, tariffsFiatStilo);
    }

    private List<Automobile> buiderAutomobile(){
        Automobile kiaEclipse = new Automobile(this.builderManufacturer().get(0), this.builderModelYear().get(0), this.builderCategory().get(0));
        Automobile kiaPicanto = new Automobile(this.builderManufacturer().get(0), this.builderModelYear().get(1), this.builderCategory().get(0));
        Automobile kiaSorento = new Automobile(this.builderManufacturer().get(0), this.builderModelYear().get(2), this.builderCategory().get(0));
        Automobile fiatMobi = new Automobile(this.builderManufacturer().get(1), this.builderModelYear().get(3), this.builderCategory().get(1));
        Automobile fiatPalio = new Automobile(this.builderManufacturer().get(1), this.builderModelYear().get(4), this.builderCategory().get(1));
        Automobile fiatStilo = new Automobile(this.builderManufacturer().get(1), this.builderModelYear().get(5), this.builderCategory().get(1));;

        return Arrays.asList(kiaEclipse, kiaPicanto, kiaSorento, fiatMobi, fiatPalio, fiatStilo);
    }

    private List<ModelYear> builderModelYear(){
        ModelYear eclipse = new ModelYear("3522", "Eclipse GT 3.8 V6 267cv", "2019 gasolina");
        ModelYear picanto = new ModelYear("8213", "Picanto GT 1.0 12V Flex Aut.", "2018 gasolina");
        ModelYear sorento = new ModelYear("3970", "Sorento EX 2.5 16V 4x4 Mec. Diesel", "2009 Diesel");
        ModelYear mobi = new ModelYear("7612", "MOBI WAY 1.0 Fire Flex 5p.", "2019 gasolina");
        ModelYear palio = new ModelYear("531", "Palio EX 1.0 mpi 4p", "2000 Álcool");
        ModelYear stilo = new ModelYear("5065", "Stilo Duologic 1.8 ATTRACTIVE Flex 8V 5p", "2011 Gasolina");

        return Arrays.asList(eclipse, picanto, sorento, mobi, palio, stilo);
    }

    private List<Category> builderCategory(){
        Category compactHatch = new Category(1, "Compact Hatch");
        Category mediumHatch = new Category(2, "Medium Hatch");

        return Arrays.asList(compactHatch, mediumHatch);
    }

    private List<Manufacturer> builderManufacturer(){
        Manufacturer kia = new Manufacturer(31, "KIA");
        Manufacturer fiat = new Manufacturer(21, "FIAT");

        return Arrays.asList(kia, fiat);
    }

}
