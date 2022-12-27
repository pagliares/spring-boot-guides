package com.example.relationaldataaccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RelationalDataAccessApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(RelationalDataAccessApplication.class);
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.info("Creating tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS");
        /* SERIAL (used in PostgreSQL and h2, although deprecated) is similar to auto_increment in MySQL
           It looks IDENTIY is the replacement for SERIAL
           https://vladmihalcea.com/postgresql-serial-column-hibernate-identity/
        */
        jdbcTemplate.execute("CREATE TABLE customers(" + "id IDENTITY, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> splitUpNames =
                        Arrays.asList("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long").stream()
                        .map(name -> name.split(" "))
                        .collect(Collectors.toList());

        // Use a Java 8 stream to print out each tuple of the list
        splitUpNames.forEach(name ->
                log.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        // For single insert statements, the insert method of JdbcTemplate is good.
        // However, for multiple inserts, it is better to use batchUpdate.
        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)", splitUpNames);

        log.info("Querying for customer records where first_name = 'Josh':");
        jdbcTemplate.query(
                "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
                new Object[] { "Josh" },
                (rs, rowNum) -> new Customer(rs.getLong("id"),
                        rs.getString("first_name"), rs.getString("last_name"))
         ).forEach(customer -> log.info(customer.toString()));
    }

    public static void main(String[] args) {
        SpringApplication.run(RelationalDataAccessApplication.class, args);
    }

}
