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
import com.gamezone.common.dao.base.ILoginDao;
import com.gamezone.common.error.GenericError;
import com.gamezone.common.model.User;
import com.google.gson.Gson;

@Controller
@Transactional
public class LoginResource {
	
	Gson gson = new Gson();
	
	@Autowired
	GenericError error;
	
    @Autowired
    private ILoginDao dao;
	
	@RequestMapping(value = "/login/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object findUser(@PathVariable String name) throws IOException, SQLException{
		try{
			User user = dao.findByName(name);
			if(user == null){
				error.setErrCode(ErrorConstants.USER_BY_NAME_ERR_CODE);
				error.setErrMsg(ErrorConstants.USER_NOT_FOUND+name);
				return new Response(error);
			}
			return new Response(user);
		}catch (Exception e){
			//log the exception
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return new Response(error);
		}
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody 
	public Object createUser(@RequestBody String requestBody) throws IOException, SQLException{
		try{
			User user = gson.fromJson(requestBody, User.class);
			dao.create(user);
			user = dao.findByName(user.getName());
			return new Response(user);
		}catch(Exception e){
			//log the exception
			error.setErrCode(ErrorConstants.INTERNAL_SERVER_ERROR);
			error.setErrMsg(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
			return new Response(error);
		}
	}
}
