package com.mjovanc.blog.model;

import com.fasterxml.jackson.annotation.JsonGetter;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BlogCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToMany(mappedBy="blogCategories")
    List<BlogPost> blogPosts;

    @JsonGetter("blogPosts")
    public List<String> getAllBlogPosts() {
        if (blogPosts != null) {
            return blogPosts.stream()
                    .map(bp -> {
                        return "/v1/posts/" + bp.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public BlogCategory() {

    }

    public BlogCategory(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
