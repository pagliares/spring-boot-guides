# Spring Boot Guides

Repository with examples illustrated at Spring Boot QuickStart Guides (https://spring.io/quickstart e https://spring.io/guides). The examples are used in the classes about Spring Boot, taught by Rodrigo Martins Pagliares in the Computer Science undergraduate course at UNIFAL-MG, Brazil.

## Examples

### 01 - demo

- This first Spring Boot was generated with aid of Spring Initializer (https://start.spring.io/) by selecting Maven as build tool, Spring Boot 3.0.0, jar packaging, Java 17, and with the Spring Web Dependency. 

- In the example we program a classic “Hello World!” endpoint (RESTful) that can be accessed from any browser. 
- The example also illustrates how to pass parameters to the server via URL (query string).
- The parameter is used to produce the output view shown in the web browser.  
- To execute the example:
   - ./mvnw spring-boot:run (GNU/Linux)
   - mvnw spring-boot:run (Windows)

- To test the example:
   - <p>http://localhost:8080/hello (outputs Hello World!)</p>
   - <p>http://localhost:8080/hello<strong>?name=Pagliares</strong> (outputs Hello Pagliares! )</p>

<pre>
<strong>@SpringBootApplication</strong>
<strong>@RestController</strong>
public class DemoApplication {

   public static void main(String[] args) {
      <strong>SpringApplication.run(DemoApplication.class, args)</strong>;
   }

   <strong>@GetMapping("/hello")</strong>
   public String hello(<strong>@RequestParam(value = "name", defaultValue = "World"</strong>) String name) {
      return String.format("Hello %s!", name);
   }
}
</pre>

### 02 - command-line-spring-boot

- In addition to web applications, we can use spring boot to develop command-line programs.
- This example shows how to create a simple command line application in Spring Boot by implementing the interface CommandLineRunner (in the next example we will provide an alternative implementation that uses Java SE 8 lambdas).
- This example project was created with Spring Initializr without explicitly adding any dependencies

<p align="center"> <img src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/Command_Line_Runner.png" width=795 height="493" alt="Spring initializr printscreen" title="Spring initializr"></p>


- CommandLineRunner is an interface used to indicate that a bean should run when it is contained within a SpringApplication. 
- The overriden run() method of the CommandLineRunner will be executed after the application starts.
- One common use case of CommandLineRunner is to load some static data at application startup.  
- The example also demonstrates how we can inject a bean ApplicationContext with the annotation @Autowired

<pre>
@SpringBootApplication
public class CommandLineSpringBootApplication <strong>implements CommandLineRunner</strong> {
	<strong>@Autowired</strong>
	private ApplicationContext ctx;

	public static void main(String[] args) {
		SpringApplication.run(CommandLineSpringBootApplication.class, args);
	}

	@Override
	<strong>public void run(String... args) throws Exception</strong> { 
		System.out.println("Hello, cruel Spring Boot command-line world!");
		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = <strong>ctx.getBeanDefinitionNames()</strong>;
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}
}

</pre>

### 03 - spring-boot

- This example builds a simple web application with Spring Boot and add some useful monitoring services to it.
- The project can be created by visiting Spring Initializr, filling in your project details, picking your options, and downloading a bundled up project as a zip file. I added the Spring Web Dependency.
- If your IDE has the Spring Initializr integration, you can complete this process from your IDE.
 - Spring Web allows build web, including RESTful, applications using Spring MVC. Spring web uses Apache Tomcat as the default embedded container. 
- This example includes a web controller for a simple web application (HelloController) that outputs HelloWorld when requested from the the root of the application / 
- URLs used in this example:

	<p>http://localhost:8080/ </p>  
	<p>http://localhost:8080/actuator/health <p>
	<p>http://localhost:8080/actuator </p>

- We can use a web browser or the command-line curl utility to test our endpoints. 

- This example also evolves the simple application class created by the Spring Initializr.  

<pre>
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}

}

 
<strong>@Bean</strong>
	public <strong>CommandLineRunner</strong> commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}
 </pre>

- The CommandLineRunner method marked as a @Bean runs on start up. It retrieves all the beans that were created by your application or that were automatically added by Spring Boot. It sorts them and prints them out (Notice that the helloController we created is on the list) 
- The example also demonstrates how we can include unit and integration tests in a Spring Boot application.
- If you use Maven, add the following to your pom.xml file to enable automated tests:

<pre>
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
</pre>


- The example includes a simple unit test that mocks the servlet request and response by calling your endpoint (HelloController). We use MockMvc that comes from Spring Test and lets us send HTTP requests into the DispatcherServlet and make assertions about the result. 

- Having used @SpringBootTest, we are asking for the whole application context to be created. An alternative would be to ask Spring Boot to create only the web layers of the context by using @WebMvcTest. 

- The example also includes an simple full-stack integration test  

<pre>
@SpringBootTest(<strong>webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT</strong>)
public class HelloControllerIT {

