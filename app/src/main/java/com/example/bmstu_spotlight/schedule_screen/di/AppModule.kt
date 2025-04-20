package com.example.bmstu_spotlight.schedule_screen.di

import com.example.bmstu_spotlight.schedule_screen.data.repository.FakeScheduleRepository
import com.example.bmstu_spotlight.schedule_screen.domain.repository.ScheduleRepository
import com.example.bmstu_spotlight.schedule_screen.domain.usecase.GetScheduleCase
import com.example.bmstu_spotlight.schedule_screen.presentation.view_model.ScheduleViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val scheduleModule = module {
    single<ScheduleRepository> {
        FakeScheduleRepository()
    }
    factory {
        GetScheduleCase(get())
    }
    viewModel{
        ScheduleViewModel(get())
    }
}