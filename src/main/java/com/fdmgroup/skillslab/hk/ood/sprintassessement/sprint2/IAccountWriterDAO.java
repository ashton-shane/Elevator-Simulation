package com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;

public interface IAccountWriterDAO {
    public void createAccount(Account account);
    public void removeAccount(Account account);
    public void updateAccount(Account account);
}
