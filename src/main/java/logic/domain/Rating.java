package logic.domain;

public class Rating {

    private String author;
    private Double averageRating;
    private Double ratingsSum;
    private Integer ratingsNumber;

    public Rating(Double rating, String author) {
        this.author = author;
        this.averageRating = rating;
        this.ratingsSum = rating;
        this.ratingsNumber = 1;
    }

    public void updateRatings(Double rating) {
        this.ratingsSum += rating;
        this.ratingsNumber += 1;
        this.averageRating = this.ratingsSum/this.ratingsNumber;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public void eraseUnnecessaryValues() {
        ratingsNumber = null;
        ratingsSum = null;
    }
}
