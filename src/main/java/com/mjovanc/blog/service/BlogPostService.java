package com.mjovanc.blog.service;

import com.mjovanc.blog.model.BlogPost;
import com.mjovanc.blog.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogPostService {

    private final BlogPostRepository blogPostRepository;

    @Autowired
    public BlogPostService(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getBlogPostById(Long id) {
        if (blogPostRepository.existsById(id)) {
            BlogPost blogPost = blogPostRepository.findById(id).get();
            return blogPost;
        }

        return null;
    }

    public HttpStatus createBlogPost(BlogPost blogPost) {
        blogPostRepository.save(blogPost);
        return HttpStatus.CREATED;
    }

    public HttpStatus updateBlogPostById(Long id, BlogPost blogPost) {
        Optional<BlogPost> blogPostToFind = blogPostRepository.findById(id);

        if (blogPostToFind.isPresent()) {
            BlogPost blogPostToUpdate = blogPostToFind.get();
            blogPostToUpdate.setTitle(blogPost.getTitle());
            blogPostToUpdate.setText(blogPost.getText());
            blogPostToUpdate.setUpdated(blogPost.getUpdated());
            blogPostToUpdate.setCreated(blogPost.getCreated());

            blogPostRepository.save(blogPostToUpdate);
            return HttpStatus.OK;
        } else {
            blogPostRepository.save(blogPost);
            return HttpStatus.CREATED;
        }
    }

    public HttpStatus deletePostById(Long id) {
        if (blogPostRepository.existsById(id)) {
            blogPostRepository.deleteById(id);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }
}
