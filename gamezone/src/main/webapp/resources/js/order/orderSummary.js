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
	updateCartTotalWithTax();
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
	html = html + '<td class="product-qty" style="align:center">';
	html = html + jsonObj.quantity;
	html = html + '</td>';
	html = html + '<td class="product-total" id="totalPrice'+jsonObj.offers.id+'">$'+(parseInt(jsonObj.quantity)*jsonObj.offers.prices.amount.toFixed(2)).toFixed(2)+'</td>';
	
	html = html + "</tr>";
	$("#cartBody").append(html);
}
  
function updateCartTotalWithTax(){
	var totalPriceWithTax = window.sessionStorage.getItem("cartTotalWithTax");
	console.log('totalPriceWithTax  ' + totalPriceWithTax);
	jQuery("#cartTotalWithTax").text("$"+parseFloat(totalPriceWithTax).toFixed(2));
}