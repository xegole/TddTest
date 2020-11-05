package com.bigthinkapps.vt.domain

import com.bigthinkapps.vt.domain.entities.Account
import com.bigthinkapps.vt.domain.entities.Client
import com.bigthinkapps.vt.domain.exceptions.NegativeValueException
import com.bigthinkapps.vt.domain.exceptions.OverDraftException
import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class SomeCodeTest {

    /* Feature: Deposit money into an account
    As a client of the bank
    I want to deposit money into my account
    In order to increase my balance

    Scenario: An existing client deposits money into his account
    Given an existing client with id “francisco” with 100 USD in his account
    When he deposits 10 USD into his account
    Then the balance of his account is 110 USD */

    @Test
    fun `Given a client the id must be "francisco"`() {
        // Given
        val inputId = "francisco"
        // When
        val client = Client(inputId)
        // Then
        assertEquals(inputId, client.id)
    }

    @Test
    fun `Given a client should have $100 usd in his account`() {
        // Given
        val inputId = "francisco"
        val inputAmount = 100.0
        // When
        val client = Client(inputId, Account(inputAmount))
        // Then
        assertEquals(inputId, client.id)
        assertEquals(inputAmount, client.account.balance, 0.0)
    }

    @Test
    fun `Given a client that deposits $10 usd the new balance is $110 usd`() {
        // Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newDeposit = 10.0
        // When
        val client = Client(inputId, Account(inputAmount))
        client.deposit(newDeposit)
        // Then
        assertEquals(inputId, client.id)
        assertEquals(inputAmount + newDeposit, client.account.balance, 0.0)
    }

    /* Currently, users can deposit negative amounts of money,
    which does not make sense. Add a new test case to fix this issue. */

    @Test
    fun `Given a client that deposits $-5 usd should return error`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newDeposit = -5.0
        //When
        val client = Client(inputId, Account(inputAmount))
        //Then
        assertFailsWith<Exception> {
            client.deposit(newDeposit)
        }
    }

    /* Feature: Withdraw money from an account
    As a client of the bank
    I want to withdraw money from my account
    In order to have cash

    Scenario: An existing client withdraws money from his account
    Given an existing client with id “francisco” with 100 USD in his account
    When he withdraws 10 USD from his account
    Then the new balance is 90 USD */

    @Test
    fun `Given a client that withdraws $10 usd the new balance is $90 usd`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newWithdraw = 10.0
        //When
        val client = Client(inputId, Account(inputAmount))
        client.withdraw(newWithdraw)
        //Then
        assertEquals(inputAmount - newWithdraw, client.account.balance, 0.0)
    }

    /* Add a scenario in the withdrawal feature for the case when a withdrawal generates an overdraft.
      Withdrawal of amounts bigger than the current account balance must not be allowed. */

    @Test
    fun `Given a client that withdraws $110 usd in his account generate overdraft should return error`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newWithdraw = 110.0
        //When
        val client = Client(inputId, Account(inputAmount))
        //Then
        assertFailsWith<OverDraftException> {
            client.withdraw(newWithdraw)
        }
    }

    /* Check whether it is possible to withdraw a negative value. If it is possible, fix it.
        Add the corresponding test case to fix this issue. */

    @Test
    fun `Given a client that withdraws $-10 usd should return error`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newWithdraw = -10.0
        //When
        val client = Client(inputId, Account(inputAmount))
        //Then
        assertFailsWith<NegativeValueException> {
            client.withdraw(newWithdraw)
        }
    }

    /* Feature: Transfer money from an account
    As a client of the bank to other one
    I want to transfer money from my account to other
    In order to transfer cash

    Scenario: An existing client transfer money from his account to other one
    Given an existing client with id “francisco” with 100 USD in his account
    When he transfer 20 USD from his account to "Diego's" account
    Then the new balance is 80 USD */

    @Test
    fun `Given a client that transfers $110 usd from his account to other one generate overdraft should return error`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newTransfer = 110.0
        //When
        val client = Client(inputId, Account(inputAmount))
        //Then
        assertFailsWith<OverDraftException> {
            client.transfer(newTransfer)
        }
    }

    @Test
    fun `Given a client that transfers $-10 usd from his account to other one should return error`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newTransfer = -20.0
        //When
        val client = Client(inputId, Account(inputAmount))
        //Then
        assertFailsWith<NegativeValueException> {
            client.transfer(newTransfer)
        }
    }

    @Test
    fun `Given a client that transfers $80 usd from his account to other one the new balance is 20 usd`() {
        //Given
        val inputId = "francisco"
        val inputAmount = 100.0
        val newTransfer = 80.0
        //When
        val client = Client(inputId, Account(inputAmount))
        client.transfer(newTransfer)
        //Then
        assertEquals(inputAmount - newTransfer, client.account.balance, 0.0)
    }
}