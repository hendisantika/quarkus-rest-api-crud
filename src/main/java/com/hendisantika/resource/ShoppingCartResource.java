package com.hendisantika.resource;

import com.hendisantika.entity.ShoppingCart;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

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

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getSingleCart(@PathParam("id") Long id) {
        return ShoppingCart.findByShoppingCartId(id)
                .onItem().ifNotNull().transform(cart -> Response.ok(cart).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);

    }
}
