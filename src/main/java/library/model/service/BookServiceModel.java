package library.model.service;

import library.model.entity.Author;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceModel extends BaseServiceModel {

    private String title;
    private String isbn;

    private AuthorServiceModel author;
    private String yearOfIssue;
    private String publishingHouse;
    private String availabilityStatus;
    private int numberOfPages;
    private String coverType;
    private String content;
    private String description;
    private String language;
    private double rating;
    private BigDecimal price;
    private int quantity;
    private BigDecimal discount;

    private List<CategoryServiceModel> category;
    private SizeServiceModel size;
    private List<String> photos;

    public BookServiceModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public AuthorServiceModel getAuthor() {
        return author;
    }

    public void setAuthor(AuthorServiceModel author) {
        this.author = author;
    }

    public String getYearOfIssue() {
        return yearOfIssue;
    }

    public void setYearOfIssue(String yearOfIssue) {
        this.yearOfIssue = yearOfIssue;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCoverType() {
        return coverType;
    }

    public void setCoverType(String coverType) {
        this.coverType = coverType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public List<CategoryServiceModel> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryServiceModel> category) {
        this.category = category;
    }

    public SizeServiceModel getSize() {
        return size;
    }

    public void setSize(SizeServiceModel size) {
        this.size = size;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        this.photos = photos;
    }
}
