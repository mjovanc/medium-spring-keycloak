package com.mjovanc.blog.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String created;
    private String updated;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blog_post_category",
            joinColumns = {@JoinColumn(name = "blog_post_id")},
            inverseJoinColumns = {@JoinColumn(name = "blog_post_category_id")}
    )
    @JsonProperty("blog_categories")
    public List<BlogCategory> blogCategories;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "blog_post_tag",
            joinColumns = {@JoinColumn(name = "blog_post_id")},
            inverseJoinColumns = {@JoinColumn(name = "blog_post_tag_id")}
    )
    @JsonProperty("blog_tags")
    public List<BlogTag> blogTags;

    @JsonGetter("blog_categories")
    public List<String> getAllWalletPlatforms() {
        if(blogCategories != null) {
            return blogCategories.stream()
                    .map(bc -> {
                        return "/v1/categories/" + bc.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    @JsonGetter("blog_tags")
    public List<String> getAllWalletStorages() {
        if(blogTags != null) {
            return blogTags.stream()
                    .map(bt -> {
                        return "/v1/tags/" + bt.getId();
                    }).collect(Collectors.toList());
        }
        return null;
    }

    public BlogPost() {

    }

    public BlogPost(String title, String text, String created, String updated) {
        this.title = title;
        this.text = text;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
