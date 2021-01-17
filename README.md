Aws Lambda function for putting data into DynamoDB. There is no 
### Contents
```
├── create          (lambda for putting changes into ddb)
├── dynamodb_to_s3  (lambda for taking a static snapshot from ddb) (INCOMPLETE! TBD!)
├── dynamodb        (module with ddb helper functions)
└── model           (module with datastructure that is used by create)
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

###if you want to compile ahead-of-time with native-image the commands become
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
