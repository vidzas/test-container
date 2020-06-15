package com.example.demo;

import com.example.demo.mongo.Customer;
import com.example.demo.mongo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class HelloController {

    @Autowired
    private CustomerRepository repository;

    @RequestMapping("/loko/show")
    public String show() {

        repository.deleteAll();

        // save a couple of customers
        // repository.save(new Customer("Alice", "Smith"));
        // repository.save(new Customer("Bob", "Smith"));

        String results = "showing: ";
        for (Customer customer : repository.findAll()) {
            results = results + customer.firstName;
        }
        return results;
    }

    @RequestMapping("/loko/delete")
    public String delete() {
        repository.deleteAll();
        return "deleted";
    }

    @RequestMapping(value = "/loko/add/{id}", method = GET)
    public String add(@PathVariable("id") String id) {
        repository.save(new Customer(id, id));
        return "added";
    }
}
