package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model;
import java.util.ArrayList;

public abstract class Customer {
    private final long CUSTOMER_ID;
    private long nextCustomerId = 2_000_000;
    private String name;
    private String address;
    static protected ArrayList<Account> accounts;

    // constructor
    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
        this.CUSTOMER_ID = nextCustomerId;
        nextCustomerId += 7;
    }

    // methods
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void removeAccount(Account account) {
        accounts.remove(account);
    }

    public abstract void chargeAllAccounts(double amount);

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
