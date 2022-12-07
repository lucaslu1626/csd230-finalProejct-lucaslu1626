// Lu Lu
package com.example.whowroteit;


public class GoogleBookModel {
    private String title;
    private String subtitle;
    private String authors;
    private String description;
    private String publisher;
    private String publishedDate;
    private String thumbnail;

    public GoogleBookModel(String title, String subtitle, String authors, String description, String publisher, String publishedDate, String thumbnail)
    {
        this.title = title;
        this.subtitle = subtitle;
        this.authors = authors;
        this.description = description;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.thumbnail = thumbnail;
    }
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getSubtitle() {return subtitle;}
    public void setSubtitle(String subtitle) {this.subtitle = subtitle;}
    public String getAuthors() {return authors;}
    public void setAuthors(String authors) {this.authors = authors;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public String getPublisher() {return publisher;}
    public void setPublisher(String publisher) {this.publisher = publisher;}
    public String getPublishedDate() {return publishedDate;}
    public void setPublishedDate(String publishedDate) {this.publishedDate = publishedDate;}
    public String getThumbnail() {return thumbnail;}
    public void setThumbnail(String thumbnail) {this.thumbnail = thumbnail;}
}
