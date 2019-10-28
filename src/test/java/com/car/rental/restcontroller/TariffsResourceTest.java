package com.car.rental.restcontroller;

import com.car.rental.bean.CustomModelMapper;
import com.car.rental.dto.tariff.TariffsDTO;
import com.car.rental.model.orm.*;
import com.car.rental.service.MessageService;
import com.car.rental.service.automobile.TariffsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;


@RunWith(SpringRunner.class)
@WebMvcTest(TariffsResource.class)
public class TariffsResourceTest {

    @TestConfiguration
    static class AutomobileResourceContextConfiguration {
        @Bean
        public MessageService messageService() {
            return new MessageService();
        }

        @Bean
        public CustomModelMapper customModelMapper() {
            return new CustomModelMapper(messageService());
        }
    }

    @Autowired
    private MockMvc mvc;
    @Autowired
    public CustomModelMapper mapper;
    @Autowired
    public MessageService messageService;
    @MockBean
    private TariffsService tariffsService;
    @Autowired
    private ObjectMapper om;

    @Test
    public void givenTariffs_whenGetTariffsById_thenReturnJson() throws Exception {
        Category sedan = new Category(3, "Sedan");
        Manufacturer fiat = new Manufacturer(21, "FIAT");
        ModelYear stilo2011 = new ModelYear("4531", "Stilo Dualogic 1.8 Sporting Flex 8V 5p", "2011 Gasolina");
        Automobile automobile = new Automobile(fiat, stilo2011, sedan);

        Tariffs tariffs = new Tariffs();
        tariffs.setId( Long.parseLong("50") );
        tariffs.setAutomobile( automobile );
        tariffs.setActived( Boolean.TRUE );
        tariffs.setWeekday( new BigDecimal("25.3") );
        tariffs.setWeekendDay( new BigDecimal("23.3") );
        tariffs.setWeekdayLoyalty( new BigDecimal("22.3") );
        tariffs.setWeekendDayLoyalty( new BigDecimal("20.3") );

        given(this.tariffsService.findById(tariffs.getId())).willReturn(tariffs);

        mvc.perform(get("/car-rental/tariffs/50").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(tariffs.getId().intValue())))
                .andExpect(jsonPath("$.automobileManufacturerName", is(fiat.getName())))
                .andExpect(jsonPath("$.automobileCategoryName", is(sedan.getName())))
                .andExpect(jsonPath("$.automobileModelYearName", is(stilo2011.getName())))
                .andExpect(jsonPath("$.automobileModelYearYear", is(stilo2011.getYear())))
                .andExpect(jsonPath("$.weekday", is(tariffs.getWeekday().doubleValue())))
                .andExpect(jsonPath("$.weekendDay", is(tariffs.getWeekendDay().doubleValue())))
                .andExpect(jsonPath("$.weekdayLoyalty", is(tariffs.getWeekdayLoyalty().doubleValue())))
                .andExpect(jsonPath("$.weekendDayLoyalty", is(tariffs.getWeekendDayLoyalty().doubleValue())));
        verify(this.tariffsService, VerificationModeFactory.times(1)).findById(tariffs.getId());
        reset(this.tariffsService);
    }

    @Test
    public void givenTariffs_whenPutTariffs_thenReturnJson() throws Exception {
        doNothing().when(this.tariffsService).delete(1L);

        mvc.perform(MockMvcRequestBuilders.delete("/car-rental/tariffs/1"))
                .andExpect(status().isNoContent());

        verify(this.tariffsService, times(1)).delete(1L);
        reset(this.tariffsService);
    }

    @Test
    public void givenTariffs_whenPostTariffs_thenReturnJson() throws Exception {
        Category sedan = new Category(3, "Sedan");
        Manufacturer fiat = new Manufacturer(21, "FIAT");
        ModelYear stilo2011 = new ModelYear("4531", "Stilo Dualogic 1.8 Sporting Flex 8V 5p", "2011 Gasolina");
        Automobile automobile = new Automobile(fiat, stilo2011, sedan);

        Tariffs tariffs = new Tariffs();
        tariffs.setId( Long.parseLong("50") );
        tariffs.setAutomobile( automobile );
        tariffs.setActived( Boolean.TRUE );
        tariffs.setWeekday( new BigDecimal("25.3") );
        tariffs.setWeekendDay( new BigDecimal("23.3") );
        tariffs.setWeekdayLoyalty( new BigDecimal("22.3") );
        tariffs.setWeekendDayLoyalty( new BigDecimal("20.3") );

        when(this.tariffsService.create(any(TariffsDTO.class))).thenReturn(tariffs);

        mvc.perform(MockMvcRequestBuilders.post("/car-rental/tariffs")
                .content(om.writeValueAsString(tariffs))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(tariffs.getId().intValue())))
                .andExpect(jsonPath("$.automobileManufacturerName", is(fiat.getName())))
                .andExpect(jsonPath("$.automobileCategoryName", is(sedan.getName())))
                .andExpect(jsonPath("$.automobileModelYearName", is(stilo2011.getName())))
                .andExpect(jsonPath("$.automobileModelYearYear", is(stilo2011.getYear())))
                .andExpect(jsonPath("$.weekday", is(tariffs.getWeekday().doubleValue())))
                .andExpect(jsonPath("$.weekendDay", is(tariffs.getWeekendDay().doubleValue())))
                .andExpect(jsonPath("$.weekdayLoyalty", is(tariffs.getWeekdayLoyalty().doubleValue())))
                .andExpect(jsonPath("$.weekendDayLoyalty", is(tariffs.getWeekendDayLoyalty().doubleValue())));

        verify(this.tariffsService, times(1)).create(any(TariffsDTO.class));
        reset(this.tariffsService);
    }

}
