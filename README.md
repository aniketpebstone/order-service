# The Orderservice for Tusla PoC

## Locally
To run this service locally you need a local Eureka instance, otherwise it will crash.

## PCF
To build and push to Cloud Foundry you run the following command.
```
mvn clean package && cf push
```
This assumes you've set the correct Cloud Foundry environment beforehand.