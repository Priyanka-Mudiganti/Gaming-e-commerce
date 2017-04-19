function incrementQty(id){
	var i = jQuery("#qty"+id).val();
	i = parseInt(i)+1;
	jQuery("#qty"+id).val(i);
	
	var totalPrice = getNumberFromString(jQuery("#unitPrice"+id).html())*i;
	jQuery("#totalPrice"+id).html("$"+totalPrice.toFixed(2));
	updateCartTotal();
	//update the items in the cart
	changeQuantityForLineItem(id,i);
}


function decrementQty(id){
	var i = jQuery("#qty"+id).val();
	i = parseInt(i)-1;
	if(i<=0){
		i = 1;
	}
	jQuery("#qty"+id).val(i);
	var totalPrice = getNumberFromString(jQuery("#unitPrice"+id).html())*i;
	jQuery("#totalPrice"+id).html("$"+totalPrice.toFixed(2));
	updateCartTotal();
	//update the items in the cart
	changeQuantityForLineItem(id,i);
}
