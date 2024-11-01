1. Create a Dynamic Web Project called ecommerce-backend.

2. Add the 66 Spring jar files located at D:\Software\Spring\spring-framework-5.3.9\libs to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

3. Add the 16 Hibernate jar files located at D:\Software\Hibernate\hibernate-release-5.6.0.Final\lib\required to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

4. Add the 3 jars files c3p0-0.9.5.5.jar, hibernate-c3p0-5.6.0.Final.jar, mchange-commons-java-0.2.19.jar located at D:\Software\Hibernate\hibernate-release-5.6.0.Final\lib\optional\c3p0 to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib. These jar files are used to configure and manage database connections in Java applications, especially when working with Hibernate for ORM (Object-Relational Mapping). They are commonly used together to enable connection pooling, which helps manage database connections efficiently, reducing the overhead of opening and closing connections frequently.

5. Add the mysql connector located at D:\Software\mysql-connector\mysql-connector-java-8.0.27 to D:\Java EE Workspace\ecommerce-backend\WebContent\WEB-INF\lib.

6. Add the 3 jackson libraries jackson-databind-2.14.0.jar,
jackson-core-2.14.0.jar, jackson-annotations-2.14.0.jar to get support for JSON serialization and deserialization in Java.

7. Build the project and run it on Tomcat Server.

8. Hit http://localhost:9003/ecommerce-backend/TestDbServlet to check the DB connection.

9. Hit http://localhost:9003/ecommerce-backend/test/hello to check the test endpoint in DemoRestController.java.

