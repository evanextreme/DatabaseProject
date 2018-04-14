package com.bobby.tables.RetailStore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bobby.tables.RetailStore.models.Brand;
import com.bobby.tables.RetailStore.repository.BrandDAO;
import com.bobby.tables.RetailStore.repository.DiscountDAO;
import com.bobby.tables.RetailStore.repository.ProductDAO;
import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class CheckoutController {
	
	//
	// API Mapping
	//
	
	@RequestMapping(value="/api/brand", method = RequestMethod.GET, params={"brandID"})
    public @ResponseBody Brand getBrand(@RequestParam(value = "brandID") int brandID) {
        return BrandDAO.getBrandById(brandID);
    }
	
	@RequestMapping(value="/api/brand/list", method = RequestMethod.GET)
    public @ResponseBody List<Brand> getAllBrands() {
        return BrandDAO.getAllBrands();
    }
	
	//
	// URL Mapping
	//
	
	@RequestMapping(value = {"/", "/home"})
    public String home(Model model){
		model.addAttribute("items", ProductDAO.getAllProducts());
		model.addAttribute("cart", BrandDAO.getAllBrands());
		model.addAttribute("discounts", DiscountDAO.getAllDiscounts());
        return "home";
    }
	
	//checkout?discountField=0
	@RequestMapping(value="/checkout", method = RequestMethod.POST, params={"discountField"})
	public String test(Model model, @RequestBody String json, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "discountField") int discountID) {
		//discountID is 0 if no discount is applied
		System.out.println("json: " + json);
		
		model.addAttribute("items", ProductDAO.getAllProducts());
		model.addAttribute("cart", BrandDAO.getAllBrands());
		model.addAttribute("discounts", DiscountDAO.getAllDiscounts());
		return "home";
	}
}
