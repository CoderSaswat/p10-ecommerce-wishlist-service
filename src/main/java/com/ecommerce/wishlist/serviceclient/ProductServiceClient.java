package com.ecommerce.wishlist.serviceclient;


import com.ecommerce.wishlist.dto.ProductDto;

import java.util.List;
import java.util.UUID;

public interface ProductServiceClient {
    List<ProductDto> getProductsByIds(List<UUID> productIds);
}
