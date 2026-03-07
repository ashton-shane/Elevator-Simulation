package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.List;

public class AccountServiceImpl implements IAccountService {
    // This is the service layer of the app that will make calls to the InMemoryDAO layer
    InMemoryDAO inMemoryDAO;

    public AccountServiceImpl(InMemoryDAO inMemoryDAO){
        this.inMemoryDAO = inMemoryDAO;
    }

    @Override
    public List<Account> getAccounts() {
        return List.of();
    }

    @Override
    public void createAccount(Account account) {

    }

    @Override
    public void removeAccount(Account account) {

    }


}
