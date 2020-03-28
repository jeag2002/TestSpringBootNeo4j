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

5)Access to html console and follow the instructions  
5.1) Linux (or Windows with docker native engine): localhost:8080/index.html
5.2) Windows (with docker toolbox): 192.168.99.100:8080/index.html

If you want to connect with the neo4j console directly

1) Access to the following url
1.1) Linux,Mac,Windows with docker native engine: localhost:7474
1.2) Windows with docker Toolbox: 192.168.99.100:7474

2) Use the parameters
2.1) URI
2.1.1)(Linux; Mac,Windows docker native engine) : bolt://localhost:7687
2.1.2)(Windows with docker toolbox): bolt://192.168.99.100:7687
2.2) User: Neo4j
2.3) Password: bitnami

3) Execute the following query:
MATCH (p:Plain)-[m:MEAL]-(c:Course)-[d:DISH]-(l:Plate) RETURN * LIMIT 100




