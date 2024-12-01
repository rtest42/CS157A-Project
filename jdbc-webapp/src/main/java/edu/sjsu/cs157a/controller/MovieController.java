package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.MovieDAO;
import edu.sjsu.cs157a.model.Movie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/movies/*")
public class MovieController extends HttpServlet {
	private MovieDAO movieDAO;

	@Override
	public void init() {
		movieDAO = new MovieDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/dashboard":
				listMovies(request, response);
				break;
			case "/details":
				getMovie(request, response);
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listMovies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		ArrayList<Movie> movies = movieDAO.getAllMovies();
		request.setAttribute("movies", movies);
		request.getRequestDispatcher("/movies/dashboard.jsp").forward(request, response);
	}

	private void getMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		Movie movie = movieDAO.getMovie(movieID);
		if (movie != null) {
			request.setAttribute("movie", movie);
			request.getRequestDispatcher("/movie/details.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Movie not found");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			addMovie(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addMovie(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
		String title = request.getParameter("title");
		String director = request.getParameter("director");
		String genre = request.getParameter("genre");
		int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
		double rating = Double.parseDouble(request.getParameter("rating"));
		String description = request.getParameter("description");

		Movie newMovie = new Movie();
		newMovie.setTitle(title);
		newMovie.setDirector(director);
		newMovie.setGenre(genre);
		newMovie.setReleaseYear(releaseYear);
		newMovie.setRating(rating);
		newMovie.setDescription(description);

		movieDAO.addMovie(newMovie);
		response.sendRedirect("/movies/dashboard.jsp");
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			updateMovie(request, response);
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void updateMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		/*
		 * int movieID = Integer.parseInt(request.getParameter("movieID")); String title
		 * = request.getParameter("title"); String director =
		 * request.getParameter("director"); String genre =
		 * request.getParameter("genre"); int releaseYear =
		 * Integer.parseInt(request.getParameter("releaseYear")); double rating =
		 * Double.parseDouble(request.getParameter("rating")); String description =
		 * request.getParameter("description");
		 * 
		 * Movie movie = new Movie(); movie.setMovieID(movieID); movie.setTitle(title);
		 * movie.setDirector(director); movie.setGenre(genre);
		 * movie.setReleaseYear(releaseYear); movie.setRating(rating);
		 * movie.setDescription(description);
		 * 
		 * movieDAO.updateMovie(movie); response.sendRedirect("movies?action=list");
		 */
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			deleteMovie(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			throw new ServletException(e);
		}
	}

	private void deleteMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ClassNotFoundException {
		int movieID = Integer.parseInt(request.getParameter("movieID"));
		movieDAO.deleteMovie(movieID);
		response.sendRedirect("movies?action=list");
	}
}
