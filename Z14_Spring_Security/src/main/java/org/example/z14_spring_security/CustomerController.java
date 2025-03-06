package org.example.z14_spring_security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    private List<Customer> customers = new ArrayList<Customer>(
            List.of(new Customer(1,"Harsha"),
                    new Customer(2,"Charith")
            )
    );

    @GetMapping
    public List<Customer> getCustomers() {
        return customers;
    }

    @PostMapping
    public void addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
    }

    //generate token
    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
