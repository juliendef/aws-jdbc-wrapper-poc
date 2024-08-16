It's a crappy code for a dummy test. Make sure you don't go into heart attack when you see this.

# aws-jdbc-wrapper-poc

The purpose is testing the AWS RDS connection using the AWS advanced JDBC wrapper.

## How to run

Create a new file named `application.properties` in the `src/main/resources` folder and add the following content:

```properties
db_url=jdbc:mysql://<your-endpoint>:3306/<your-database-name>
db_username=<your-username>
db_password=<your-password>
```

Adapt URL according to what you're testing (RDS, or basic mysql).

Then, run either `App` or `JdbcBoilerplate` depending on what you want to test using your IDE.

## Docker

Fill the `.env` file using `.env.example` as a template.

Build the project using the following command:

```bash
./mvnw clean package
```

Then, run the following command:

```bash
docker-compose build && docker-compose up
```