	@Autowired
	private TestRestTemplate template;

    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        <strong>assertThat(response.getBody()).isEqualTo("Greetings from Spring Boot!")</strong>;
    }
}
</pre>

- Finnaly, the example illustrates that we can add production grade services (management services) to our Spring Boot application to, among other things, monitor the status of our web application on production environments.

- To enable management services. Add it to Maven

<p><dependency>
   <groupId>org.springframework.boot</groupId>
   <artifactId>spring-boot-starter-actuator</artifactId>
</dependency></p>


- Accessing the management services with http://localhost:8080/actuator/health produces {"status":"UP"}

- Accessing the management services with http://localhost:8080/actuator produces

<p>{"_links":{"self":{"href":"http://localhost:8080/actuator","templated":false},"health-path":{"href":"http://localhost:8080/actuator/health/{*path}","templated":true},"health":{"href":"http://localhost:8080/actuator/health","templated":false}}}</p>

 - Refer to https://spring.io/guides/gs/spring-boot if you are interested on more information about the HelloController example, such as Jar and Groovy language Support.


### 04 - accessing-data-mysql

- This example demonstrates how to develop a Spring application that is bound to a MySQL database and is ready for production.

### 05 - relational-data-access

- This example demonstrates the process of accessing relational data with Spring.
- You will build an application that uses Spring’s JdbcTemplate to access data stored in a relational database.
- The project is a spring-boot project with the dependencies JDBC API and H2 Database.
- In this example, we created the project with IntelliJ assistant (that integrates with Spring Initializr).
- The example is a command-line application with Spring Boot (the RelationalDataAccessApplication class implements Spring Boot’s CommandLineRunner, which means it will execute the run() method after the application context is loaded).

<pre>
@SpringBootApplication
public class RelationalDataAccessApplication <strong>implements CommandLineRunner</strong> {
  ...
  @Override
  public void <strong>run(String... strings) throws Exception</strong> {
     ...
  }
   ...
}
</pre>

- A simple customer object is used to demonstrate persistence features with JdbcTemplate
- The example uses a Spring template class called JdbcTemplate that makes it easy to work with SQL relational databases and JDBC.
- The example includes a class (RelationalDataAccessApplication) that can store and retrieve data over JDBC.
- Instead of using System.out.print statements to print messages to the console, we use sl4j (already included with Spring Boot) to manage all messages printed to the console (use a log frameworks has several advantages over System.out.print statements (for example, allows define message levels and redirectig output for a file instead of console, etc).

<pre>
@SpringBootApplication
public class RelationalDataAccessApplication implements CommandLineRunner {
   ...
   private static final <strong>Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class)</strong>;
   ...
   <strong>log.info("Creating tables")</strong>;
   ...
}
</pre>

- Spring Boot supports H2 (an in-memory relational database engine) and automatically creates a connection to it. Because we use spring-jdbc, Spring Boot automatically creates a JdbcTemplate that is automatically injected in the code.

<pre>
   @SpringBootApplication
   public class RelationalDataAccessApplication implements CommandLineRunner {
      ...
      <strong>@Autowired</strong>
      JdbcTemplate jdbcTemplate;
       ...
  }
</pre>

- The method execute of JdbcTemplate can be used, for instance, to execute drop table e create tabel SQL DDL statements.

<pre>
jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
jdbcTemplate.execute("CREATE TABLE customers(id ENTITY, first_name VARCHAR(255), last_name VARCHAR(255))");
</pre>

- For single insert statements, the insert method of JdbcTemplate is good, However, for multiple inserts, it is better to use batchUpdate. Suppose splitUpNames is a list of array objects that contains on each object of the array, the first name and the last name of a customer. In this scenario, multiple insertions can be done with just one statement.
     
<pre>
 jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);
</pre>

- The example demonstrates the use of the method query of the object JdbcTemplate in order to retrieve from the database all customers with firstname equals to 'Josh'.

- The example takes a list of strings and, by using Java 8 streams, split them into firstname/lastname pairs in a Java array.
- It is a good practice to use ? for arguments in a SQL Query to avoid SQL injection attacks by instructing JDBC to bind variables.
- The example uses the query method to search your table for records that match the criteria. You again use the ? arguments to create parameters for the query, passing in the actual values when you make the call. The last argument is a Java 8 lambda that is used to convert each result row into a new Customer object.

<pre>
log.info("Querying for customer records where first_name = 'Josh':");
        <strong>jdbcTemplate.query</strong>(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
                new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"),
                        rs.getString("first_name"), rs.getString("last_name"))
         ).forEach(customer -> log.info(customer.toString()));
</pre>

- You can run the application by using ./mvnw spring-boot:run. 
- Alternatively, you can build the JAR file with ./mvnw clean package and then run the JAR file, as follows:

<pre>java -jar target/gs-relational-data-access-0.1.0.jar</pre>
