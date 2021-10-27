package com.hendisantika.repository;

import com.hendisantika.entity.ShoppingCart;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/10/21
 * Time: 13.27
 */
@ApplicationScoped
public class ShoppingCartRepository implements PanacheRepository<ShoppingCart> {
}
