package edu.sjsu.cs157a.model;

/**
 * Represents a Movie in the movie database system.
 * A Movie object includes details such as title, director, genre, release year, rating, and description.
 */
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
	
    /**
     * Parameterized constructor for creating a Movie object with specific details.
     *
     * @param id          The unique movie ID
     * @param title       The title of the movie
     * @param director    The director of the movie
     * @param genre       The genre of the movie
     * @param releaseYear The release year of the movie
     * @param rating      The average rating of the movie
     * @param description A brief description of the movie
     */
	public Movie(int id, String title, String director, String genre, int releaseYear, double rating, String description) {
		this.setMovieID(id);
		this.setTitle(title);
		this.setDirector(director);
		this.setGenre(genre);
		this.setReleaseYear(releaseYear);
		this.setRating(rating);
		this.setDescription(description);
	}

	// Getter and setter methods for attributes
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
