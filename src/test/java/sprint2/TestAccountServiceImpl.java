package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.AccountReaderDAO;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.AccountServiceImpl;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.AccountWriterDAO;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.InMemoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TestAccountServiceImpl {
    AccountServiceImpl accountService;
    InMemoryDAO inMemoryDAO;
    AccountReaderDAO accountReaderDAO;
    AccountWriterDAO accountWriterDAO;

    @BeforeEach
    public void test_configurations(){
        inMemoryDAO = spy(new InMemoryDAO());
        accountReaderDAO = inMemoryDAO;
        accountWriterDAO = inMemoryDAO;
        accountService = new AccountServiceImpl(inMemoryDAO, accountReaderDAO, accountWriterDAO);
    }

    @Test
    public void returnsEmptyListOfAccounts_whenGetAccountsIsEmpty(){
        assertEquals(0,accountService.getAccounts().size());
    }

    @Test
    public void verifyCallsCreateAccountOnce_whenCreateAccount(){
        Account account = mock(Account.class);
        accountService.createAccount(account);
        verify(accountWriterDAO).createAccount(account);
    }

    @Test
    public void returnsListOfAccountsOfSize1_whenAddOneAccount(){
        Account account = mock(Account.class);
        accountService.createAccount(account);
        assertEquals(1, accountService.getAccounts().size());
    }

    @Test
    public void verifyCallsDeleteAccountOnce_whenDeleteAccount(){
        Account account = mock(Account.class);
        accountService.removeAccount(account);
        verify(accountWriterDAO).deleteAccount(account);
    }

    @Test
    public void returnsFalse_whenFindingAccount1AfterTwoAccountsAddedAndOneAccountRemoved(){
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
        accountService.removeAccount(account2);
        assertFalse(accountReaderDAO.findAccount(account2));
    }

    @Test
    public void returnsListOfAccountsOfSize1_whenTwoAccountsAddedAndOneAccountRemoved(){
        Account account1 = mock(Account.class);
        Account account2 = mock(Account.class);
        accountService.createAccount(account1);
        accountService.createAccount(account2);
        accountService.removeAccount(account2);
        assertEquals(1, accountService.getAccounts().size());
    }



}
