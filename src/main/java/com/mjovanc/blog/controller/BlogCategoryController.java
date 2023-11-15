package com.mjovanc.blog.controller;

import com.mjovanc.blog.model.BlogCategory;
import com.mjovanc.blog.service.BlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/categories")
public class BlogCategoryController {

    private final BlogCategoryService blogCategoryService;

    @Autowired
    public BlogCategoryController(BlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<BlogCategory>> getAllBlogCategories() {
        return new ResponseEntity<>(blogCategoryService.getAllBlogCategories(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<BlogCategory> getBlogCategoryById(@PathVariable Long id) {
        BlogCategory blogCategory = blogCategoryService.getBlogCategoryById(id);
        if (blogCategory != null) {
            return new ResponseEntity<>(blogCategory, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<BlogCategory> createBlogCategory(@RequestBody BlogCategory blogCategory) {
        return new ResponseEntity<>(blogCategoryService.createBlogCategory(blogCategory));
    }

    @PutMapping("{id}")
    public ResponseEntity<BlogCategory> updateBlogCategoryById(@PathVariable Long id, @RequestBody BlogCategory blogCategory) {
        return new ResponseEntity<>(blogCategoryService.updateBlogCategoryById(id, blogCategory));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BlogCategory> deleteBlogCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(blogCategoryService.deleteCategoryById(id));
    }
}