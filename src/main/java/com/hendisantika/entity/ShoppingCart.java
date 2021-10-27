package com.hendisantika.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/10/21
 * Time: 07.05
 */
@Entity
@Table(name = "ShoppingCart", schema = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQueries(value = {@NamedQuery(name = "ShoppingCart.findAll",
        query = "SELECT c FROM ShoppingCart c LEFT JOIN FETCH c.cartItems item LEFT JOIN FETCH item.product"),
        @NamedQuery(name = "ShoppingCart.getById",
                query = "SELECT c FROM ShoppingCart c LEFT JOIN FETCH c.cartItems item LEFT JOIN FETCH item.product " +
                        "where c.id = ?1")})
public class ShoppingCart extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int cartTotal;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn
    public Set<ShoppingCartItem> cartItems;

    public String name;

}
