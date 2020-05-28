# votingAPI
voting API is a secure Rest API that will be used by a web system to register votes from a company employees towards their colleagues in different areas. Authorization & Authentication management was done with JWT methods. 

## Stack
- Spring Boot
- Spring Security
- Spring Data JPA
- Dependency Manager: Maven
- Code Generator & Builder: Lombok
- Database: H2
- Testing Tool: Junit
- API Documentation: Swagger

## Architecture
This API was built in the context of a Client-Server architecture. On the Server side, the REST services have been splited into 4 packages: model (entity modeling), repository and services (interfaces for and with application logic), and controllers (services exposure). The main used dessign patterns are DTO (Data Transfer Object) for POJO's creation and Facade in order to provide an easy interface for developed services.

## Functionality
There are two user roles with different access levels: "ADMIN" can access everything and "EMPLOYEE" only some resources. Referred to voting, employees can vote for other colleagues (not theirself) in different areas once per month.

## Endpoints
**ALL USERS**
**Login**: The logging endpoint is not protected at all. Required params: username and password. 
POST /login
	```sh
	$ curl -i -H "Content-Type: application/json" -X POST -d '{ "username": "user", "password": "password"}' http://localhost:8080/login
	```
**NOTE:** The expected response is a generated TOKEN with 8h expiration time. Replace it in next requests

**EMPLOYEE**
**New vote**: Required params: recipientId, areaId. Optional params: comment, date
POST /votes
	```sh
	$ curl -i -H 'Content-Type: application/json' -H 'Authorization: Bearer TOKEN' -X POST -d '{ "recipient_id": 1, "area_id": 3, "comment": "vv", "date": "2020-05-25"}' http://localhost:8080/votes/
	```
**ADMIN**
**Get number of registered employees**: No required params
GET /users/total
	```sh
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/users/total
	```

**Get the most voted employees by year and month**: Required params: year, month
GET /votes/year/{year}/month/{month}
	```sh
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/votes/year/2020/month/05
	```

**Get the most voted employees by area**: Required params: areaId
GET /votes/area/{areaId}
	```sh
	$ curl -H "Authorization: Bearer TOKEN" http://localhost:8080/votes/area/2
	```
## How to run the project

In a Terminal:

**How run the Server**
1. Choose a directory to create the virtual environment **predictorenv** & activate it:
	```sh
	$ virtualenv predictorenv
	$ source predictorenv/bin/activate
	```
2. In **pico_placa_predictor** directory, install all specified tools  in **requirements.txt** file
	```sh
	$ pip install -r requirements.txt
	```
3. In the same directory, run the following commands in order to create all Django models (classes) in the default database SQLite :
	```sh
	$ python manage.py migrate
	$ python manage.py makemigrations pico_placa
	```
4. Run the server:
	```sh
	$ python manage.py runserver
	```
**How run the Client**
 1. From the **picoPlacaPredictorClient** directory, run:
	```sh
	$ npm install
	$ ng serve 
	```
	By default, the client starts in 4200 port: http://localhost:4200/

## How the predictor works

The predictor app is Client-Server model. The server is an API REST (Django REST) and the Client is performed by Angular.
In the form, enter:
1. A License plate (which format is 3 letters & 4 numbers, i.e. 'ABC1234')
2. A Date and Time
3. Submit

It will show you if a car with that license plate can or not can be on the road at that date and that time. If the license plate format is not correct, the app will notify you.

## Additional steps
- Type Ctrl-C to down server and client. 
- The performed tests are in **pico_placa/tests**. To see the tests result, run: 
	```sh
	$ python manage.py test
	```
- To deactivate **predictorenv**, from any path, run:
	```sh
	$ deactivate
	```
	
	**NOTE:** These steps were written for Mac OS, Other OS are supported from the virtual environment creation.
