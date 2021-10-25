package com.grofandris.api.Bank;

import com.grofandris.api.Bank.controllers.AccountController;
import com.grofandris.api.Bank.models.Account;
import com.grofandris.api.Bank.models.User;
import com.grofandris.api.Bank.services.AccountService;

import static org.junit.jupiter.api.Assertions.*;

import com.grofandris.api.Bank.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccountController.class)
class BankApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    @MockBean
    private UserService userService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testGetBalance() {


        User user = new User();
        user.setEmail("mail@gmail.com");
        user.setId(null);
        user.setUserName("Feri");
        user.setId(1L);
        userService.save(user);

        Account account = new Account(1L, null, "OTP", 10000.00);

        account.setUser(user);

        accountService.saveAccount(account);

        assertEquals(10000.00, account.getBalance());

    }

}
