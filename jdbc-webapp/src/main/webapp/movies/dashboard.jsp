<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.sjsu.cs157a.dao.MovieDAO" %>
<%@ page import="edu.sjsu.cs157a.model.Movie" %>
<%
	MovieDAO movieDAO = new MovieDAO();
	ArrayList<Movie> movies = movieDAO.getAllMovies();
%>
<!DOCTYPE html>
<html>
	<head>
		<title>Movies</title>
	</head>
	<body>
		<h1>Movies</h1>
		<table border="1">
			<tr>
				<th>Title</th>
				<th>Director</th>
				<th>Genre</th>
				<th>Release Year</th>
				<th>Rating</th>
				<th>Details</th>
			</tr>
			<%
				for (Movie movie : movies) {
			%>
			<tr>
				<td><%= movie.getTitle() %></td>
				<td><%= movie.getDirector() %></td>
				<td><%= movie.getGenre() %></td>
				<td><%= movie.getReleaseYear() %></td>
				<td><%= movie.getRating() %></td>
				<td><a href="details.jsp?movieID=<%= movie.getMovieID() %>"></a></td>
			</tr>
			<%
				}
			%>
		</table>
		
	</body>
</html>