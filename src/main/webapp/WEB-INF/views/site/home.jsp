<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="header.jsp"%>
<style>
.card:hover {
	cursor: pointer;
}
</style>
<header class="py-5">
	<div class="container px-4 px-lg-5 my-5">
		<div class="text-center text-dark">
			<h1 class="display-4 fw-bolder">Shop in style</h1>
			<p class="lead fw-normal text-dark-50 mb-0">With the Best TV
				Dealers</p>
		</div>
	</div>
</header>
<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row">
			<div id="filters" class="container mt-4 mb-4">
				<div class="row">
					<div class="col-md-3">
						<input type="number" id="minPrice" class="form-control"
							placeholder="Minimum Price">
					</div>
					<div class="col-md-3">
						<input type="number" id="maxPrice" class="form-control"
							placeholder="Maximum Price">
					</div>
					<div class="col-md-3">
						<input type="text" id="name" class="form-control"
							placeholder="Product Name">
					</div>
					<div class="col-md-3">
						<button onclick="applyFilters()" class="btn btn-primary">Apply
							Filters</button>
						<button onclick="clearFilters()" class="btn btn-secondary">Clear
							Filters</button>
					</div>
				</div>
			</div>

			<div id="products"
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
				<c:forEach var="product" items="${products}" varStatus="status">
					<div class="col mb-5">
						<div class="card h-100">
							<div class="card-detail">
								<input type="hidden" data-id value="${product.getId()}" />
								<div class="badge bg-dark text-white position-absolute"
									style="top: 0.5rem; right: 0.5rem">Sale</div>
								<img class="card-img-top" data-image
									src="ImageRetrieve?fileName=${product.getImage()}"
									alt="${product.getName()}">
								<div class="card-body p-4">
									<div class="text-center">
										<h5 class="fw-bolder" data-name>${product.getName()}</h5>
										<div
											class="d-flex justify-content-center small text-warning mb-2">
											<div class="bi-star-fill"></div>
											<div class="bi-star-fill"></div>
											<div class="bi-star-fill"></div>
											<div class="bi-star-fill"></div>
											<div class="bi-star-fill"></div>
										</div>
										<!-- Product price-->
										<span>Rs. <span data-price>${product.getPrice()}</span></span>
									</div>
								</div>
							</div>
							<!-- Product actions-->
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="#">Add to
										cart</a>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</section>

<script>
let cards = document.querySelectorAll(".card");
let cardDetails = document.querySelectorAll(".card-detail");

cardDetails.forEach(card => {
	card.addEventListener("click", function(e){
		const id = card.querySelector("[data-id]").value;
		window.location.href = 'product?id=' + id;
	});
});

cards.forEach(card => {
	let cartBtn = card.querySelector("a.btn");
	cartBtn.addEventListener("click", function(e){
		e.preventDefault();
		if (!<%=isLoggedIn%>){
			window.alert("You haven't logged in, please log in to add to the cart...");
			return;
		}
		
		let id = card.querySelector("[data-id]").value;
		let name = card.querySelector("[data-name]").innerHTML;
		let image = card.querySelector("[data-image]").src;
		let price = card.querySelector("[data-price]").innerHTML;
		
		let product = {
			"id":id,
			"name" : name,
			"image" : image,
			"price" : price
		};
		if (addToCart(product)){
			window.alert(product.name + " was Sucessfully Added");	
		}
	});
})

function addToCart(product){
	let cartItems = JSON.parse(sessionStorage.getItem('cart')) || [];
    cartItems.push(product);
    sessionStorage.setItem('cart', JSON.stringify(cartItems));
    return true;
}

function applyFilters() {
	let name = document.getElementById("name").value;
    let minPrice = document.getElementById("minPrice").value;
    let maxPrice = document.getElementById("maxPrice").value;
    
    console.log(minPrice);
    
    if (name == ""){
    	name = "";
    }
    
    if (minPrice == ""){
    	minPrice = 0;
    }
    
    if (maxPrice == ""){
    	maxPrice = 0;
    }
    
    console.log(minPrice, maxPrice, name);
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                document.getElementById("products").innerHTML = xhr.responseText;
            } else {
                console.error("Error: " + xhr.statusText);
            }
        }
    };
    xhr.open("GET", "filter?name=" + encodeURIComponent(name) + "&minPrice=" + encodeURIComponent(minPrice) + "&maxPrice=" + encodeURIComponent(maxPrice), true);
    xhr.send();
}

function clearFilters() {
    document.getElementById("minPrice").value = "";
    document.getElementById("maxPrice").value = "";
    document.getElementById("name").value = "";
    
    let xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            if (xhr.status == 200) {
                document.getElementById("products").innerHTML = xhr.responseText;
            } else {
                console.error("Error: " + xhr.statusText);
            }
        }
    };
    xhr.open("GET", "filter?name=" + encodeURIComponent("") + "&minPrice=" + encodeURIComponent(0) + "&maxPrice=" + encodeURIComponent(0), true);
    xhr.send();
}
</script>
<%@ include file="footer.jsp"%>