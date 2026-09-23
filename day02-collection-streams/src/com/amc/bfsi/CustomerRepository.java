package com.amc.bfsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Day 2 - in-memory store. This same class becomes a Spring bean on Day 3. */
public class CustomerRepository {

    private final Map<Integer, Customer> store = new HashMap<>();

    public void save(Customer customer) {
        store.put(customer.getCustomerId(), customer);
    }

    public Customer findById(int id) throws CustomerNotFoundException {
        Customer customer = store.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("No customer with id " + id);
        }
        return customer;
    }

    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }

    public boolean deleteById(int id) {
        return store.remove(id) != null;
    }
}
