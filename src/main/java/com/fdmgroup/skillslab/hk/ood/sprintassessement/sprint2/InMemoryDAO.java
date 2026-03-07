package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDAO implements IAccountWriterDAO, IAccountReaderDAO {
    // This will be the database stub for the Reader/Writer interfaces.
    private ArrayList<Account> mockAccounts;

    public InMemoryDAO(){
        mockAccounts = new ArrayList<>();
    }

    @Override
    public List<Account> readAccounts() {
        return mockAccounts;
    }

    @Override
    public boolean findAccount(Account account) {
        for (Account acc : this.mockAccounts) {
            if (acc == account) {
                return true;
            }
        }
        return false;
    }
    @Override
    public void createAccount(Account account) {
        mockAccounts.add(account);
    }

    @Override
    public void deleteAccount(Account account) {
        this.mockAccounts.remove(account);
    }

    @Override
    public void updateAccount(Account account) {

    }


}
