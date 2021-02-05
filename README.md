## About The Workshop

We will try to build a minimal API testing setup, where we will combine the API testing with performance testing.

## Must read
1. [API requests](https://www.restapitutorial.com/lessons/httpmethods.html)
2. [CRUD explained](https://www.bmc.com/blogs/rest-vs-crud-whats-the-difference/)
3. [Introduction to Lombok](https://www.baeldung.com/intro-to-project-lombok)
4. [Builder Pattern](https://www.baeldung.com/creational-design-patterns#builder)
5. [Object Mapper](https://www.baeldung.com/jackson-object-mapper-tutorial)
6. [JMETER JAVA DSL](https://github.com/abstracta/jmeter-java-dsl)

## Prerequisites

1. Intellij
2. Java 8 installed
3. Maven installed
4. Postman
5. Lombok plugin installed in Intellij
6. Git installed
7. Docker installed
8. docker-compose installed
9. To have the latest Intellij and Lombok plugin installed(there is a possibility of having issues -> new version of lombok, and old version of Intellij )


## Installation

1. git clone https://github.com/BojteTamas/ApiAndPerformanceWokshop
2. In Intellij you can open the project by open the pom.xml, and after choose import as project.
3. Go to plugins -> Marketplace, search for Lombok -> install it
4. Go to plugins -> Marketplace, search for RoboPOJOGenerator -> install it
5. The expected behavior is to see in the right-down side a popup where Intellij says **Enable lombok**
6. mvn install
7. Create an account on https://www.demoblaze.com/
8. Restart Intellij in order to use installed plugins
9. Run the test from test.RunningTest.testingTest 
10. Check if docker-compose is installed by running in the terminal the command : docker-compose


## Tools/libraries used

1. Lombok
2. jmeter-java-dsl
3. Jackson
4. RestAssured
5. Chrome Dev tools

## USEFUL LINKS

1. https://www.base64decode.org/
2. https://www.epochconverter.com/
3. https://www.demoblaze.com/index.html
4. http://www.sumondey.com/serialization-and-deserialization-using-jackson-objectmapper
5. https://www.baeldung.com/rest-assured-tutorial