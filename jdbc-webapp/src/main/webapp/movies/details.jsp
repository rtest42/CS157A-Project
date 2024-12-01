<%@ page import="edu.sjsu.cs157a.model.Movie" %>
<%@ page import="edu.sjsu.cs157a.dao.MovieDAO" %>
<%
    String movieID = request.getParameter("movieID");
    MovieDAO movieDAO = new MovieDAO();
    Movie movie = movieDAO.getMovie(Integer.parseInt(movieID));
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= movie.getTitle() %></title>
</head>
<body>
    <h1><%= movie.getTitle() %></h1>
    <p><strong>Director:</strong> <%= movie.getDirector() %></p>
    <p><strong>Genre:</strong> <%= movie.getGenre() %></p>
    <p><strong>Release Year:</strong> <%= movie.getReleaseYear() %></p>
    <p><strong>Rating:</strong> <%= movie.getRating() %></p>
    <p><strong>Description:</strong> <%= movie.getDescription() %></p>
</body>
</html>