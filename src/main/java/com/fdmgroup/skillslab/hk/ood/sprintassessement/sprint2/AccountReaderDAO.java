package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

import java.util.List;

public interface AccountReaderDAO {
    public List<Account> readAccounts();
    public boolean findAccount(Account account);
}
