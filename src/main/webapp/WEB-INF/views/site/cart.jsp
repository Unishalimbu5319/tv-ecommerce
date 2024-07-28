<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<style>
.cart-container {
	border: 1px solid rgba(0, 0, 0, 0.05);
	padding: 30px;
}

.cart-container .cart-body {
	border-top: 1px solid rgba(0, 0, 0, 0.05);
	border-bottom: 1px solid rgba(0, 0, 0, 0.05);
	padding: 30px 0 20px;
	margin: 20px 0 30px;
}
</style>
<c:forEach var="item" items="${cartItems}" varStatus="status">
	<p>${item}</p>
</c:forEach>

<div class="container">
	<div class="contentbar">
		<!-- Start row -->
		<div class="row">
			<!-- Start col -->
			<div class="col-md-12 col-lg-12 col-xl-12">
				<div class="card m-b-30">
					<div class="card-header">
						<h5 class="card-title">Cart</h5>
					</div>
					<div class="card-body">
						<div class="row justify-content-center">
							<div class="col-lg-10 col-xl-12">
								<div class="cart-container">
									<div class="cart-head">
										<div class="table-responsive">
											<table class="table table-borderless">
												<thead>
													<tr>
														<th scope="col">#</th>
														<th scope="col">Action</th>
														<th scope="col">Photo</th>
														<th scope="col">Product</th>
														<th scope="col">Qty</th>
														<th scope="col">Price</th>
														<th scope="col" class="text-right">Total</th>
													</tr>
												</thead>
												<tbody>
												</tbody>
											</table>
										</div>
									</div>
									<div class="cart-body">
										<div class="row">
											<div class="col-md-12 order-1 order-lg-2 col-lg-7 col-xl-6">
												<div class="order-total table-responsive ">
													<table class="table table-borderless text-right">
														<tbody>
															<tr>
																<td>Sub Total :</td>
																<td id="sub-total"></td>
															</tr>
															<tr>
																<td>Shipping :</td>
																<td>Rs. 0.00</td>
															</tr>
															<tr>
																<td>Tax(18%) :</td>
																<td id="tax"></td>
															</tr>
															<tr>
																<td class="f-w-7 font-18"><h4>Amount :</h4></td>
																<td class="f-w-7 font-18"><h4 id="total"></h4></td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="cart-footer text-right">
										<a href="/tv/home" class="btn btn-info my-1">
											<i class="ri-save-line mr-2"></i>Update Cart
										</a>
										<a href="/tv/checkout" class="btn btn-success my-1">Proceed
											to Checkout<i class="ri-arrow-right-line ml-2"></i>
										</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- End col -->
		</div>
		<!-- End row -->
	</div>
</div>
<script>
	let cartItemsString = sessionStorage.getItem("cart");
	let cartItems = JSON.parse(cartItemsString);
	
	let totalAmount = 0;
	let subTotal = document.querySelector("#sub-total");
	let total = document.querySelector("#total");
	let tax = document.querySelector("#tax");
	
	cartItems.forEach(item => {
		let tbody = document.querySelector(".cart-head table tbody");
		tbody.appendChild(createCartItem(item));	
	});
	
	let items = document.querySelectorAll(".cart-head tbody tr");
	items.forEach(item => {
		let qty = item.querySelector("input[type=number]");
		let price = Number(item.querySelector(".price").innerHTML.replace("Rs. ", ""));
		let totalPrice = item.querySelector(".total-price");
		
		qty.addEventListener("change", function(e){
			if (qty.value < 0){
				qty.value = 0;
			}
			totalPrice.innerHTML = "Rs. " + qty.value * price;
		
			totalAmount = 0;
			items.forEach(i => {
				let price = Number(i.querySelector(".total-price").innerHTML.replace("Rs. ", ""));
				totalAmount += price;
			});
			let taxAmount = 0.18 * totalAmount;
			tax.innerHTML = "Rs. " + taxAmount;
			subTotal.innerHTML = "Rs. " + totalAmount;
			total.innerHTML = "Rs. " + (totalAmount + taxAmount);
		});
		totalAmount += Number(totalPrice.innerHTML.replace("Rs. ", ""));
	});
	
	subTotal.innerHTML = "Rs. " + totalAmount;
	tax.innerHTML = "Rs. " + 0.18*totalAmount;
	total.innerHTML = "Rs. " + (totalAmount + 0.18*totalAmount);
	
	const deleteBtns = document.querySelectorAll(".delete");
	deleteBtns.forEach(btn => {
		btn.addEventListener("click", function(e){
			e.preventDefault();
			
			removeFromCart(btn.getAttribute("id"));
		});
	});
	
	function removeFromCart(productId){
		var cartItems = JSON.parse(sessionStorage.getItem('cart')) || [];
		
	    var index = cartItems.findIndex(function(item) {
	        return item.id === productId;
	    });

	    if (index !== -1) {
	        cartItems.splice(index, 1);
	    }
	    
	    sessionStorage.setItem('cart', JSON.stringify(cartItems));
	    window.location.reload();
	}
	
	function createCartItem(item){
		var row = document.createElement('tr');
		var hiddenInput = document.createElement("input");
		hiddenInput.setAttribute("tupe", "hidden");
		hiddenInput.setAttribute("value", item.id);
		
		var column1 = document.createElement('th');
		column1.setAttribute('scope', 'row');
		column1.textContent = '1';
		row.appendChild(column1);

		var column2 = document.createElement('td');
		var deleteLink = document.createElement('a');
		deleteLink.classList.add("d-flex", "align-items-center", "justify-content-center", "delete");
		deleteLink.setAttribute('id', item.id);
		deleteLink.classList.add('text-danger');
		
		var deleteIcon = document.createElement('i');
		deleteIcon.classList.add('bi', "bi-trash");
		deleteLink.appendChild(deleteIcon);
		column2.appendChild(deleteLink);
		row.appendChild(column2);

		var column3 = document.createElement('td');
		var img = document.createElement('img');
		img.setAttribute('src', item.image);
		img.classList.add('img-fluid');
		img.setAttribute('width', '35');
		img.setAttribute('alt', 'product');
		column3.appendChild(img);
		row.appendChild(column3);

		var column4 = document.createElement('td');
		column4.textContent = item.name;
		row.appendChild(column4);

		var column5 = document.createElement('td');
		var qtyInput = document.createElement('input');
		qtyInput.setAttribute('type', 'number');
		qtyInput.classList.add('form-control', 'cart-qty');
		qtyInput.setAttribute('name', 'cartQty1');
		qtyInput.setAttribute('id', 'cartQty1');
		qtyInput.setAttribute('value', '1');
		var formGroup = document.createElement('div');
		formGroup.classList.add('form-group', 'mb-0');
		formGroup.appendChild(qtyInput);
		column5.appendChild(formGroup);
		row.appendChild(column5);

		var column6 = document.createElement('td');
		column6.classList.add("price");
		column6.textContent = 'Rs. ' + item.price;
		row.appendChild(column6);

		var column7 = document.createElement('td');
		column7.classList.add("total-price");
		column7.classList.add('text-right');
		column7.textContent = 'Rs. ' + item.price;
		row.appendChild(column7);
		
		return row;
	}
</script>
<%@ include file="footer.jsp"%>