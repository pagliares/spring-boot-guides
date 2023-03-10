# Spring Boot Guides

Repository with examples illustrated at Spring Boot QuickStart Guides (https://spring.io/quickstart and https://spring.io/guides). The examples are used in the classes about Spring Boot, taught by Rodrigo Martins Pagliares in the Computer Science undergraduate course at UNIFAL-MG, Brazil.

## Outline

### Part I - First steps with Spring Boot
<p><a href="https://github.com/pagliares/spring-boot-guides#01---spring-quickstart-guide">01 - Spring Quickstart Guide</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#02---command-line-spring-boot">02 - command-line-spring-boot</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#03---building-an-application-with-spring-boot">03 - Building an Application with Spring Boot</a></p>

### Part II - Persistence with Spring Boot
<p><a href="https://github.com/pagliares/spring-boot-guides#04---accessing-data-mysql">04 - accessing-data-mysql</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#05---relational-data-access">05 - Accessing Relational Data using JDBC with Spring
</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#06---managing-transactions">06 - managing-transactions</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#07---accessing-data-jpa">07 - accessing-data-jpa</a></p>

### Part III - RESTful with Spring Boot
<p><a href="https://github.com/pagliares/spring-boot-guides#08---accessing-data-rest">08 - accessing-data-rest</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#09---rest-service">09 - rest-service</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#10---quoters-and-consuming-rest">10 - quoters and consuming-rest</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#11---actuator-service">11 - actuator-service</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#12---rest-hateoas">12 - Building a Hypermedia-Driven RESTful Web Service</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#13---enabling-cross-origin-requests-for-a-restful-web-service-rest-service-cors">13 - Enabling Cross Origin Requests for a RESTful Web Service</a></p>

### Part IV - Spring MVC
<p><a href="https://github.com/pagliares/spring-boot-guides#14---serving-web-content-with-spring-mvc">14 - Serving Web Content with Spring MVC
</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#15---handling-form-submission">15 - Handling Form Submission
</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#16---validating-form-input">16 - Validating Form Input</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#17---uploading-files">17 - Uploading Files</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#18---testing-the-web-layer">18 - Testing the Web Layer</a></p>

### Part V - Spring Security
<p><a href="https://github.com/pagliares/spring-boot-guides#19---securing-a-web-application">19 - Securing a Web Application</a></p>

### Part VI - Docker Containers
<p><a href="https://github.com/pagliares/spring-boot-guides#20---spring-boot-with-docker">20 - Spring Boot with Docker</a></p>

## Part I - First steps with Spring Boot

### 01 - Spring Quickstart Guide  

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> demo.
- Refer to https://spring.io/quickstart if you are interested on more information about this example.

<strong>Introduction</strong>

- This first Spring Boot was generated with aid of Spring Initializer (https://start.spring.io/) by selecting Maven as build tool, Spring Boot 3.0.0, jar packaging, Java 17, and with the Spring Web Dependency. 
- In the example we program a classic ???Hello World!??? endpoint (RESTful) that can be accessed from any browser. 
- The example also illustrates how to pass parameters to the server via URL (query string).
- The parameter is used to produce the output view shown in the web browser.  
- To execute the example:
   - ./mvnw spring-boot:run (GNU/Linux)
   - mvnw spring-boot:run (Windows)
- To test the example:
   - http://localhost:8080/hello (outputs Hello World!)
   - http://localhost:8080/hello<strong>?name=Pagliares</strong> (outputs Hello Pagliares!)

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

### 02 - Command-line development with Spring Boot

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> command-line-spring-boot
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

### 03 - Building an Application with Spring Boot

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> spring-boot
- Refer to https://spring.io/guides/gs/spring-boot/ if you are interested on more information about this example.

<strong>Introduction</strong>

- This example builds a simple web application with Spring Boot and add some useful monitoring services to it.
- The project can be created by visiting Spring Initializr, filling in your project details, picking your options, and downloading a bundled up project as a zip file. I added the Spring Web Dependency.
- If your IDE has the Spring Initializr integration, you can complete this process from your IDE.
 - Spring Web allows build web, including RESTful, applications using Spring MVC. Spring web uses Apache Tomcat as the default embedded container. 
- This example includes a web controller for a simple web application (HelloController) that outputs HelloWorld when requested from the the root of the application / 
- URLs used in this example:
	- http://localhost:8080/
	- http://localhost:8080/actuator/health
	- http://localhost:8080/actuator
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

## Part II - Persistence with Spring Boot

### 04 - Accessing data with MySQL

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/accessing-data-mysql/ if you are interested on more information about this example.
- <strong>Project source:</strong> accessing-data-mysql.

<strong>Introduction</strong>

- This example demonstrates how to develop a Spring application that is bound to a MySQL database and is ready for production.
- The example uses Spring Data JPA to access the database, but this is only one of many possible choices (for example, you could use plain Spring JDBC).
- <strong>Dependencies</strong>: Spring Web, Spring Data JPA, and MySQL Driver.
- To execute this example, you need acesss to MySQL (you can use any MySQL frontend you like: MySQL Monitor, MySQL Workbench, PHPMyAdmin, etc) in order to be able to create a database.
- To create a new database, run the following commands at the mysql prompt:

<pre>
mysql> create database db_example; -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
</pre>

### 05 - Accessing Relational Data using JDBC with Spring

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/relational-data-access/ if you are interested on more information about this example.
- <strong>Project source:</strong> relational-data-access.

<strong>Introduction</strong>

- This example demonstrates the process of accessing relational data with Spring.
- You will build an application that uses Spring???s JdbcTemplate to access data stored in a relational database.
- The project is a spring-boot project with the dependencies JDBC API and H2 Database.
- In this example, we created the project with IntelliJ assistant (that integrates with Spring Initializr).
- The example is a command-line application with Spring Boot (the RelationalDataAccessApplication class implements Spring Boot???s CommandLineRunner, which means it will execute the run() method after the application context is loaded).

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

<p align="center"> <img src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/Output.png" width=750 height="286" alt="Example output" title="Example output to the console"></p>


### 06 - managing-transactions

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/managing-transactions/ if you are interested on more information about this example.
- Before trying this example, we suggest reviewing the use of transactions with JDBC (see example 20 - TransactionManagement in the repository located at: https://github.com/pagliares/jdbc-hands-on)
- The example (managing-transactions) in this repo walks you through the process of wrapping database operations with non-intrusive transactions.
- You will build a simple JDBC application wherein you make database operations transactional without having to write specialized JDBC code.
- The examples illustrates a BookingService class to create a JDBC-based service that books people into the system by name.
- This method is tagged with <strong>@Transactional</strong>, meaning that any failure causes the entire operation to roll back to its previous state and to re-throw the original exception. This means that none of the people are added to BOOKINGS if one person fails to be added.
- You also have a findAllBookings method to query the database. Each row fetched from the database is converted into a String, and all the rows are assembled into a List.

<pre>
@Component
public class BookingService {
    ???
    private final JdbcTemplate jdbcTemplate;
    ???
    ???
    <strong>@Transactional</strong>
    public void book(String... persons) {
        for (String person : persons) {
            logger.info("Booking " + person + " in a seat...");
            <strong>jdbcTemplate.update("insert into BOOKINGS(FIRST_NAME) values (?)", person);</strong>
        }
    }

   
    public List<String> findAllBookings() {
        <strong>return jdbcTemplate.query("select FIRST_NAME from BOOKINGS",
                (rs, rowNum) -> rs.getString("FIRST_NAME"));</strong>
    }

}
</pre>

- Notice this example application actually has zero configuration. Spring Boot detects <strong>spring-jdbc</strong> and <strong>h2</strong> on the classpath and automatically creates a DataSource and a JdbcTemplate for you. Because this infrastructure is now available and you have no dedicated configuration, a DataSourceTransactionManager is also created for you. This is the component that intercepts the method annotated with <strong>@Transactional</strong> (for example, the book method on BookingService). The BookingService is detected by classpath scanning.

- Another Spring Boot feature demonstrated in this example is the ability to initialize the schema on startup. The following file (from <strong>src/main/resources/schema.sql</strong>) defines the database schema:

<pre>
drop table BOOKINGS if exists;
create table BOOKINGS(ID serial, FIRST_NAME varchar(5) NOT NULL);
</pre>

- There is also a CommandLineRunner that injects the BookingService and performs some database transactions on its run method. 

<pre>
<strong>@Component</strong>
class AppRunner <strong>implements CommandLineRunner</strong> {
    ...
    private final BookingService bookingService;

    public AppRunner(<strong>BookingService bookingService</strong>) {
        this.bookingService = bookingService;
    }
    
    ...
    
    @Override
    public void <strong>run(String... args) throws Exception</strong> {
        bookingService.book("Alice", "Bob", "Carol");
        Assert.isTrue(bookingService.findAllBookings().size() == 3,
                "First booking should work with no problem");
        logger.info("Alice, Bob and Carol have been booked");

</pre>

- If you use Maven, you can run the application by using <strong>./mvnw spring-boot:run</strong>. Alternatively, you can build the JAR file with <strong>./mvnw clean package</strong> and then run the JAR file, as follows:

<pre>java -jar target/gs-managing-transactions-0.1.0.jar</pre>

### 07 - accessing-data-jpa 

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/accessing-data-jpa/ if you are interested on more information about this example.
- This example walks you through the process of building an application that uses <strong>Spring Data JPA</strong> to store and retrieve data in a relational database.
- This example is a simple application that uses Spring Data JPA to save objects to and fetch them from a database, all without writing a concrete repository implementation.
- You will build an application that stores <strong>Customer POJOs (Plain Old Java Objects)</strong> in a memory-based database.
- In this example, you store Customer objects, each annotated as a <strong>JPA entity</strong>. 
- You also have two constructors in Customer entity. The default constructor exists only for the sake of JPA. You do not use it directly, so it is designated as protected.
- <strong>Spring Data JPA</strong> focuses on using JPA to store data in a relational database. Its most compelling feature is the ability to <strong>create repository implementations automatically, at runtime, from a repository interface</strong>.

<pre>
public <strong>interface</strong> CustomerRepository <strong>extends CrudRepository<Customer, Long></strong> {
    List<Customer> findByLastName(String lastName);
    Customer findById(long id);
}
</pre>

- CustomerRepository extends the CrudRepository interface. The type of entity and ID that it works with, Customer and Long, are specified in the generic parameters on CrudRepository.
- By extending CrudRepository, CustomerRepository inherits several methods for working with Customer persistence, including <strong>methods for saving, deleting, and finding Customer entities</strong>.
- <strong>Spring Data JPA</strong> also lets you define other query methods by declaring their method signature. For example, CustomerRepository includes the <strong>findByLastName()</strong> method.
- In a typical Java application, you might expect to write a class that implements CustomerRepository. However, that is what makes Spring Data JPA so powerful: <strong>You need not write an implementation of the repository interface</strong>. <strong>Spring Data JPA creates an implementation</strong> when you run the application.
- To get output (to the console, in this example), you need to set up a <strong>logger</strong>. Then you need to set up some data and use it to generate output. 
- The <strong>AccessingDataJpaApplication</strong> class includes a demo() method that puts the CustomerRepository through a few tests:
	- It fetches the CustomerRepository from the Spring application context (not shown explicitly in the code)
	- It saves a handful of Customer objects, demonstrating the <strong>save()</strong> method and setting up some data to work with. 
        - It calls <strong>findAll()</strong> to fetch all Customer objects from the database. 
        - It calls <strong>findById()</strong> to fetch a single Customer by its ID. 
        - It calls <strong>findByLastName()</strong> to find all customers whose last name is "Bauer". 
- The <strong>demo()</strong> method returns a <strong>CommandLineRunner bean</strong> that automatically runs the code when the application launches.

<pre>
<strong>@Bean</strong>
public <strong>CommandLineRunner</strong> demo(CustomerRepository repository) {
   return (args) -> {
      <strong>// save a few customers</strong>
      log.info("Saving few customers with save method of CustomerRepository:");

      <strong>repository.save(new Customer("Jack", "Bauer"));</strong>
      repository.save(new Customer("Chloe", "O'Brian"));
      repository.save(new Customer("Kim", "Bauer"));
      repository.save(new Customer("David", "Palmer"));
      repository.save(new Customer("Michelle", "Dessler"));

       <strong>// fetch all customers</strong>
       log.info("Customers found with findAll():");
       log.info("-------------------------------");
       for (Customer customer : <strong>repository.findAll()</strong>) {
          log.info(customer.toString());
       }
       log.info("");

        <strong>// fetch an individual customer by ID</strong>
        Customer customer = <strong>repository.findById(1L)</strong>;

        log.info("Customer found with findById(1L):");
        log.info("--------------------------------");
        log.info(customer.toString());
        log.info("");

         <strong>// fetch customers by last name - Using lambda - Java SE 8</strong>
         log.info("Customer found with findByLastName('Bauer'):");
         log.info("--------------------------------------------");
         <strong>repository.findByLastName("Bauer")</strong>.forEach(bauer -> {
           log.info(bauer.toString());
         });

         <strong>// fetch customers by last name - loop for, Pre-Java SE 8</strong>
         // for (Customer bauer : <strong>repository.findByLastName("Bauer")</strong>) {
         //    log.info(bauer.toString());
         // }
         log.info("");
        };
    }
</pre>

- <strong>By default, Spring Boot enables JPA repository support and looks in the package (and its subpackages) where @SpringBootApplication is located</strong>. If your configuration has JPA repository interface definitions located in a package that is not visible, you can point out alternate packages by using @EnableJpaRepositories and its type-safe basePackageClasses=MyRepository.class parameter.


## Part III - RESTful with Spring Boot


### 08 - accessing-data-rest

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/accessing-data-rest/ if you are interested on more information about this example.
- This example walks you through the process of creating an application that accesses relational <strong>JPA-based backend data</strong> through a <strong>hypermedia-based RESTful front end</strong>.
- You will build a Spring application that lets you create and retrieve <strong>Person objects</strong> stored in a database by using <strong>Spring Data REST</strong>. <strong>Spring Data REST</strong> takes the features of <strong>Spring HATEOAS</strong> and <strong>Spring Data JPA</strong> and automatically combines them together.
- <strong>Spring Data REST</strong> also supports <strong>Spring Data Neo4j</strong>, <strong>Spring Data Gemfire</strong>, and <strong>Spring Data MongoDB</strong> as <strong>backend data stores</strong>, but those are not part of this example.
- <strong>Dependencies: Rest Repositories, Spring Data JPA, and H2 Database</strong>.
- The example uses a domain object to present a person
- At the end of this example, student will understand how to make a domain object accessible to the world through HTTP and how to perfom CRUD operation on it by using a command-line tool. 
- The examples will enable the student to try communicating with the RESTful endpoints by using the URLs
	- $ curl http://localhost:8080
	- $ curl http://localhost:8080/people
	- $ curl -i -H "Content-Type:application/json" -d '{"firstName": "Frodo", "lastName": "Baggins"}' http://localhost:8080/people
	- $ curl http://localhost:8080/people
	- $ curl http://localhost:8080/people/1
	- $ curl http://localhost:8080/people/search
	- $ curl 'http://localhost:8080/people/search/findByLastName?name=Baggins'
	- $ curl -X PUT -H "Content-Type:application/json" -d '{"firstName": "Bilbo", "lastName": "Baggins"}' http://localhost:8080/people/1
	- $ curl -X PATCH -H "Content-Type:application/json" -d '{"firstName": "Bilbo Jr."}' http://localhost:8080/people/1
	- $ curl -X DELETE http://localhost:8080/people/1

- The Person object has a first name and a last name. There is also an ID object that is configured to be automatically generated, so you need not deal with that.
- The example uses a repository (<strong>interface PersonRepository</strong>).
- This repository is an interface that lets you perform various operations involving Person objects. It gets these operations by extending the <strong>PagingAndSortingRepository interface</strong> that is defined in <strong>Spring Data Commons</strong>.
- At runtime, <strong>Spring Data REST</strong> automatically creates an implementation of this interface. Then it uses the <strong>@RepositoryRestResource</strong> annotation to direct Spring MVC to create RESTful endpoints at <strong>/people</strong>.
- <strong>@RepositoryRestResource</strong> is not required for a repository to be exported. It is used only to change the export details, such as using <strong>/people</strong> instead of the default value of </strong>/persons</strong>.
- The <strong>Repository</strong> defines a <strong>custom query</strong> to retrieve a list of Person objects based on the lastName.
- Spring Boot automatically spins up <strong>Spring Data JPA</strong> to create a concrete implementation of the <strong>PersonRepository</strong> and configure it to talk to a <strong>back end in-memory database</strong> by using JPA.
- <strong>Spring Data REST</strong> builds on top of <strong>Spring MVC</strong>. It creates a collection of <strong>Spring MVC controllers</strong>, <strong>JSON converters</strong>, and other beans to provide a <strong>RESTful front end</strong>. These components link up to the <strong>Spring Data JPA backend</strong>. When you use Spring Boot, this is all autoconfigured.
- You can use any REST client you wish to test the example. The following examples use the *nix tool, <strong>curl</strong>:
- The following example shows how to do see the top level service (Notice in the output that there is a <strong>people link</strong> located at <strong>http://localhost:8080/people</strong>. It has some options, such as <strong>?page, ?size, and ?sort</strong>

<pre>
<strong>$ curl http://localhost:8080</strong>
{
  "_links" : {
    "people" : {
      "href" : <strong>"http://localhost:8080/people{?page,size,sort}"</strong>,
      "templated" : true
    }
  }
}
</pre>

- The following example shows how to see the people records (none at present):

<pre>
$ curl http://localhost:8080/<strong>people</strong>
{
  "_embedded" : {
    "people" : []
  },
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : <strong>"http://localhost:8080/people/search"</strong>
    }
  },
  "page" : {
    "size" : 20,
    "totalElements" : 0,
    "totalPages" : 0,
    "number" : 0
  }
}
</pre>

- The following listing shows how to create a new Person:

<pre>
<strong>$ curl -i -H "Content-Type:application/json" -d '{"firstName": "Frodo", "lastName": "Baggins"}' http://localhost:8080/people</strong>
HTTP/1.1 201 Created
Server: Apache-Coyote/1.1
Location: http://localhost:8080/people/1
Content-Length: 0
Date: Wed, 26 Feb 2014 20:26:55 GMT
</pre>

-i: Ensures you can see the response message including the headers. The URI of the newly created Person is shown (http://localhost:8080/people/1).
-H "Content-Type:application/json": Sets the content type so the application knows the payload contains a <strong>JSON object</strong>.
-d '{"firstName": "Frodo", "lastName": "Baggins"}': Is the data being sent.

- Note: on Windows you might need to replace the single quotes with double quotes and escape the existing double quotes, 
	- i.e. -d "{\"firstName\": \"Frodo\", \"lastName\": \"Baggins\"}".

- Notice how the response to the POST operation includes a Location header. This contains the URI of the newly created resource (http://localhost:8080/people/1). 

- The following example shows how to query for all people:

<pre>
<strong>$ curl http://localhost:8080/people</strong>
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "_embedded" : {
    "people" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "_links" : {
        "self" : {
          "href" : <strong>"http://localhost:8080/people/1"</strong>
        }
      }
    } ]
  },
  "page" : {
    "size" : 20,
    <strong>"totalElements" : 1,</strong>
    "totalPages" : 1,
    "number" : 0
  }
}
</pre>

- You can query directly for the individual record, as follows:

<pre>
<strong>$ curl http://localhost:8080/people/1</strong>
{
  "firstName" : "Frodo",
  "lastName" : "Baggins",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1"
    }
  }
}
</pre>

- This might appear to be purely web-based. However, <strong>behind the scenes, there is an H2 relational database</strong>. In production, you would probably use a real one, such as <strong>PostgreSQL</strong>.
- In this exemple, there is only one domain object. With a more complex system, where domain objects are related to each other, <strong>Spring Data REST</strong> renders additional links to help navigate to connected records.
- The following example shows hot to find all the <strong>custom queries</strong>
	- You can see the URL for the query, including the HTTP query parameter, name. 
	- Note that this matches the <strong>@Param("name")</strong> annotation embedded in the interface:

<pre>
<strong>$ curl http://localhost:8080/people/search</strong>
{
  "_links" : {
    "findByLastName" : {
      "href" : <strong>"http://localhost:8080/people/search/findByLastName{?name}"</strong>,
      "templated" : true
    }
  }
}
</pre>

- The following example shows how to use the <strong>findByLastName query</strong>:

<pre>
<strong>$ curl 'http://localhost:8080/people/search/findByLastName?name=Baggins'</strong>
{
  "_embedded" : {
    "persons" : [ {
      "firstName" : "Frodo",
      "lastName" : "Baggins",
      "_links" : {
        "self" : {
          "href" : <strong>"http://localhost:8080/people/1"</strong>
        }
      }
    } ]
  }
}
</pre>

- You can also issue <strong>PUT</strong>, <strong>PATCH</strong>, and <strong>DELETE</strong> REST calls to replace, update, or delete existing records (respectively). The following example uses a <strong>PUT</strong> call:

<pre>
<strong>$ curl -X PUT -H "Content-Type:application/json" -d '{"firstName": "Bilbo", "lastName": "Baggins"}' http://localhost:8080/people/1</strong>
$ curl http://localhost:8080/people/1
{
  "firstName" : "Bilbo",
  "lastName" : "Baggins",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1"
    }
  }
}
</pre>

