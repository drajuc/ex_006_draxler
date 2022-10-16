package com.example.ex_006_draxler.db;

import com.example.ex_006_draxler.pojo.Customer;
import jakarta.ws.rs.NotFoundException;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDatabaseStatic {
    private List<Customer> customerList = new ArrayList<>();

    private static CustomerDatabaseStatic theInstance = null;


    public synchronized static CustomerDatabaseStatic getInstance() {
        if (theInstance == null) {
            theInstance = new CustomerDatabaseStatic();
        }
        return theInstance;
    }

    public List<Customer> getCustomers() {
        return customerList;
    }

    public synchronized void addCustomer(Customer customer) {
        Optional<Customer> existingCustomer = customerList.stream().filter(existing -> existing.getId() == customer.getId()).findFirst();
        if (existingCustomer.isPresent()) {
            throw new KeyAlreadyExistsException();
        }
        customerList.add(customer);
    }

    public Customer findCustomerById(int id) {
        Optional<Customer> customer = customerList.stream().filter(c -> c.getId() == id).findFirst();

        return customer.orElseThrow(() -> {
            String msg = String.format("ID (%d) not found", id);
            return new NotFoundException(msg);
        });
    }

    public Customer removeCustomer(int id) {
        Optional<Customer> existingCustomer = customerList.stream().filter(existing -> existing.getId() == id).findFirst();
        if (!existingCustomer.isPresent()) {
            throw new NotFoundException();
        }
        Customer customer = existingCustomer.get();
        customerList.remove(customer);
        return customer;
    }

    public void replaceCustomer(Customer customer) {

    }
}
