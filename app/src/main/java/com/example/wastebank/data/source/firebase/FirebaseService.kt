package com.example.wastebank.data.source.firebase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {

    // Inisialisasi FirebaseAuth
    val auth: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    // Inisialisasi Firebase Realtime Database
    val db: FirebaseDatabase by lazy {
        FirebaseDatabase.getInstance("https://waste-bank-3db5d-default-rtdb.asia-southeast1.firebasedatabase.app")
    }
}