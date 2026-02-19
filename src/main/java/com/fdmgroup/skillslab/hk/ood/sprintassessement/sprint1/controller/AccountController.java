package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.controller;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.*;

import java.util.ArrayList;

public class AccountController {
    private ArrayList<Customer> customers;
    private ArrayList<Account> accounts;

    public Customer createCustomer(String name, String address, String type) {
        switch(type) {
            case "person":
                Person person = new Person(name, address);
                customers.add(person);
                return person;
            case "company":
                Company company = new Company(name, address);
                customers.add(company);
                return company;
        }
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);
        customer.getAccounts().clear();
    }

    public Account createAccount(Customer customer, String type) {
        switch(type) {
            case "checking":
                CheckingAccount checkingAccount = new CheckingAccount();
                accounts.add(checkingAccount);
                customer.addAccount(checkingAccount);
                return checkingAccount;
            case "savings":
                SavingsAccount savingsAccount = new SavingsAccount();
                accounts.add(savingsAccount);
                customer.addAccount(savingsAccount);
                return savingsAccount;
        }
    }

    public void removeAccount(Account account){
        accounts.remove(account);

    }


    // getters and setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
