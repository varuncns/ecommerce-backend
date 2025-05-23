package com.ecommerce.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import com.ecommerce.entity.*;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);
    @Transactional
    void deleteByCartId(Long cartId);
}
