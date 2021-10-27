# quarkus-rest-api-crud Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. Be aware that it’s not an _über-jar_ as
the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/quarkus-rest-api-crud-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html
.

## Provided Code

### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)

# quarkus-rest-api-crud

### Run Postgres docker container

     docker run -d --rm --name my_reative_db -e POSTGRES_USER=user -e POSTGRES_PASSWORD=password -e POSTGRES_DB=my_db -p 5432:5432 postgres:10.5

### Create shopping cart

     curl -s -X POST "http://localhost:8080/v1/carts" -H "Content-Type: application/json" -d '{"name":"myCart", "cartTotal": 2}'

### Add a product

     curl -i -X POST "http://localhost:8080/v1/products" -H "Content-Type: application/json" -d '{"title":"product_title","description":"product_description"}'

### Get all products back

     curl -i -X GET "http://localhost:8080/v1/products" 

### Add product to cart

     curl -s -X PUT "http://localhost:8080/v1/carts/1/1"

### Delete product from cart

     curl -s -X DELETE "http://localhost:8080/v1/carts/1/1"

### Get all carts

    curl -i -X GET "http://localhost:8080/v1/carts" 

### Get single cart

    curl -i -X GET "http://localhost:8080/v1/carts/1" 

### Build Docker Image

     mvn clean install -Dquarkus.container-image.build=true -Dmaven.test.skip

### Run the Application

    docker run --net=host -it --rm --name tic_toc  -p 8080:8080 <name>/reactive-rest-hibernate:1.1.0

### Clean DB

    docker rm $(docker ps -a -q) -f

    docker volume prune

### CRUD Product Page

Located at http://localhost:8080

### Native image build

    ./mvnw package -Pnative -Dquarkus.native.container-build=true

### Example response GET All Carts

    [
      {
        "cartItems": [
          {
            "product": {
              "createdAt": "2021-05-31T06:11:14Z",
              "description": "description",
              "id": 2,
              "title": "Product2",
              "updatedAt": "2021-05-31T06:11:14Z"
            },
            "quantity": 1
          },
          {
            "product": {
              "createdAt": "2021-05-31T06:11:14Z",
              "description": "description",
              "id": 1,
              "title": "Product1",
              "updatedAt": "2021-05-31T06:11:14Z"
            },
            "quantity": 2
          }
        ],
        "cartTotal": 3,
        "id": 1,
        "name": "MyCart"
      },
      {
        "cartItems": [
          {
            "product": {
              "createdAt": "2021-05-31T06:11:14Z",
              "description": "description",
              "id": 2,
              "title": "Product2",
              "updatedAt": "2021-05-31T06:11:14Z"
            },
            "quantity": 1
          },
          {
            "product": {
              "createdAt": "2021-05-31T06:11:14Z",
              "description": "description",
              "id": 1,
              "title": "Product1",
              "updatedAt": "2021-05-31T06:11:14Z"
            },
            "quantity": 2
          }
        ],
        "cartTotal": 3,
        "id": 1,
        "name": "MyCart"
      },
      {
        "cartItems": [],
        "cartTotal": 0,
        "id": 2,
        "name": "myCart"
      }
    ]
