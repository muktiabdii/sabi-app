package com.example.wastebank.domain.usecase

import com.example.wastebank.domain.repository.MoneyExchangeRepository

class MoneyExchangeUseCase(private val moneyExchangeRepository: MoneyExchangeRepository) {

    // Fungsi untuk melakukan penukaran uang
    // Menerima poin, nama bank, dan nomor rekening sebagai input
    // Akan mengembalikan hasil operasi melalui onResult callback
    suspend fun exchangeMoney(point: Int, bankName: String, accountNumber: String, onResult: (Boolean, String?) -> Unit) {

        // Validasi input: memastikan poin lebih besar dari 0 dan nama bank serta nomor rekening tidak kosong
        if (point <= 0 || bankName.isBlank() || accountNumber.isBlank()) {
            // Jika validasi gagal, kirim pesan error melalui onResult
            onResult(false, "Harap isi semua data")
            return
        }

        // Jika validasi berhasil, melanjutkan ke repository untuk proses penukaran uang
        moneyExchangeRepository.exchangeMoney(point, bankName, accountNumber, onResult)
    }
}
