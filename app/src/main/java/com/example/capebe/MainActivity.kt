package com.example.capebe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.capebe.ui.navigation.AppNavigation
import com.example.capebe.ui.theme.CAPEBETheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CAPEBETheme {
                AppNavigation()
            }
        }
    }
}