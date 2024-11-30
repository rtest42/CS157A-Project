package edu.sjsu.cs157a.model;

public class Review {
	private int reviewId;
	private int userId;
	private int movieId;
	private double rating;
	private String comment;
	
	public Review() {
		
	}
	
	public Review(int id, int userId, int movieId, double rating, String comment) {
		this.setReviewID(id);
		this.setUserID(userId);
		this.setMovieID(movieId);
		this.setRating(rating);
		this.setComment(comment);
	}

	public int getReviewID() {
		return reviewId;
	}

	public void setReviewID(int id) {
		this.reviewId = id;
	}

	public int getUserID() {
		return userId;
	}

	public void setUserID(int userId) {
		this.userId = userId;
	}

	public int getMovieID() {
		return movieId;
	}

	public void setMovieID(int movieId) {
		this.movieId = movieId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
