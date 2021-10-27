package com.hendisantika.resource;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import org.jboss.logging.Logger;

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
 * Time: 14.27
 */
@Path("/mail")
public class MailResource {

    @Inject
    Mailer mailer;

    @Inject
    ReactiveMailer reactiveMailer;

    @Inject
    Logger logger;

    @GET
    @Blocking
    public void sendEmail() {
        mailer.send(Mail.withText("hendi@yopmail.com", "Ahoy from Quarkus", "A simple email sent from" +
                " a Quarkus application."));
        logger.info("Email sent!");
    }

    @GET
    @Path("/reactive")
    public Uni<Void> sendEmailUsingReactiveMailer() {
        return reactiveMailer.send(                         // <4>
                Mail.withText("hendi@yopmail.com",
                        "Ahoy from Quarkus",
                        "A simple email sent from a Quarkus application using the reactive API."
                )
        );
    }
}
