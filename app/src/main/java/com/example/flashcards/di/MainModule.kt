package com.example.flashcards.di

import androidx.room.Room
import com.example.flashcards.data.local.database.AppDatabase
import com.example.flashcards.data.repository.home.HomeRepositoryImpl
import com.example.flashcards.domain.repository.HomeRepository
import com.example.flashcards.domain.useCase.SearchCardUseCase
import com.example.flashcards.domain.useCase.SearchCardUseCaseImpl
import com.example.flashcards.presentation.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositories = module {
    single { HomeRepositoryImpl(dao = get()) as HomeRepository }
}
val loadUseCase = module {
    single { SearchCardUseCaseImpl(repository = get()) as SearchCardUseCase }
}
val loadViewModel = module {
    viewModel {
        HomeViewModel(
            searchCardUseCase = get()
        )
    }
}
val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "card"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().cardDao() }
}