<!DOCTYPE html>
<html>
<head>
<title>Admin Panel - ${pageTitle}</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f9f9f9;
	margin: 0;
	padding: 0;
}

header h2 {
	font-size: 2rem;
	color: black;
	padding: 16px
}

.sidebar {
	margin: 0;
	padding: 0;
	width: 200px;
	background-color: #f1f1f1;
	position: fixed;
	height: 100%;
	overflow: auto;
}

.sidebar a {
	display: block;
	color: black;
	padding: 16px;
	text-decoration: none;
}

.sidebar a.active {
	background-color: #04AA6D;
	color: white;
}

.sidebar a:hover:not(.active) {
	background-color: #555;
	color: white;
}

div.content {
	margin-left: 200px;
	padding: 1px 16px;
	height: 1000px;
}

.container {
	width: 80%;
	margin: 0 auto;
	padding: 20px;
	border-radius: 5px;
}

h1 {
	text-align: center;
	font-size: 24px;
	color: #333;
	margin-bottom: 20px;
}

form {
	max-width: 600px;
	margin: 0 auto;
}

.form-field {
	margin-bottom: 20px;
}

.form-field label {
	display: block;
	margin-bottom: 5px;
	font-weight: bold;
	color: #333;
}

.form-field input[type="text"], .form-field input[type="number"],
	.form-field textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

.form-field input[type="file"] {
	margin-top: 10px;
}

.form-field input[type="submit"] {
	background-color: #007bff;
	color: #fff;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

.form-field input[type="submit"]:hover {
	background-color: #0056b3;
}

table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid #ddd;
	padding: 10px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

img {
	max-width: 100px;
	max-height: 100px;
}

.edit-btn, .delete-btn {
	padding: 8px 12px;
	border: none;
	cursor: pointer;
	border-radius: 4px;
}

.edit-btn {
	background-color: #4CAF50;
	color: white;
}

.delete-btn {
	background-color: #f44336;
	color: white;
}

.serial-number {
	font-weight: bold;
}

/* On screens that are less than 700px wide, make the sidebar into a topbar */
@media screen and (max-width: 700px) {
	.sidebar {
		width: 100%;
		height: auto;
		position: relative;
	}
	.sidebar a {
		float: left;
	}
	div.content {
		margin-left: 0;
	}
}

/* On screens that are less than 400px, display the bar vertically, instead of horizontally */
@media screen and (max-width: 400px) {
	.sidebar a {
		text-align: center;
		float: none;
	}
}
</style>
</head>
<body>
	<header class="sidebar">
		<h2>Welcome ${user}</h2>
		<div>
			<a href="/tv/products">Products</a> 
			<a href="/tv/AddProduct">Add Product</a> 
			<a href="/tv/orders">Orders</a>
			<%
		Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
		if (isLoggedIn != null && isLoggedIn) {
		%>
      		<a href="/tv/logout" class="mx-2 d-flex align-items-center">Logout</a>
		<% } %>
		</div>
	</header>

	<script>
    	let current_location = window.location.href;
    	const nav_items = document.querySelectorAll(".sidebar a");
    	console.log(current_location);
    	nav_items.forEach(item => {
    		if (current_location.includes(item.getAttribute("href"))){
    			item.classList.add("active");
    		}
    	});
    </script>

	<div class="content">