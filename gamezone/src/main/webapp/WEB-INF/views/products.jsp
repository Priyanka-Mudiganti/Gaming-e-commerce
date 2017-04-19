<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java"%>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<% 
	Gson gson = new Gson();
	JsonObject myJSON = (JsonObject)request.getAttribute("prodList");
	String jsonStr = gson.toJson(myJSON);
%> 

<html >
<head>
	<meta charset="UTF-8">
	<title>GameZone Store Front</title>
	<script
		src='/gamezone/resources/js/lib/jquery.min.js'></script>
	<script
		src='/gamezone/resources/js/lib/bootstrap.min.js'></script>
	<script
		src="/gamezone/resources/js/lib/prefixfree.min.js"></script>
	<script
		src='/gamezone/resources/js/lib/jquery.simplePagination.js'></script>
	
	<link rel="stylesheet"
		href="/gamezone/resources/css/reset.min.css">
	
	<link rel="stylesheet"
		href="/gamezone/resources/css/bootstrap.min.css">
	
	<link rel='stylesheet prefetch'
		href='/gamezone/resources/css/bootstrap.min.css'>
	<spring:url value="/resources/js/index.js" var="indexJS" />
	<spring:url value="/resources/js/header.js" var="headerJS" />
	<spring:url value="/resources/js/cart/cart.js" var="cartJS" />
	<script src="${indexJS}"></script>
	<script src="${cartJS}"></script>
	<script src="${headerJS}"></script>
	<link rel='stylesheet prefetch'
		href='/gamezone/resources/css/products.css'>
</head>

<body>
  <%@include file="header.jsp"%>
  <div class="container">
    <div class="well well-sm">
        <strong>Display</strong>
        <div class="btn-group">
            <a href="#" id="list" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-th-list">
            </span>List</a> <a href="#" id="grid" class="btn btn-default btn-sm"><span
                class="glyphicon glyphicon-th"></span>Grid</a>
        </div><br/><br/>
		<div class="filter">
			<span>
				<label>Sort by:</label>
				<select name="#">
					<option value="#">Popularity</option>
					<option value="#">Highest Rating</option>
					<option value="#">Lowest price</option>
				</select>
			</span>
			<span>&nbsp;&nbsp;</span>
			<span>
				<label>Genre</label>
				<select name="#">
					<option value="#">Show All</option>
					<option value="#">Action</option>
					<option value="#">Racing</option>
					<option value="#">Strategy</option>
				</select>
			</span>
			<span>&nbsp;&nbsp;</span>
			<span>
				<label>Show:</label>
				<select name="#">
					<option value="#">9</option>
					<option value="#">18</option>
					<option value="#">27</option>
				</select>
			</span>
		</div>        
    </div>
    <div id="products" class="row list-group">
    </div>
    
    <div id="itemContainer" style="display:none">
	    <div class="item  .col-xs-3 col-lg-3">
               	<div id="prodDetails"></div>
	            <div class="thumbnail">
	                <img id="tnImage" height="150" width="150" class="group list-group-image" src="" alt="" /><br/>
	                <!-- <div id="caption" class="caption">
	                <img id="tnImage" height="150" width="150" class="group list-group-image" src="" alt="" />
	                    <h4 class="group inner list-group-item-heading">
	                        <span id="title"></span></h4>
	                    <p class="group inner list-group-item-text">
	                    	<span id="smallDesc"></span>
	                    </p>
	                    <div class="row">
	                        <div class="col-xs-12 col-md-6">
	                            <p class="lead">
	                            	<span id="price">10.00</span>
	                            </p>
	                        </div>
	                        <div class="col-xs-12 col-md-6">
	                            <a class="btn btn-success" href="http://www.jquery2dotnet.com">Add to cart</a>
	                        </div>
	                    </div>
	                </div> -->
	                <div id="offerOptions">
                		<input id="monthly" type="radio" name="productTerm" value="M">&nbsp; Monthly &nbsp;<span id="monthly"></span><br/>
						<input id="yearly" type="radio" name="productTerm" value="Y">&nbsp; Yearly &nbsp;<span id="yearly"></span><br/><br/>
	                </div>
                	<a class="btn btn-success" id="addToCartBtn">Add to cart</a>
	                
	            </div>
	        </div>
    </div>
</body>

 <script language="javascript">
 console.log('<%=jsonStr%>');
 	var json = JSON.parse('<%=jsonStr%>');
	var prodList = json.data;
	var i=0;
	for(i=0;i<prodList.length;i++){
		// initialize quantity by 0 We will increment this later once the item is added to the cart.
		prodList[i].quantity = 0;
		var lineItemStr = JSON.stringify(prodList[i]);
		$("#itemContainer").find("#title").text(prodList[i].name);
		$("#itemContainer").find("#tnImage").attr("src","/gamezone/resources/images/product/_tn/"+prodList[i].imageUrl+prodList[i].imgExt);
		$("#itemContainer").find("#addToCartBtn").attr("href","javascript:addToCart("+lineItemStr+")");
		$("#itemContainer").find("#offerOptions>input[type=radio]").attr("name","productTerm-"+prodList[i].id);

		// attach price ids
		$("#itemContainer").find("#offerOptions>#monthly").text("$"+prodList[i].offers[0].prices[0].amount.toFixed(2));
		$("#itemContainer").find("#offerOptions>#monthly").attr("id","offer-price-"+prodList[i].offers[0].id+"-"+prodList[i].offers[0].prices[0].id);
		$("#itemContainer").find("#offerOptions>#yearly").text("$"+prodList[i].offers[1].prices[0].amount.toFixed(2));
		$("#itemContainer").find("#offerOptions>#yearly").attr("id","offer-price-"+prodList[i].offers[1].id+"-"+prodList[i].offers[1].prices[0].id);

		// attach offer ids		
		$("#itemContainer").find("#offerOptions>input[value=M]").attr("id","offer-"+prodList[i].offers[0].id);
		$("#itemContainer").find("#offerOptions>input[value=Y]").attr("id","offer-"+prodList[i].offers[1].id);
		
		var html = '';
		html = html + '<inut type="hidden" name="prodName"'+i+'value='+prodList[i].name+'/>';
		html = html + '<inut type="hidden" name="console"'+i+'value='+prodList[i].console+'/>';
		html = html + '<inut type="hidden" name="genre"'+i+'value='+prodList[i].genre+'/>';
		html = html + '<inut type="hidden" name="prodUrl"'+i+'value='+prodList[i].imageUrl+'/>';
		html = html + '<inut type="hidden" name="prodExt"'+i+'value='+prodList[i].imgExt+'/>';
		$("#itemContainer").find("#prodDetails").html(html);
		$("#products").append($("#itemContainer").html());
	}  	
 </script>

</html>

