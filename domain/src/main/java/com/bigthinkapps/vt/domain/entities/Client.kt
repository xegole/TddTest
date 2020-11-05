package com.bigthinkapps.vt.domain.entities

import com.bigthinkapps.vt.domain.exceptions.NegativeValueException
import com.bigthinkapps.vt.domain.exceptions.OverDraftException

data class Client(val id: String = "", val account: Account = Account()) {

    fun deposit(newDeposit: Double) {
        if (newDeposit > 0) {
            account.balance += newDeposit
        } else {
            throw(NegativeValueException())
        }
    }

    fun withdraw(newWithdraw: Double) {
        if (newWithdraw <= 0) {
            throw (NegativeValueException())
        }

        val newBalance = account.balance - newWithdraw

        if (newBalance >= 0) {
            account.balance = newBalance
        } else {
            throw(OverDraftException())
        }
    }

    fun transfer(newTransfer: Double) {
        if (newTransfer <= 0) {
            throw (NegativeValueException())
        }

        if (newTransfer > account.balance) {
            throw(OverDraftException())
        }else{
            val newBalance = account.balance - newTransfer
            account.balance = newBalance
        }
    }
}