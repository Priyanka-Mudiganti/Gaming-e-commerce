<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Game Zone | Order Summary</title>
		<script
			src='/gamezone/resources/js/lib/jquery.min.js'></script>
		<script
			src='/gamezone/resources/js/lib/bootstrap.min.js'></script>
		<script
			src='/gamezone/resources/js/lib/spinner.js'></script>
		<script
			src='/gamezone/resources/js/header.js'></script>	
		<script
			src='/gamezone/resources/js/cart/cart.js'></script>
		<script
			src='/gamezone/resources/js/order/orderSummary.js'></script>			
		<script
			src='/gamezone/resources/js/order/order.js'></script>

		<link rel="stylesheet"
			href="/gamezone/resources/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="/gamezone/resources/css/spinner.css">		
		<link rel="stylesheet"
			href="/gamezone/resources/css/orderSummary.css">				
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>

	<body>
		<%@include file="header.jsp"%>
		<form action="/gamezone/submitPurchaseOrder" method="POST">
		<div id="site-content">
			<main class="main-content">
				<div class="container">
					<div class="page">
						<table class="cart" id="cartTable">
							<thead>
								<tr>
									<th class="product-name">Product Name</th>
									<th class="product-price">Price</th>
									<th class="product-qty">Quantity</th>
									<th class="product-total">Total</th>
								</tr>
							</thead>
							<tbody id="cartBody">
							</tbody>
						</table> <!-- .cart -->

						<div class="cart-total">
							<p class="total"><strong>Total </strong><span class="num" id="cartTotalWithTax"></span></p>
							<p>
								<a href="/gamezone/showCart" class="button">Edit Cart</a>
								<input id="checkout" onclick="javascript:submitOrder()" type="submit" class="button" value="Submit Order">
							</p>
						</div> <!-- .cart-total -->
						
					</div>
				</div> <!-- .container -->
			</main> <!-- .main-content -->
		</div>
		<input type="hidden" value="" id="cart" name="cart"/>
		</form>
		<div class="overlay"></div>
		
		
	</body>

</html>