package com.hendisantika.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/10/21
 * Time: 07.12
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@Getter
@Table(name = "ShoppingCartItem", schema = "product", indexes = {
        @Index(name = "shopping_cart_item_cart_product_index", columnList = "cart_id, product_id"),
})
public class ShoppingCartItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbTransient
    public Long id;

    @Column(name = "total_price", precision = 21, scale = 2)
    public BigDecimal totalPrice;

    @Column(name = "quantity")
    public Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonbTransient
    public ShoppingCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Product product;
}
