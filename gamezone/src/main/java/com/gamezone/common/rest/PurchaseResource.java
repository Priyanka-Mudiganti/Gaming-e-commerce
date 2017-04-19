package com.gamezone.common.rest;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamezone.common.client.Response;
import com.gamezone.common.constants.ErrorConstants;
import com.gamezone.common.dao.base.IPurchaseDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.FulfillmentStatus;
import com.gamezone.common.model.OrderState;
import com.gamezone.common.model.PurchaseOrder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Transactional
@RequestMapping("/purchase")
public class PurchaseResource {
	
	Gson gson = new GsonBuilder()
			   .setDateFormat("MM/dd/YYYY").create();
	
	@Autowired
	GenericError error;
	
    @Autowired
    private IPurchaseDao dao;
	

    /**
     * 
     * @param requestBody
     * @return
     * @throws IOException
     * @throws SQLException
     * 
     * {
		  "buyerId": "1003",
		  "fulfillmentStatus":"PENDING",
		  "orderState":"PENDING",
		  "created":"04/08/2017",
		  "lastModified":"04/08/2017",
		  "paymentProfileId":4,
		  "amount":"100.00",
		  "orderType":"NEW",
		    "lineItems":[{
		             "priceId":"1001",
		             "productId":"1001",
		             "offerId":"1001",
		             "quantity":2
		        },
		        {
		             "priceId":"1002",
		             "productId":"1001",
		             "offerId":"1002",
		             "quantity":2
		        }
		      ]
		}
     * 
     */
	@RequestMapping(value = "/placeOrder", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object placeOrder(@RequestBody String requestBody) throws IOException, SQLException{
		try{
			PurchaseOrder purchaseOrder = gson.fromJson(requestBody, PurchaseOrder.class);
			//default to pending until the payment is successful
			purchaseOrder.setFulfillmentStatus(FulfillmentStatus.PENDING);
			purchaseOrder.setOrderState(OrderState.PENDING);
			dao.create(purchaseOrder);
			return new Response(purchaseOrder);
		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
	
	@RequestMapping(value = "/fulfill/{poId}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object fulfillOrder(@PathVariable Long poId) throws IOException, SQLException{
		try{
			PurchaseOrder purchaseOrder = dao.findById(poId);
			purchaseOrder.setFulfillmentStatus(FulfillmentStatus.FULFILLED);
			purchaseOrder.setOrderState(OrderState.CHARGED);
			return new Response(dao.update(purchaseOrder));
		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
	
	@RequestMapping(value = "/cancel/{poId}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object declineOrder(@PathVariable Long poId) throws IOException, SQLException{
		try{
			PurchaseOrder purchaseOrder = dao.findById(poId);
			purchaseOrder.setFulfillmentStatus(FulfillmentStatus.FULFILLED);
			purchaseOrder.setOrderState(OrderState.DECLINED);
			return new Response(dao.update(purchaseOrder));
		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
}
