package com.manoj.service;

import com.manoj.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory(String name, Long userId) throws Exception;

    public List<Category> findCategoryByCarDId(Long id) throws Exception;

    public Category findCategoryById(Long id) throws Exception;

}
