<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rentals</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body>
    <!-- Container to center the content -->
    <div class="container py-5">
        <h1 class="text-center mb-4">Your Rentals</h1>
        
        <!-- Greeting and Profile/Logout buttons -->
        <div class="row mb-3">
            <div class="col-md-6">
                <h4>Hello, ${empty user ? 'guest' : user.firstName}!</h4>
            </div>
            <div class="col-md-6 text-right">
            	<c:choose>
	            	<c:when test="${empty user}">
	            		<form action="${pageContext.request.contextPath}/users/login" method="get" class="d-inline">
		                    <button type="submit" class="btn">Login</button>
		                </form>
	            	</c:when>
	            	<c:otherwise>
		                <form action="${pageContext.request.contextPath}/users/logout" method="post" class="d-inline">
		                    <button type="submit" class="btn btn-danger">Logout</button>
		                </form>
	            	</c:otherwise>
	            </c:choose>
                <form action="${pageContext.request.contextPath}/users/profile" method="get" class="d-inline">
                    <button type="submit" class="btn btn-primary" <c:if test="${empty user}">disabled</c:if>>Profile</button>
                </form>
                <form action="${pageContext.request.contextPath}/movies/dashboard" method="get" class="d-inline">
                    <button type="submit" class="btn">Watch more movies</button>
                </form>
            </div>
        </div>
        
        <!-- Movies Table -->
        <div class="table-responsive">
            <table class="table table-bordered table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>Movie</th>
                        <th>Start Date</th>
                        <th>Return Date</th>
                        <th>Return Status</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <!-- Iterate over the movies list -->
                    <c:forEach var="rental" items="${rentals}" varStatus="status">
                        <tr>
                            <td>${movies[status.index].title}</td>
                            <td>${rental.startDate}</td>
                            <td>${rental.returnDate}</td>
                            <td>${rental.returned ? 'Returned' : 'Rented'}</td>
                            <td><a href="configure?id=${rental.rentalID}" class="btn btn-info btn-sm">Configure</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
