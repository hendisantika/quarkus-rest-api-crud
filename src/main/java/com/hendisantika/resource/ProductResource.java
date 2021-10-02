package com.hendisantika.resource;

import com.hendisantika.entity.Product;
import io.smallrye.mutiny.Uni;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.Status.OK;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/10/21
 * Time: 07.19
 */
@Path("/v1/products")
public class ProductResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getProducts() {
        return Product.getAllProducts()
                .onItem().transform(products -> Response.ok(products))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("{id}")
    public Uni<Response> getSingleProduct(@PathParam("id") Long id) {
        return Product.findByProductId(id)
                .onItem().ifNotNull().transform(product -> Response.ok(product).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> add(Product product) {
        return Product.addProduct(product)
                .onItem().transform(id -> URI.create("/v1/products/" + id.id))
                .onItem().transform(uri -> Response.created(uri))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("{id}")
    public Uni<Response> update(@PathParam("id") Long id, Product product) {
        if (product == null || product.description == null) {
            throw new WebApplicationException("Product description was not set on request.", 422);
        }
        return Product.updateProduct(id, product)
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }

    @DELETE
    @Path("{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return Product.deleteProduct(id)
                .onItem().transform(entity -> !entity ? Response.serverError().status(NOT_FOUND).build()
                        : Response.ok().status(OK).build());
    }
}