- The following example uses a <strong>PATCH</strong> call:

<pre>
<strong>$ curl -X PATCH -H "Content-Type:application/json" -d '{"firstName": "Bilbo Jr."}' http://localhost:8080/people/1</strong>
$ curl http://localhost:8080/people/1
{
  "firstName" : "Bilbo Jr.",
  "lastName" : "Baggins",
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people/1"
    }
  }
}
</pre>

- <strong>PUT</strong> replaces an entire record. Fields not supplied are replaced with <strong>null</strong>. You can use <strong>PATCH</strong> to update a subset of items.

- You can also delete records, as the following example shows:

<pre>
<strong>$ curl -X DELETE http://localhost:8080/people/1</strong>
$ curl http://localhost:8080/people
{
  "_links" : {
    "self" : {
      "href" : "http://localhost:8080/people{?page,size,sort}",
      "templated" : true
    },
    "search" : {
      "href" : "http://localhost:8080/people/search"
    }
  },
  "page" : {
    "size" : 20,
    "<strong>totalElements" : 0</strong>,
    "totalPages" : 0,
    "number" : 0
  }
}
</pre>

- A convenient aspect of this <strong>hypermedia-driven interface</strong> is that <strong> you can discover all the RESTful endpoints by using curl (or whatever REST client you like)</strong>. You need not exchange a formal contract or interface document with your customers.

