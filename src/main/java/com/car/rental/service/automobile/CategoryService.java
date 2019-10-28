package com.car.rental.service.automobile;

import com.car.rental.model.orm.Category;
import com.car.rental.model.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public List<Category> listAll(){
        return this.categoryRepository.findAll();
    }

    @Transactional
    public Optional<Category> findById(Integer id){
        return this.categoryRepository.findById(id);
    }

}
