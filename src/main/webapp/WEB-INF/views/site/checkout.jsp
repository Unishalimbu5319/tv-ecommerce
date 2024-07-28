<%@ include file="header.jsp"%>
<div class="container my-5">
	<form method="POST" action="" class="needs-validation" novalidate="">
		<div class="row">
			<div class="col-md-4 order-md-2 mb-4">
				<h4 class="d-flex justify-content-between align-items-center mb-3">
					<span class="text-muted">Your cart</span> <span
						class="badge badge-secondary badge-pill">3</span>
				</h4>
				<ul class="cart-items list-group mb-3">
					<div class="list-body"></div>
					<li class="list-group-item d-flex justify-content-between">
						<input type="hidden" name="total" value=""/>
						<span>Total (Rs)</span> <strong id="total">Rs. 20</strong>
					</li>
				</ul>
			</div>

			<div class="col-md-8 order-md-1">
				<h4 class="mb-3">Shipping address</h4>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="firstName">First name</label> 
						<input type="text"
							class="form-control" id="firstName" placeholder=""
							value="${user.getFirstName()}" required="">
						<div class="invalid-feedback">Valid first name is required.
						</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="lastName">Last name</label> 
							<input type="text"
							class="form-control" id="lastName" placeholder=""
							value="${user.getLastName()}" required="">
						<div class="invalid-feedback">Valid last name is required.</div>
					</div>
				</div>

				<div class="mb-3">
					<label for="email">Email <span class="text-muted">(Optional)</span></label>
					<input name="email" type="email" class="form-control" id="email"
						placeholder="you@example.com" value="${user.getEmail()}">
					<div class="invalid-feedback">Please enter a valid email
						address for shipping updates.</div>
				</div>

				<div class="mb-3">
					<label for="address">Address</label> 
					<input type="text" name="address"
						class="form-control" id="address" placeholder="" required="">
					<div class="invalid-feedback">Please enter your shipping
						address.</div>
				</div>

				<div class="row">
					<div class="col-md-5 mb-3">
						<label for="country">Country</label> 
						<select style="height: 38px;" name="country"
							class="custom-select d-block w-100" id="country" required="">
							<option value="">Choose...</option>
							<option>Nepal</option>
						</select>
						<div class="invalid-feedback">Please select a valid country.</div>
					</div>
					
					<div class="col-md-4 mb-3">
						<label for="state">State</label> 
						<select style="height: 38px;" name="state"
							class="custom-select d-block w-100" id="state" required="">
							<option value="">Choose...</option>
							<option>Bagmati</option>
							<option>Koshi</option>
							<option>Madhesh</option>
							<option>Gandaki</option>
							<option>Lumbini</option>
							<option>Karnali</option>
							<option>Sudurpaschim</option>
						</select>
						<div class="invalid-feedback">Please provide a valid state.
						</div>
					</div>
					<div class="col-md-3 mb-3">
						<label for="zip">Zip</label> 
						<input type="text" name="zip"
							class="form-control" id="zip" placeholder="" required="">
						<div class="invalid-feedback">Zip code required.</div>
					</div>
				</div>
				<hr class="mb-4">
				<h4 class="mb-3">Payment</h4>

				<div class="d-block my-3">
					<div class="custom-control custom-radio">
						<input id="credit" name="paymentMethod" type="radio"
							class="custom-control-input" checked="" required="" value="Credit Card"> <label
							class="custom-control-label" for="credit">Credit card</label>
					</div>
					<div class="custom-control custom-radio">
						<input id="debit" name="paymentMethod" value="Debit Card" type="radio"
							class="custom-control-input" required=""> <label
							class="custom-control-label" for="debit">Debit card</label>
					</div>
					<div class="custom-control custom-radio">
						<input id="cod" name="paymentMethod" value="Cash On Delivery" type="radio"
							class="custom-control-input" required=""> <label
							class="custom-control-label" for="cod">Cash On Delivery</label>
					</div>
				</div>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label for="cc-name">Name on card</label> 
						<input type="text" name="cc-name"
							class="form-control" id="cc-name" placeholder="" required="">
						<small class="text-muted">Full name as displayed on card</small>
						<div class="invalid-feedback">Name on card is required</div>
					</div>
					<div class="col-md-6 mb-3">
						<label for="cc-number">Credit card number</label> 
						<input name="cc-number"
							type="text" class="form-control" id="cc-number" placeholder=""
							required="">
						<div class="invalid-feedback">Credit card number is required</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 mb-3">
						<label for="cc-expiration">Expiration</label> 
						<input type="text" name="cc-expiration"
							class="form-control" id="cc-expiration" placeholder=""
							required="">
						<div class="invalid-feedback">Expiration date required</div>
					</div>
					<div class="col-md-3 mb-3">
						<label for="cc-expiration">CVV</label> 
						<input type="text" name="cc-cvv"
							class="form-control" id="cc-cvv" placeholder="" required="">
						<div class="invalid-feedback">Security code required</div>
					</div>
				</div>
				<hr class="mb-4">
				<button class="btn btn-primary btn-lg btn-block" type="submit">Order</button>
			</div>
		</div>
	</form>
</div>

<script>
	    let cartItemsString = sessionStorage.getItem("cart");
		let cartItems = JSON.parse(cartItemsString);
		
		let total = 0;
		
		cartItems.forEach(item => {
			let ul = document.querySelector("ul.cart-items .list-body");
			ul.appendChild(createCheckoutCartItem(item));
			total += Number(item.price);
		});
		
		
		let totalAmount = document.getElementById("total");
		totalAmount.innerHTML = "Rs. " + total;
		console.log(document.querySelector("[name=total]").value=total);
    	
    	function createCheckoutCartItem(item){    		
		    var listItem = document.createElement('li');
		    listItem.classList.add('list-group-item', 'd-flex', 'justify-content-between', 'lh-condensed');
		    var innerDiv = document.createElement('div');
		
		    var heading = document.createElement('h6');
		    heading.classList.add('my-0');
		    heading.textContent = item.name;
		    innerDiv.appendChild(heading);
		    
		    listItem.appendChild(innerDiv);
		
		    var price = document.createElement('span');
		    price.classList.add('text-muted');
		    price.textContent = 'Rs. ' + item.price;
		
		    listItem.appendChild(price);
		    return listItem;
    	}
    </script>
<%@ include file="footer.jsp"%>