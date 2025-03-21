package com.example.wastebank.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wastebank.domain.usecase.MoneyExchangeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoneyExchangeViewModel(private val moneyExchangeUseCase: MoneyExchangeUseCase) : ViewModel() {

    // State untuk menyimpan jumlah poin yang dimiliki oleh pengguna
    private val _points = MutableStateFlow(0)
    val points: StateFlow<Int> = _points

    // State untuk menyimpan nama bank tujuan penukaran
    private val _bankName = MutableStateFlow("")
    val bankName: StateFlow<String> = _bankName

    // State untuk menyimpan nomor rekening tujuan penukaran
    private val _accountNumber = MutableStateFlow("")
    val accountNumber: StateFlow<String> = _accountNumber

    // State untuk menyimpan jumlah uang yang ingin ditukarkan (dalam rupiah)
    private val _amount = MutableStateFlow(0)
    val amount: StateFlow<Int> = _amount

    // State untuk menyimpan pesan error jika ada kesalahan selama proses penukaran
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun clearState() {
        _points.value = 0
        _bankName.value = ""
        _accountNumber.value = ""
        _amount.value = 0
        _errorMessage.value = null
    }

    // Fungsi untuk memperbarui data poin dan jumlah uang yang ingin ditukarkan
    // Menghitung jumlah uang berdasarkan poin dan biaya administrasi
    fun updatePointAndAmount(value: Int) {
        _points.value = value
        _amount.value = value * 10 // Setiap poin setara dengan 10 rupiah
    }

    // Fungsi untuk memperbarui nama bank tujuan
    fun updateBankName(value: String) { _bankName.value = value }

    // Fungsi untuk memperbarui nomor rekening tujuan
    fun updateAccountNumber(value: String) { _accountNumber.value = value }

    // Fungsi untuk mereset pesan error
    fun resetErrorMessage() { _errorMessage.value = null }

    // Fungsi untuk memproses penukaran uang dengan menggunakan use case
    // Mengirimkan hasil ke onResult callback
    fun exchangeMoney(onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            moneyExchangeUseCase.exchangeMoney(
                points.value, bankName.value, accountNumber.value
            ) { success, message ->
                if (success) {
                    onResult(true, "") // Penukaran berhasil
                } else {
                    onResult(false, "") // Penukaran gagal
                    _errorMessage.value = message // Menyimpan pesan error untuk ditampilkan
                }
            }
        }
    }


    // Factory class untuk membuat instance ViewModel dengan dependensi
    class Factory(
        private val moneyExchangeUseCase: MoneyExchangeUseCase) : ViewModelProvider.Factory {

        // Fungsi untuk membuat instance ViewModel
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MoneyExchangeViewModel::class.java)) {
                return MoneyExchangeViewModel(moneyExchangeUseCase) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class") // Jika kelas tidak dikenal
        }
    }
}
