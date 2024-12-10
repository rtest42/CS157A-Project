<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Profile</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
</head>
<body class="d-flex justify-content-center align-items-center" style="height: 100vh;">

	<!-- Main content container -->
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<!-- Profile Box -->
				<div class="card p-4 shadow-lg">
					<h1 class="text-center mb-4">User Profile</h1>
					
					<div class="mb-3">
						<p><strong>Name:</strong> ${user.firstName} ${user.lastName}</p>
					</div>
					<div class="mb-3">
						<p><strong>Email:</strong> ${user.email}</p>
					</div>
					<div class="mb-3">
						<p><strong>Phone:</strong> ${user.phone}</p>
					</div>
					<div class="mb-3">
						<p><strong>Password:</strong> <input type="password" id="password" value="${user.password}" class="border-0" readonly></p>
					</div>
					<div class="mb-3">
						<p><strong>Join Date:</strong> ${user.joinDate}</p>
					</div>

					<!-- Action Buttons -->
					<div class="d-flex justify-content-between mt-4">
						<form action="${pageContext.request.contextPath}/movies/dashboard" method="get">
							<button class="btn btn-secondary" name="back" value="back">Back</button>
						</form>
						<form action="${pageContext.request.contextPath}/rentals/list" method="get">
							<button class="btn btn-primary" name="rentals" value="rentalsk">View Your Rentals</button>
						</form>
						<form action="${pageContext.request.contextPath}/reviews/list" method="get">
							<button class="btn btn-info" name="reviews" value="reviews">View Your Reviews</button>
						</form>
					</div>
					
					<!-- Edit and Delete Profile Button -->
					<div class="d-flex justify-content-between mt-4">
						<form action="${pageContext.request.contextPath}/users/edit" method="get">
							<button class="btn btn-warning" name="editProfile" value="editProfile">Edit Profile</button>
						</form>
						<form action="${pageContext.request.contextPath}/users/delete" method="post" onsubmit="return confirm('Are you sure you want to delete your account? This action cannot be undone.');">
							<button class="btn btn-danger" name="deleteAccount" value="deleteAccount">Delete Account</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
