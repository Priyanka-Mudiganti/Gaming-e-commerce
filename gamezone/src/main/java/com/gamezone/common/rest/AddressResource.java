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
import com.gamezone.common.dao.base.IAddressDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.Address;
import com.google.gson.Gson;

@Controller
@Transactional
@RequestMapping("/address")
public class AddressResource {
	
	Gson gson = new Gson();
	
	@Autowired
	GenericError error;
	
    @Autowired
    private IAddressDao dao;
	
    /**
     * @param requestBody
     * @return
     * @throws IOException
     * @throws SQLException
     * 
     * {
	 *	  "addr1": "addr1",
	 *	  "addr2":"addr2",
	 *	  "city":"San Francisco",
	 *	  "state":"CA",
	 *	  "zip":"94105",
	 *	  "country":"US",
	 *	  "isShipping":false
	 *	}
     */
	@RequestMapping(value = "/billing/add", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object addBillingAddress(@RequestBody String requestBody) throws IOException, SQLException{
		try{
			Address billingAddress = gson.fromJson(requestBody, Address.class);
			billingAddress.setShipping(false);
			dao.create(billingAddress);
			billingAddress = dao.findById(billingAddress.getId());
			return new Response(billingAddress);
		}catch(Exception e){
			//log the exception
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return new Response(error);
		}
	}
}
