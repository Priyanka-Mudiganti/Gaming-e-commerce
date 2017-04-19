/**
 * 
 */
jQuery(document).ready(function() {
	getItemsInTheCart();
});

function getItemsInTheCart(){
	var itemsInTheCart = 0;
	for(var key in window.localStorage){
		if(window.localStorage.hasOwnProperty(key)){
			var obj = window.localStorage.getItem(key);
			var jsonObj = JSON.parse(obj);
			
			appendToCart(jsonObj);
			
			var qty = jsonObj.quantity;
			itemsInTheCart = itemsInTheCart+parseInt(qty);
		}
	}
	updateCartTotal();
	toggleEmptyCart(itemsInTheCart);
}

function appendToCart(jsonObj){
	
	var html = '<tr id=\"tr'+jsonObj.offers.id+'\">';
	
	html = html + '<td class="product-name">';
	html = html + '<div class="product-thumbnail">';
	html = html + '<img src="/gamezone/resources/images/dummy/cart-thumb-1.jpg" alt="">';
	html = html + '</div>';
	html = html + '<div class="product-detail">';
	html = html + '<h3 class="product-title">'+jsonObj.name+'</h3>';
	html = html + '<p>'+jsonObj.largeDesc+'</p>';
	html = html + '</div>';
	html = html + '</td>';
	html = html + '<td class="product-price" id="unitPrice'+jsonObj.offers.id+'">$'+jsonObj.offers.prices.amount.toFixed(2)+'</td>';
	html = html + '<td class="product-qty">';
	html = html + '<div class="input-group spinner">';
	html = html + '<input type="text" id="qty'+jsonObj.offers.id+'" class="form-control" value="'+jsonObj.quantity+'">';
	html = html + '<div class="input-group-btn-vertical">';
	html = html + '<button onclick="javascript:incrementQty('+jsonObj.offers.id+')" class="btn btn-default" type="button"><i class="fa fa-caret-up"></i></button>';
	html = html + '<button onclick="javascript:decrementQty('+jsonObj.offers.id+')" class="btn btn-default" type="button"><i class="fa fa-caret-down"></i></button>';
	html = html + '</div>';
	html = html + '</td>';
	html = html + '<td class="product-total" id="totalPrice'+jsonObj.offers.id+'">$'+(parseInt(jsonObj.quantity)*jsonObj.offers.prices.amount.toFixed(2)).toFixed(2)+'</td>';
	html = html + '<td class="action"><a href="javascript:deleteFromCart('+jsonObj.offers.id+')"><i class="fa fa-times"></i></a></td>';
	
	html = html + "</tr>";
	$("#cartBody").append(html);
}

function deleteFromCart(id){
	// remove item from cart
	jQuery("#tr"+id).remove();
	removeItemFromCart(id);
	updateCartTotal();
}

function updateCartTotal(){
	var totalPrice = 0;
	jQuery("[id^=totalPrice]").each(function(){
		var price = parseFloat(getNumberFromString(jQuery(this).html())).toFixed(2);
		totalPrice =  parseFloat(totalPrice)+parseFloat(price);
	});
	jQuery("#cartTotal").text("$"+totalPrice.toFixed(2));
	window.sessionStorage.setItem("cartTotal", totalPrice.toFixed(2));
	
	var totalPriceWithTax = totalPrice+parseFloat('10.00');
	window.sessionStorage.setItem("cartTotalWithTax", totalPriceWithTax.toFixed(2));
}


  
  




	


	





	
	



