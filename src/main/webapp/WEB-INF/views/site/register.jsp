<%@ include file="header.jsp"%>
<style>
div.container-fluid {
	width: 450px;
	height: 100vh;
}
</style>
<div class="container-fluid d-flex justify-content-center flex-column">
	<h1 class="text-center">Register Form</h1>
	<form class="row g-3 needs-validation" novalidate action=""
		method="POST">
		<div class="col-md-6">
			<label for="validationCustom01" class="form-label">First name</label>
			<input type="text" name="firstName" class="form-control"
				id="validationCustom01" value="" required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<div class="col-md-6">
			<label for="validationCustom02" class="form-label">Last name</label>
			<input type="text" name="lastName" class="form-control"
				id="validationCustom02" value="" required>
			<div class="valid-feedback">Looks good!</div>
		</div>
		<div class="col-md-12">
			<label for="validationCustomUsername" class="form-label">Username</label>
			<div class="input-group has-validation">
				<span class="input-group-text" id="inputGroupPrepend">@</span> <input
					type="text" name="username" class="form-control"
					id="validationCustomUsername" aria-describedby="inputGroupPrepend"
					required>
				<div class="invalid-feedback">Please choose a username.</div>
			</div>
		</div>
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
		<div class="col-md-12">
			<label for="validationCustomCPassword" class="form-label">Confirm
				Password</label>
			<div class="input-group has-validation">
				<span class="input-group-text" id="inputGroupPrepend"></span> <input
					type="password" name="cPassword" class="form-control"
					id="validationCustomCPassword" aria-describedby="inputGroupPrepend"
					required>
				<div class="invalid-feedback">Please enter the same password.</div>
			</div>
		</div>
		<div class="col-md-12">
			<select name="role" class="form-select" aria-label="Roles">
				<option selected>Your Role</option>
				<option value="Staff">Staff</option>
				<option value="Regular">Regular</option>
			</select>
		</div>
		<div class="col-12">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="invalidCheck" required> <label class="form-check-label"
					for="invalidCheck"> Agree to terms and conditions </label>
				<div class="invalid-feedback">You must agree before
					submitting.</div>
			</div>
		</div>
		<div class="col-12">
			<button class="btn btn-primary" type="submit">Submit form</button>
		</div>
		<div class="col-12">
			 <p class="text-center mt-3">Have an account? <a href="/tv/login">Login</a>.</p>
		</div>
	</form>
</div>
<script>
(() => { 'use strict'
	  const forms = document.querySelectorAll('.needs-validation')
	  
	  Array.from(forms).forEach(form => {
	    form.addEventListener('submit', event => {
	      if (!form.checkValidity()) {
	        event.preventDefault();
	        event.stopPropagation();
	        
	      }

	      form.classList.add('was-validated')
	    }, false)
	  })
	})()
</script>
<%@ include file="footer.jsp"%>