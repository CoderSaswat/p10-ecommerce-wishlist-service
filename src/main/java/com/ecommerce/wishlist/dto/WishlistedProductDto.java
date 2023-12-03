package com.ecommerce.wishlist.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WishlistedProductDto {
    private UUID userId;
    private List<ProductDto> products;
}