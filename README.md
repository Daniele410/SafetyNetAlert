
# SafetyNet Alerts 

The SafetyNet Alerts app. Developing with spring boot. The purpose of this app is to send information to emergency service systems.

** Note ** : Configure before the data.json file path in the application.properties in order to start the application

### Prerequisites
- Java 1.8
- Maven 3.6.2

### Installing

A step by step series of examples that tell you how to get a development env running:

1.Install Java:

https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html

2.Install Maven:

https://maven.apache.org/install.html


### Running App

To run the app you go to the folder safetyNetAlert

Command line : mvn package 

then

Command line : java -jar target/safetyNetAlert-0.0.1-SNAPSHOT.jar

mvn spring-boot:run

### Tester l'app :

Command line : mvn verify

Generate jacoco and surefire test reports : mvn site

Utiliser Postman pour tester les endpoints et les URLs.


### Les Endpoints : opération CRUD

## Person endpoints

GET http://localhost:8080/person : get All Persons

GET http://localhost:8080/person/address : get all Persons by address

POST http://localhost:8080/person/ : save a Person

PUT http://localhost:8080/person/firstName/LastName : Update a Person

DELETE http://localhost:8080/person/firstName/LastName: delete a Person

## Firestation endpoints 

GET http://localhost:8080/firestations : get All FireStations

GET http://localhost:8080/firestation/stationNumber : get all FireStations with the station number

POST http://localhost:8080/firestation : save a FireStation

PUT http://localhost:8080/firestation/address : update a Firestation

DELETE http://localhost:8080/firestation/address : delete a Firestation

## Medical record endpoints 

GET http://localhost:8080/medicalRecord : get All MedicalRecords

GET http://localhost:8080/medicalRecord/lastName : get all MedicalRecords with the last name

POST http://localhost:8080/medicalRecord : save a MedicalRecord

PUT http://localhost:8080/medicalRecord/firstName/lastName : update a MedicalRecord

DELETE http://localhost:8080/medicalRecord/firstName/lastName : delete a MedicalRecord

### Alerts URLS

GET http://localhost:8080/communityEmail?city=`city` : This url must return the email addresses of all the inhabitants of the city.

GET http://localhost:8080/personInfo?firstName=`firstName`&lastName=`lastName` : This url must return the name, address, age, email address and medical history (medication, dosage, allergies) of each inhabitant.

GET http://localhost:8080/childAlert?address=`address` : This url should return a list of children (any individual aged 18 or under) living at this address.

GET http://localhost:8080/phoneAlert?firestation=`firestation_number` : This url should return a list of phone numbers of residents served by the fire station.

GET http://localhost:8080/firestation?stationNumber=`station_number` : This url should return a list of people covered by the corresponding fire station.

GET http://localhost:8080/fire?address=`address` : This url must return the list of inhabitants living at the given address as well as the number of the fire station serving it.

GET http://localhost:8080/flood/stations?stations=`a_list_ofStation_numbers` : This url should return a list of all households served by the barracks. This list must group people by address.
