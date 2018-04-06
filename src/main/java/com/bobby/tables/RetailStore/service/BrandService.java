package com.bobby.tables.RetailStore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bobby.tables.RetailStore.domain.Brand;
import com.bobby.tables.RetailStore.repository.BrandDAO;

@Service
public class BrandService {
	
	@Autowired
	private BrandDAO brandDAO;
	
	@Transactional(readOnly = true)
	public Brand getBrand(int id){
		Optional<Brand> optBrand = brandDAO.findById(id);
		if(optBrand.isPresent()){
			return optBrand.get();
		}else{
			return null;
		}
	}
	
	@Transactional(readOnly = true)
	public List<Brand> getAllBrands(){
		return brandDAO.findAll();
	}
	
	@Transactional(readOnly = false)
	public Brand createCategory(Brand myBrand){
		return brandDAO.saveAndFlush(myBrand);
	}
	
	@Transactional(readOnly = false)
	public Brand deleteBrand(int brandID){
		Brand myCat = getBrand(brandID);
		brandDAO.deleteById(brandID);
		return myCat;
	}
}
