package com.example.tilakcollegestudy.data.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository(private val auth: FirebaseAuth) {

    suspend fun loginUser(email: String, password: String): Result<Boolean> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



    suspend fun signupUser(email: String, password: String): Result<Boolean> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(true)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun logout() {
        auth.signOut()
    }
}