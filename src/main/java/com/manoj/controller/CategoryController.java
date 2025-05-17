package com.manoj.controller;

import com.manoj.model.Category;
import com.manoj.model.User;
import com.manoj.service.CategoryService;
import com.manoj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createCategory(@RequestBody Category category,
                                                    @RequestHeader("Authorization") String jwt)throws Exception {
        User user=userService.findUserBYJwtToken(jwt);

        Category createdCategory=categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping("/category/carD")
    public ResponseEntity<List<Category>> getCarDCategory(
                                                    @RequestHeader("Authorization") String jwt)throws Exception {
        User user=userService.findUserBYJwtToken(jwt);

        List<Category> categories= categoryService.findCategoryByCarDId(user.getId());

        return new ResponseEntity<>(categories, HttpStatus.CREATED);
    }

}
