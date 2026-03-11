package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDAO implements AccountWriterDAO, AccountReaderDAO {
    // This will be the database stub for the Reader/Writer interfaces.
    private ArrayList<Account> mockAccounts;

    public InMemoryDAO(){
        mockAccounts = new ArrayList<>();
    }

    @Override
    public List<Account> readAccounts() {
        return this.mockAccounts;
    }

    @Override
    public boolean findAccount(Account account) {
        return this.mockAccounts.contains(account);
    }

    @Override
    public void createAccount(Account account) {
        this.mockAccounts.add(account);
    }

    @Override
    public void deleteAccount(Account account) {
        this.mockAccounts.remove(account);
    }

}
