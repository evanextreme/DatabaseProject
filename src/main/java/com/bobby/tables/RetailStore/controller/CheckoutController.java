package com.bobby.tables.RetailStore.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONArray;
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
		model.addAttribute("discounts", DiscountDAO.getAllDiscounts());
        return "home";
    }
	
	//checkout?discountField=0
	@RequestMapping(value="/checkout", method = RequestMethod.POST)
	public void checkoutCart(Model model, @RequestBody String json, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		//discountID is 0 if no discount is applied
		System.out.println("json: " + json);
		JSONArray objArr = new JSONArray(json);

		int storeNum = objArr.getInt(0);
		int custNum = objArr.getInt(1);
		double total = objArr.getDouble(2);
		int discountNum = objArr.getInt(3);
		
		Store store = StoreDAO.getStoreById(storeNum);
		
		Transaction myTrans = new Transaction();
		myTrans.setTotal(total);
		myTrans.setDate(new DateTime());
		myTrans.setCustomer(CustomerDAO.getCustomerById(custNum));
		myTrans.setStore(store);
		
		if(discountNum != 0){
			myTrans.setDiscount(DiscountDAO.getDiscountById(discountNum));
		}
		
		TransactionDAO.addTransaction(myTrans);
		myTrans = TransactionDAO.getNewestTransaction();
		
		JSONArray transactionArr = objArr.getJSONArray(4);
		for(int i=0; i<transactionArr.length(); i++){
			JSONObject obj = transactionArr.getJSONObject(i);
			
			int productID = obj.getInt("productID");
			int quantityOfItem = obj.getInt("quantityOfItem");
			
			TransactionProduct myTranProd = new TransactionProduct(TransactionDAO.getTransactionById(myTrans.getId()), ProductDAO.getProductById(productID), quantityOfItem);
			TransactionProductDAO.addTransactionProduct(myTranProd);
		}
		
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
	}
	
	@RequestMapping(value="/return", method = RequestMethod.POST)
	public void returnCart(Model model, @RequestBody String json, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		//discountID is 0 if no discount is applied
		System.out.println("json: " + json);
		JSONArray objArr = new JSONArray(json);

		int storeNum = objArr.getInt(0);
		int custNum = objArr.getInt(1);
		double total = objArr.getDouble(2);
		int discountNum = objArr.getInt(3);
		int transactionNum = objArr.getInt(4);
		
		Store store = StoreDAO.getStoreById(storeNum);
		
		Transaction myTrans = new Transaction();
		myTrans.setTotal(total);
		myTrans.setDate(new DateTime());
		myTrans.setCustomer(CustomerDAO.getCustomerById(custNum));
		myTrans.setStore(store);
		myTrans.setOriginalTransaction(TransactionDAO.getTransactionById(transactionNum));
		
		if(discountNum != 0){
			myTrans.setDiscount(DiscountDAO.getDiscountById(discountNum));
		}
		
		TransactionDAO.addTransaction(myTrans);
		myTrans = TransactionDAO.getNewestTransaction();
		
		JSONArray transactionArr = objArr.getJSONArray(5);
		for(int i=0; i<transactionArr.length(); i++){
			JSONObject obj = transactionArr.getJSONObject(i);
			
			int productID = obj.getInt("productID");
			int quantityOfItem = obj.getInt("quantityOfItem");
			
			TransactionProduct myTranProd = new TransactionProduct(TransactionDAO.getTransactionById(myTrans.getId()), ProductDAO.getProductById(productID), quantityOfItem);
			TransactionProductDAO.addTransactionProduct(myTranProd);
		}
	}
}
