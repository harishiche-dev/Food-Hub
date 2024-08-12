package com.tron.FoodHub.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tron.FoodHub.entity.Category;
import com.tron.FoodHub.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	List<Product> findByCategory_categoryId(Long categoryId); 
	
	List<Product> findByCategory(Category category);
}
