package com.example.wastebank.data.repository

import com.example.wastebank.data.mapper.UserMapper
import com.example.wastebank.data.model.UserData
import com.example.wastebank.data.source.firebase.FirebaseService
import com.example.wastebank.domain.model.UserDomain
import com.example.wastebank.domain.repository.UserProfileRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await

class UserProfileRepositoryImpl : UserProfileRepository {

    private val auth = FirebaseService.auth
    private val db = FirebaseService.db
    private val userRef = db.getReference("users")

    override suspend fun getUserProfile(): UserDomain? {
        val userId = auth.currentUser?.uid ?: return null
        val snapshot = userRef.child(userId).get().await()
        val userData = snapshot.getValue(UserData::class.java) ?: return null
        return UserMapper.mapToDomain(userData)
    }


    override suspend fun editUserProfile(user: UserDomain): Result<Unit> {
        val userId = auth.currentUser?.uid ?: return Result.failure(Exception("User not authenticated"))
        return try {
            userRef.child(userId).setValue(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getUserPoint(): Int? {
        val userId = auth.currentUser?.uid ?: return null
        val snapshot = userRef.child(userId).child("points").get().await()
        return snapshot.getValue(Int::class.java)
    }

    override suspend fun getUserName(): String? {
        val userId = auth.currentUser?.uid ?: return null
        val snapshot = userRef.child(userId).child("name").get().await()
        return snapshot.getValue(String::class.java)
    }

    override suspend fun deleteAccount(): Result<Unit> {
        val userId = auth.currentUser?.uid ?: return Result.failure(Exception("User not authenticated"))
        return try {
            userRef.child(userId).removeValue().await()
            auth.currentUser?.delete()?.await()
            auth.signOut()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}