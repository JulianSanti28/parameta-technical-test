## Parameta
To execute the project use the following tools
- Maven
- IDE
- Docker
## Docker MySQL

Run the following command to boot an appropriate MySQL image for this Spring Boot project

```sh
docker run --name <container_name> -e MYSQL_ROOT_PASSWORD=<password> -p <local_posrt>:3306 -d mysql:latest
```
- <container_name> is the name that will be given to the MySQL container.
- <password> is the password that will be used to access the MySQL server.
- <local_port> is the local port that will be mapped to port 3306 of the MySQL container, allowing access to the MySQL server from outside the container.
    
For this concrete example, use this command
    
```sh
docker run --name parameta-mysql -e MYSQL_ROOT_PASSWORD=parameta-pw -d -p 3306:3306 mysql:latest
```
    
Create a MySQL client to create a database, follow the below command and provide your MySQL server password given in the above command

```sh
docker run -it --name mysql-client --link parameta-mysql:mysql --rm mysql:latest mysql -hmysql -uroot -p
```
Create a table within this database with the following command

```sh
CREATE DATABASE ParametaTechnicalTest;
```
The Spring Boot application will be ready to go if you have followed the above steps correctly.

## Features

This project has a REST service for employee registration, business rules are used for its operation. Use a REST client like Postman, for example.

## URL REST Service POST

```sh
localhost:8081/api/v1/employee
```
Localhost is just an example of the application host, modify this value according to your environment

Send a request body in JSON format such as the following
    
```sh
{
    "names":"Julian",
    "surnames":"Martinez",
    "documentType":"CC",
    "documentNumber": "1061226",
    "birthdate": "1998-05-28",
    "hireDate": "2022-08-01",
    "position": "Backend Developer",
    "salary": 500
}
```
You should receive a response message like the following with status code 201

```sh
  {
    "id": 1,
    "names": "Julian",
    "surnames": "Martinez",
    "documentType": "CC",
    "documentNumber": "1061226",
    "birthdate": "1998-05-28T05:00:00.000+00:00",
    "hireDate": "2022-08-01T05:00:00.000+00:00",
    "position": "Backend Developer",
    "salary": "500",
    "hiringTime": "0 años, 8 meses, y 22 días",
    "currentAge": "24 años, 10 meses, y 26 días"
}
```

Where you will also have the additional information requested

## Business rules

All business rules have been applied and captured in a clear and usable way, modify input values to get error response by business rules, for example:

- names and surnames empty
- negative salary
- hire date null

```sh
{
    "names":"",
    "surnames":"",
    "documentType":"CC",
    "documentNumber": "1061226",
    "birthdate": "1998-05-28",
    "position": "Backend Developer",
    "salary": 500
}
```

You will receive a response like the following

```sh
{
    "hireDate": "La fecha de contratacion no puede ser nula",
    "names": "El nombre no puede estar vacio.",
    "surnames": "Los apellidos no pueden estar vacios."
}
```

Also check other business rules such as the age of majority of the person, for example

```sh
{
    "names":"Julian",
    "surnames":"Martinez",
    "documentType":"CC",
    "documentNumber": "1061226",
    "birthdate": "2010-05-28",
    "hireDate": "2022-08-01",
    "position": "Backend Developer",
    "salary": 500
}
```

You will receive a response like the following
``` sh
{
    "error": "El empleado ingresado no cumple con la mayoría de edad"
}
```

Check the business rule of the date format

```sh
{
    "names":"Julian",
    "surnames":"Martinez",
    "documentType":"CC",
    "documentNumber": "1061226",
    "birthdate": "2010-05/28",
    "hireDate": "2022-08-01",
    "position": "Backend Developer",
    "salary": 500
}
```

You will receive a response like the following

```sh
{
    "error": "El formato de la fecha de nacimiento debe ser: (yyyy-MM-dd)"
}
```

Finally, try to break most of the business rules, for example:

```sh
{
    "names":"",
    "surnames":"",
    "documentType":"",
    "documentNumber": "",
    "birthdate": "2000-05/28",
    "hireDate": "2022/08-01",
    "position": "Backend Developer",
    "salary": -2
}
```

You will receive a response like the following

```sh

{
    "names": "El nombre no puede estar vacio.",
    "documentType": "El tipo de documento no puede estar vacio.",
    "documentNumber": "El numero de documento no puede estar vacio.",
    "salary": "El salario debe ser un numero positivo o cero.",
    "surnames": "Los apellidos no pueden estar vacios."
}

```
As it can be identified, all business rules have been handled in an appropriate and clear way for the client. All HTTP request statuses have been handled, 400 for client errors and 201 for creating a resource

