/**
 * 
 */
jQuery(document).ready(function() {

	updateCartTotalWithTax();
	
	setUserLoginFlag();
	jQuery("#payment_method_stripe").click(function(){
		jQuery("#stripe-card-number").attr("required");
	});
	jQuery("#payment_method_paypal").click(function(){
		alert('1');
	});
});


function setUserLoginFlag(){
	// when the user is on payment page, that means, the user must have already logged in.
	// when the browser is closed, this will go away
	window.sessionStorage.setItem("isUserLoggedIn",true);
}


function togglePaymentMethod(){
	alert('inside toggle');
	var cardSelected = jQuery("#payment_method_stripe:checked");
	var paypalSelected = jQuery("#payment_method_paypal:checked");
	
	alert('card '+ cardSelected);
	alert('paypal '+ paypalSelected);
}

function updateCartTotalWithTax(){
	var totalPrice = window.sessionStorage.getItem("cartTotal");
	var totalPriceWithTax = window.sessionStorage.getItem("cartTotalWithTax");
	console.log('totalPrice  ' + totalPrice);
	console.log('totalPriceWithTax  ' + totalPriceWithTax);
	jQuery("#cartTotalWithTax").text("$"+parseFloat(totalPriceWithTax).toFixed(2));
	jQuery("#cartTotal").text("$"+parseFloat(totalPrice).toFixed(2));
}


