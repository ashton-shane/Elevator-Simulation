package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint1.model.Account;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.AccountServiceImpl;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.InMemoryDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestAccountServiceImpl {
    AccountServiceImpl accountService;
    InMemoryDAO inMemoryDAO;

    @BeforeEach
    public void test_configurations(){
        inMemoryDAO = mock(InMemoryDAO.class);
        accountService = new AccountServiceImpl(inMemoryDAO);
    }

    @Test
    public void returnsEmptyListOfAccounts_whenGetAccountsIsEmpty(){
        assertEquals(0,accountService.getAccounts().size());
    }

    @Test
    public void verifyCallsCreateAccountOnce_whenCreateAccount(){
        Account account = mock(Account.class);
        accountService.createAccount(account);
        verify(inMemoryDAO).createAccount(account);
    }

    @Test
    public void returnsListOfAccountsOfSize1_whenAddOneAccount(){
        Account account = mock(Account.class);
        accountService.createAccount(account);
        assertEquals(1, accountService.getAccounts().size());
    }
}
