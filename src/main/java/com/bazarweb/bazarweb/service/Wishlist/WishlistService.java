package com.bazarweb.bazarweb.service.Wishlist;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bazarweb.bazarweb.model.Product.Product;
import com.bazarweb.bazarweb.model.User.User;
import com.bazarweb.bazarweb.model.Wishlist.Wishlist;
import com.bazarweb.bazarweb.model.Wishlist.WishlistItems;
import com.bazarweb.bazarweb.repository.Product.ProductRepository;
import com.bazarweb.bazarweb.repository.User.UserRepository;
import com.bazarweb.bazarweb.repository.Wishlist.WishlistRepository;
import com.bazarweb.bazarweb.service.Cart.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlistRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public Wishlist findOrCreateWishlistByEmail(String email) {
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
        
        // Ищем wishlist по userId, чтобы избежать дублирования
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserId(user.getId());
        return wishlistOptional.orElseGet(() -> createWishlist(user));  // Если wishlist нет - создаём новый
    }
    
    private Wishlist createWishlist(User user) {
        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .items(new ArrayList<>())
                .build();
        return wishlistRepository.save(wishlist);
    }

    public Wishlist addToWishlist(String email, int productId) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + productId));
        
        // Проверяем, есть ли уже такой товар в списке желаний
        boolean productExists = wishlist.getItems().stream()
                .anyMatch(item -> item.getProduct().getId() == productId);
        
        if (!productExists) {
            WishlistItems wishlistItem = WishlistItems.builder()
                    .wishlist(wishlist)
                    .product(product)
                    .build();
            
            wishlist.getItems().add(wishlistItem);
            return wishlistRepository.save(wishlist);
        }
        
        return wishlist; // Товар уже есть в списке желаний
    }

    public void removeWishlistItem(String email, int productId) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        
        wishlist.getItems().removeIf(item -> item.getProduct().getId() == productId);
        wishlistRepository.save(wishlist);
    }
    
    public Wishlist clearWishlist(String email) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        // Удаляем все элементы из списка желаний
        wishlist.getItems().clear();
        return wishlistRepository.save(wishlist);
    }

    public List<WishlistItems> getWishlistItems(String email) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        return wishlist.getItems();
    }
    
    public boolean isProductInWishlist(String email, int productId) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        return wishlist.getItems().stream()
                .anyMatch(item -> item.getProduct().getId() == productId);
    }

    public void moveToCart(String email, int productId, CartService cartService) {
        Wishlist wishlist = findOrCreateWishlistByEmail(email);
        
        // Проверяем, есть ли товар в списке желаний
        Optional<WishlistItems> wishlistItemOptional = wishlist.getItems().stream()
                .filter(item -> item.getProduct().getId() == productId)
                .findFirst();
        
        if (wishlistItemOptional.isPresent()) {
            // Добавляем товар в корзину с количеством 1
            cartService.addToCart(email, productId, 1);
            
            // Удаляем товар из списка желаний
            removeWishlistItem(email, productId);
        }
    }
}