### 09 - rest-service

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/rest-service/ if you are interested on more information about this example.
- This example walks you through the process of creating a <strong>???Hello, World??? RESTful web service with Spring</strong>.
- Dependencies: <strong>Spring Web</strong>.
- The example uses the <strong>Jackson JSON library</strong> to automatically marshal instances of type Greeting into JSON. Jackson is included by default by the <strong>web starter</strong>.
- You will build a service that will accept HTTP GET requests at http://localhost:8080/greeting. It will respond with a JSON representation of a greeting, as the following listing shows:

<pre>{"id":1,"content":"Hello, World!"}</pre>

- You can customize the greeting with an optional name parameter in the query string, as the following listing shows:

<pre>http://localhost:8080/greeting?name=User</pre>

<pre>{"id???:2,???content":"Hello, User!"}</pre>

- To model the greeting representation, create a resource representation class (Greeting POJO). 

<pre>
package com.example.restservice;

public class Greeting {

   private final long id;
   private final String content;

   public Greeting(long id, String content) {
	this.id = id;
	this.content = content;
   }

   public long getId() {
	return id;
   }

   public String getContent() {
	return content;
   }
}
</pre>

- In Spring???s approach to building RESTful web services, <strong>HTTP requests are handled by a controller</strong>. 
- These components are identified by the <strong>@RestController</strong> annotation
- the <strong>GreetingController</strong> shown in the following listing  handles GET requests for <strong>/greeting</strong> by returning a new instance of the Greeting class:

<pre>
<strong>@RestController</strong>
public class GreetingController {

   private static final String template = "Hello, %s!";
   <strong>private final AtomicLong counter = new AtomicLong()</strong>;

   <strong>@GetMapping("/greeting")</strong>
   public Greeting greeting(<strong>@RequestParam(value = "name", defaultValue = "World"</strong>) String name) {
	return new Greeting(<strong>counter.incrementAndGet(), String.format(template, name)</strong>);
   }
}
</pre>

- The <strong>@GetMapping</strong> annotation ensures that HTTP GET requests to <strong>/greeting</strong> are mapped to the <strong>greeting()</strong> method.
- There are companion annotations for other HTTP verbs (e.g. <strong>@PostMapping</strong> for POST). There is also a <strong>@RequestMapping</strong> annotation that they all derive from, and can serve as a synonym (e.g. <strong>@RequestMapping(method=GET)</strong>).
- <strong>@RequestParam</strong> binds the value of the query string parameter name into the name parameter of the <strong>greeting()</strong> method. If the name parameter is absent in the request, the defaultValue of <strong>World</strong> is used.
- The implementation of the method body creates and returns a new Greeting object with id and content attributes based on the next value from the counter and formats the given name by using the greeting template.
- <strong>Important:</strong> A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created. Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object. The object data will be written directly to the HTTP response as JSON. 
- This example code uses Spring <strong>@RestController</strong> annotation, which marks the class as a controller where every method returns a domain object instead of a view. It is shorthand for including both <strong>@Controller</strong> and <strong>@ResponseBody</strong>.
- The Greeting object must be converted to JSON. Thanks to Spring???s HTTP message converter support, you need not do this conversion manually. Because <strong>Jackson 2</strong> is on the classpath, Spring???s <strong>MappingJackson2HttpMessageConverter</strong> is automatically chosen to convert the Greeting instance to JSON.
- Notice also how the <strong>id attribute</strong> has changed from 1 to 2 and so on after each request. This proves that you are working against the <strong>same GreetingController instance across multiple requests</strong> and that its counter field is being incremented on each call as expected.

### 10 - quoters and consuming-rest

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/consuming-rest/ if you are interested on more information about this example.
- This example walks you through the process of creating an <strong>application that consumes a RESTful web service</strong>.
- You will build an application that uses <strong>Spring???s RestTemplate</strong> to retrieve a <strong>random Spring Boot quotation</strong> at http://localhost:8080/api/random
- Dependencies in the consuming-rest project: <strong>Spring Web</strong>.
- To run the example (consuming-rest) we need a source of REST resources. We use a second project for this purpose (quoters, from https://github.com/spring-guides/quoters)
- You can run quoters application in a separate terminal and access the result at http://localhost:8080/api/random. 
	- That address randomly fetches a quotation about Spring Boot and returns it as a <strong>JSON document</strong>. 
- Other valid addresses include 
	- http://localhost:8080/api/ (for all the quotations) and 
	- http://localhost:8080/api/1 (for the first quotation), 
	- http://localhost:8080/api/2 (for the second quotation), and so on (up to 11 at present).
- Dependencies in the quoters project:
	- Spring MVC
	- Spring Data JPA
	- H2 embedded database
- If you request that URL through a <strong>web browser</strong> or <strong>curl</strong>, you receive a <strong>JSON document</strong> that looks something like this:

<pre>
$ curl http://localhost:8080/api/random
{
   type: "success",
   value: {
      id: 10,
      quote: "Really loving Spring Boot, makes stand alone Spring apps easy."
   }
}
</pre>

- <strong>A more useful way to consume a REST web service is programmatically</strong>. To help you with that task, Spring provides a convenient template class called <strong>RestTemplate</strong>. 
- <strong>RestTemplate</strong> makes interacting with most RESTful services a one-line incantation. And it can even bind that data to custom domain types.
- The example presents  a domain class (Quote) that contains the data that you need.  

<pre>
package com.example.consumingrest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

<strong>@JsonIgnoreProperties(ignoreUnknown = true)</strong>
public class Quote {

  private String type;
  private Value value;

  ...  // getters and setters ommited

 
}

</pre>

- It is annotated with <strong>@JsonIgnoreProperties</strong> from the <strong>Jackson JSON processing library</strong> to indicate that any properties not bound in this type should be ignored.
- To directly <strong>bind your data to your custom types</strong>, you need to specify the variable name to be exactly the same as the key in the JSON document returned from the API. 
- In case your <strong>variable name</strong> and <strong>key in JSON doc</strong> do not match, you can use <strong>@JsonProperty</strong> annotation to specify the exact key of the JSON document. 
	- This example matches each variable name to a JSON key, so you do not need that annotation here.
- The example also shwos a <strong>ConsumingRestApplication</strong> class to get it to show quotations from our RESTful source. The class has:
	- A <strong>logger</strong>, to send output to the log (the console, in this example).
	- A <strong>RestTemplate</strong>, which uses the <strong>Jackson JSON processing library</strong> to process the incoming data.
	- A <strong>CommandLineRunner</strong> that <strong>runs the RestTemplate</strong> (and, consequently, fetches our quotation) on <strong>startup</strong>.
	
<pre>

@SpringBootApplication
public class ConsumingRestApplication {

	private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ConsumingRestApplication.class, args);
	}

	<strong>@Bean</strong>
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	<strong>@Bean</strong>
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			<strong>Quote quote = restTemplate.getForObject("http://localhost:8080/api/random", Quote.class);</strong>
			log.info(quote.toString());
		};
	}
}

