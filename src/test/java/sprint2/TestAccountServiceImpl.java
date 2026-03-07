package sprint2;

import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.AccountServiceImpl;
import com.fdmgroup.skillslab.hk.ood.sprintassessement.sprint2.InMemoryDAO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestAccountServiceImpl {
    @Test
    public void returnsEmptyListOfAccounts_whenGetAccountsIsEmpty(){
        InMemoryDAO inMemoryDAO = mock(InMemoryDAO.class);
        AccountServiceImpl accountService = new AccountServiceImpl(inMemoryDAO);
        assertEquals(0,accountService.getAccounts().size());
    }
}
