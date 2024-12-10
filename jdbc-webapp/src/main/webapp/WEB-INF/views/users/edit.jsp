<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Edit Profile</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="d-flex justify-content-center align-items-center" style="height: 100vh;">

	<!-- Main content container -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<!-- Edit Profile Box -->
				<div class="card p-4 shadow-lg">
					<h1 class="text-center mb-4">Edit Profile</h1>
					
					<form action="${pageContext.request.contextPath}/users/update" method="post">
						
						<div class="mb-3">
							<label for="firstName">First Name:</label>
							<input type="text" name="firstName" id="firstName" class="form-control" value="${user.firstName}" required>
						</div>
						
						<div class="mb-3">
							<label for="lastName">Last Name:</label>
							<input type="text" name="lastName" id="lastName" class="form-control" value="${user.lastName}" required>
						</div>

						<div class="mb-3">
							<label for="email">Email:</label>
							<input type="email" name="email" id="email" class="form-control" value="${user.email}" required>
						</div>

						<div class="mb-3">
							<label for="phone">Phone:</label>
							<input type="tel" name="phone" id="phone" class="form-control" value="${user.phone}" required>
						</div>

						<div class="mb-3">
							<label for="password">Password:</label>
							<input type="password" name="password" id="password" class="form-control" minlength="8" value="${user.password}" required>
						</div>

						<button type="submit" class="btn btn-success btn-block">Update Profile</button>
					</form>

					<!-- Back Button -->
					<div class="mt-3 text-center">
						<form action="${pageContext.request.contextPath}/users/profile" method="get">
							<button class="btn btn-secondary" type="submit">Cancel</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
