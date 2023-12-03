package com.ecommerce.wishlist.service;


import com.ecommerce.wishlist.dto.ProductDto;
import com.ecommerce.wishlist.dto.WishlistDto;

import java.util.List;
import java.util.UUID;

public interface WishlistService {
    WishlistDto getWishlist(UUID userId);
    List<ProductDto> getWishlistProductDetails(UUID userId);
    WishlistDto addProductToWishlist(UUID userId, UUID productId);
    WishlistDto removeProductFromWishlist(UUID userId, UUID productId);
}
