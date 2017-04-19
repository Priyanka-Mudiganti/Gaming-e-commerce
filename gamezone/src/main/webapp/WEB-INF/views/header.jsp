	<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Ecommerce Video Game | Cart</title>

		<!-- Loading third party fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto:100,300,400,700|" rel="stylesheet" type="text/css">
		<link href="/gamezone/resources/fonts/font-awesome.min.css" rel="stylesheet" type="text/css">
		<link href="/gamezone/resources/fonts/lineo-icon/style.css" rel="stylesheet" type="text/css">
		<link rel="stylesheet" href="/gamezone/resources/css/style.css">
		<!-- Loading main css file -->
		<link rel="stylesheet" href="/gamezone/resources/css/header.css">
		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->
	</head>
	<body class="slider-collapse">
		
		<div id="site-content">
			<div class="site-header">
				<div class="container">
					<a href="/gamezone/store" id="branding">
						<img src="/gamezone/resources/images/logo.png" alt="" class="logo">
						<div class="logo-text">
							<h1 class="site-title">Game Zone</h1>
							<small class="site-description">All day gaming...</small>
						</div>
					</a> <!-- #branding -->

					<div class="right-section pull-right">
						<a href="/gamezone/showCart" class="cart"><i class="icon-cart"></i> <span id="numOfLineItems"></span> items in cart</a>
						<a href="javascript:clearCart()" class="login-button empty-cart">EmptyCart</a>
					</div> <!-- .right-section -->

				</div> <!-- .container -->
			</div> <!-- .site-header -->

			<div class="home-slider">
				<ul class="slides">

				</ul> <!-- .slides -->
			</div> <!-- .home-slider -->
		</div>

		<div class="overlay"></div>
	</body>
</html>