package com.hendisantika.resource;

import com.hendisantika.entity.ShoppingCart;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/10/21
 * Time: 07.25
 */
@Path("/v1/carts")
public class ShoppingCartResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getCarts() {
        return ShoppingCart.getAllShoppingCarts()
                .onItem().transform(shoppingcarts -> Response.ok(shoppingcarts))
                .onItem().transform(Response.ResponseBuilder::build);
    }
}