</pre>

- Since the quoters project is already running on <strong>port 8080</strong>, you <strong>MUST</strong> initialize the project <strong>consuming-rest</strong> in another port (if you don't do this, when try running <strong>consumming-rest</strong> you will get an error of <strong>port 8080 already in use</strong> (by <strong>quoters</strong> application).
- To change de <strong>default port (8080)</strong> to another one (for example, <strong>9090</strong>) include the line below in the file src/main/resources/<strong>application.properties</strong> of the <strong>consuming-rest project</strong> and run again the application:

<pre>server.port=<strong>9090</strong></pre>. 

- If you see an error that reads, Could not extract response: no suitable HttpMessageConverter found for response type [class com.example.consumingrest.Quote], it is possible that you are in an environment that cannot connect to the backend service (which sends JSON if you can reach it). Maybe you are behind a corporate proxy. Try setting the http.proxyHost and http.proxyPort system properties to values appropriate for your environment.

### 11 - actuator-service

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/actuator-service/ if you are interested on more information about this example.
- <strong>Spring Boot Actuator</strong> is a sub-project of Spring Boot. It <strong>adds several production grade services to your application</strong> with little effort on your part. 
- In this example, you will build an application and then see how to add these services.
- In this example, you will build a <strong>simple RESTful service</strong> with Spring and add to it some useful built-in services with Spring Boot Actuator.
- This example takes you through creating a <strong>???Hello, world??? RESTful web service with Spring Boot Actuator</strong>. 
- You will build a service that handles <strong>GET requests for /hello-world, optionally with a name query parameter</strong>.

<pre>
$ curl http://localhost:<strong>9000</strong>/hello-world
</pre>

- It responds with the following JSON:

<pre>
{
    "id": 1,
    "content": "Hello, World!"
}
</pre>

- The id field is a unique identifier for the greeting, and content contains the textual representation of the greeting.

- <strong>Dependencies of the example</strong>:  Spring Web and Spring Boot Actuator.
- The <strong>@SpringBootApplication</strong> annotation provides a load of defaults (like the <strong>embedded servlet container</strong>), depending on the contents of your classpath and other things. It also turns on <strong>Spring MVC???s @EnableWebMvc</strong> annotation, which activates web endpoints.

<pre>
public class Greeting {

  private final long id;
  private final String content;

  // Getters and setters ommited
  ...
}
</pre>

- The example includes a <strong>endpoint controller</strong> that will serve the representation class.
- In Spring, REST endpoints are Spring MVC controllers.
- The example illustrates how to handle a GET request for the /hello-world endpoint and returns the Greeting resource:

<pre>
<strong>@Controller</strong> 
public class HelloWorldController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  <strong>@GetMapping("/hello-world")</strong>
  <strong>@ResponseBody</strong>
  public Greeting sayHello(<strong>@RequestParam(name="name", required=false, defaultValue="Stranger")</strong> String name) {
    return new Greeting(counter.incrementAndGet(), String.format(template, name));
  }

}
</pre>

- The key difference between a <strong>human-facing controller and a REST endpoint controller is in how the response is created</strong>. Rather than rely on a view (such as JSP or Thymeleaf) to render model data in HTML, an <strong>endpoint controller returns the data to be written directly to the body of the response</strong>.
- The <strong>@ResponseBody</strong> annotation tells Spring MVC not to render a model into a view but, rather, to write the returned object into the response body. It does so by using one of Spring???s message converters. Because <strong>Jackson 2 is in the classpath</strong>, MappingJackson2HttpMessageConverter will handle the conversion of a Greeting object to JSON if the <strong>request???s Accept header specifies that JSON should be returned</strong>.
- <strong>Spring Boot Actuator</strong> defaults to running on port <strong>8080</strong>. By adding an <strong>application.properties</strong> file, you can override that setting. 

<pre>
<strong>server.port: 9000</strong>
management.server.port: 9001
management.server.address: 127.0.0.1
</pre>

- You can test that it is working on <strong>port 9000</strong> by running the following commands in a terminal:

<pre>
<strong>$ curl localhost:9000/hello-world</strong>
{"id":1,"content":"Hello, Stranger!"}
</pre>

<pre>
<strong>$ curl localhost:9000/actuator/health</strong>
{"status":"<strong>UP</strong>"}
</pre>

- The <strong>status is UP</strong>, so the actuator <strong>service is running</strong>.
- The example also presents a <strong>unit and an integration tests</strong> for your application that ensures that your controller and management endpoints are responsive.
- Note that the tests start the application on a <strong>random port</strong>.

<pre>
<strong>@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)</strong>
<strong>@TestPropertySource(properties = {"management.port=0"})</strong>
public class HelloWorldApplicationTests {

    <strong>@LocalServerPort</strong>
    private int port;

    @Value("${local.management.port}")
    private int mgt;

    <strong>@Autowired</strong>
    private TestRestTemplate testRestTemplate;

    <strong>@Test</strong>
    public void <strong>shouldReturn200WhenSendingRequestToController</strong>() throws Exception {
        ???

    }

    <strong>@Test</strong>
    public void <strong>shouldReturn200WhenSendingRequestToManagementEndpoint</strong>() throws Exception {
       ???
    }

}
</pre>

### 12 - Building a Hypermedia-Driven RESTful Web Service

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/rest-hateoas/ if you are interested on more information about this example.

<strong>12.1 Introduction</strong>

- This example walks you through the process of creating a <strong>???Hello, World??? Hypermedia-driven REST web service with Spring HATEOAS</strong>.
- Hypermedia is an important aspect of REST. It lets you build services that <strong>decouple client and server to a large extent and let them evolve independently</strong>. 
- <strong>The representations returned for REST resources contain not only data but also links to related resources</strong>. Thus, the design of the representations is crucial to the design of the overall service.
- <strong>Spring HATEOAS</strong>: a library of APIs that you can use to create links that point to Spring MVC controllers, build up resource representations, and control how they are rendered into supported hypermedia formats (such as HAL).
- <strong>Dependencies</strong> used in this example (spring boot Maven project): Spring HATEOAS.

<strong>12.2 Resource representation class</strong>

- To model the greeting representation, create a <strong>resource representation class</strong>. 

<pre>
public class Greeting <strong>extends RepresentationModel<Greeting></strong> {

	private final String content;

	<strong>@JsonCreator</strong>
	public Greeting(<strong>@JsonProperty("content")</strong> String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
</pre>

	- <strong>@JsonCreator</strong>: Signals how Jackson can create an instance of this POJO.
	- <strong>@JsonProperty</strong>: Marks the field into which Jackson should put this constructor argument.

- Spring will use the Jackson JSON library to automatically marshal instances of type Greeting into JSON.

<strong>12.3 REST controller</strong>

- In Spring???s approach to building RESTful web services, HTTP requests are handled by a controller. 
- We use the <strong>@RestController</strong> annotation, which combines the <strong>@Controller</strong> and <strong>@ResponseBody</strong> annotations.

<pre>
<strong>@RestController</strong>
public class GreetingController {

	private static final String TEMPLATE = "Hello, %s!";

	<strong>@RequestMapping("/greeting")</strong>
	public HttpEntity<Greeting> greeting(
		<strong>@RequestParam(value = "name", defaultValue = "World")</strong> String name) {

		Greeting greeting = new Greeting(String.format(TEMPLATE, name));
		<strong>greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());</strong>

		return new <strong>ResponseEntity<>(greeting, HttpStatus.OK)</strong>;
	}
}

</pre>

- The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
- 	The above example does not specify GET vs. PUT, POST, and so forth, because @RequestMapping maps all HTTP operations by default. Use @GetMapping("/greeting") to narrow this mapping.

- Because the @RestController annotation is present on the class, an implicit @ResponseBody annotation is added to the greeting method. This causes Spring MVC to render the returned HttpEntity and its payload (the Greeting) directly to the response.

- The most interesting part of the method implementation is how you create the link that points to the controller method and how you add it to the representation model. Both linkTo(???) and methodOn(???) are static methods on ControllerLinkBuilder that let you fake a method invocation on the controller. The returned LinkBuilder will have inspected the controller method???s mapping annotation to build up exactly the URI to which the method is mapped.

- The call to withSelfRel() creates a Link instance that you add to the Greeting representation model.

<strong>12.4 Testing the service</strong>

<pre>
<strong>http://localhost:8080/greeting</strong>
{
  "content":"Hello, World!",
  "_links":{
    "self":{
      "href":"http://localhost:8080/greeting?name=World"
    }
  }
}
</pre>

<pre>

http://localhost:8080/greeting<strong>?name=User</strong>

{
  "content":"Hello, <strong>User</strong>!",
  "_links":{
    "self":{
      "href":"http://localhost:8080/greeting?name=User"
    }
  }
}
</pre>

### 13 - Enabling Cross Origin Requests for a RESTful Web Service  
 - <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> rest-service-cors
- Refer to https://spring.io/guides/gs/rest-service-cors/ and https://spring.io/guides/gs/consuming-rest-jquery/ if you are interested on more information about this example.

<strong>Introduction</strong>

- This example presents a ???Hello, World??? RESTful web service with Spring that includes <strong>headers for Cross-Origin Resource Sharing (CORS) in the response</strong>. 
- This example also presents a simple <strong>jQuery client</strong> that consumes a Spring MVC-based RESTful web service.
- You can find more information about Spring CORS support in this blog post.
	- https://spring.io/blog/2015/06/08/cors-support-in-spring-framework
- <strong>Dependencies</strong>:  Spring Web and Apache httpclient5.

<strong>The httpclient Dependency</strong>

- To test this example, you need, in addition to Spring Boot dependencies, the <strong>Apache httpclient library</strong>. See pom.xml for details.

<strong>Resource Representation Class</strong>

<pre>
public class Greeting {
    ...
    private final long id;
    private final String content;
    ... 
    ... // Getters and setters ommited
}
</pre>

<strong>Resource controller</strong>

<pre>
<strong>@RestController</strong>
public class GreetingController {

