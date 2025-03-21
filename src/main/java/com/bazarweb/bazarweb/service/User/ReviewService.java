package com.bazarweb.bazarweb.service.User;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bazarweb.bazarweb.model.Product.Product;
import com.bazarweb.bazarweb.model.User.Review;
import com.bazarweb.bazarweb.model.User.User;
import com.bazarweb.bazarweb.repository.Product.ProductRepository;
import com.bazarweb.bazarweb.repository.Product.ReviewRepository;
import com.bazarweb.bazarweb.repository.User.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Review> getReviewsByProduct(int productId) {
        return reviewRepository.findByProductId(productId);
    }

    public Review addReview(int productId, int userId, int rating, String comment) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Review review = Review.builder()
                .product(product)
                .user(user)
                .rating(rating)
                .comment(comment)
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByProductId(int productId) {
        return reviewRepository.findByProductId(productId);
    }

    public double calculateAverageRating(int productId) {
        List<Review> reviews = getReviewsByProductId(productId);
        return reviews.stream()
            .mapToDouble(Review::getRating)
            .average()
            .orElse(0.0);
    }
}
