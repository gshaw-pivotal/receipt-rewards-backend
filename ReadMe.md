## Build Locally

```bash
  mvnw package
```

## Run Locally (without Docker)

```bash
  mvnw spring-boot:run
```

## Docker

Replace `<image_name>` with the name you desire for the generated image

```bash
    mvnw package
    docker image build -t <image_name>:latest .
    docker run -p 8080:8080 <image_name>:latest
```

Then make requests to port 8080 on the machine running docker. For example if you are running this image / container on you local machine then you would make http requests to `localhost:8080/`

The application has 2 endpoints:
- `/receipts/process` a POST endpoint that accepts properly structured requests
- `/receipts/{id}/points` a GET endpoint that returns a response with the number of points associated with the submitted id. If the id does not exist then an error is returned, along with a status of 404

## Assumptions

- Points for characters in retailer name
  - Assumes the English alphabet only, and thus a to z, A to Z and 0 to 9 are the only eligible characters.
- Points for time.
  - Given the statement `10 points if the time of purchase is after 2:00pm and before 4:00pm.` it is assumed that these are exclusive boundaries and therefore purchases at exactly 2:00pm (14:00) and 4:00pm (16:00) are not eligible for 10 points. 