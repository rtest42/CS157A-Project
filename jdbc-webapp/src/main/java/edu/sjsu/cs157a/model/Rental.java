package edu.sjsu.cs157a.model;

import java.sql.Date;

public class Rental {
	private int rentalId;
	private int movieId;
	private int userId;
	private Date startDate;
	private Date returnDate;
	private boolean returned;
	
	public Rental() {
		this.setReturned(false);
	}
	
	public Rental(int id, int movieId, int userId, Date startDate, Date returnDate) {
		this.setRentalID(id);
		this.setMovieID(movieId);
		this.setUserID(userId);
		this.setStartDate(startDate);
		this.setReturnDate(returnDate);
		this.setReturned(false);
	}

	public int getRentalID() {
		return rentalId;
	}

	public void setRentalID(int id) {
		this.rentalId = id;
	}

	public int getMovieID() {
		return movieId;
	}

	public void setMovieID(int movieId) {
		this.movieId = movieId;
	}

	public int getUserID() {
		return userId;
	}

	public void setUserID(int userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}
	
}
