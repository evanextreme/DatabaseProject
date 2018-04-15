package com.bobby.tables.RetailStore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bobby.tables.RetailStore.models.Shipment;
import com.bobby.tables.RetailStore.repository.ShipmentDAO;
import com.bobby.tables.RetailStore.repository.StoreDAO;
import com.bobby.tables.RetailStore.repository.VendorDAO;

@Controller
public class ShipmentController {
	
	//
	// API Mapping
	//
	
	//
	// URL Mapping
	//
	
	@RequestMapping("/fulfillment")
    public String fulfillmentPage(Model model){
		model.addAttribute("orders", ShipmentDAO.getAllShipments());
        return "fulfillment";
    }
	
	@RequestMapping(value="/shipment", method = RequestMethod.PUT)
	public void updateShipment(Model model, @RequestBody String json, HttpServletRequest request, HttpServletResponse response) throws JSONException {
		//discountID is 0 if no discount is applied
		System.out.println("json: " + json);
		JSONArray objArr = new JSONArray(json);

		int shipmentNum = objArr.getInt(0);
		String placedDate = objArr.getString(1);
		String recievedDate = objArr.getString(2);
		int storeNum = objArr.getInt(3);
		int vendorNum = objArr.getInt(4);
		
		Shipment myShip = new Shipment();
		
		if(!placedDate.equals("") && recievedDate.equals("")){
			myShip = new Shipment(new DateTime(), StoreDAO.getStoreById(storeNum), VendorDAO.getVendorById(vendorNum));
		}else if(!placedDate.equals("") && !recievedDate.equals("")){
			myShip = new Shipment(new DateTime(), new DateTime(), StoreDAO.getStoreById(storeNum), VendorDAO.getVendorById(vendorNum));
		}
		
		myShip.setId(shipmentNum);
		ShipmentDAO.updateShipment(myShip);
	}
}
