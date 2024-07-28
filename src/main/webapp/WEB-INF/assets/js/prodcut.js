function addToCart(product){
	let cartItems = JSON.parse(sessionStorage.getItem('cart')) || [];
    cartItems.push(product);
    sessionStorage.setItem('cart', JSON.stringify(cartItems));
}

function removeFromCart(productId){
	var cartItems = JSON.parse(sessionStorage.getItem('cart')) || [];
	
    var index = cartItems.findIndex(function(item) {
        return item.id === productId;
    });

    if (index !== -1) {
        cartItems.splice(index, 1);
    }
    
    sessionStorage.setItem('cart', JSON.stringify(cartItems));
}