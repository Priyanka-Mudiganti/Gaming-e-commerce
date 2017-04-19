/**
 * 
 */

function submitOrder(){
	var orderData = getLineItemsForOrder();
	console.log(orderData.length);
	console.log("order data  "+ JSON.stringify(orderData));
	
	jQuery("#cart").val(JSON.stringify(orderData));
}

function getLineItemsForOrder(){
	var jsonArr = new Array();
	var i=0;
	for(var key in window.localStorage){
		if(window.localStorage.hasOwnProperty(key)){
			var obj = window.localStorage.getItem(key);
			var jsonObj = JSON.parse(obj);
			
			jsonArr[i] = jsonObj;
			i++
		}
	}
	return jsonArr;
}