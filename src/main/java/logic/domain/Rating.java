package logic.domain;

import com.google.gson.annotations.Expose;

public class Rating {

    @Expose
    private String author;
    @Expose
    private Double average;
    private Double ratingsSum;
    private int ratingsNumber;

    public Rating(Double rating, String author) {
        this.author = author;
        this.average = rating;
        this.ratingsSum = rating;
        this.ratingsNumber = 1;
    }

    public void updateRatings(Double rating) {
        this.ratingsSum += rating;
        this.ratingsNumber += 1;
        this.average = this.ratingsSum/this.ratingsNumber;
    }

    public Double getAverage() {
        return average;
    }
}
