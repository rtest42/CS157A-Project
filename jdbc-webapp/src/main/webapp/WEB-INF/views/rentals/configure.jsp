<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Rental Details</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="bg-light">

    <div class="container py-5 ard p-4 shadow-lg">
        <h1 class="text-center mb-4">Rental Details</h1>
        
        <!-- Rental Details Section -->
        <div class="row mb-4">
            <div class="col-12">
                <p><strong>Movie:</strong> ${movie.title}</p>
                <p><strong>Start Date:</strong> ${rental.startDate}</p>
                <p><strong>Return Date:</strong> ${rental.returnDate}</p>
                <p><strong>Return Status:</strong> ${rental.returned ? 'Returned' : 'Not Returned'}</p>
            </div>
        </div>

        <!-- Navigation Buttons -->
<div class="row mb-4 align-items-center">
    <div class="col-12 d-flex justify-content-start">
        <!-- Update Return Date Form -->
        <form action="${pageContext.request.contextPath}/rentals/update" method="post" class="d-flex align-items-center mr-3">
            <input type="hidden" name="rentalID" value="${rental.rentalID}" />
            <label for="newReturnDate" class="mr-2 mb-0">Select New Return Date:</label>
            <input type="date" id="newReturnDate" name="newReturnDate" 
                   value="${rental.returnDate}" 
                   min="${rental.returnDate}" 
                   required 
                   class="form-control form-control-sm w-auto mr-2" />
            <button type="submit" class="btn btn-warning" 
                    <c:if test="${rental.returned}">
                        disabled="disabled"
                    </c:if>>
                Update Return Date
            </button>
        </form>

        <!-- Return Button -->
        <form action="${pageContext.request.contextPath}/rentals/return" method="post">
            <input type="hidden" name="rentalID" value="${rental.rentalID}" />
            <button type="submit" class="btn btn-success" 
                    <c:if test="${rental.returned}">
                        disabled="disabled"
                    </c:if>>
                Return
            </button>
        </form>
    </div>
</div>
        <div class="row mb-4 col-12">
        <!-- Back Button -->
        <form action="${pageContext.request.contextPath}/rentals/list" method="get" class="mr-3">
            <button type="submit" class="btn btn-secondary">Back</button>
        </form>
        </div>

    </div>

</body>
</html>
