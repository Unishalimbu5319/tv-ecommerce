<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TV Store | ${title}</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css"
	integrity="sha384-4LISF5TTJX/fLmGSxO53rV4miRxdg84mZsxmO8Rx5jGtp/LbrixFETvWa5a6sESd"
	crossorigin="anonymous">
</head>
<body>
	<nav class="navbar navbar-expand-lg bg-body-tertiary">
		<div class="container">
			<a class="navbar-brand" href="/tv/home">TV Store</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="/tv/home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="/tv/profile">User
							Profile</a></li>
					<li class="nav-item"><a class="nav-link" href="/tv/about">About
							Us</a></li>
				</ul>

				<div class="d-flex">
					<form class="d-flex">
						<a id="cart" href="/tv/cart" class="btn btn-outline-dark" type="submit">
							<i class="bi-cart-fill me-1"></i> Cart
						</a>
					</form>
					<%
					Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
					if (isLoggedIn != null && isLoggedIn) {
					%>
					<a href="/tv/logout" class="mx-2 d-flex align-items-center">Logout</a>
					<%
					} else {
					%>
					<a href="/tv/login" class="mx-2 d-flex align-items-center">Login</a>
					<%
					}
					%>
				</div>
			</div>
		</div>
	</nav>
	<script>
		let cartBtn = document.querySelector("#cart");
		cartBtn.addEventListener("click", function(e){
			if (!<%= isLoggedIn %>){
				if (!window.confirm("You are not logged in, click yes to go to the login..")){					
					e.preventDefault();
				}
			}
		});
	</script>