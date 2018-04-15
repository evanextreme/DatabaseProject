package com.bobby.tables.RetailStore.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bobby.tables.RetailStore.models.Brand;
import com.bobby.tables.RetailStore.models.Store;
import com.bobby.tables.RetailStore.models.Transaction;
import com.bobby.tables.RetailStore.models.TransactionProduct;
import com.bobby.tables.RetailStore.repository.BrandDAO;
import com.bobby.tables.RetailStore.repository.CustomerDAO;
import com.bobby.tables.RetailStore.repository.DiscountDAO;
import com.bobby.tables.RetailStore.repository.ProductDAO;
import com.bobby.tables.RetailStore.repository.StoreDAO;
import com.bobby.tables.RetailStore.repository.TransactionDAO;
import com.bobby.tables.RetailStore.repository.TransactionProductDAO;

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
		model.addAttribute("store", StoreDAO.getStoreById(1));
        return "home";
    }
	
	//checkout?discountField=0
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public void test(Model model, @RequestBody String json, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		//discountID is 0 if no discount is applied
		json = json.replace("[", "");
		json = json.replace("]", "");
		System.out.println("json: " + json);

		JSONObject obj = new JSONObject(json);
		
		int productID = obj.getInt("productID");
		int quantityOfItem = obj.getInt("quantityOfItem");
		double total = obj.getDouble("total");
		
		Store store = new Store();
		JSONObject storeObj = obj.getJSONObject("store");
		store.setId(storeObj.getInt("id"));
		store.setPhoneNumber(storeObj.getString("phoneNumber"));
		store.setAddress(storeObj.getString("address"));
		store.setEmail(storeObj.getString("email"));
		
		Transaction myTrans = new Transaction();
		myTrans.setTotal(total);
		myTrans.setDate(new DateTime());
		myTrans.setCustomer(CustomerDAO.getCustomerById(1));
		myTrans.setStore(store);
		
		TransactionDAO.addTransaction(myTrans);
		System.out.println("transactionID: " + myTrans.getId());
		TransactionProduct myTranProd = new TransactionProduct(myTrans, ProductDAO.getProductById(productID), quantityOfItem);
		TransactionProductDAO.addTransactionProduct(myTranProd);
		System.out.println("should be the same: " + myTranProd.getTransaction().getId());
		
		//DO NOT DELETE: code to do a direct mapping to an object
//		json = json.replace("[", "");
//		json = json.replace("]", "");
//		System.out.println("json: " + json);
//
//		ObjectMapper mapper = new ObjectMapper();
//		Product myProd = mapper.readValue(json, Product.class);
//		
//		System.out.println("Product- name: " + myProd.getName() + " qty: " + myProd.getQuantityInStore() + " vendor: " + myProd.getVendor().getName());
//		ProductDAO.addProduct(myProd);
		
		model.addAttribute("items", ProductDAO.getAllProducts());
		model.addAttribute("cart", BrandDAO.getAllBrands());
		model.addAttribute("discounts", DiscountDAO.getAllDiscounts());
	}
}
