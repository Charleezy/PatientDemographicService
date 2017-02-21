# Patient Cross Registration Service  

Run the application by using maven and installing. Access the endpoints using port 8080  

- post for adding demographics  
localhost:8081/addDemographic  
documentID "1"  
firstName "Qazzxc123"  
lastName "guan"  
dob "mar/24/1991"  
address "toronto street"  

returns the id of the demographic  

- post for updating demographics  
localhost:8081/updateDemographic  
documentID "1"  
firstName "Qazzxc123"  
lastName "guan"  
dob "mar/24/1991"  
address "toronto street"  
returns the id of the demographic  

- post  
localhost:8081/queryPatients  
parameter "firstName"  
value "charlie"  

- get getDemographicByDocuments  
localhost:8081/getDemographicByDocument  
documentID 1  

#To run with maven  
Set up a MySQL database with the parameters specified in application.properties  

./mvnw spring-boot:run  

Or build the jar  
./mvnw clean package  