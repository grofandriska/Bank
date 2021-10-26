package com.grofandris.api.Bank;

import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.models.User;
import com.grofandris.api.Bank.services.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountTest {

    @LocalServerPort
    private int port;

    private String baseUrl;

    @MockBean
    private AccountService accountService;


    @Autowired
    private TestRestTemplate testRestTemplate;

    @BeforeEach
    public void setUp() {
        this.baseUrl = "http://localhost:" + port;
    }

    @Test
    public void addNewAccountAndFindReturnSame() {

        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account account = new Account(1L, null, "OTP", 10000.00);
        account.setUser(user);

        Account result = testRestTemplate.postForObject(baseUrl + "/save", account, Account.class);

        Account compareTo = testRestTemplate.getForObject(baseUrl + "/getById/" + 1L, Account.class);
        assertEquals(result.getBalance(), compareTo.getBalance());

    }

    @Test
    public void withdrawMoneyFromAccountOk() {
        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account account = new Account(1L, null, "OTP", 10000.00);
        account.setUser(user);
        accountService.saveAccount(account);
        Account result = testRestTemplate.postForObject(baseUrl + "/save", account, Account.class);
        Account compareTo = testRestTemplate.getForObject(baseUrl + "/getById/" + 1L, Account.class);
        when(accountService.withdrawFromBalance(compareTo.getId(), 2000.00)).thenReturn(8000.00);


    }

    @Test
    public void testGetBalanceReturnsAmount() {
        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account account = new Account(1L, null, "OTP", 10000.00);
        account.setUser(user);

        Account result = testRestTemplate.postForObject(baseUrl + "/save", account, Account.class);
        when(accountService.getBalance(result.getId())).thenReturn(10000.00);

    }

    @Test
    public void depositMoneyAddToBalance() {
        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account account = new Account(1L, null, "OTP", 10000.00);
        account.setUser(user);

        Account result = testRestTemplate.postForObject(baseUrl + "/save", account, Account.class);

        when(accountService.addToBalance(result.getId(), 2000.00)).thenReturn(12000.00);

    }

    @Test
    public void transferMoneyThrowsRuntimeException() {
        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account account = new Account(1L, null, "OTP", 0.00);
        account.setUser(user);

        Account result = testRestTemplate.postForObject(baseUrl + "/save", account, Account.class);

        User userSecond = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        Account accountSecond = new Account(2L, null, "OTP", 10000.00);
        account.setUser(userSecond);

        Account resultSecond = testRestTemplate.postForObject(baseUrl + "/save", accountSecond, Account.class);

        when(accountService.transferMoney(1L, 2L, 1000.00)).thenThrow(RuntimeException.class);
    }
}
