Aws Lambda function for putting data into DynamoDB. 

There is no infrastructure-as-code yet. You have to setup the DynamoDB table, the SNS topic and all Roles and Permissions by yourself, sorry.
### Contents
```
├── create          // lambda for putting data into ddb
├── dynamodb_to_s3  // lambda for taking a static snapshot of ddb
├── dynamodb        // module for ddb base functions
└── model           // module for ddb schema and interaction functions
```
### How to run
##### to create the Lambda
```shell
./mvnw clean package -pl create -am
create/target/manage.sh create
```
#### once it is created you can use
```shell
./mvnw clean package -pl create -am
create/target/manage.sh update
```
#### then you can invoke the lambda through the aws console or with
```shell
cd create
target/manage.sh invoke
```

### if you want to compile ahead-of-time with native-image the commands become
##### to create the Lambda
```shell
./mvnw clean package -pl create -am -P native
create/target/manage.sh native create
```
#### once it is created you can use
```shell
./mvnw clean package -pl create -am -P native
create/target/manage.sh native update
```
#### then you can invoke the lambda through the aws console or with
```shell
cd create
target/manage.sh native invoke
```