package edu.sjsu.cs157a.model;

/**
 * Represents a Review in the movie database system.
 * A Review includes a rating and a comment left by a user for a specific movie.
 */
public class Review {
	private int reviewId;
	private int userId;
	private int movieId;
	private double rating;
	private String comment;
	
	public Review() {
		
	}
	
	/**
     * Parameterized constructor for creating a Review object with specific details.
     *
     * @param id      The unique review ID
     * @param userId  The ID of the user who wrote the review
     * @param movieId The ID of the movie being reviewed
     * @param rating  The rating given by the user
     * @param comment The comment left by the user
     */
	public Review(int id, int userId, int movieId, double rating, String comment) {
		this.setReviewID(id);
		this.setUserID(userId);
		this.setMovieID(movieId);
		this.setRating(rating);
		this.setComment(comment);
	}
	
	// Getter and setter methods for attributes
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
