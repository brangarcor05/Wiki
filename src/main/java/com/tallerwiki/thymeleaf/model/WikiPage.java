package com.tallerwiki.thymeleaf.model;

import java.util.ArrayList;
import java.util.List;

public class WikiPage {

    private String id;
    private String categoryId;
    private String title;
    private String summary;
    private List<Section> sections = new ArrayList<>();

    public WikiPage() {
    }

    public WikiPage(String id, String categoryId, String title, String summary, List<Section> sections) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.summary = summary;
        this.sections = sections;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
