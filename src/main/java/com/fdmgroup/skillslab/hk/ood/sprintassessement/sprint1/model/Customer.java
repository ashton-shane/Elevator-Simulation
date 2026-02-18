package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;
import java.util.ArrayList;

public abstract class Customer {
    private long CUSTOMER_ID = 2_000_000;
    private long nextCustomerId;
    private String name;
    private String address;
    private ArrayList<Account> accounts;

    // constructor
    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.nextCustomerId = CUSTOMER_ID;
        CUSTOMER_ID += 7;
    }

    // methods
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    // getters and setters
    public long getCUSTOMER_ID() {
        return CUSTOMER_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

}
