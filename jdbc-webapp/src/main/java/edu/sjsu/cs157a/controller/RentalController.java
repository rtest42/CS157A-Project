package edu.sjsu.cs157a.controller;

import edu.sjsu.cs157a.dao.RentalDAO;
import edu.sjsu.cs157a.model.Rental;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/rentals/*")
public class RentalController extends HttpServlet {

}
/*
package com.example.moviedb.controller;

import com.example.moviedb.dao.RentalDAO;
import com.example.moviedb.model.Rental;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/rentals")
public class RentalController extends HttpServlet {
    private RentalDAO rentalDAO;

    @Override
    public void init() {
        rentalDAO = new RentalDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("list".equals(action)) {
                listRentals(request, response);
            } else if ("get".equals(action)) {
                getRental(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void listRentals(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Rental> rentals = rentalDAO.getAllRentals();
        request.setAttribute("rentals", rentals);
        request.getRequestDispatcher("/rental-list.jsp").forward(request, response);
    }

    private void getRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        Rental rental = rentalDAO.getRentalByID(rentalID);
        if (rental != null) {
            request.setAttribute("rental", rental);
            request.getRequestDispatcher("/rental-details.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Rental not found");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            addRental(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void addRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int movieID = Integer.parseInt(request.getParameter("movieID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String startDate = request.getParameter("startDate");
        String returnDate = request.getParameter("returnDate");
        String status = request.getParameter("status");

        Rental newRental = new Rental();
        newRental.setMovieID(movieID);
        newRental.setUserID(userID);
        newRental.setStartDate(startDate);
        newRental.setReturnDate(returnDate);
        newRental.setStatus(status);

        rentalDAO.addRental(newRental);
        response.sendRedirect("rentals?action=list");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            updateRental(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void updateRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        int movieID = Integer.parseInt(request.getParameter("movieID"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String startDate = request.getParameter("startDate");
        String returnDate = request.getParameter("returnDate");
        String status = request.getParameter("status");

        Rental rental = new Rental();
        rental.setRentalID(rentalID);
        rental.setMovieID(movieID);
        rental.setUserID(userID);
        rental.setStartDate(startDate);
        rental.setReturnDate(returnDate);
        rental.setStatus(status);

        rentalDAO.updateRental(rental);
        response.sendRedirect("rentals?action=list");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            deleteRental(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void deleteRental(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int rentalID = Integer.parseInt(request.getParameter("rentalID"));
        rentalDAO.deleteRental(rentalID);
        response.sendRedirect("rentals?action=list");
    }
}*/
