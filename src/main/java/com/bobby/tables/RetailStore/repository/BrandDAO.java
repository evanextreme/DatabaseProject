package com.bobby.tables.RetailStore.repository;

public interface BrandDAO {
	
	//-------------NOTE-----------------
	//IGNORE the code below, these are some examples of custom queries. JPA provides most of the basic ones tho so no need to stress about these until they are needed!!!
	//-------------NOTE-----------------
	
	//SELECT * FROM category WHERE category_id IS NULL
//	@Query("FROM Brand myBrand WHERE cat.id = 1")
//	public List<Brand> getAllBrands();
//	
//	@Query("FROM Category WHERE category_id = :category_id ORDER BY name ASC")//TODO sort categories and positions by 'popularity' rather than name
//	public List<Category> getChildCategories(@Param("category_id") int category_id);
//	
//	@Query("FROM Position WHERE category_id = :category_id ORDER BY name ASC")
//	public List<Position> getChildPositions(@Param("category_id") int category_id);
}
