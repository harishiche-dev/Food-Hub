package com.tron.FoodHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tron.FoodHub.entity.Category;
import com.tron.FoodHub.entity.Product;
import com.tron.FoodHub.repo.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
		}
	
	public Product getProductById(Long id) {
		return productRepository.findById(id).orElse(null);		
	}
	
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
	
	public Product updateProduct(Long id, Product product) {
		Product existingProduct = getProductById(id);
		if(existingProduct != null) {
			existingProduct.setCategory(product.getCategory());
			existingProduct.setProductName(product.getProductName());
			existingProduct.setProductPrice(product.getProductPrice());
			existingProduct.setProductImage(product.getProductImage());
			existingProduct.setCreatedOn(product.getCreatedOn());
			return productRepository.save(existingProduct);
		}
		return null;
	}
	
	public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findByCategory_categoryId(categoryId); // Custom method
    }
	
	public List<Product> getProductsByCategoryName(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);
        if (category != null) {
            return productRepository.findByCategory(category);
        }
        return null;
    }
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}

}
