package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;


import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.List;

public interface AccountService {
    public List<Account> getAccounts();
    public void createAccount(Account account);
    public void removeAccount(Account account);
}
