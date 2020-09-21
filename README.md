# Simple Java Server

A basic Maven Java server using Spring Boot, and MongoDB. Created using [Spring Initializr](https://start.spring.io/). The backend for the [Simple Todo App](https://github.com/adelabdallah/simple-todo-app).

## Running Locally

- `git clone https://github.com/adelabdallah/simple-java-server.git`

- `cd simple-java-server`

- Ensure the Java 11 SDK is specified in the `JAVA_HOME` environment variable.

- [Install MongoDB](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/) using default settings as a MongoDB Service. This project was created using a Windows machine using the [cmder](https://cmder.net/) console emulator, so these instructions are for that specific setup. Your mileage may vary.

  - [MongoDB Compass](https://www.mongodb.com/products/compass) was used as a GUI for monitoring the DB. I used it to create my collections and view data.

  - Alternatively, [start a shell](https://docs.mongodb.com/manual/mongo/) using `"C:\Program Files\MongoDB\Server\4.4\bin\mongo.exe"`, and do everything there.

- Create a database using `md "C:\data\db"`, or whatever path you'd like.

- Start database using `"C:\Program Files\MongoDB\Server\4.4\bin\mongod.exe" --dbpath="c:\data\db"`.

  - If stopping the above command doesn't shutdown the DB, use the following in the shell:

    ```javascript
    use admin
    db.shutdownServer()
    quit()
    ```

- Create a collection called `tasks`.

- Finally, start the Spring server using: `mvnw spring-boot:run`.

## API

There are four endpoints:

- `GET /get`
- `POST /create`

```json
{
  "id": "string",
  "title": "string",
  "date": "string",
  "description": "string"
}
```

- `POST /update`

```json
{
  "id": "string",
  "title": "string",
  "date": "string",
  "description": "string",
  "isCompleted": "boolean"
}
```

- `DELETE /delete?id=<TASK_ID>`

## Sample Requests

All requests were done in the command line using [HTTPie](https://httpie.org/), a wonderful little HTTP Client. Feel free to use your client of choice. The following commands use HTTPie and are OS agnostic (as far as I can tell).

### Get all tasks

```bash
http GET localhost:8080/get
```

### Create task

```bash
http POST localhost:8080/create id=abc123 title=someTitle0 date=02012020 description=someDescription0

http POST localhost:8080/create id=123abc title=someTitle2 date=02012020 description=someDescription2

http POST localhost:8080/create id=bcd234 title=someTitle3 date=02012020 description=someDescription3
```

### Delete task

```bash
http DELETE localhost:8080/delete?id=bcd234
```

### Update task

```bash
http POST localhost:8080/update id=abc123 title=otherTitle date=02012020 description=someDescription isCompleted=false
```