	private static final String template = "Hello, %s!";

	private final AtomicLong counter = new AtomicLong();
	<strong>@CrossOrigin(origins = "http://localhost:8080")</strong>
	@GetMapping("/greeting")
	public Greeting greeting(@RequestParam(<strong>required = false</strong>, defaultValue = "World") String name) {
		System.out.println("==== get greeting ====");
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

}
</pre>

- In the example, <strong>query string parameter</strong> is not required. If it is absent in the request, the defaultValue of World is used.
- <strong>@RestController</strong> annotation assumes that every method inherits the <strong>@ResponseBody</strong> semantics by default. Therefore, a returned object data is inserted directly into the response body.

<strong>Enabling CORS</strong>

- You can enable <strong>cross-origin resource sharing (CORS) from either in individual controllers or globally</strong>. 
- You can even combine global and controller-level CORS configuration.

<strong>Controller Method CORS Configuration</strong>

- Add a <strong>@CrossOrigin</strong> annotation to the <strong>handler method</strong>

<pre>
<strong>@CrossOrigin(origins = "http://localhost:8080")</strong>
@GetMapping("/greeting")
public Greeting greeting(@RequestParam(required = false, defaultValue = "World") String name) {
	System.out.println("==== get greeting ====");
	return new Greeting(counter.incrementAndGet(), String.format(template, name));
</pre>

- This <strong>@CrossOrigin</strong> annotation enables cross-origin resource sharing <strong>only for this specific method</strong>. 
- By default, it allows all origins, all headers, and the HTTP methods specified in the <strong>@RequestMapping</strong> annotation.
- Also, a <strong>maxAge of 30 minutes</strong> is used. You can customize this behavior by specifying the value of one of the following annotation attributes:
	- origins
	- methods
	- allowedHeaders
	- exposedHeaders
	- allowCredentials
	- maxAge.

- In this example, we allow only http://localhost:8080 to send <strong>cross-origin requests</strong>.
-  You can also add the <strong>@CrossOrigin</strong> annotation at the <strong>controller class level</strong> as well, to enable CORS on <strong>all handler methods of this class</strong>.

<strong>Global CORS Configuration</strong>

- In addition (or as an alternative) to fine-grained annotation-based configuration, you can define some <strong>global CORS configuration</strong> as well. 
- By default, all origins and GET, HEAD, and POST methods are allowed.

<pre>
<strong>@GetMapping("/greeting-javaconfig")</strong>
public Greeting greetingWithJavaconfig(@RequestParam(required = false, defaultValue = "World") String name) {
   System.out.println("==== in greeting ====");
   return new Greeting(counter.incrementAndGet(), String.format(template, name));
}
</pre>

- The difference between the <strong>greetingWithJavaconfig method</strong> and the <strong>greeting method</strong> (used in the controller-level CORS configuration) is the <strong>route (/greeting-javaconfig rather than /greeting)</strong> and the presence of the <strong>@CrossOrigin origin</strong>.
- The following example, shows how to add CORS mapping in the application class:
- You need to add a method in the Application class generated when creating the project to configure how to handle cross-origin resource sharing.

<pre>
@SpringBootApplication
public class RestServiceCorsApplication {

   ...
   ...
   
   <strong>@Bean</strong>
   public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		@Override
		public void addCorsMappings(<strong>CorsRegistry registry</strong>) {
			<strong>registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:8080");</strong>
		}
	};
  }
}
</pre>

<strong>Run the applicationr</strong>

<pre>
./mvnw spring-boot:run
</pre>

<strong>Build an executable jar file</strong>

</pre>
./mvnw clean package
java -jar target/gs-rest-service-cors-0.1.0.jar
</pre>

<strong>Test the service</strong>

- Visit http://localhost:8080/greeting

<pre>{"id":1,"content":"Hello, World!"}</pre>

- Visit http://localhost:8080/greeting?name=Florentino

<pre> {"id":2,"content":"Hello, Florentino!"} </pre>

- Now you can test that the <strong>CORS headers</strong> are in place and allow a <strong>Javascript client from another origin</strong> to access the service. 
- To do so, you need to create a <strong>Javascript client</strong> to consume the service. The following listing shows such a client (<strong>hello.js</strong>)

<pre>
$(document).ready(function() {
    $.ajax({
        url: "http://localhost:8080/greeting"
    }).then(function(data, status, jqxhr) {
       $('.greeting-id').append(data.id);
       $('.greeting-content').append(data.content);
       console.log(jqxhr);
    });
});
</pre>

- This <strong>javascript client</strong> is represented as a simple JavaScript function. It uses <strong>jQuery???s $.ajax() method</strong>to consume the REST service at http://localhost:8080/greeting. 
- If successful, it will assign the JSON received to data, effectively making it a Greeting model object. The <strong>id</strong> and content are then appended to the <strong>greeting-id and greeting-content DOM elements</strong> respectively.
- Note the use of the <strong>jQuery promise .then()</strong>. This directs jQuery to execute the <strong>anonymous function</strong> when the <strong>$.ajax() method</strong> completes, passing the data result from the completed AJAX request.
- This <strong>javascript client</strong> uses <strong>jQuery</strong> to consume the REST service at http://localhost:8080/greeting. It is loaded by index.html nested inside the head element:

<pre>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="hello.js"></script>
</pre>

- The first script tag loads the <strong>minified jQuery library (jquery.min.js)</strong> from a <strong>content delivery network (CDN)</strong> so that you don???t have to download jQuery and place it in your project. 
- It also loads the controller code (<strong>hello.js</strong>) from the application???s path.
- Also note that the p tags (see index.html) include <strong>class attributes</strong>.
- These class attributes help jQuery to reference the HTML elements and update the text with the values from the id and content properties of the JSON received from the REST service.
- Once the app starts, open http://localhost:8080 in your browser, where you should see the following:

<pre>
The ID is 1
The content is, Hello World!
</pre>

- To <strong>test the CORS behaviour</strong>, you need to <strong>start the client from another server or port</strong>. Doing so not only avoids a collision between the two applications but also ensures that the client code is served from a different origin than the service. 
- To <strong>start the app running on localhost at port 9000</strong> (as well as the one that is already running on port 8080), run the following Maven command:

<pre>

./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=9000

</pre>

- Note: the spring guide uses the following command (instead of the previous one). This command did not work on my machine:

<pre> ./mvnw spring-boot:run -Dserver.port=9000 </pre>

- Once the app starts, open http://localhost:9000 in your browser, where you should see the following:

<pre>
The ID is 
The content is
</pre>

- If the service response includes the <strong>CORS headers</strong>, then the ID and content are rendered into the page. 
- But if the <strong>CORS headers are missing</strong> (or insufficient for the client), the browser fails the request and the values are not rendered into the DOM.

## Part IV - Spring MVC

### 14 - Serving Web Content with Spring MVC

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> serving-web-content.
- Refer to https://spring.io/guides/gs/serving-web-content/ if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of creating a ???Hello, World??? web site with Spring.
- You will build an application that has a static home page and that will also accept HTTP GET requests at: http://localhost:8080/greeting.
- It will respond with a web page that displays HTML. The body of the HTML will contain a greeting: ???Hello, World!???
- You can customize the greeting with an optional name parameter in the query string. The URL might then be http://localhost:8080/greeting?name=User.
- The name parameter value overrides the default value of World and is reflected in the response by the content changing to ???Hello, User!???
- Dependencies: Spring Web, Thymeleaf, and Spring Boot DevTools.

<strong>Web Controller</strong>

- In Spring???s approach to building web sites, HTTP requests are handled by a controller. You can easily identify the controller by the @Controller annotation. 
- In the following example, GreetingController handles GET requests for /greeting by returning the name of a View (in this case, greeting). A View is responsible for rendering the HTML content.

<pre>
@Controller
public class GreetingController {

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
}
</pre>

- The value of the name parameter is added to a Model object, ultimately making it accessible to the view template.
- The implementation of the method body relies on a view technology (in this case, Thymeleaf) to perform server-side rendering of the HTML. Thymeleaf parses the greeting.html template and evaluates the th:text expression to render the value of the ${name} parameter that was set in the controller.

<img align="center" width=817 height=261 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/greetinghtml.png"/>

<strong>Spring Boot Devtools</strong>

- A common feature of developing web applications is coding a change, restarting your application, and refreshing the browser to view the change. This entire process can eat up a lot of time. To speed up this refresh cycle, Spring Boot offers with a handy module known as spring-boot-devtools. Spring Boot Devtools:
	- Enables hot swapping.
	- Switches template engines to disable caching.
	- Enables LiveReload to automatically refresh the browser.
	- Other reasonable defaults based on development instead of production.

<strong>Test the Application</strong>

- Visit http://localhost:8080/greeting, where you should see ???Hello, World!???
- Provide a name query string parameter by visiting http://localhost:8080/greeting?name=User. Notice how the message changes from ???Hello, World!??? to ???Hello, User!???:

<strong>Home Page</strong>

- <strong>Static resources, including HTML and JavaScript and CSS</strong>, can be served from your Spring Boot application by dropping them into the right place in the source code. 
- By default, <strong>Spring Boot serves static content from resources in the classpath at /static (or /public)</strong>. 
- The index.html resource is special because, if it exists, it is used as a welcome page for the serving-web-content application, which means it is served up as the root resource
	- That is, at http://localhost:8080/

<img align="center" width=817 height=261 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/indexhtml.png"/>

### 15 - Handling Form Submission

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> handling-form-submission
- Refer to https://spring.io/guides/gs/handling-form-submission if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of using Spring to create and submit a web form, which will be accessible at the following URL: 
	- http://localhost:8080/greeting
- <strong>Dependencies</strong>: Spring Web and Thymeleaf.

<strong>Web Controller</strong>

<pre>
@Controller
public class GreetingController {

  <strong>@GetMapping("/greeting")</strong>
  public String greetingForm(<strong>Model</strong> model) {
    <strong>model.addAttribute("greeting", new Greeting());</strong>
    return "greeting";
  }

