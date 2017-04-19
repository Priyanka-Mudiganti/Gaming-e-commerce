package com.gamezone.common.rest;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

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
import com.gamezone.common.dao.base.IProductDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.Product;

@Controller
@Transactional
@RequestMapping("/products")
public class ProductResource {
	
	@Autowired
	GenericError error;
	
    @Autowired
    private IProductDao dao;
	
	@RequestMapping(method = RequestMethod.GET,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object getAllProducts() throws IOException, SQLException{
		List<Product> offerings = dao.findAll();
		return new Response(offerings);
	}

	@RequestMapping(value = {"/{id}"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object getProductById(@PathVariable Long id) throws IOException, SQLException{
		try{
			Product offering = dao.findById(id);
			if(offering == null){
				error.setErrCode(ErrorConstants.OFFERING_BY_ID_ERR_CODE);
				error.setErrMsg(ErrorConstants.OFFERING_ERROR_MSG+id);
				return new Response(error);
			}
			return new Response(offering);
		}catch (Exception e){
			//log the exception
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return new Response(error);
		}
	}
}
