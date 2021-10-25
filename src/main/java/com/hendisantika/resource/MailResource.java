package com.hendisantika.resource;

import io.quarkus.mailer.Mailer;

import javax.inject.Inject;
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
}
