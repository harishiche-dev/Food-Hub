package com.tron.FoodHub.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tron.FoodHub.entity.Cart;
import com.tron.FoodHub.repo.CartRepository;

@Service
public class CartService {
	
	@Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(long cartId, Cart cartDetails) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.setProductId(cartDetails.getProductId());
        cart.setProductName(cartDetails.getProductName());
        cart.setProductPrice(cartDetails.getProductPrice());
        cart.setProductImage(cartDetails.getProductImage());
        cart.setQuantity(cartDetails.getQuantity());
        cart.setSubTotal(cartDetails.getSubTotal());
        cart.setDiscountPercentage(cartDetails.getDiscountPercentage());
        cart.setDiscountAmount(cartDetails.getDiscountAmount());
        cart.setNetBill(cartDetails.getNetBill());
        cart.setOrderNo(cartDetails.getOrderNo());
        cart.setStatus(cartDetails.getStatus());
        cart.setCreatedOn(cartDetails.getCreatedOn());
        cart.setCustomer(cartDetails.getCustomer());
        cart.setProducts(cartDetails.getProducts());

        return cartRepository.save(cart);
    }

    public Cart getCartById(long cartId) {
        return cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public void deleteCart(long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }
}
