# votingAPI
voting API is a secure Rest API that will be used by a web system to register votes from a company employees towards their colleagues in different areas. Authorization & Authentication management was done with JWT methods. 

# Stack
- Java 11
- Spring Boot
- Spring Security
- Spring Data JPA
- IDE: InteliJ IDEA 
- Dependency Manager: Maven
- Code Generator & Builder: Lombok ([Needs to enable 'Annotation Proccesing'](https://immutables.github.io/apt.html))
- Database: H2
- Testing Tool: Junit
- API Documentation: Swagger

# Architecture
This API was built in the context of a Client-Server architecture. On the Server side, the REST services have been splited into 4 packages: model (entity modeling), repository and services (interfaces for and with application logic), and controllers (services exposure). The main used dessign patterns are DTO (Data Transfer Object) for POJO's creation and Facade in order to provide an easy interface for developed services.

# Functionality
There are two user roles with different access levels: "ADMIN" can access everything and "EMPLOYEE" only some resources. Referred to voting, employees can vote for other colleagues (not theirself) in different areas once per month. All pre-charged data is in resources/import.sql

# Endpoints

## ALL USERS

**Login**: The logging endpoint is not protected at all. Required params: username and password. 


**POST /login**

	```
	$ curl -i -H "Content-Type: application/json" -X POST -d '{ "username": "user", "password": "password"}' http://localhost:8080/login
	```
	
**NOTE:** The expected response is a generated TOKEN with 8h expiration time. Replace it in next requests

## EMPLOYEE


**New vote**: Required params: recipientId, areaId. Optional params: comment, date: 


**POST /votes**

	```
	$ curl -i -H 'Content-Type: application/json' -H 'Authorization: Bearer TOKEN' -X POST -d '{ "recipient_id": 1, "area_id": 3, "comment": "vv", "date": "2020-05-25"}' http://localhost:8080/votes/
	```
	
## ADMIN


**Get number of registered employees**: No required params


**GET /users/total**


	```
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/users/total
	```
	

**Get the most voted employees by year and month**: Required params: year, month


**GET /votes/year/{year}/month/{month}**

	```
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/votes/year/2020/month/05
	```


**Get the most voted employees by area**: Required params: areaId


**GET /votes/area/{areaId}**

	```
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/votes/area/2
	```
