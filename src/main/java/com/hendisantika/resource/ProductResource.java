package com.hendisantika.resource;

import com.hendisantika.entity.Product;
import com.hendisantika.repository.ProductRepository;

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
 * Time: 07.19
 */
@Path("/v1/products")
public class ProductResource {
    @Inject
    ProductRepository productRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return productRepository.listAll();
    }

    @GET
    @Path("{id}")
    public Product getSingleProduct(@PathParam("id") Long id) {
        return productRepository.findByProductId(id);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Product product) {
        productRepository.persist(product);
        return Response.created(URI.create("/products/" + product.id)).build();
    }

    @PUT
    @Transactional
    @Path("{id}")
    public Product update(@PathParam("id") Long id, Product product) {
        Product result = productRepository.findByProductId(id);
        if (result == null) {
            throw new WebApplicationException("Product description was not set on request.", 422);
        }
        result.title = product.title;
        result.description = product.description;
        return result;
    }

    @DELETE
    @Transactional
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        Product result = productRepository.findByProductId(id);
        if (result == null) {
            throw new WebApplicationException("Product ID was not set on request.", 422);
        }
        result.delete();
    }
}
