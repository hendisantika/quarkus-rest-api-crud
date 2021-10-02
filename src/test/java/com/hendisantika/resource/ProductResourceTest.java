package com.hendisantika.resource;

import io.quarkus.test.junit.QuarkusTest;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

/**
 * Created by IntelliJ IDEA.
 * Project : quarkus-rest-api-crud
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 02/10/21
 * Time: 08.16
 */
@QuarkusTest
class ProductResourceTest {
    static final Jsonb jsonb = JsonbBuilder.create(new JsonbConfig());
}
