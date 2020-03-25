Test application, using SpringBoot v2.2.2 and Neo4J 4.0

Windows 10 x64 professional
JDK 1.8.0_212
Eclipse 2019-06 (4.12.0)
Neo4J (Graph Database) 4.0
SpringBoot 2.2.2.RELEASE
Docker Toolbox for windows

Application TestSpringBootNeo4J:

Instructions:

1)mvn clean install 
2)docker-compose down --rmi all
3)docker-compose down -v
4)docker-compose up

It depends of your OS:

Linux (or Windows with docker native engine): localhost:8080/index.html
Windows (with docker toolbox): 192.168.99.100:8080/index.html

Follow the instructions.
