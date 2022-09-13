package com.acme.banking.dbo.service;

import com.acme.banking.dbo.domain.Account;
import com.acme.banking.dbo.domain.Cash;
import com.acme.banking.dbo.domain.Client;
import com.acme.banking.dbo.repo.AccountRepo;
import com.acme.banking.dbo.repo.ClientRepo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessingTest {

    private static final double TEST_FUNDS_AMOUNT = 10.0;

    @Mock private ClientRepo clientRepoDoubler;
    @Mock private AccountRepo accountRepoDoubler;
    @Mock private Cash cash;

    @InjectMocks private Processing processing;

    @Mock private Account accountWithFunds;

    @BeforeEach
    private void createMockAccount() {
        Account account = mock(Account.class);
        lenient().when(account.getId()).thenReturn(1);
        lenient().when(account.getAmount()).thenReturn(TEST_FUNDS_AMOUNT);
        accountWithFunds = account;
    }

    @Test
    void shouldCreateAndSaveClientWithIdAndNameWhenCorrectNameProvided() {
        final Client client = processing.createClient("dummy name");
        Assertions.assertThat(client)
                .isNotNull()
                .hasFieldOrProperty("id")
                .hasFieldOrPropertyWithValue("name", "dummy name");
        Mockito.verify(clientRepoDoubler).save(client);
    }


    @Test
    void getAccountsByClientId() {
    }

    @Test
    void transfer() {
    }

    @Test
    void shouldWithdrawMoneyAndPrintReciteWhenAccountExistsWithSufficientFunds() {
        // given
        when(accountRepoDoubler.getById(1)).thenReturn(accountWithFunds);

        // when
        processing.cash(1, 1);

        // then
        verify(accountWithFunds).setAmount(9.0);
        verify(accountRepoDoubler).save(accountWithFunds);
        verify(cash).log(1.0, 1);
    }

    @Test
    void shouldShowErrorWhenNegativeWithdrawAmount() {
        assertThrows(
                IllegalArgumentException.class,
                () -> processing.cash(-1, 1)
        );
    }

    @Test
    void shouldShowErrorWhenInsufficientFunds() {
        when(accountRepoDoubler.getById(1)).thenReturn(accountWithFunds);

        assertThrows(
                IllegalArgumentException.class,
                () -> processing.cash(TEST_FUNDS_AMOUNT + 1, 1)
        );
        verify(accountWithFunds, times(0)).setAmount(anyDouble());
        verify(accountRepoDoubler, times(0)).save(accountWithFunds);
        verify(cash, times(0)).log(anyDouble(), anyInt());
    }
}