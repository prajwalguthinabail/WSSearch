The setup requires MongoDb installed in the local system.

Application installation:
1. A3.war could be used to import the project
2. Deploy A3 on Glassfish 4.1.1
3. Necessary jars are included. (mongo-java-driver-3.6.3.jar and gson-2.8.2.jar)
3. Run ReadAndParse.java to create and populate API and Mashup collection in the DB.
4. The DB runs on "address 127.0.0.1 and port 27017.
3. The User Interface is web based. Description is available with test cases in the writeup.
4. To test the service, Run the A3 project, and open the page "http://localhost:8080/A3/queryPage.jsp"



This application was developed using- 
OS: Windows 10 64bit
IDE: NetBeans IDE 8.2
Database: MongoDB 3.6
Java 1.8
Server: Glassfish 4.1.1