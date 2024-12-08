<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<title>Rental Details</title>
</head>
<body>
	<h1>Rental Details</h1>
	<p>
		<strong>Rental</strong> ${rental.rentalID}
	</p>
	<p>
		<strong>Movie</strong> ${rental.movieID}
	</p>
	<p>
		<strong>Start Date</strong> ${rental.startDate}
	</p>
	<p>
		<strong>Return Date</strong> ${rental.returnDate}
	</p>
	<p>
		<strong>Return</strong> ${rental.returned}
	</p>
	<form action="${pageContext.request.contextPath}/rentals/list"
		method="get">
		<button name="back" value="back">Back</button>
	</form>
	<form action="${pageContext.request.contextPath}/rentals/update"
		method="post">
		<input type="hidden" name="rentalID" value="${rental.rentalID}" />
        <label for="newReturnDate">Select New Return Date:</label>
        <input type="date" id="newReturnDate" name="newReturnDate" 
               value="${rental.returnDate}" 
               min="${rental.returnDate}" required />
        <button type="submit"
            <c:if test="${rental.returned}">
                disabled="disabled"
            </c:if>>
            Update Return Date
        </button>
	</form>
	<form action="${pageContext.request.contextPath}/rentals/return"
		method="post">
		<input type="hidden" name="rentalID" value="${rental.rentalID}" />
		<button type="submit"
			<c:if test="${rental.returned}">
				disabled="disabled" 
			</c:if>>
			Return
		</button>
	</form>
</body>
</html>