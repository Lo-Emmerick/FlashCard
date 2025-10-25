package com.example.laecards.domain.error

sealed class RegisterError(message: String) : Exception(message) {
    object TextEmpty : RegisterError("Esse campo não pode ficar vazio")
}