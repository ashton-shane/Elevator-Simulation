package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.controller;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Company;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Customer;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Person;

import java.util.ArrayList;

public class AccountController {
    ArrayList<Customer> customers;

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void removeCustomer(Customer customer){
        customer.remove(customer);
    }
    public void createCustomer(String name, String address, String type) {
        switch(type) {
            case "person":
                Person person = new Person(name, address);
            case "company":
                Company company = new Company(name, address);
        }
    }

}
