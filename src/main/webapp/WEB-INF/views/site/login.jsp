<%@ include file="header.jsp"%>
<style>
div.container-fluid {
	width: 450px;
	height: 100vh;
}
</style>
<div class="container-fluid d-flex justify-content-center flex-column">
	<%
	String error = (String) request.getAttribute("error");
	if (error != null) {
	%>
	<div class="alert alert-danger mt-3" role="alert">
		<%=error%>
	</div>
	<%
	}
	%>
	
	<h1 clas="text-center">Login Form</h1>
	<form class="row g-3 needs-validation" novalidate action=""
		method="POST">
		<div class="col-md-12">
			<label for="validationCustomEmail" class="form-label">Email</label>
			<div class="input-group has-validation">
				<span class="input-group-text" id="inputGroupPrepend"></span> <input
					type="email" name="email" class="form-control"
					id="validationCustomEmail" aria-describedby="inputGroupPrepend"
					required>
				<div class="invalid-feedback">Enter your email.</div>
			</div>
		</div>
		<div class="col-md-12">
			<label for="validationCustomPassword" class="form-label">Password</label>
			<div class="input-group has-validation">
				<span class="input-group-text" id="inputGroupPrepend"></span> <input
					type="password" name="password" class="form-control"
					id="validationCustomPassword" aria-describedby="inputGroupPrepend"
					required>
				<div class="invalid-feedback">Please enter a password.</div>
			</div>
		</div>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">Login</button>
		</div>
		<div class="col-12">
			 <p class="text-center mt-3">Don't have an account? <a href="/tv/register">Create one</a>.</p>
		</div>
	</form>
</div>
<script>
(() => { 'use strict'
	  const forms = document.querySelectorAll('.needs-validation')
	  
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
	      if (!form.checkValidity()) {
	        event.preventDefault()
	        event.stopPropagation()
	      }

	      form.classList.add('was-validated')
	    }, false)
	  })
	})()
</script>
<%@ include file="footer.jsp"%>