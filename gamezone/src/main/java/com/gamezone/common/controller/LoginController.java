package com.gamezone.common.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gamezone.common.client.RestClient;
import com.gamezone.common.client.RestClientPost;
import com.gamezone.common.constants.Constants;
import com.gamezone.common.model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class LoginController {
	
	Gson gson = new Gson();
	
	@RequestMapping(value="/showLogin")
	public ModelAndView showLogin() throws IOException{
		return new ModelAndView("login");
	}
	
	@RequestMapping(value="/loginUser", method = RequestMethod.POST)
	public ModelAndView login(@RequestParam String name, @RequestParam String password) throws IOException{
		JsonObject jsonResponse = RestClient.sendGetRequest(Constants.LOGIN_API+"/"+name); 
		ModelAndView mav = new ModelAndView();
		mav.addObject("userInfo", jsonResponse);
		if(isValidUser(jsonResponse, name, password)){
			mav.setViewName("payment");
		}else{
			mav.setViewName("login");
		}
		return mav;
	}
	
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public ModelAndView register(@RequestParam String name, 
			@RequestParam String password,
			@RequestParam String email) throws IOException{
		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		String jsonPayload = gson.toJson(user);
		
		ModelAndView mav = new ModelAndView();
		try{
			JsonObject jsonResponse = RestClientPost.sendPostRequest(Constants.REGISTER_API, jsonPayload);
			JsonObject error = (JsonObject) jsonResponse.get("error");
			mav.setViewName("payment");
			mav.addObject("userInfo", jsonResponse);	
			if(error != null){
				mav.setViewName("login");
			}
		}catch(Exception e){
			return new ModelAndView("login");
		}
		return mav;
	}
	
	private boolean isValidUser(JsonObject respJson, String name, String password){
		JsonObject userJson = (JsonObject) respJson.get("data");
		if(userJson != null){
			String userName = userJson.get("name").getAsString();
			String passWord = userJson.get("password").getAsString();
			
			if(userName.equals(name) && passWord.equals(password)){
				return true;
			}			
		}
		return false;
	}
}
