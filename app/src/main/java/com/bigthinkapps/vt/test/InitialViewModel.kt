package com.bigthinkapps.vt.test

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigthinkapps.vt.domain.entities.Account
import com.bigthinkapps.vt.domain.entities.Client

class InitialViewModel : ViewModel() {

    val inputDataDeposit by lazy {
        MutableLiveData("")
    }
    val currentDeposit by lazy {
        MutableLiveData("")
    }
    val inputWithdrawal by lazy {
        MutableLiveData("")
    }
    val currentWithdrawal by lazy {
        MutableLiveData("")
    }
    val inputDataTransfer by lazy {
        MutableLiveData("")
    }
    val currentTransfer by lazy {
        MutableLiveData("")
    }

    private val currentClientMock by lazy {
        Client(id = "Diego", Account(balance = 100.0))
    }

    val onDepositClick = View.OnClickListener {
        val newDeposit = inputDataDeposit.value?.inputValue() ?: 0.0
        try {
            currentClientMock.deposit(newDeposit)
            currentDeposit.value = "New balance ${currentClientMock.account.balance}usd"
        } catch (e: Exception) {
            currentDeposit.value = e.localizedMessage
        }
    }

    val onWithdrawalClick = View.OnClickListener {
        val newWithdrawal = inputWithdrawal.value?.inputValue() ?: 0.0
        try {
            currentClientMock.withdraw(newWithdrawal)
            currentWithdrawal.value = "New balance ${currentClientMock.account.balance}usd"
        } catch (e: Exception) {
            currentWithdrawal.value = e.localizedMessage
        }
    }

    val onTransferClick = View.OnClickListener {
        val newTransfer = inputDataTransfer.value?.inputValue() ?: 0.0
        try {
            currentClientMock.transfer(newTransfer)
            currentTransfer.value =
                "New balance after transfer: ${currentClientMock.account.balance}usd"
        } catch (e: Exception) {
            currentTransfer.value = e.localizedMessage
        }
    }
}