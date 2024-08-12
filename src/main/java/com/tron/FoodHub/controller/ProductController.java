package com.tron.FoodHub.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tron.FoodHub.entity.Category;
import com.tron.FoodHub.entity.Product;
import com.tron.FoodHub.service.CategoryService;
import com.tron.FoodHub.service.CloudinaryService;
import com.tron.FoodHub.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CloudinaryService cloudinaryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Long id) {
		return productService.getProductById(id);	
	}
	
	@PostMapping
    public ResponseEntity<Product> createProduct(@RequestParam("name") String name,
                                                  @RequestParam("price") double price,
                                                  @RequestParam("file") MultipartFile file,
                                                  @RequestParam("categoryName") String categoryName) {
        try {
            // Upload the image to Cloudinary and get the URL
            String imageUrl = cloudinaryService.uploadFile(file);
            
         // Fetch the category by ID
            Category category = categoryService.getCategoryByName(categoryName);

            if (category == null) {
                return ResponseEntity.badRequest().body(null); // Category not found
            }

            // Create a new Product object
            Product product = new Product();
            product.setProductName(name);
            product.setProductPrice(price);
            product.setProductImage(imageUrl);
            product.setCategory(category);
            product.setCreatedOn(new Timestamp(System.currentTimeMillis())); // Or use Timestamp if needed
            // Set the category, fetch it from the service or set it manually
            // Assuming categoryService.getCategoryById(categoryId) is available
            // Category category = categoryService.getCategoryById(categoryId);
            // product.setCategory(category);

            // Save the product
            Product savedProduct = productService.saveProduct(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestParam("name") String name,
                                                 @RequestParam("createdOn") String createdOn,
                                                 @RequestParam("price") double price,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            Product existingProduct = productService.getProductById(id);
            if (existingProduct != null) {
                // Update the existing product details
                existingProduct.setProductName(name);
                existingProduct.setProductPrice(price);
                existingProduct.setCreatedOn(Timestamp.valueOf(createdOn));

                // Update image if a new file is provided
                if (file != null && !file.isEmpty()) {
                    String imageUrl = cloudinaryService.uploadFile(file);
                    existingProduct.setProductImage(imageUrl);
                }

                // Save the updated product
                Product updatedProduct = productService.updateProduct(id, existingProduct);
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
	
	@GetMapping("/categoryName/{categoryName}")
    public ResponseEntity<List<Product>> getProductsByCategoryName(@PathVariable String categoryName) {
        List<Product> products = productService.getProductsByCategoryName(categoryName);
        if (products != null && !products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build(); // No products found for this category
        }
    }
	
	@GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable Long categoryId) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        if (products != null && !products.isEmpty()) {
            return ResponseEntity.ok(products);
        } else {
            return ResponseEntity.notFound().build(); // No products found for this category
        }
    }
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
	}

}
