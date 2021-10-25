package com.hendisantika.resource;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MailTemplate;
import io.quarkus.mailer.Mailer;
import io.quarkus.qute.CheckedTemplate;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;

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

    @CheckedTemplate
    static class Templates {
        public static native MailTemplate.MailTemplateInstance hello(String name); // <1>
    }

    @GET
    @Path("/template")
    public Uni<Void> sendTypeSafeTemplate() {
        // the template looks like: Hello {name}!
        return Templates.hello("John")
                .to("your-destination-email@quarkus.io")
                .subject("Hello from Qute template")
                .send();
    }
}
