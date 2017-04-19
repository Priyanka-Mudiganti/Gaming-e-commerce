/**
 * 
 */
jQuery(document).ready(function() {
	
	if(jQuery("#numOfLineItems")){
		jQuery("#numOfLineItems").text(getCountOfItemsInCart());
	}
	
	if(jQuery("#checkout")){
		jQuery("#checkout").attr("href", resolveCheckoutUrl());
	}
});

function getCountOfItemsInCart(){
	var itemsInTheCart = 0;
	for(var key in window.localStorage){
		if(window.localStorage.hasOwnProperty(key)){
			var obj = window.localStorage.getItem(key);
			var jsonObj = JSON.parse(obj);
			var qty = jsonObj.quantity;
			itemsInTheCart = itemsInTheCart+parseInt(qty);
		}
	}
	return itemsInTheCart;
}

function resolveCheckoutUrl(){
	// If user is already logged in, then show the payment information page.
	// If the user is not logged in or the session is invalidated, then show login page.
	var isUserLoggedIn = window.sessionStorage.getItem("isUserLoggedIn");
	if(isUserLoggedIn) {
		return  '/gamezone/checkout/true';
	} else {
		return '/gamezone/checkout/false';
	}
}