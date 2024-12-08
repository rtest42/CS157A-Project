package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.RentalDAO;
import edu.sjsu.cs157a.model.Rental;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet("/rentals/*")
public class RentalController extends HttpServlet {
	private static final String LIST = "/list";
	private static final String CONFIGURE = "/configure";
	
    private RentalDAO rentalDAO;

    @Override
    public void init() {
        rentalDAO = new RentalDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        String url = "/WEB-INF/views/rentals" + action + ".jsp";
        try {
        	switch(action) {
        	case LIST:
        		listRentals(request, response);
        		break;
        	case CONFIGURE:
        		getRental(request, response);
        		break;
        	}
        	request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void listRentals(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
    	Integer userID = (Integer) request.getSession().getAttribute("userID");
    	ArrayList<Rental> rentals = rentalDAO.getAllRentalsFromUser(userID);
        request.setAttribute("rentals", rentals);
    }

    private void getRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
        int rentalID = Integer.parseInt(request.getParameter("id"));
        Rental rental = rentalDAO.getRental(rentalID);
        // User user = (User) request.getSession().getAttribute("user");
		
		request.setAttribute("rental", rental);
        //if (rental != null) {
        //    request.setAttribute("rental", rental);
        //    request.getRequestDispatcher("/rental-details.jsp").forward(request, response);
        //} else {
        //    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Rental not found");
        //}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String action = request.getPathInfo();
        try {
        	switch (action) {
        	case "/rent":
        		addRental(request, response);
        		break;
        	case "/update":
        		updateRental(request, response);
        		break;
        	case "/return":
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
    	int userID = (Integer) request.getSession().getAttribute("userID");
        rentalDAO.addRental(movieID, userID);
        response.sendRedirect(request.getContextPath() + "/rentals/list");
    }

    private void updateRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ParseException {
    	int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        String newReturnDateStr = request.getParameter("newReturnDate");
        Rental rental = rentalDAO.getRental(rentalID);
        if (newReturnDateStr != null && !newReturnDateStr.isEmpty()) {
            Date newReturnDate = Date.valueOf(newReturnDateStr);
            rental.setReturnDate(newReturnDate);
        }

        rentalDAO.updateRental(rental);
        response.sendRedirect(request.getContextPath() + "/rentals/list");
    }
    
    private void returnRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ClassNotFoundException, ParseException {
    	int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        Rental rental = rentalDAO.getRental(rentalID);
        rental.setReturned(true);
        rentalDAO.updateRental(rental);
        response.sendRedirect(request.getContextPath() + "/rentals/list");
    }
}
