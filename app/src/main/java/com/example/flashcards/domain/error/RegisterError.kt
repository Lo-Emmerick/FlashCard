package com.example.flashcards.domain.error

sealed class RegisterError(message: String) : Exception(message) {
    object TextEmpty : RegisterError("Esse campo não pode ficar vazio")
}