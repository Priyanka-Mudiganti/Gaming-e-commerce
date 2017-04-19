package com.gamezone.common.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.gamezone.common.dao.base.IPaymentProfileDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.PaymentProfile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Controller
@Transactional
@RequestMapping("/paymentProfile")
public class PaymentProfileResource {
	
	Gson gson = new GsonBuilder()
			   .setDateFormat("MM/dd/YYYY").create();
	
	@Autowired
	GenericError error;
	
    @Autowired
    private IPaymentProfileDao dao;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object getAllPaymentProfiles() throws IOException, SQLException{
		List<PaymentProfile> paymentProfiles = dao.findAll();
		return new Response(paymentProfiles);
	}
	
	/**
	 * @param requestBody
	 * @return
	 * @throws IOException
	 * @throws SQLException
	 * 
	 * Credit Card request
		{
		  "name": "CitiCreditCard",
		  "userId":"1003",
		  "paymentMethod":"VISA",
		  "last4Digits":"4325",
		  "expirationDate":"10/15/2017",
		  "billingAddressId":"1008"
		}
	 *
	 *paypal request
		{
		  "name": "Paypal",
		  "userId":"1003",
		  "paymentMethod":"PAYPAL",
		  "expirationDate":"10/15/2017",
		  "billingAddressId":"1008",
		  "token":"1239985221"
		}
	 *
	 */
	
	@RequestMapping(value = "/add", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object addPaymentProfile(@RequestBody String requestBody) throws IOException, SQLException{
		try{
			PaymentProfile paymentProfile = gson.fromJson(requestBody, PaymentProfile.class);
			dao.create(paymentProfile);
			paymentProfile = dao.findById(paymentProfile.getId());
			return new Response(paymentProfile);
		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
}
