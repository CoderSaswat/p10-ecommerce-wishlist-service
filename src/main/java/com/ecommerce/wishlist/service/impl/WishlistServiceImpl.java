package com.ecommerce.wishlist.service.impl;

import com.ecommerce.wishlist.dto.ProductDto;
import com.ecommerce.wishlist.dto.WishlistDto;
import com.ecommerce.wishlist.exception.BusinessException;
import com.ecommerce.wishlist.model.Wishlist;
import com.ecommerce.wishlist.repository.WishlistRepository;
import com.ecommerce.wishlist.service.WishlistService;
import com.ecommerce.wishlist.serviceclient.ProductServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {
    private final ModelMapper modelMapper;
    private final WishlistRepository wishlistRepository;
    private final ProductServiceClient productServiceClient;

    @Override
    public WishlistDto getWishlist(UUID userId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserId(userId);
        if(wishlistOptional.isEmpty()){
            return null;
        }
        return modelMapper.map(wishlistOptional.get(), WishlistDto.class);
    }

    @Override
    public List<ProductDto> getWishlistProductDetails(UUID userId) {
        Wishlist wishlist = getOrCreateWishlist(userId);
        List<UUID> productIds = wishlist.getProductIds();
        return productServiceClient.getProductsByIds(productIds);
    }

    @Override
    public WishlistDto addProductToWishlist(UUID userId, UUID productId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserId(userId);
        Wishlist wishlist;
        if(wishlistOptional.isPresent()){
            wishlist = wishlistOptional.get();
            if( wishlist.getProductIds().contains(productId)){
                throw new BusinessException("item is already wishlisted");
            }
        }else{
            wishlist = new Wishlist();
            wishlist.setUserId(userId);
            wishlist.setId(UUID.randomUUID());
        }
        wishlist.getProductIds().add(productId);
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return modelMapper.map(savedWishlist, WishlistDto.class);
    }

    @Override
    public WishlistDto removeProductFromWishlist(UUID userId, UUID productId) {
        Wishlist wishlist = getOrCreateWishlist(userId);
        boolean removed = wishlist.getProductIds().remove(productId);
        if(!removed){
            throw new BusinessException("no such wishlisted product to remove");
        }
        Wishlist savedWishlist = wishlistRepository.save(wishlist);
        return modelMapper.map(savedWishlist, WishlistDto.class);
    }

    // Helper method to get or create a wishlist for a user
    private Wishlist getOrCreateWishlist(UUID userId) {
        Optional<Wishlist> wishlistOptional = wishlistRepository.findByUserId(userId);
        if(wishlistOptional.isEmpty()) {
            Wishlist wishlist = new Wishlist();
            wishlist.setUserId(userId);
            wishlist.setId(UUID.randomUUID());
            return wishlist;
        }
        return wishlistOptional.get();
    }

    // Helper method to get product details (simulated)
    private ProductDto getProductDetails(UUID productId) {
        // Simulated method to fetch product details from another service or repository
        // In a real-world scenario, you would fetch this information from a product service or repository
        ProductDto productDto = new ProductDto();
        productDto.setId(productId);
        productDto.setName("Product Name"); // Simulated name
        productDto.setPrice(29.99); // Simulated price
        productDto.setAvailableQuantity(100); // Simulated available quantity
        // Other simulated product details
        return productDto;
    }
}
