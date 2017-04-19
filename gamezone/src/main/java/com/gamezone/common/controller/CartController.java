package com.gamezone.common.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

@Controller
public class CartController {
	
	Gson gson = new Gson();
	
	@RequestMapping(value="/showCart")
	public ModelAndView showLogin() throws IOException{
		return new ModelAndView("cart");
	}
	
	@RequestMapping(value="/addToCart", method = RequestMethod.POST)
	public ModelAndView addToCart(@RequestParam String name, @RequestParam String password) throws IOException{
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/checkout/{isUserLoggedIn}")
	public ModelAndView checkout(@PathVariable boolean isUserLoggedIn) throws IOException{
		if(isUserLoggedIn){
			return new ModelAndView("payment");
		}
		return new ModelAndView("login");
	}
}
