package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.MovieDAO;
import edu.sjsu.cs157a.model.Movie;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/movies/*")
public class MovieController extends HttpServlet {
	private static final String DASHBOARD = "/dashboard";
	private static final String DETAILS = "/details";
	
	private MovieDAO movieDAO;

	@Override
	public void init() {
		movieDAO = new MovieDAO();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		String url = "/WEB-INF/views/movies" + action + ".jsp";
		try {
			switch (action) {
			case DASHBOARD:
				listMovies(request, response);
				break;
			case DETAILS:
				getMovie(request, response);
				break;
			}
			request.getRequestDispatcher(url).forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void listMovies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		ArrayList<Movie> movies = movieDAO.getAllMovies();
		request.setAttribute("movies", movies);
	}
	
	private void getMovie(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException, ClassNotFoundException {
		int movieID = Integer.parseInt(request.getParameter("id"));
		Integer userID = (Integer) request.getSession().getAttribute("userID");
		
		Movie movie = movieDAO.getMovie(movieID);
		request.setAttribute("movie", movie);
		
		boolean canRent = movieDAO.canRent(userID, movieID);
		boolean canReview = movieDAO.canReview(userID, movieID);
		request.setAttribute("canRent", canRent);
		request.setAttribute("canReview", canReview);
	}
}
