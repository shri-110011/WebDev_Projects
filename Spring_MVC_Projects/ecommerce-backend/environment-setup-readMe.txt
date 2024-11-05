************************** Environment setup without Maven: ***********************************************************

1. Create a Dynamic Web Project called ecommerce-backend.

2. Add the 66 Spring jar files located at D:\Software\Spring\spring-framework-5.3.9\libs to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

3. Add the 16 Hibernate jar files located at D:\Software\Hibernate\hibernate-release-5.6.0.Final\lib\required to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

4. Add the 3 jars files c3p0-0.9.5.5.jar, hibernate-c3p0-5.6.0.Final.jar, mchange-commons-java-0.2.19.jar located at D:\Software\Hibernate\hibernate-release-5.6.0.Final\lib\optional\c3p0 to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib. These jar files are used to configure and manage database connections in Java applications, especially when working with Hibernate for ORM (Object-Relational Mapping). They are commonly used together to enable connection pooling, which helps manage database connections efficiently, reducing the overhead of opening and closing connections frequently.

5. Add the mysql connector located at D:\Software\mysql-connector\mysql-connector-java-8.0.27 to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

6. Add the 3 jackson libraries jackson-databind-2.14.0.jar,
jackson-core-2.14.0.jar, jackson-annotations-2.14.0.jar to get support for JSON serialization and deserialization in Java.

7. Add the 4 hibernate validator jars.
hibernate-validator-6.2.0.Final, hibernate-validator-annotation-processor-6.2.0.Final, hibernate-validator-cdi-6.2.0.Final

8. Add the javax.validation jar validation-api-2.0.1.Final.
This is part of the Java Bean Validation API, which provides a standard way to declare validation constraints on object properties. By annotating fields (e.g., @NotNull, @Size), you can enforce certain rules on data across Java applications.

9. Build the project and run it on Tomcat Server.

10. Hit http://localhost:9003/ecommerce-backend/TestDbServlet to check the DB connection.

11. Hit http://localhost:9003/ecommerce-backend/test/hello to check the test endpoint in DemoRestController.java.

** Note: Without maven it becomes very difficult to manually download the jars and their compile time dependencies.

************************** Environment setup with Maven: ***********************************************************

1. Create a Maven Project.
Select maven-archetype-webapp from org.apache.

2. Add these dependencies in the pom.xml file:
<dependencies>

		<!-- Spring -->
		<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-webmvc</artifactId>
			<version>5.3.9</version>
		</dependency>


		<!-- https://mvnrepository.com/artifact/org.springframework/spring-tx -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-tx</artifactId>
			<version>5.3.9</version>
		</dependency>


		<!-- https://mvnrepository.com/artifact/org.springframework/spring-orm -->
		<dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-orm</artifactId>
			<version>5.3.9</version>
		</dependency>


		<!-- Add Jackson for JSON converters -->
		<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
		<dependency>
			<groupId>com.fasterxml.jackson.core</groupId>
			<artifactId>jackson-databind</artifactId>
			<version>2.9.5</version>
		</dependency>


		<!-- Hibernate -->
		<!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core -->
		<dependency>
			<groupId>org.hibernate</groupId>
			<artifactId>hibernate-core</artifactId>
			<version>5.6.0.Final</version>
		</dependency>


		<!-- MySQL -->
		<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>8.0.31</version>
		</dependency>


		<!-- C3PO -->
		<!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
		<dependency>
			<groupId>com.mchange</groupId>
			<artifactId>c3p0</artifactId>
			<version>0.9.5.5</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.validation/validation-api -->
		<dependency>
			<groupId>javax.validation</groupId>
			<artifactId>validation-api</artifactId>
			<version>2.0.1.Final</version>
		</dependency>

		<!-- https://mvnrepository.com/artifact/javax.servlet/javax.servlet-api -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>4.0.1</version>
			<scope>provided</scope>
		</dependency>

		<!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
		<dependency>
			<groupId>redis.clients</groupId>
			<artifactId>jedis</artifactId>
			<version>5.2.0</version>
		</dependency>

</dependencies>

3. Build the project and run it on Tomcat Server.

4. Hit http://localhost:9003/ecommerce-backend/TestDbServlet to check the DB connection.

5. Hit http://localhost:9003/ecommerce-backend/test/hello to check the test endpoint in DemoRestController.java.