<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Register</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="d-flex justify-content-center align-items-center" style="height: 100vh;">
	<!-- Main content container -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<!-- Register Box -->
				<div class="card p-4 shadow-lg">
					<h1 class="text-center mb-4">Register</h1>
					<form action="${pageContext.request.contextPath}/users/register" method="post">
						<div class="mb-3">
							<label for="firstName" class="form-label">First Name:</label>
							<input type="text" name="firstName" id="firstName" class="form-control" required>
						</div>
						<div class="mb-3">
							<label for="lastName" class="form-label">Last Name:</label>
							<input type="text" name="lastName" id="lastName" class="form-control" required>
						</div>
						<div class="mb-3">
							<label for="email" class="form-label">Email:</label>
							<input type="email" name="email" id="email" class="form-control" required>
						</div>
						<div class="mb-3">
							<label for="phone" class="form-label">Phone:</label>
							<input type="tel" name="phone" id="phone" class="form-control" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}">
						</div>
						<div class="mb-3">
							<label for="password" class="form-label">Password:</label>
							<input type="password" name="password" id="password" class="form-control" minlength="8" required>
						</div>
						<button type="submit" class="btn btn-primary w-100">Register</button>
					</form>
					<div class="text-center mt-3">
						<form action="${pageContext.request.contextPath}/users/login" method="get">
							<button type="submit" class="btn btn-secondary w-100">Login</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
