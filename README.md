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
