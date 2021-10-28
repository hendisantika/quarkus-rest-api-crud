package com.hendisantika.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 28/10/21
 * Time: 14.22
 */

@Path("/hello")
public class ExampleResource {
    @GET
    public String hello() {
        return "Hello RESTEasy";
    }

}
