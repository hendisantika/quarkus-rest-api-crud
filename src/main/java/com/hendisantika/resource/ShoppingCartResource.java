package com.hendisantika.resource;

import com.hendisantika.entity.Product;
import com.hendisantika.entity.ShoppingCart;
import com.hendisantika.repository.ProductRepository;
import com.hendisantika.repository.ShoppingCartRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

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
    @Inject
    ShoppingCartRepository shoppingCartRepository;

    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ShoppingCart> getCarts() {
        return ShoppingCart.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingCart getSingleCart(@PathParam("id") Long id) {
        return ShoppingCart.findById(id);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart == null || shoppingCart.name == null) {
            throw new WebApplicationException("ShoppingCart name was not set on request.", 422);
        }
        shoppingCartRepository.persist(shoppingCart);
        return Response.created(URI.create("/carts/" + shoppingCart.id)).build();
    }

    @PUT
    @Path("{cartid}/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public ShoppingCart update(@PathParam("cartid") Long id, @PathParam("productid") Long product) {
        ShoppingCart result = shoppingCartRepository.findById(id);
        Product product1 = productRepository.findById(product);

        if (result == null || product1 == null) {
            throw new WebApplicationException("Shopping Cart or Product description was not set on request.", 422);
        }
        return result;
    }

    @DELETE
    @Path("{cartid}/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("cartid") Long id, @PathParam("productid") Long product) {
        ShoppingCart result = shoppingCartRepository.findById(id);
        if (result == null) {
            throw new WebApplicationException("Product ID was not set on request.", 422);
        }
        result.delete();
    }
}
