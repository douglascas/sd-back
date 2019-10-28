package com.car.rental.restcontroller;

import com.car.rental.bean.CustomModelMapper;
import com.car.rental.dto.ModelYearDTO;
import com.car.rental.dto.fipe.ManufacturerDTO;
import com.car.rental.dto.fipe.ModelDTO;
import com.car.rental.model.orm.Category;
import com.car.rental.service.MessageService;
import com.car.rental.service.automobile.CategoryService;
import com.car.rental.service.fipe.FipeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AutomobileResource.class)
public class AutomobileResourceTest {

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

    @MockBean
    private FipeService fipeService;
    @MockBean
    private CategoryService categoryService;
    @Autowired
    public CustomModelMapper mapper;
    @Autowired
    public MessageService messageService;



    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void givenCategories_whenGetCategories_thenReturnJsonArray() throws Exception {
        Category sedan = new Category("Sedan");
        Category van = new Category("Van");
        Category pickup = new Category("Pickup");

        List<Category> allCategories = Arrays.asList(sedan, van, pickup);

        given(this.categoryService.listAll()).willReturn(allCategories);

        mvc.perform(get("/car-rental/categories").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].name", is(sedan.getName())))
                .andExpect(jsonPath("$[1].name", is(van.getName())))
                .andExpect(jsonPath("$[2].name", is(pickup.getName())));
        verify(this.categoryService, VerificationModeFactory.times(1)).listAll();
        reset(this.categoryService);
    }

    @Test
    public void givenCategories_whenGetCategoryById_thenReturnJson() throws Exception {
        Category sedan = new Category(3, "Sedan");

        given(this.categoryService.findById(sedan.getId())).willReturn(Optional.of(sedan));

        mvc.perform(get("/car-rental/categories/3").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(sedan.getId())))
                .andExpect(jsonPath("$.name", is(sedan.getName())));
        verify(this.categoryService, VerificationModeFactory.times(1)).findById(sedan.getId());
        reset(this.categoryService);
    }

    @Test
    public void givenManufacturer_whenGetManufacturers_thenReturnJsonArray() throws Exception {
        ManufacturerDTO audi = new ManufacturerDTO(6, "AUDI");
        ManufacturerDTO bmw = new ManufacturerDTO(7, "BMW");
        ManufacturerDTO citroen = new ManufacturerDTO(13, "CITROEN");
        ManufacturerDTO fiat = new ManufacturerDTO(21, "FIAT");

        List<ManufacturerDTO> manufacturers = Arrays.asList(audi, bmw, citroen, fiat);
        ResponseEntity<List<ManufacturerDTO>> entity = new ResponseEntity<>(manufacturers, HttpStatus.OK);

        given(this.fipeService.manufactures()).willReturn(entity);

        mvc.perform(get("/car-rental/manufacturers").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].id", is(audi.getId())))
                .andExpect(jsonPath("$[0].name", is(audi.getName())))
                .andExpect(jsonPath("$[1].id", is(bmw.getId())))
                .andExpect(jsonPath("$[1].name", is(bmw.getName())))
                .andExpect(jsonPath("$[2].id", is(citroen.getId())))
                .andExpect(jsonPath("$[2].name", is(citroen.getName())))
                .andExpect(jsonPath("$[3].id", is(fiat.getId())))
                .andExpect(jsonPath("$[3].name", is(fiat.getName())));
        verify(this.fipeService, VerificationModeFactory.times(1)).manufactures();
        reset(this.fipeService);
    }

    @Test
    public void givenModel_whenGetModels_thenReturnJsonArray() throws Exception {
        ManufacturerDTO fiat = new ManufacturerDTO(21, "FIAT");

        ModelDTO stilo = new ModelDTO("4531", "Stilo Dualogic 1.8 Sporting Flex 8V 5p");
        ModelDTO tipo = new ModelDTO("630", "Tipo 1.6 i.e. 2p e 4p");
        ModelDTO idea = new ModelDTO("5341", "Idea ESSENCE 1.6 Flex 16V 5p");

        List<ModelDTO> models = Arrays.asList(stilo, tipo, idea);
        ResponseEntity<List<ModelDTO>> entity = new ResponseEntity<>(models, HttpStatus.OK);

        given(this.fipeService.models(fiat.getId())).willReturn(entity);

        mvc.perform(get("/car-rental/manufacturers/21/models").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(stilo.getId())))
                .andExpect(jsonPath("$[0].name", is(stilo.getName())))
                .andExpect(jsonPath("$[1].id", is(tipo.getId())))
                .andExpect(jsonPath("$[1].name", is(tipo.getName())))
                .andExpect(jsonPath("$[2].id", is(idea.getId())))
                .andExpect(jsonPath("$[2].name", is(idea.getName())));
        verify(this.fipeService, VerificationModeFactory.times(1)).models(fiat.getId());
        reset(this.fipeService);
    }

    @Test
    public void givenModelYear_whenGetModelYears_thenReturnJsonArray() throws Exception {
        ManufacturerDTO fiat = new ManufacturerDTO(21, "FIAT");
        ModelDTO stilo = new ModelDTO("4531", "Stilo Dualogic 1.8 Sporting Flex 8V 5p");

        ModelYearDTO stilo2011 = new ModelYearDTO("2011-1", "2011 Gasolina");
        ModelYearDTO stilo2010 = new ModelYearDTO("2010-1", "2010 Gasolina");

        List<ModelYearDTO> models = Arrays.asList(stilo2011, stilo2010);
        ResponseEntity<List<ModelYearDTO>> entity = new ResponseEntity<>(models, HttpStatus.OK);

        given(this.fipeService.modelsYear(fiat.getId(), Integer.parseInt(stilo.getId()))).willReturn(entity);

        mvc.perform(get("/car-rental/manufacturers/21/models/4531").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(stilo2011.getId())))
                .andExpect(jsonPath("$[0].name", is(stilo2011.getName())))
                .andExpect(jsonPath("$[1].id", is(stilo2010.getId())))
                .andExpect(jsonPath("$[1].name", is(stilo2010.getName())));
        verify(this.fipeService, VerificationModeFactory.times(1)).modelsYear(fiat.getId(), Integer.parseInt(stilo.getId()));
        reset(this.fipeService);
    }

}
