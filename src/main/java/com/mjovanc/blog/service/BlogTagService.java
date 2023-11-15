package com.mjovanc.blog.service;

import com.mjovanc.blog.model.BlogTag;
import com.mjovanc.blog.repository.BlogTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogTagService {

    private final BlogTagRepository blogTagRepository;

    @Autowired
    public BlogTagService(BlogTagRepository blogTagRepository) {
        this.blogTagRepository = blogTagRepository;
    }

    public List<BlogTag> getAllBlogTags() {
        return blogTagRepository.findAll();
    }

    public BlogTag getBlogTagById(Long id) {
        if (blogTagRepository.existsById(id)) {
            BlogTag blogTag = blogTagRepository.findById(id).get();
            return blogTag;
        }

        return null;
    }

    public HttpStatus createBlogTag(BlogTag blogTag) {
        blogTagRepository.save(blogTag);
        return HttpStatus.CREATED;
    }

    public HttpStatus updateBlogTagById(Long id, BlogTag blogTag) {
        Optional<BlogTag> blogTagToFind = blogTagRepository.findById(id);

        if (blogTagToFind.isPresent()) {
            BlogTag blogTagToUpdate = blogTagToFind.get();
            blogTagToUpdate.setName(blogTag.getName());

            blogTagRepository.save(blogTagToUpdate);
            return HttpStatus.OK;
        } else {
            blogTagRepository.save(blogTag);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus deleteTagById(Long id) {
        if (blogTagRepository.existsById(id)) {
            blogTagRepository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
