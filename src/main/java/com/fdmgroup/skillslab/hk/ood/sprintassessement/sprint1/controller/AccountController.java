package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.controller;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.*;

import java.util.ArrayList;

public class AccountController {
    ArrayList<Customer> customers;
    ArrayList<Account> accounts;

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
    }

    public Account createAccount(double balance, String type) {
        switch(type) {
            case "checking":
                CheckingAccount checkingAccount = new CheckingAccount(balance);
                accounts.add(checkingAccount);
                Customer.addAccount(checkingAccount);
                return checkingAccount;
            case "savings":
                SavingsAccount savingsAccount = new SavingsAccount(balance);
                accounts.add(savingsAccount);
                Customer.addAccount(savingsAccount);
                return savingsAccount;
        }
    }

    public void removeAccount(Account account){
        accounts.remove(account);
        Customer.removeAccount(account);
    }


    // getters and setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
