package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.RentalDAO;
import edu.sjsu.cs157a.dao.MovieDAO;
import edu.sjsu.cs157a.model.Rental;
import edu.sjsu.cs157a.model.Movie;
import edu.sjsu.cs157a.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

@WebServlet("/rentals/*")
public class RentalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Constants for rental paths
	private static final String RENTALS = "/rentals";
	private static final String LIST = "/list";
	private static final String CONFIGURE = "/configure";
	private static final String RENT = "/rent";
	private static final String UPDATE = "/update";
	private static final String RETURN = "/return";
	
    private RentalDAO rentalDAO;
    private MovieDAO movieDAO;

    @Override
    public void init() {
        rentalDAO = new RentalDAO();
        movieDAO = new MovieDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = "/WEB-INF/views" + RENTALS + action + ".jsp";
        try {
        	switch(action) {
        	case LIST:
        		listRentals(request, response);
        		break;
        	case CONFIGURE:
        		getRental(request, response);
        		break;
        	}
        	// Forward the request to the corresponding JSP page
        	request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void listRentals(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
    	User user = (User) request.getSession().getAttribute("user");
    	
    	ArrayList<Rental> rentals = rentalDAO.getAllRentalsFromUser(user.getUserID());
        request.setAttribute("rentals", rentals);
    	
    	// Get movies and map with the same index
    	Movie[] movies = new Movie[rentals.size()];
    	for (int i = 0; i < movies.length; ++i) {
    		movies[i] = movieDAO.getMovie(rentals.get(i).getMovieID());
    	}
        request.setAttribute("movies", movies);
    }

    private void getRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        int rentalID = Integer.parseInt(request.getParameter("id"));
        Rental rental = rentalDAO.getRental(rentalID);
        Movie movie = movieDAO.getMovie(rental.getMovieID());
		request.setAttribute("rental", rental);
		request.setAttribute("movie", movie);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getPathInfo();
        try {
        	switch (action) {
        	case RENT:
        		addRental(request, response);
        		break;
        	case UPDATE:
        		updateRental(request, response);
        		break;
        	case RETURN:
        		returnRental(request, response);
        		break;
        	}
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void addRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException {
    	int movieID = Integer.parseInt(request.getParameter("movieID"));
    	User user = (User) request.getSession().getAttribute("user");
        rentalDAO.addRental(movieID, user.getUserID());
        // Send GET request to specified URL
        response.sendRedirect(request.getContextPath() + RENTALS + LIST);
    }

    private void updateRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ParseException {
    	int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        String newReturnDateStr = request.getParameter("newReturnDate");
        Rental rental = rentalDAO.getRental(rentalID);
        
        // Check valid return date
        if (newReturnDateStr != null && !newReturnDateStr.isEmpty()) {
            Date newReturnDate = Date.valueOf(newReturnDateStr);
            rental.setReturnDate(newReturnDate);
        }

        rentalDAO.updateRental(rental);
        response.sendRedirect(request.getContextPath() + RENTALS + LIST);
    }
    
    private void returnRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ParseException {
    	int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        Rental rental = rentalDAO.getRental(rentalID);
        rental.setReturned(true);
        rentalDAO.updateRental(rental);
        response.sendRedirect(request.getContextPath() + RENTALS + LIST);
    }
}
