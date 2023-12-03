package com.ecommerce.wishlist.controller;

import com.ecommerce.wishlist.dto.ProductDto;
import com.ecommerce.wishlist.dto.WishlistDto;
import com.ecommerce.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/wishlist")
public class WishlistController {
    private final WishlistService wishlistService;

    @GetMapping("/{userId}")
    public WishlistDto getWishlist(@PathVariable UUID userId) {
        return wishlistService.getWishlist(userId);
    }

    @GetMapping("/{userId}/products")
    public List<ProductDto> getWishlistProductDetails(@PathVariable UUID userId) {
        return wishlistService.getWishlistProductDetails(userId);
    }

    @PostMapping("/{userId}/addProduct/{productId}")
    public WishlistDto addProductToWishlist(@PathVariable UUID userId, @PathVariable UUID productId) {
        return wishlistService.addProductToWishlist(userId, productId);
    }

    @DeleteMapping("/{userId}/removeProduct/{productId}")
    public WishlistDto removeProductFromWishlist(@PathVariable UUID userId, @PathVariable UUID productId) {
        return wishlistService.removeProductFromWishlist(userId, productId);
    }
}