  <strong>@PostMapping("/greeting")</strong>
  public String greetingSubmit(<strong>@ModelAttribute</strong> Greeting greeting, <strong>Model model</strong>) {
    <strong>model.addAttribute("greeting", greeting);</strong>
    return <strong>"result"</strong>;
  }
}
</pre>

- The two methods in the controller are both mapped to /greeting. 
- You can use <strong>@RequestMapping</strong> (which, by default, maps all HTTP operations, such as GET, POST, and so forth). However, in this case, the greetingForm() method is specifically mapped to GET by using @GetMapping, while greetingSubmit() is mapped to POST with @PostMapping. 
- This mapping lets the controller differentiate the requests to the /greeting endpoint.
- The <strong>greetingForm()</strong> method uses a <strong>Model object</strong> to expose a new Greeting to the view template.


<strong>Model</strong>

<pre>
public class Greeting {

  private long id;
  private String content;
  
  ??? // Getters and Setters omitted
}
</pre>

- The fields <strong>id</strong> and <strong>content</strong> of the Greeting class <strong>correspond to the form fields in the greeting view</strong> and are used to capture the information from the form.
- The implementation of the method greetingSubmit relies on a view technology to perform server-side rendering of the HTML by converting the view name (greeting) into a template to render. 
- In this case, we use <strong>Thymeleaf</strong>, which parses the <strong>greeting.html template</strong> and evaluates the various template expressions to render the form.

<img align="center" width=807 height=433 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/greetingformhtml.png"/>

- The <strong>th:action="@{/greeting}" expression</strong> directs the form to POST to the <strong>/greeting endpoint</strong>, while the <strong>th:object="${greeting}" expression declares the model object to use for collecting the form data</strong>. 
- The two form fields, expressed with <strong>th:field="{id}"</strong> and <strong>th:field="{content}"</strong>, correspond to the fields in the Greeting object.
- the form submits to the /greeting endpoint by using a POST call. The greetingSubmit() method receives the Greeting object that was populated by the form. <strong>The Greeting is a @ModelAttribute</strong>, so it is bound to the incoming form content. 
- The submitted data can be rendered in the result view by referring to it by name (by default, the name of the method parameter, so <strong>greeting</strong> in this case). 
- The id is rendered in the <strong><p th:text="'id: ' + ${greeting.id}" /> expression</strong>. 
- Likewise, the content is rendered in the <strong><p th:text="'content: ' + ${greeting.content}" /> expression</strong>.

<img align="center" width=810 height=329 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/greetingresulthtml.png"/>

- This example uses two separate view templates for rendering the form and displaying the submitted data. However, you can use a single view for both purposes.

<strong>Make the Application Executable</strong>

- Although you can package this service as a traditional WAR file for deployment to an external application server, the simpler approach is to create a standalone application (JAR).
   - This selection is done when creating the project with Spring Initializr.
- You package everything in a single, executable JAR file, driven by a main() method. 
- You use Spring???s support for embedding the <strong>Tomcat servlet container as the HTTP runtime</strong>, instead of deploying to an external instance.

<strong>Test the service</strong>

- Visit http://localhost:8080/greeting

<img align="center" width=312 height=243 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/Form.png"/>

- Submit an ID and message to see the results:

<img align="center" width=326 height=235 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/Form_Result.png"/>

### 16 - Validating Form Input

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> validating-form-input
- Refer to https://spring.io/guides/gs/validating-form-input if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of configuring a web application form to support validation.
- You will build a simple <strong>Spring MVC application</strong> that takes user input and checks the input by using standard validation annotations.
- You will also see how to display the <strong>error message</strong> on the screen so that the user can re-enter input to make it be valid.
- Dependencies: Spring Web, Thymeleaf, and Validation.

<strong>PersonForm Object</strong>

- The application involves validating a user???s name and age, so you first need a class that backs the form used to create a person.

<pre>
public class PersonForm {

   <strong>@NotNull</strong>
   <strong>@Size(min=2, max=30)</strong>
   private String name;

   <strong>@NotNull</strong>
   <strong>@Min(18)</strong>
   private Integer age;

   ??? // Getters and setters omitted

   public String toString() {
	return "Person(Name: " + this.name + ", Age: " + this.age + ")";
   }
}
</pre>

- <strong>@Size(min=2, max=30)</strong>: Allows names between 2 and 30 characters long.
- <strong>@NotNull</strong>: Does not allow a null value, which is what Spring MVC generates if the entry is empty.
- <strong>@Min(18)</strong>: Does not allow the age to be less than 18.

<strong>Web Controller</strong>

<pre>
<strong>@Controller</strong>
public class WebController <strong>implements WebMvcConfigurer</strong> {

   <strong>@Override
   public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/results").setViewName("results");
   }</strong>

   @GetMapping("/")
   public String showForm(PersonForm personForm) {
	return "form";
   }

   @PostMapping("/")
   public String checkPersonInfo(<strong>@Valid PersonForm personForm, BindingResult bindingResult</strong>) {

	if (<strong>bindingResult.hasErrors()</strong>) {
		return "form";
	}

	return "<strong>redirect:/results</strong>";
   }
}
</pre>

- This controller has a GET method and a POST method. Both methods are mapped to /.

- The <strong>showForm method</strong> returns the form template. It includes a PersonForm in its method signature so that the template can associate form attributes with a PersonForm.
- The <strong>checkPersonInfo method</strong> accepts two arguments:
    - A <strong>personForm object</strong> marked with <strong>@Valid</strong> to gather the attributes filled out in the form.
    - A <strong>bindingResult object</strong> so that you can test for and retrieve validation errors.
- You can retrieve all the attributes from the form, which is bound to the <strong>PersonForm object</strong>. 
- In the code, you test for errors. If you encounter an error, you can send the user back to the original form template. In that situation, all the error attributes are displayed.
- If all of the person???s attribute are valid, it redirects the browser to the final results template.

<strong>HTML Front End</strong>

<img align="center" width=688 height=523 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/Form_Validating_Form_input.png"/>

- The form is geared to post to /
- It is marked as being backed up by the <strong>personForm object</strong> that you saw in the GET method in the web controller. This is known as a <strong>???bean-backed form???</strong>.
- There are two fields in the <strong>PersonForm bean</strong>, and you can see that they are tagged with <strong>th:field="*{name}" and th:field="*{age}"</strong>. 
- Next to each field is an element that is used to show any validation errors.
- In general, if the user enters a name or age that violates the <strong>@Valid constraints</strong>, it bounces back to this page with the error message displayed. If a valid name and age is entered, the user is routed to the next web page.

<strong>Run the application</strong>

@SpringBootApplication
public class ValidatingFormInputApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ValidatingFormInputApplication.class, args);
	}
}

- To activate Spring MVC, you would normally add <strong>@EnableWebMvc to the Application class</strong>. 
- But Spring Boot???s <strong>@SpringBootApplication</strong> already adds this annotation when it detects <strong>spring-webmvc</strong> on your classpath. This same annotation lets it find the annotated <strong>@Controller</strong> class and its methods.
- The Thymeleaf configuration is also taken care of by @SpringBootApplication. By default, templates are located in the classpath under <strong>templates/</strong> and are resolved as views by stripping the '.html' suffix off the file name. 

<strong>Testing the application</strong>

- If you visit http://localhost:8080/, you should see something like the following image:

<img align="center" width=524 height=300 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/valid-01.png"/>

- The following pair of images show what happens if you enter N for the name and 15 for your age and click on Submit:

<img align="center" width=524 height=300 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/valid-02.png"/>

<img align="center" width=524 height=300 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/valid-03.png"/>

- The preceding images show that, because the values violated the constraints in the PersonForm class, you get bounced back to the ???main??? page. 
- Note that, if you click on Submit with nothing in the entry box, you get a different error, as the following image shows:

<img align="center" width=524 height=300 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/valid-04.png"/>

- If you enter a valid name and age, you end up on the results page, as the following image shows:

<img align="center" width=524 height=300 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/valid-05.png"/>

### 17 - Uploading Files

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> uploading-files
- Refer to https://spring.io/guides/gs/uploading-files if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of creating a server application that  handle file uploads by receiving <strong>HTTP multi-part file uploads</strong>.
- The example also build a simple HTML interface to upload a test file.
- <strong>Dependencies</strong>: Spring Web and Thymeleaf.

<strong>Application class</strong>

- To upload files with Servlet containers, you need to register a <strong>MultipartConfigElement class</strong> (which would be <multipart-config> in web.xml).
- As part of <strong>auto-configuring Spring MVC</strong>, Spring Boot will create a MultipartConfigElement bean and make itself ready for file uploads.

<strong>File upload controller</strong>

<pre>
@Controller
public class FileUploadController {

	private final <strong>StorageService storageService</strong>;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(<strong>Model model</strong>) throws IOException {

		<strong>model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));</strong>

		return "uploadForm";
	}

	@GetMapping("/files/{filename:.+}")
	<strong>@ResponseBody</strong>
	public <strong>ResponseEntity<Resource></strong> serveFile(<strong>@PathVariable</strong> String filename) {

		<strong>Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);</strong>
	}

	@PostMapping("/")
	public String handleFileUpload(@RequestParam("file") <strong>MultipartFile file</strong>,
			<strong>RedirectAttributes redirectAttributes</strong>) {

		storageService.store(file);
		<strong>redirectAttributes.addFlashAttribute("message",
				"You successfully uploaded " + file.getOriginalFilename() + "!");</strong>

		return "redirect:/";
	}

	<strong>@ExceptionHandler(StorageFileNotFoundException.class)</strong>
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return <strong>ResponseEntity.notFound().build()</strong>;
	}
}
</pre>

