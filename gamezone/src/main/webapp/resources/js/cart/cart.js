/**
 * 
 */



function addToCart(lineItem){
	var index;
	if($("#offer-"+lineItem.offers[0].id).is(":checked")){
		index=lineItem.offers[0].id;
		lineItem.offers = lineItem.offers[0];
		lineItem.offers.prices = lineItem.offers.prices[0];
	}else if($("#offer-"+lineItem.offers[1].id).is(":checked")){
		index=lineItem.offers[1].id;
		lineItem.offers = lineItem.offers[1];
		lineItem.offers.prices = lineItem.offers.prices[0];
	} else {
		return false;
	}
	var obj = window.localStorage.getItem(index);
	if(obj){
		// TODO: Need to beautify this confirmation dialog.
		if(!confirm("This product has already been added to the cart. Are you sure want to add more quantity?")){
			return;
		}
		var jsonObj = JSON.parse(obj);
		jsonObj.quantity = parseInt(jsonObj.quantity) + 1;
		var lineItemStr = JSON.stringify(jsonObj);
		window.localStorage.setItem(index, lineItemStr);
	}else {
		// set initial default quantity to 1
		lineItem.quantity = "1";
		var lineItemStr = JSON.stringify(lineItem);
		window.localStorage.setItem(index, lineItemStr);
	}
	jQuery("#numOfLineItems").text(getCountOfItemsInCart());
}

function clearCart(){
	window.localStorage.clear();
	var count = getCountOfItemsInCart();
	jQuery("#numOfLineItems").text(count);
	toggleEmptyCart(count);
}

function removeItemFromCart(key){
	window.localStorage.removeItem(key);
	var count = getCountOfItemsInCart();
	jQuery("#numOfLineItems").text(count);
	toggleEmptyCart(count);
}

function changeQuantityForLineItem(key, newQuantity){
	var obj = window.localStorage.getItem(key);
	var jsonObj = JSON.parse(obj);
	jsonObj.quantity = newQuantity;
	var lineItemStr = JSON.stringify(jsonObj);
	window.localStorage.setItem(key, lineItemStr);
	jQuery("#numOfLineItems").text(getCountOfItemsInCart());
}

function toggleEmptyCart(numOfItems){
	if(numOfItems==0){
		jQuery("#cartTable").hide();
		jQuery(".cart-total").hide();
		jQuery("#emptyCart").show();
	} else {
		jQuery("#emptyCart").hide();
		jQuery("#cartTable").show();
		jQuery(".cart-total").show();
	}
}

function getNumberFromString(strNumber){
	 return strNumber.replace( /^\D+/g, '');
}


