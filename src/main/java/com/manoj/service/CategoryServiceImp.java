package com.manoj.service;

import com.manoj.Repository.CategoryRepository;
import com.manoj.model.CarD;
import com.manoj.model.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CarDService carDService;


    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        CarD carD =carDService.getCarDByUserId(userId);
        Category category=new Category();
        category.setName(name);
        category.setCarD(carD);

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findCategoryByCarDId(Long id) throws Exception {
        CarD carD=carDService.getCarDByUserId(id);
        return categoryRepository.findByCarDId(carD.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);

        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found");
        }
        return optionalCategory.get();
    }
}
