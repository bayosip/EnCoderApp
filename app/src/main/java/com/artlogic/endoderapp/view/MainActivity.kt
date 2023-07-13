package com.artlogic.endoderapp.view

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.artlogic.endoderapp.view.presentation.ScreenNames
import com.artlogic.endoderapp.view.screens.DecodingScreen
import com.artlogic.endoderapp.view.screens.EncodingScreen
import com.artlogic.endoderapp.view.screens.SelectionScreen
import com.artlogic.endoderapp.view.toolbar.DecoderToolBar
import com.artlogic.endoderapp.view_model.DecodingViewModel
import com.artlogic.endoderapp.view_model.EncodingViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val decoderVm: DecodingViewModel by viewModels()
    private val encodeVm: EncodingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }

    @Composable
    private fun AppContent() {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                DecoderToolBar(
                    backAction = { navController.popBackStack() },
                    currentScreen = navController
                        .currentBackStackEntryAsState()
                        .value?.destination?.route?: ScreenNames.SELECTION
                )
            },
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it.calculateBottomPadding())
            ) {
                NavHost(
                    navController = navController,
                    startDestination = ScreenNames.SELECTION
                ) {
                    composable(ScreenNames.SELECTION) {
                        SelectionScreen(
                            navigateToDecoder = {
                                navController.navigate(ScreenNames.DECODE)
                            },
                            navigateToEncoder = {
                                navController.navigate(ScreenNames.ENCODE)
                            })
                    }
                    composable(ScreenNames.ENCODE) {
                        EncodingScreen(
                            encodeAction = { input ->
                                encodeVm.encodeString(input)
                            },
                            clearAction = { encodeVm.clear() },
                            screenState = encodeVm.state
                        )
                    }
                    composable(ScreenNames.DECODE) {
                        DecodingScreen(
                            decodeAction = { inputs ->
                                decoderVm.decodeInputs(inputs)
                            },
                            clearAction = {
                                decoderVm.clearOutput()
                            },
                            screenState = decoderVm.state,
                        )
                    }
                }
            }
        }
    }
}