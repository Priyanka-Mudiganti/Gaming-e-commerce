package com.gamezone.common.rest;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamezone.common.client.Response;
import com.gamezone.common.constants.ErrorConstants;
import com.gamezone.common.dao.base.ISubscriptionDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.Subscription;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Transactional
@RequestMapping("/subscription")
public class SubscriptionResource {
	
	Gson gson = new GsonBuilder()
			   .setDateFormat("MM/dd/YYYY").create();
	
	@Autowired
	GenericError error;
	
    @Autowired
    private ISubscriptionDao dao;
	
    /**
     * 
     * @param requestBody
     * @return
     * @throws IOException
     * @throws SQLException
     * 
     * {
		  "buyerId": "1003",
		  "created":"04/08/2017",
		  "lastModified":"04/08/2017",
		  "nextBillingDate":"05/08/2017",
		  "paymentProfileId":4,
		  "amount":"100.00",
		  "purchaseOrderId":1009,
		  "productId":1001,
		  "offerId":1001,
		  "priceId":1001,  
		  "quantity":2,
		  "status":"ACTIVE",
		  "lastModified":"04/08/2017"
		}
     * 
     * 
     */
	@RequestMapping(method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object create(@RequestBody String requestBody) throws IOException, SQLException{
		try{
			Subscription subscription = gson.fromJson(requestBody, Subscription.class);
			dao.create(subscription);
			return new Response(subscription);
		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
}
