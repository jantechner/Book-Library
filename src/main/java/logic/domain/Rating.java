package logic.domain;

public class Rating {

    private Double average;
    private Double ratingsSum;
    private int ratingsNumber;

    public Rating(Double rating) {
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
