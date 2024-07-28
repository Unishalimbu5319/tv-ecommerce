<%@ include file="header.jsp"%>
<style>
	.row {
		max-width: 800px;
		margin: 0 auto;
	}
</style>
<div class="container-fluid mt-5">
	<div class="row">
		<div class="col-md-6">
			<h2>Contact Us</h2>
			<p>For inquiries or assistance, you can reach us via the
				following methods:</p>
			<ul>
				<li>Phone: +977 9808508164, +977 9840037736, +977 9861963455, 9818003357</li>
				<li>Email: dhirajgiri8a11@gmail.com, yunnielimbu@gmail.com, baibhavregmi2@gmail.com, shirishthapa064@gmail.com</li>
			</ul>
		</div>
		<div class="col-md-6">
			<h2>Send Us a Message</h2>
			<form action="" method="post">
				<div class="mb-3">
					<label for="email" class="form-label">Email:</label> 
					<input type="email" class="form-control" id="email" name="email" required>
				</div>
				<div class="mb-3">
					<label for="message" class="form-label">Message:</label>
					<textarea class="form-control" id="message" name="message" rows="5"
						required>
					</textarea>
				</div>
				<button type="submit" class="btn btn-primary">Send Message</button>
			</form>
		</div>
	</div>
</div>
<%@ include file="footer.jsp"%>