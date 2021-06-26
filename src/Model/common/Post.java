package Model.common;

import java.io.Serializable;

public class Post implements Serializable, Comparable {
    public Post() {
    }

    private Account writer;
    private String title;
    private String description;
    private Long createdTime;
    private String imagePath = "";

    public Post(Account writer, String title, String description) {
        this.writer = writer;
        this.title=title;
        this.description=description;
        createdTime = Time.getMilli();
    }

    public Account getWriter() {
        return writer;
    }

    public void setWriter(Account writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Long getCreatedTime() {
        return createdTime;
    }
}
