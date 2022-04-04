# apptracker

## Steps for build and running

- mvn clean install
- mvn spring-boot:run

### Curl requests

### file tracker
```
curl --location --request GET 'http://localhost:8080/ping?path=tmp&name=ok' \
--data-raw ''
```

### Getting Image
```
curl --location --request GET 'http://localhost:8080/img' \
--data-raw ''
```