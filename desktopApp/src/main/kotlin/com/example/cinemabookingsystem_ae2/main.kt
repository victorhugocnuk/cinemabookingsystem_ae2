package com.example.cinemabookingsystem_ae2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "cinemabookingsystem_ae2",
    ) {
        App()
    }
}