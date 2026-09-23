package com.amc.bfsi.repository;

import com.amc.bfsi.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository                       // tells Spring to create and manage this object
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<Integer, Customer> store = new LinkedHashMap<>();

    @Override
    public void save(Customer customer) {
        store.put(customer.getCustomerId(), customer);
    }

    @Override
    public Customer findById(int id) {
        return store.get(id);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList<>(store.values());
    }
}
