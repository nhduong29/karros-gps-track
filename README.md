#GPS Track
# Task
- An endpoint that allow users to upload "gpx" file and store mandatory information such as "metadata, waypoint, track" 
- An endpoint to return a list of "Latest track" from our users
- An endpoint to allow users to view details of their gpx file

# Candidate's comments
Project structure:
- Java 8
- NodeJS
- Maven
- Spring Boot
- Spring Security
- JSON Web Token
####  Build using maven 
	
```
mvn clean install
```
	
#### Start the app
	
```
mvn spring-boot:run
```

#### Import the Role in the first init
```
INSERT INTO roles(name) VALUES('ROLE_USER');INSERT INTO roles(name) VALUES('ROLE_ADMIN');
```

####  Using Postman to test the API
Plase import file `AirlineBooking.postman_collection.json` to Postman.

####  RESTful API
##### You can import the postman collection in folder `postman\GPS Track.postman_collection.json` to your Postman app to run the api demo

##### Sign up
- API: POST ```/api/auth/signup```
- Request body: ```{
	"name" : "Duong Nguyen",
	"username" : "nhduong29",
	"email" : "nhduong29@gmail.com",
	"password" : "000000"
}```



##### Sign in
- API: GET ```/api/auth/signin```
- Request body: ```{
	"username" : "nhduong29",
	"password" : "123456"
}```





##### Get User info
- API: GET ```/api/users/nhduong1```

##### Return a list of "Latest track" from our users
- API: GET ```/api/gps/tracks?page=0&size=20&oderBy=uploadDate&direction=desc```
- PARAM: 
------ ```page``` : ```page number```
------ ```size``` : ```number of items per page```
------ ```oderBy``` : ```field to order [id,name,uploadDate,uploadBy]```
------ ```direction``` : ```sort direction```



##### Get detail of GPX
- API: GET ```/api/gps/{gpxId}```



##### Upload File
- API: POST ```/api/file/uploadFile```
- Request body: `form-data, parram name: file`



##### Download file

- API: GET ```api/file/downloadFile/{fileId}```

