<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Game Zone | Order Confirmation</title>
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

		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->

	</head>

	<body>
		<%@include file="header.jsp"%>
		<form action="/gamezone/submitPurchaseOrder">
		<div id="site-content">
			<main class="main-content">
				<div class="container">
					<div class="page">
						<table>
						Your Order has been confirmed !!
						
						Your Order Number is :
						</table>

					</div>
				</div> <!-- .container -->
			</main> <!-- .main-content -->
		</div>
		</form>
		<div class="overlay"></div>
		
		
	</body>

</html>