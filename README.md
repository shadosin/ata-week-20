# Week 20 - Integration Tests

Deploy the DynamoDB tables using the following Cloudformation commands:

```
aws cloudformation create-stack --stack-name kenziechat-users --template-body file://UserTable.yaml --capabilities CAPABILITY_IAM
aws cloudformation wait stack-create-complete --stack-name kenziechat-users

aws cloudformation create-stack --stack-name kenziechat-comments --template-body file://CommentTable.yaml --capabilities CAPABILITY_IAM
aws cloudformation wait stack-create-complete --stack-name kenziechat-comments
```

Run the application with 

```
./gradlew bootRunDev
```

Run Integration tests with 
```
./gradlew :IntegrationTests:test
```