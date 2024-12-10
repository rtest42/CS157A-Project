<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Login</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="d-flex justify-content-center align-items-center" style="height: 100vh;">
	<!-- Main content container -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<!-- Login Box -->
				<div class="card p-4 shadow-lg">
					<h1 class="text-center mb-4">Sign In</h1>
					<!-- Display error message if login failed -->
                    <c:if test="${param.status == 'error'}">
                        <div class="alert alert-danger" role="alert">
                        	Invalid email or password.
                        </div>
                    </c:if>
                    <c:if test="${param.status == 'registered'}">
                        <div class="alert alert-success" role="alert">
                        	Successfully registered an account!
                        </div>
                    </c:if>
                    <c:if test="${param.status == 'logout'}">
                        <div class="alert alert-success" role="alert">
                        	Logout successful.
                        </div>
                    </c:if>
                    <c:if test="${param.status == 'deleted'}">
                        <div class="alert alert-danger" role="alert">
                        	Deleted user successfully!
                        </div>
                    </c:if>
					<form action="${pageContext.request.contextPath}/users/login" method="post">
						<div class="mb-3">
							<label for="email" class="form-label">Email:</label>
							<input type="email" name="email" id="email" class="form-control" required>
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">Password:</label>
							<input type="password" name="password" id="password" class="form-control" required>
						</div>
						<button type="submit" class="btn btn-primary w-100">Login</button>
					</form>
					<div class="text-center mt-3">
						<form action="${pageContext.request.contextPath}/users/register" method="get">
							<button type="submit" class="btn btn-secondary w-100">Register</button>
						</form>
					</div>
					<div class="text-center mt-3">
						<form action="${pageContext.request.contextPath}/movies/dashboard" method="get">
							<button type="submit" class="btn btn-warning w-100">View movies as guest</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
