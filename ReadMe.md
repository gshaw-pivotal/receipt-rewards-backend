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