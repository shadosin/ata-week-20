package com.kenzie.chat.webapi;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.env.MockPropertySource;
import org.testcontainers.containers.GenericContainer;

public class DynamoDbInitializer  implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    private static GenericContainer<?> dynamoDb;

    private static GenericContainer<?> getDynamoDbInstance() {
        if (dynamoDb == null) {
            dynamoDb = new GenericContainer<>("amazon/dynamodb-local:latest")
                    .withExposedPorts(8000);
        }
        return dynamoDb;
    }

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        // dynamodb-local is currently disabled!  Since this assignment is all about integration tests, you should run
        // integration tests against live AWS for this assignment.
        // Normally you could have springboot utilize dynamodb-local to run integration tests, but since this uses
        // Two separate services, and the UserSystem uses DynamoDB mapper instead of Springboot CrudRepository, then
        // it makes the most sense to not use dynamodb-local here.
//        if (System.getenv("STACK_NAME") == null && System.getenv("ARTIFACT_BUCKET") == null) {
//            getDynamoDbInstance().start();
//            String dynamoDbEndpoint = "http://localhost:8000";
//            configurableApplicationContext.getEnvironment()
//                    .getPropertySources()
//                    .addFirst(new MockPropertySource("dynamodb-initializer-properties")
//                            .withProperty("dynamodb.override_endpoint", "true")
//                            .withProperty("dynamodb.endpoint", dynamoDbEndpoint));
//        }
    }
}
