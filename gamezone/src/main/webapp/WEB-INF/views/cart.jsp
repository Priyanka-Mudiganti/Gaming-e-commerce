<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Game Zone | Cart</title>
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
			src='/gamezone/resources/js/cart/shoppingCart.js'></script>			

		<link rel="stylesheet"
			href="/gamezone/resources/css/bootstrap.min.css">
		<link rel="stylesheet"
			href="/gamezone/resources/css/spinner.css">		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>

	<body>
		<%@include file="header.jsp"%>
		<div id="site-content">
			<main class="main-content">
				<div class="container">
					<div align="center" id="emptyCart">
						<span><strong>Your cart is empty.</strong></span>
						<span><a href="/gamezone/store" class="button">Continue Shopping</a></span>
					</div>
					<div class="page">
						<table class="cart" id="cartTable">
							<thead>
								<tr>
									<th class="product-name">Product Name</th>
									<th class="product-price">Price</th>
									<th class="product-qty">Quantity</th>
									<th class="product-total">Total</th>
									<th class="action"></th>
								</tr>
							</thead>
							<tbody id="cartBody">
							</tbody>
						</table> <!-- .cart -->

						<div class="cart-total">
							<p class="total"><strong>Total (excluding taxes)</strong><span class="num" id="cartTotal"></span></p>
							<p>
								<a href="/gamezone/store" class="button">Continue Shopping</a>
								<a id="checkout" href="#" class="button">Checkout</a>
							</p>
						</div> <!-- .cart-total -->
						
					</div>
				</div> <!-- .container -->
			</main> <!-- .main-content -->
		</div>
		<div class="overlay"></div>
		
		
	</body>

</html>