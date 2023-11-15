package com.mjovanc.blog.service;

import com.mjovanc.blog.model.BlogCategory;
import com.mjovanc.blog.repository.BlogCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogCategoryService {

    private final BlogCategoryRepository blogCategoryRepository;

    @Autowired
    public BlogCategoryService(BlogCategoryRepository blogCategoryRepository) {
        this.blogCategoryRepository = blogCategoryRepository;
    }

    public List<BlogCategory> getAllBlogCategories() {
        return blogCategoryRepository.findAll();
    }

    public BlogCategory getBlogCategoryById(Long id) {
        if (blogCategoryRepository.existsById(id)) {
            BlogCategory blogCategory = blogCategoryRepository.findById(id).get();
            return blogCategory;
        }

        return null;
    }

    public HttpStatus createBlogCategory(BlogCategory blogCategory) {
        blogCategoryRepository.save(blogCategory);
        return HttpStatus.CREATED;
    }

    public HttpStatus updateBlogCategoryById(Long id, BlogCategory blogCategory) {
        Optional<BlogCategory> blogCategoryToFind = blogCategoryRepository.findById(id);

        if (blogCategoryToFind.isPresent()) {
            BlogCategory blogCategoryToUpdate = blogCategoryToFind.get();
            blogCategoryToUpdate.setName(blogCategory.getName());

            blogCategoryRepository.save(blogCategoryToUpdate);
            return HttpStatus.OK;
        } else {
            blogCategoryRepository.save(blogCategory);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus deleteCategoryById(Long id) {
        if (blogCategoryRepository.existsById(id)) {
            blogCategoryRepository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
