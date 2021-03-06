package com.hendisantika.resource;

import io.quarkus.mailer.MailTemplate;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.mutiny.Uni;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 25/10/21
 * Time: 14.25
 */
@Path("/type-safe")
public class TypeSafeMailResource {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<Response> greeting(
            @Valid @Email @QueryParam("email") String email,
            @Valid @NotBlank @QueryParam("name") String name) {
        return Templates.hello(name)
                .to(email)
                .subject("Ahoy " + name + "!")
                .send()
                .map(x -> Response.accepted().build());
    }

    @CheckedTemplate
    static class Templates {
        public static native MailTemplate.MailTemplateInstance hello(String name);
    }
}
