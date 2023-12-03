package com.ecommerce.wishlist.repository;

import com.ecommerce.wishlist.model.Wishlist;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WishlistRepository extends MongoRepository<Wishlist, UUID> {
    Optional<Wishlist> findByUserId(UUID userId);
}
