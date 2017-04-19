package com.gamezone.common.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

@Controller
public class OrderController {
	
	Gson gson = new Gson();
	
	@RequestMapping(value="/showOrderSummary")
	public ModelAndView showOrderSummary() throws IOException{
		return new ModelAndView("orderSummary");
	}
	
	@RequestMapping(value="/submitPurchaseOrder")
	public ModelAndView submitPurchaseOrder(@RequestParam String cart) throws IOException{
		JsonArray array = new JsonArray();
		array = gson.fromJson(cart, JsonArray.class);
		
		System.out.println(array.size());
		
		return new ModelAndView("orderConfirmation");
	}
}
