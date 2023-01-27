# product-service

## Run service with database in Docker container

### Build Spring project

Open terminal in the root project directory (`product-service/`), then run script from *gradlew* file to automatically
download Gradle and build new `.jar` file:

```shell
./gradlew clean build
```

### Run Docker container

next, use following Docker commands to build image and then run container:

```shell
docker-compose build
docker-compose up -d
```

## Stop container (stopping and saving Docker container in current state, along with e.g. database data)

Being in root directory (`product-service/`), use Docker command:

```shell
docker-compose stop
```

## Resume/start a new container based on built image (resuming previously stopped container or starting new one when there is no container)

Being in root directory (`product-service/`), use Docker command (`-d` flag to run container in the background):

```shell
docker-compose up -d
```

## Remove container (and its state, along with e.g. database data)

Being in root directory (`product-service/`), use Docker command:

```shell
docker-compose down
```

## Default configs

### Spring App

By default, service starts on `http://localhost:8090`

### MongoDB

Default database URI: `mongodb://mongo_dev:mongo_dev@localhost:27017/mongo_dev`
