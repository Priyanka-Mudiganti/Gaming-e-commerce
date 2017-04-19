package com.gamezone.common.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gamezone.common.client.RestClient;
import com.gamezone.common.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class ProductController {
	Gson gson = new Gson();
			
	@RequestMapping(value="/store")
	public ModelAndView loadProducts() throws IOException{
		JsonObject jsonResponse = RestClient.sendGetRequest(Constants.PRODUCTS_API);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products");
		mav.addObject("prodList", jsonResponse);
		return mav;
	}
}
