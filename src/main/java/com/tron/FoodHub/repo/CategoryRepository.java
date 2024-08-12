package com.tron.FoodHub.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tron.FoodHub.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	Category findByCategoryName(String categoryName);
}
