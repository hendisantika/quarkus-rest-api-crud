package com.hendisantika;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.MockMailbox;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 27/10/21
 * Time: 15.53
 */
@QuarkusTest
class MailTest {

    private static final String TO = "hendi@yopmail.com";

    @Inject
    MockMailbox mailbox;

    @BeforeEach
    void init() {
        mailbox.clear();
    }

    @Test
    void testTextMail() throws MessagingException, IOException {
        // call a REST endpoint that sends email
        given()
                .when()
                .get("/mail")
                .then()
                .statusCode(204);

        // verify that it was sent
        List<Mail> sent = mailbox.getMessagesSentTo(TO);
        assertThat(sent).hasSize(1);
        Mail actual = sent.get(0);
        assertThat(actual.getText()).contains("A simple email sent from a Quarkus application.");
        assertThat(actual.getSubject()).isEqualTo("Ahoy from Quarkus");

        assertThat(mailbox.getTotalMessagesSent()).isEqualTo(1);
    }

    @Test
    void testInvalidMail() {
        given()
                .queryParam("name", "foo")
                .queryParam("email", "not-an-email")
                .when()
                .get("/type-safe")
                .then()
                .statusCode(400);

        assertThat(mailbox.getTotalMessagesSent()).isEqualTo(0);
    }
}