- The <strong>FileUploadController class</strong> is annotated with <strong>@Controller</strong> so that Spring MVC can pick it up and look for routes.
- Each method is tagged with @GetMapping or @PostMapping to tie the path and the HTTP action to a particular controller action.
- <strong>GET /</strong>: Looks up the current list of uploaded files from the StorageService and loads it into a Thymeleaf template. It calculates a link to the actual resource by using <strong>MvcUriComponentsBuilder</strong>.
- <strong>GET /files/{filename}</strong>: Loads the resource (if it exists) and sends it to the browser to download by using a <strong>Content-Disposition response header</strong>.
- <strong>POST /</strong>: Handles a <strong>multi-part message file</strong> and gives it to the StorageService for saving.
- In a production scenario, you more likely would store the files in a temporary location, a database, or perhaps a NoSQL store (such as Mongo???s GridFS). <strong>It is best to NOT load up the file system of your application with content</strong>.

<strong>Storage service</strong>

<pre>
public interface StorageService {

   void init();

   void store(<strong>MultipartFile file</strong>);

   <strong>Stream<Path></strong> loadAll();

   <strong>Path</strong> load(String filename);

   <strong>Resource</strong> loadAsResource(String filename);

   void deleteAll();

}
</pre>

<strong>HTML template</strong>

- The following Thymeleaf template shows an example of how to upload files and show what has been uploaded:

<img align="center" width=688 height=523 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/HTML_Template_Uploading_Files.png"/>


- This template has three parts:
   - An optional message at the top where Spring MVC writes a <strong>flash-scoped message</strong>.
   - A form that lets the user upload files.
   - A list of files supplied from the backend.

<strong>Tuning File Upload Limits</strong>

- When configuring file uploads, it is often useful to set limits on the size of files. Imagine trying to handle a 5GB file upload! With Spring Boot, we can tune its auto-configured MultipartConfigElement with some property settings.
- Add the following properties to your existing properties settings (in <strong>src/main/resources/application.properties</strong>):

<pre>
spring.servlet.multipart.max-file-size=128KB
spring.servlet.multipart.max-request-size=128KB
</pre>

- The multipart settings are constrained as follows:
   - <strong>spring.servlet.multipart.max-file-size</strong> is set to 128KB, meaning total file size cannot exceed 128KB.
   - <strong>spring.servlet.multipart.max-request-size</strong> is set to 128KB, meaning total request size for a multipart/form-data cannot exceed 128KB.

<strong>Run the application</strong>

- You want a target folder to which to upload files, so you need to enhance the basic <strong>UploadingFilesApplication class</strong> that Spring Initializr created and add a Boot <strong>CommandLineRunner</strong> to delete and re-create that folder at startup. 

<pre>
@SpringBootApplication
<strong>@EnableConfigurationProperties(StorageProperties.class)</strong>
public class UploadingFilesApplication {
   ...
   ...
	
   @Bean
   CommandLineRunner init(StorageService storageService) {
	<strong>return (args) -> {
	   storageService.deleteAll();
	   storageService.init();</strong>
	};
   }
}
</pre>

<strong>Testing the application</strong>

- With the server running, you need to open a browser and visit http://localhost:8080/ to see the upload form. 
- Pick a (small) file and press Upload. You should see the success page from the controller. If you choose a file that is too large, you will get an ugly error page.
- You should then see a line resembling the following in your browser window: <strong>???You successfully uploaded <name of your file>!???</strong>

<strong>Automated testing</strong>

<pre>
<strong>@AutoConfigureMockMvc
@SpringBootTest</strong>
public class FileUploadTests {

   @Autowired
   private <strong>MockMvc</strong> mvc;

   <strong>@MockBean</strong>
   private StorageService storageService;

   <strong>@Test</strong>
   public void shouldListAllFiles() throws Exception {
	<strong>given(this.storageService.loadAll())
			.willReturn(Stream.of(Paths.get("first.txt"), Paths.get("second.txt")));

	this.mvc.perform(get("/")).andExpect(status().isOk())
				.andExpect(model().attribute("files",
						Matchers.contains("http://localhost/files/first.txt",
								"http://localhost/files/second.txt")));</strong>
   }

   <strong>@Test</strong>
   public void shouldSaveUploadedFile() throws Exception {
	<strong>MockMultipartFile multipartFile = new MockMultipartFile("file", "test.txt",
				"text/plain", "Spring Framework".getBytes());
	this.mvc.perform(multipart("/").file(multipartFile))
				.andExpect(status().isFound())
				.andExpect(header().string("Location", "/"));

	then(this.storageService).should().store(multipartFile);</strong>
   }

   @SuppressWarnings("unchecked")
   <strong>@Test</strong>
   public void should404WhenMissingFile() throws Exception {
	<strong>given(this.storageService.loadAsResource("test.txt"))
			.willThrow(StorageFileNotFoundException.class);

	this.mvc.perform(get("/files/test.txt")).andExpect(status().isNotFound());</strong>
	}
}
</pre>

- In those tests, you use various <strong>mocks</strong> to set up the interactions with your controller and the StorageService but also with the Servlet container itself by using <strong>MockMultipartFile</strong>.
- For an example of an integration test, see the <strong>FileUploadIntegrationTests class</strong> (which is in src/test/java/com/example/uploadingfiles).


### 18 - Testing the Web Layer

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> testing-web
- Refer to https://spring.io/guides/gs/testing-web/ if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of creating a Spring application and then testing it with JUnit and Spring MockMvc. 
- The example also demonstrates how to isolate the web layer and load a special application context.
- <strong>Dependencies</strong>: Spring Web.

<strong>Home Controller</strong>

<pre>
<strong>@Controller</strong>
public class HomeController {

	<strong>@RequestMapping("/")</strong>
	public @ResponseBody String greeting() {
		return "Hello, World";
	}
}
</pre>

<strong>Test the application</strong>

- Loading the home page at http://localhost:8080 will work, however, to give yourself more confidence that the application works when you make changes, you want to automate the testing.
- For example, below is a simple <strong>sanity check test</strong> that will fail if the application context cannot start.

<pre>
<strong>@SpringBootTest</strong>
public class TestingWebApplicationTests {

	<strong>@Test</strong>
	public void contextLoads() {
	}
}
</pre>

- The <strong>@SpringBootTest annotation</strong> tells Spring Boot to look for a <strong>main configuration class</strong> (one with <strong>@SpringBootApplication</strong>, for instance) and use that to start a <strong>Spring application context</strong>. 
- You can run this test in your IDE or on the command line (by running <strong>./mvnw test</strong> or <strong>./gradlew test</strong>), and it should pass. 
- To convince yourself that the context is creating your controller, you could add an assertion, as the following example

<pre>
@SpringBootTest
public class SmokeTest {

	<strong>@Autowired</strong>
	private HomeController controller;

	<strong>@Test</strong>
	public void contextLoads() throws Exception {
		<strong>assertThat(controller).isNotNull();</strong>
	}
}
</pre>

- Spring interprets the <strong>@Autowired annotation</strong>, and the <strong>controller is injected before the test methods are run</strong>. 
- We use <strong>AssertJ (which provides assertThat() and other methods)</strong> to express the test assertions.
- A nice feature of the Spring Test support is that the <strong>application context is cached between tests</strong>. That way, if you have multiple methods in a test case or multiple test cases with the same configuration, they incur the cost of starting the application only once.
- It is nice to have a <strong>sanity check</strong>, but you should also write some tests that assert the behavior of your application. To do that, you could start the application and listen for a connection (as it would do in production) and then send an HTTP request and assert the response as the following example:

<pre>
<strong>@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)</strong>
public class HttpRequestTest {

	<strong>@Value(value="${local.server.port}")</strong>
	private int port;

	@Autowired
	private <strong>TestRestTemplate restTemplate</strong>;

	@Test
	public void greetingShouldReturnDefaultMessage() throws Exception {
		<strong>assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("Hello, World");</strong>
	}
}
</pre>

- Note the use of <strong>webEnvironment=RANDOM_PORT to start the server with a random port</strong> (useful to avoid conflicts in test environments) and the <strong>injection of the port with @LocalServerPort</strong>. 
- Also, note that Spring Boot has automatically provided a <strong>TestRestTemplate</strong> for you. All you have to do is add @Autowired to it.
- Another useful approach is to <strong>not start the server at all but to test only the layer</strong> below that, where Spring handles the incoming HTTP request and hands it off to your controller. 
	- That way, almost of the full stack is used, and your code will be called in exactly the same way as if it were processing a real HTTP request but without the cost of starting the server. 
	- To do that, use <strong>Spring???s MockMvc</strong> and ask for that to be injected for you by using the <strong>@AutoConfigureMockMvc</strong> annotation on the test case as in the following example:

<pre>
@SpringBootTest
<strong>@AutoConfigureMockMvc</strong>
public class TestingWebApplicationTest {

	@Autowired
	private <strong>MockMvc mockMvc</strong>;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		<strong>this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));</strong>
	}
}
</pre>

- In this test, the <strong>full Spring application context is started</strong> but without the server. 
- We can narrow the tests to only the <strong>web layer</strong> by using <strong>@WebMvcTest</strong>, as the following

<pre>
<strong>@WebMvcTest</strong>
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		<strong>this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));</strong>
	}
}
</pre>

- The test assertion is the same as in the previous case. However, in this test, <strong>Spring Boot instantiates only the web layer rather than the whole context</strong>. 
- In an application with <strong>multiple controllers</strong>, you can even ask for <strong>only one to be instantiated by using, for example, @WebMvcTest(HomeController.class)</strong>.
- So far, our <strong>HomeController</strong> is simple and has no dependencies. We could make it more realistic by introducing an extra component to store the greeting (perhaps in a new controller), as in the following example:

<pre>
<strong>@Controller</strong>
public class GreetingController {

	private <strong>final GreetingService service</strong>;

	public GreetingController(GreetingService service) {
		this.service = service;
	}

	<strong>@RequestMapping("/greeting")</strong>
	public <strong>@ResponseBody </strong> String greeting() {
		return service.greet();
	}
}
</pre>

- Then create a greeting service, as the following listing:

<pre>
<strong>@Service</strong>
public class GreetingService {
	public String greet() {
		return "Hello, World";
	}
}
</pre>

- <strong>Spring automatically injects the service dependency into the controller</strong> (because of the constructor signature).

