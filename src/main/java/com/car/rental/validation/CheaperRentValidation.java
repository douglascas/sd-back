package com.car.rental.validation;

import com.car.rental.dto.rent.CheaperRentFilterDTO;
import com.car.rental.service.AbstractMessage;
import com.car.rental.util.Email;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class CheaperRentValidation extends AbstractMessage {

    /**
     * Validate information for the cheapest rent search.
     * @param filter
     * @throws IOException
     */
    public void validateSearchRequest(CheaperRentFilterDTO filter) throws IOException {
        if ( Objects.nonNull(filter.getEmail()) && filter.getEmail().equalsIgnoreCase("null")) {
            filter.setEmail(null);
        }
        
        if ( filter.getStartDate().isEqual(filter.getEndDate()) || filter.getEndDate().isBefore(filter.getStartDate()) ) {
            throwsException("error.tariffs.cheaper.rent.equal.dates");
        }

        if ( Objects.nonNull(filter.getEmail()) && !filter.getEmail().isEmpty() && !Email.isValid(filter.getEmail()) ) {
            throwsException("error.tariffs.cheaper.rent.email.invalid");
        }
    }

}
