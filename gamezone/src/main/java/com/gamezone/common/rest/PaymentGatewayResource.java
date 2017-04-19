package com.gamezone.common.rest;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gamezone.common.client.Response;
import com.gamezone.common.constants.ErrorConstants;
import com.gamezone.common.dao.base.IPaymentProfileDao;
import com.gamezone.common.dao.base.IPurchaseDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.PaymentProfile;
import com.gamezone.common.model.PurchaseOrder;

@Controller
@Transactional
@RequestMapping("/paymentGateway")
public class PaymentGatewayResource {
	
	@Autowired
	GenericError error;
	
    @Autowired
    private IPaymentProfileDao paymentProfileDao;
 
    @Autowired
    private IPurchaseDao purchaseDao;
    
    /**
     * 
     * @param orderId
     * @param paymentProfileId
     * @return
     * @throws IOException
     * @throws SQLException
     * 
     * submit/1009/4
     */
	@RequestMapping(value = "/submit/{orderId}/{paymentProfileId}", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object submitPaymentToGateway(@PathVariable Long orderId, @PathVariable Long paymentProfileId) throws IOException, SQLException{
		try{
			
			PaymentProfile pp = paymentProfileDao.findById(paymentProfileId);
			PurchaseOrder po = purchaseDao.findById(orderId);
			
			boolean isValidPayment = (pp!=null) && (po!=null) && (po.getPaymentProfileId()!=null) && (pp.getId().longValue() == po.getPaymentProfileId().longValue());
			
			if( isValidPayment){
				return new Response("{\"validPayment\":true, \"transactionState\":\"SUCCESS\", \"transactionId\":"+orderId+"}");
			}else{
				return new Response("{\"validPayment\":false, \"transactionState\":\"FAILED\", \"transactionId\":"+orderId+"}");
			}

		}catch(Exception e){
			//log the exception
			e.printStackTrace();
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+e.getMessage());
			return new Response(error);
		}
	}
}