- to test this controller with <strong>@WebMvcTest</strong>:

<pre>
<strong>@WebMvcTest(GreetingController.class)</strong>
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	<strong>@MockBean</strong>
	private GreetingService service;

	@Test
	public void greetingShouldReturnMessageFromService() throws Exception {
		<strong>when(service.greet()).thenReturn("Hello, Mock");
		this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, Mock")));</strong>
	}
}
</pre>

- We use <strong>@MockBean to create and inject a mock for the GreetingService</strong> (if you do not do so, the application context cannot start), and we set its expectations using <strong>Mockito</strong>.

## Part V - Spring Security

### 19 - Securing a web application

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> securing-web
- Refer to https://spring.io/guides/gs/securing-web/ if you are interested on more information about this example.

<strong>Introduction</strong>

- This example walks you through the process of creating a simple web application with resources that are protected by <strong>Spring Security</strong>.
- The example shows a Spring MVC application that secures the page with a login form.
- <strong>Dependencies</strong>: Spring Web and Thymeleaf.

<strong>An Unsecured Web Application</strong>

- Initially, the example demonstrates a web application includes two simple views: a <strong>home page</strong> and a <strong>???Hello, World??? page</strong>. 
- The <strong>home page</strong> is defined in the following <strong>Thymeleaf template</strong>:

<img align="center" width=785 height=252 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-html-1.png"/>

- This simple view includes a link to the <strong>/hello page</strong>, which is defined in the following <strong>Thymeleaf template</strong>:

<img align="center" width=789 height=205 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-html-2.png"/>

- The web application is based on Spring MVC. As a result, you need to configure Spring MVC and set up view controllers to expose these templates as the following example:

<pre>
@Configuration
public class MvcConfig implements WebMvcConfigurer {

   public void addViewControllers(ViewControllerRegistry registry) {
	registry.addViewController("/home").setViewName("home");
	registry.addViewController("/").setViewName("home");
	registry.addViewController("/hello").setViewName("hello");
	registry.addViewController("/login").setViewName("login");
   }
}
</pre>

- The <strong>addViewControllers()</strong> method (which overrides the method of the same name in <strong>WebMvcConfigurer</strong>) adds four view controllers.

<strong>Set up Spring Security</strong>

- Suppose that you want to prevent unauthorized users from viewing the greeting page at <strong>/hello</strong>.
- You need to add a barrier that forces the visitor to sign in before they can see that page.
	- You do that by configuring <strong>Spring Security</strong> in the application.
- If <strong>Spring Security is on the classpath</strong>, Spring Boot automatically secures all <strong>HTTP endpoints with ???basic??? authentication</strong>. 
	-  However, you can further customize the security settings.
- With <strong>Apache Maven</strong>, you need to add two extra entries (one for the application and one for testing):

<img align="center" width=603 height=313 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-maven.png"/>


- The following <strong>security configuration</strong> ensures that only <strong>authenticated users</strong> can see the secret greeting:

<pre>
<strong>@Configuration
@EnableWebSecurity</strong>
public class WebSecurityConfig {

   <strong>@Bean</strong>
   public <strong>SecurityFilterChain securityFilterChain(HttpSecurity http)</strong> throws Exception {
	<strong>http
		.authorizeHttpRequests((requests) -> requests
			.requestMatchers("/", "/home").permitAll()
			.anyRequest().authenticated()
		)
		.formLogin((form) -> form
			.loginPage("/login")
			.permitAll()
		)
		.logout((logout) -> logout.permitAll());

	return http.build();</strong>
   }

   <strong>@Bean</strong>
   public <strong>UserDetailsService</strong> userDetailsService() {
	<strong>UserDetails user =
		User.withDefaultPasswordEncoder()
			.username("user")
			.password("password")
			.roles("USER")
			.build();

	return new InMemoryUserDetailsManager(user);</strong>
   }
}
</pre>

- The <strong>WebSecurityConfig class</strong> is annotated with <strong>@EnableWebSecurity</strong> to enable Spring Security???s web security support and provide the Spring MVC integration.
- It also exposes two beans to set some specifics for the web security configuration:
   - The <strong>SecurityFilterChain bean</strong> defines which URL paths should be secured and which should not. Specifically, the <strong>/</strong> and <strong>/home</strong> paths are configured to not require any authentication. All other paths must be authenticated.
   	- When a user successfully logs in, they are redirected to the previously requested page that required authentication. There is a <strong>custom /login page</strong> (which is specified by loginPage()), and everyone is allowed to view it.
   - The <strong>UserDetailsService bean</strong> sets up an <strong>in-memory user store with a single user</strong>. That user is given a <strong>user name of user</strong>, a <strong>password of password</strong>, and a <strong>role of USER</strong>.

<strong>Login page</strong>

- The following <strong>Thymeleaf template</strong> presents a form that captures a <strong>username and password</strong> and posts them to <strong>/login:</strong>

<img align="center" width=865 height=461 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-html-3.png"/>

- If the user <strong>fails to authenticate</strong>, the page is redirected to <strong>/login?error</strong>, and your page displays the appropriate error message. 

<img align="center" width=525 height=137 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-wrong-user.png"/>

<img align="center" width=518 height=163 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-failed-authentication.png"/>

- Upon <strong>successfully signing out</strong>, your application is sent to <strong>/login?logout</strong>, and your page displays the appropriate success message.
- To display the <strong>current user name and sign out</strong>, update the <strong>hello.html</strong> to say hello to the current user and contain a Sign Out form:

<img align="center" width=780 height=317 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-html-4.png"/>

- We display the username by using <strong>Thymeleaf???s integration with Spring Security</strong>. 
- The <strong>???Sign Out??? form</strong> submits a POST to <strong>/logout</strong>. 
- Upon <strong>successfully logging out</strong>, it redirects the user to <strong>/login?logout</strong>.

<strong>Run the application</strong>

- Point your browser to http://localhost:8080. You should see the home page, as the following image shows:

<img align="center" width=516 height=201 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-index.png"/>

- When you click on the link, it attempts to take you to the greeting page at <strong>/hello</strong>. However, because that page is secured and you have not yet logged in, it takes you to the <strong>login page</strong>, as the following image shows:

<img align="center" width=520 height=143 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-form.png"/>

- At the <strong>login page</strong>, sign in as the test user by entering <strong>user and password for the username and password fields</strong>, respectively. 

<img align="center" width=516 height=138 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-valid-user.png"/>

- Once you <strong>submit the login form</strong>, you are authenticated and then taken to the <strong>greeting page</strong>, as the following image shows:

<img align="center" width=493 height=177 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-successful-authentication.png"/>

- If you click on the <strong>Sign Out button</strong>, your authentication is revoked, and you are returned to the <strong>login page</strong> with a message indicating that you are logged out.

<img align="center" width=518 height=163 src="https://github.com/pagliares/spring-boot-guides/blob/main/Images/securing-web/securing-web-logout.png"/>


## Part VI - Docker Containers

### 20 - Spring Boot with Docker

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- <strong>Project source:</strong> spring-boot-docker
- Refer to https://spring.io/guides/gs/spring-boot-docker/ if you are interested on more information about this example.
	- Important: The guide on this link mentions the installation of boot2docker if you are on a MacOS platform. This is not needed anymore (January, 2023).
	- Important: The guide uses on Dockerfile the entry <strong>FROM openjdk:8-jdk-alpine</strong>. We decided to use a newer version instead (<strong>FROM eclipse-temurin:17-jdk-focal</strong>).

<strong>Introduction</strong>

- This example walks you through the process of <strong>building a Docker image and create a Docker container</strong> for running a Spring Boot application.
- By default, <strong>Spring Boot applications run on port 8080 inside the container</strong>, and we mapped that to the same port on the host by using <strong>-p</strong> on the command line.
- We start with a <strong>basic Dockerfile</strong> and make a few tweaks. Then we show a couple of options that use <strong>build plugins</strong> (for Maven and Gradle) instead of docker.
- A <strong>Docker image</strong> is a recipe for <strong>running a containerized process</strong>.
- <strong>Pre-requisite for this example</strong>: 
	- <strong>Docker desktop</strong> installed: 
		- https://docs.docker.com/get-docker/#installation
- <strong>Dependencies</strong>: Spring Web

<strong>Spring Boot Application</strong>

<pre>
@SpringBootApplication
<strong>@RestController</strong>
public class Application {

  @RequestMapping("/")
  public String home() {
    return "Hello Docker World";
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
</pre>

- The Application class is flagged as a <strong>@SpringBootApplication</strong> and as a <strong>@RestController</strong>, meaning that it is ready for use by Spring MVC to handle web requests. 
- <strong>@RequestMapping maps / to the home() method</strong>, which sends a Hello World response. 

<strong>Running the application without the Docker container</strong> (that is, in the host OS)

- Go to localhost:8080 to see your ???Hello Docker World??? message.

<strong>Containerize the application</strong>

- Docker has a simple <strong>"Dockerfile" file format</strong> that it uses to <strong>specify the ???layers??? of an image</strong>. 
- <strong>Example 1:</strong>

<pre>
FROM eclipse-temurin:17-jdk-focal
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
</pre>

- If you use <strong>Apache Maven</strong>, you can run it with the following command:

<pre>docker build -t springio/gs-spring-boot-docker . </pre>

- This command <strong>builds an image</strong> and tags it as <strong>springio/gs-spring-boot-docker</strong>.

- The <strong>build creates a spring user and a spring group</strong> to run the application. 
- It is then copied (by the <strong>COPY command</strong>) the project <strong>JAR file</strong> into the container as <strong>app.jar</strong>, which is <strong>run in the ENTRYPOINT</strong>. 
- The array form of the <strong>Dockerfile ENTRYPOINT</strong> is used so that there is no shell wrapping the Java process.
- <strong>Running applications with user privileges helps to mitigate some risks</strong>. 
	- So, an <strong>important improvement to the Dockerfile</strong> is to <strong>run the application as a non-root user</strong>.

