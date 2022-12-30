# Spring Boot Guides

Repository with examples illustrated at Spring Boot QuickStart Guides (https://spring.io/quickstart e https://spring.io/guides). The examples are used in the classes about Spring Boot, taught by Rodrigo Martins Pagliares in the Computer Science undergraduate course at UNIFAL-MG, Brazil.

## Outline

<p><a href="https://github.com/pagliares/spring-boot-guides#01---demo">01 - demo</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#02---command-line-spring-boot">02 - command-line-spring-boot</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#03---spring-boot">03 - spring-boot</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#04---accessing-data-mysql">04 - accessing-data-mysql</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#05---relational-data-access">05 - relational-data-access</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#06---managing-transactions">06 - managing-transactions</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#07---accessing-data-jpa">07 - accessing-data-jpa</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#08---accessing-data-rest">08 - accessing-data-rest</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#09---rest-service">09 - rest-service</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#10---quoters-and-consuming-rest">10 - quoters and consuming-rest</a></p>
<p><a href="https://github.com/pagliares/spring-boot-guides#11---actuator-service">11 - actuator-service</a></p>


## Part I - First steps with Spring Boot

### 01 - demo

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
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

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
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

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
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

## Part II - Persistence with Spring Boot

### 04 - accessing-data-mysql

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- This example demonstrates how to develop a Spring application that is bound to a MySQL database and is ready for production.

### 05 - relational-data-access

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/relational-data-access/ if you are interested on more information about this example.
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
    …
    private final JdbcTemplate jdbcTemplate;
    …
    …
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
- This example walks you through the process of creating a <strong>“Hello, World” RESTful web service with Spring</strong>.
- Dependencies: <strong>Spring Web</strong>.
- The example uses the <strong>Jackson JSON library</strong> to automatically marshal instances of type Greeting into JSON. Jackson is included by default by the <strong>web starter</strong>.
- You will build a service that will accept HTTP GET requests at http://localhost:8080/greeting. It will respond with a JSON representation of a greeting, as the following listing shows:

<pre>{"id":1,"content":"Hello, World!"}</pre>

- You can customize the greeting with an optional name parameter in the query string, as the following listing shows:

<pre>http://localhost:8080/greeting?name=User</pre>

<pre>{"id”:2,”content":"Hello, User!"}</pre>

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

- In Spring’s approach to building RESTful web services, <strong>HTTP requests are handled by a controller</strong>. 
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
- The Greeting object must be converted to JSON. Thanks to Spring’s HTTP message converter support, you need not do this conversion manually. Because <strong>Jackson 2</strong> is on the classpath, Spring’s <strong>MappingJackson2HttpMessageConverter</strong> is automatically chosen to convert the Greeting instance to JSON.
- Notice also how the <strong>id attribute</strong> has changed from 1 to 2 and so on after each request. This proves that you are working against the <strong>same GreetingController instance across multiple requests</strong> and that its counter field is being incremented on each call as expected.

### 10 - quoters and consuming-rest

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/consuming-rest/ if you are interested on more information about this example.
- This example walks you through the process of creating an <strong>application that consumes a RESTful web service</strong>.
- You will build an application that uses <strong>Spring’s RestTemplate</strong> to retrieve a <strong>random Spring Boot quotation</strong> at http://localhost:8080/api/random
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
- This example takes you through creating a <strong>“Hello, world” RESTful web service with Spring Boot Actuator</strong>. 
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
- The <strong>@SpringBootApplication</strong> annotation provides a load of defaults (like the <strong>embedded servlet container</strong>), depending on the contents of your classpath and other things. It also turns on <strong>Spring MVC’s @EnableWebMvc</strong> annotation, which activates web endpoints.

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
- The <strong>@ResponseBody</strong> annotation tells Spring MVC not to render a model into a view but, rather, to write the returned object into the response body. It does so by using one of Spring’s message converters. Because <strong>Jackson 2 is in the classpath</strong>, MappingJackson2HttpMessageConverter will handle the conversion of a Greeting object to JSON if the <strong>request’s Accept header specifies that JSON should be returned</strong>.
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
        …

    }

    <strong>@Test</strong>
    public void <strong>shouldReturn200WhenSendingRequestToManagementEndpoint</strong>() throws Exception {
       …
    }

}
</pre>

### 12 - rest-hateoas

- <small><a href="https://github.com/pagliares/spring-boot-guides#outline">Back to Outline</a></small>
- Refer to https://spring.io/guides/gs/rest-hateoas/ if you are interested on more information about this example.
- This example walks you through the process of creating a <strong>“Hello, World” Hypermedia-driven REST web service with Spring HATEOAS</strong>.
- Hypermedia is an important aspect of REST. It lets you build services that <strong>decouple client and server to a large extent and let them evolve independently</strong>. 
- <strong>The representations returned for REST resources contain not only data but also links to related resources</strong>. Thus, the design of the representations is crucial to the design of the overall service.
- <strong>Spring HATEOAS</strong>: a library of APIs that you can use to create links that point to Spring MVC controllers, build up resource representations, and control how they are rendered into supported hypermedia formats (such as HAL).
- The service presented in this example will accept HTTP GET requests at .
<pre>
http://localhost:8080/greeting

{
  "content":"Hello, World!",
  "_links":{
    "self":{
      <strong>"href":"http://localhost:8080/greeting?name=World"</strong>
    }
  }
}
</pre>

- The response is a JSON representation of a greeting with the simplest possible hypermedia element, a link that points to the resource itself.
- The response already indicates that you can customize the greeting with an optional name parameter in the query string:

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

- <strong>Dependencies</strong> used in this example (spring boot Maven project): Spring HATEOAS.

<strong>Create a Resource Representation Class</strong>

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

<strong>Create a REST Controller</strong>

- In Spring’s approach to building RESTful web services, HTTP requests are handled by a controller. 
- We use the @RestController annotation, which combines the <strong>@Controller</strong> and <strong>@ResponseBody</strong> annotations.

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

- The most interesting part of the method implementation is how you create the link that points to the controller method and how you add it to the representation model. Both linkTo(…) and methodOn(…) are static methods on ControllerLinkBuilder that let you fake a method invocation on the controller. The returned LinkBuilder will have inspected the controller method’s mapping annotation to build up exactly the URI to which the method is mapped.

- The call to withSelfRel() creates a Link instance that you add to the Greeting representation model.


Test the Service
- Now that the service is up, visit http://localhost:8080/greeting, where you should see the following content:
{
  "content":"Hello, World!",
  "_links":{
    "self":{
      "href":"http://localhost:8080/greeting?name=World"
    }
  }
}

http://localhost:8080/greeting?name=User

{
  "content":"Hello, User!",
  "_links":{
    "self":{
      "href":"http://localhost:8080/greeting?name=User"
    }
  }
}





