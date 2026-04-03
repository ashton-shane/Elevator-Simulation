package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.List;

public class AccountServiceImpl implements AccountService {
    // This is the service layer of the app that will make calls to the InMemoryDAO layer
    InMemoryDAO inMemoryDAO;
    AccountWriterDAO accountWriterDAO;
    AccountReaderDAO accountReaderDAO;

    public AccountServiceImpl(InMemoryDAO inMemoryDAO,
                              AccountReaderDAO accountReaderDAO,
                              AccountWriterDAO accountWriterDAO){
        this.inMemoryDAO = inMemoryDAO;
        this.accountReaderDAO = accountReaderDAO;
        this.accountWriterDAO = accountWriterDAO;
    }

    @Override
    public List<Account> getAccounts() {
        return accountReaderDAO.readAccounts();
    }

    @Override
    public void createAccount(Account account) { accountWriterDAO.createAccount(account); }

    @Override
    public void removeAccount(Account account) {
        accountWriterDAO.deleteAccount(account);
    }

}
