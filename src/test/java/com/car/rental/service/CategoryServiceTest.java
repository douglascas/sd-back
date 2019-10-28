package com.car.rental.service;

import com.car.rental.model.orm.Category;
import com.car.rental.model.repository.CategoryRepository;
import com.car.rental.service.automobile.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CategoryServiceTest {

    @TestConfiguration
    static class CategoryServiceContextConfiguration {
        @Bean
        public CategoryService categoryService() {
            return new CategoryService();
        }
    }

    @Autowired
    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() {
        Category sedan = new Category(3, "Sedan");

        Category pickup = new Category();
        pickup.setName("Pickup");

        List<Category> categories = Arrays.asList(sedan, pickup);

        Mockito.when(this.categoryRepository.findById(sedan.getId())).thenReturn(Optional.of(sedan));
        Mockito.when(this.categoryRepository.findAll()).thenReturn(categories);
    }

    @Test
    public void whenValidId_thenCategoryShouldBeFound() {
        Optional<Category> found = this.categoryService.findById(3);

        assertThat(found.get().getId()).isEqualTo(3);
    }

    @Test
    public void given2Categories_whengetAll_thenReturn2Records() {
        Category sedan = new Category("Sedan");
        Category pickup = new Category("Pickup");

        List<Category> allCategories = this.categoryService.listAll();
        this.verifyFindAllCategoriesIsCalledOnce();
        assertThat(allCategories).hasSize(2).extracting(Category::getName).contains(sedan.getName(), pickup.getName());
    }


    private void verifyFindAllCategoriesIsCalledOnce() {
        Mockito.verify(this.categoryRepository, VerificationModeFactory.times(1)).findAll();
        Mockito.reset(this.categoryRepository);
    }
}
