package edu.sjsu.cs157a.model;

public class Movie {
	private int movieId;
	private String title;
	private String director;
	private String genre;
	private int releaseYear;
	private double rating;
	private String description;
	
	public Movie() {
		
	}
	
	public Movie(int id, String title, String director, String genre, int releaseYear, double rating, String description) {
		this.setMovieID(id);
		this.setTitle(title);
		this.setDirector(director);
		this.setGenre(genre);
		this.setReleaseYear(releaseYear);
		this.setRating(rating);
		this.setDescription(description);
	}

	public int getMovieID() {
		return movieId;
	}

	public void setMovieID(int id) {
		this.movieId = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
