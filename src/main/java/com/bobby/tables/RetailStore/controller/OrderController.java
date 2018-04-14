package com.bobby.tables.RetailStore.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bobby.tables.RetailStore.models.Brand;
import com.bobby.tables.RetailStore.repository.BrandDAO;
import com.bobby.tables.RetailStore.repository.ProductDAO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class OrderController {
	
	//
	// API Mapping
	//
	
	//
	// URL Mapping
	//
	
	@RequestMapping("/order")
    public String orderPage(Model model) throws JsonProcessingException {
        return "order";
    }
}
