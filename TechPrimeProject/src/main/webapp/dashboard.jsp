<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
.card {
	margin-bottom: 20px;
	color: rgb(0, 128, 192);
}
.header-image {
    text-align: center;
    margin-bottom: 10px;
}

.logo {
    position: absolute;
    top: 30px; /* Adjust as needed */
    left: 700px; /* Adjust as needed */
}

.logo img {
    max-width: 100px; /* Adjust width as needed */
    height: auto;
}
</style>
</head>
<body>
  <div class="header-image">
        <img src="images/Header-bg.svg" alt="Project Manager" width="1500">
        <div class="logo">
            <img src="images/Logo.svg" alt="Logo">
        </div>
        <h1>Dashboard<h1>
    </div>
 <jsp:include page="navbar.html" />
	<div class="container">
		<div class="row">
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Total Projects</h5>
						<p class="card-text" id="totalProjects">Loading...</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Closed</h5>
						<p class="card-text" id="closedProjects">Loading...</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Running</h5>
						<p class="card-text" id="runningProjects">Loading...</p>
					</div>
				</div>
			</div>
		</div>
		<div class="row mt-4">
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Closure Delay</h5>
						<p class="card-text" id="closureDelay">Loading...</p>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">Cancelled</h5>
						<p class="card-text" id="cancelledProjects">Loading...</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script>
    $(document).ready(function() {
        $.post("DashboardDataServlet", function(data) {
            $("#totalProjects").text(data.totalProjects);
            $("#closedProjects").text(data.closedProjects);
            $("#runningProjects").text(data.runningProjects);
            $("#closureDelay").text(data.closureDelay);
            $("#cancelledProjects").text(data.cancelledProjects);
        }, "json");
    });
</script>

</body>
</html>
