package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.controller;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.*;

import java.util.ArrayList;

public class AccountController {
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

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
            default:
                throw new IllegalArgumentException("Customer type does not exist.");
        }
    }

    public void removeCustomer(Customer customer){
        customers.remove(customer);                                     // remove customer from customers arraylist
        ArrayList<Account> custAccounts = customer.getAccounts();
        custAccounts.clear();                                           // remove all accounts from associated w customer instance

        for (Account custAcc : custAccounts) {                          // remove all accounts in accounts arrayList
            for (Account acc : accounts) {
                if (custAcc == acc) {
                    accounts.remove(custAcc);
                }
            }
        }
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
            default:
                throw new IllegalArgumentException("Account type does not exist.");
        }
    }

    public void removeAccount(Account account){
        accounts.remove(account);

        for (Customer customer : customers ) {
                accounts.removeIf(acc -> acc == account);
        }

    }


    // getters and setters
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
