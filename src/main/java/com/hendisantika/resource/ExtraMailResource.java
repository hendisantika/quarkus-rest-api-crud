package com.hendisantika.resource;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.smallrye.common.annotation.Blocking;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 25/10/21
 * Time: 14.23
 */
@Path("/extra")
public class ExtraMailResource {

    @Inject
    Mailer mailer;

    @GET
    @Path("/attachment")
    @Blocking
    public void sendEmailWithAttachment() {
        mailer.send(Mail.withText("your-destination-email@quarkus.io", "An email from quarkus with attachment",
                        "This is my body")
                .addAttachment("my-file-1.txt",
                        "content of my file".getBytes(), "text/plain")
        );
    }
}
