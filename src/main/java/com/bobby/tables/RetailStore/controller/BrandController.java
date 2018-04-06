package com.bobby.tables.RetailStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bobby.tables.RetailStore.domain.Brand;
import com.bobby.tables.RetailStore.service.BrandService;

@Controller
public class BrandController {
	
	@Autowired
	private BrandService brandSvc;
	
	//
	// API Mapping
	//
	
	@RequestMapping(value="/api/brand", method = RequestMethod.GET, params={"brandID"})
    public @ResponseBody Brand getBrand(@RequestParam(value = "brandID") int brandID) {
		
        return brandSvc.getBrand(brandID);
    }
	
	@RequestMapping(value="/api/brand/list", method = RequestMethod.GET)
    public @ResponseBody List<Brand> getAllBrands() {
		
        return brandSvc.getAllBrands();
    }
	
	//
	// URL Mapping
	//
	
	@RequestMapping("/")
    public String home(Model model) {
		model.addAttribute("brands", getAllBrands());
        return "home";
    }
	
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView model) {
		return new ModelAndView("test");
	}
	
	@RequestMapping("/testing")
	public String testing(Model model) {
		return "testing";
	}
}
