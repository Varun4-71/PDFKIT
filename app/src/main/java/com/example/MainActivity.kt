package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.engine.PdfEngine
import com.example.model.PdfTool
import com.example.ui.screens.CompressScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ImagesToPdfScreen
import com.example.ui.screens.MergeScreen
import com.example.ui.screens.MetadataScreen
import com.example.ui.screens.OrganizeScreen
import com.example.ui.screens.PageNumbersScreen
import com.example.ui.screens.PdfToImagesScreen
import com.example.ui.screens.SplitScreen
import com.example.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize embedded PDFBox runtime for Android
        PdfEngine.initialize(this)

        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF070B14)
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        enterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.Start,
                                animationSpec = tween(280)
                            ) + fadeIn(animationSpec = tween(280))
                        },
                        exitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.Start,
                                animationSpec = tween(280)
                            ) + fadeOut(animationSpec = tween(280))
                        },
                        popEnterTransition = {
                            slideIntoContainer(
                                AnimatedContentTransitionScope.SlideDirection.End,
                                animationSpec = tween(280)
                            ) + fadeIn(animationSpec = tween(280))
                        },
                        popExitTransition = {
                            slideOutOfContainer(
                                AnimatedContentTransitionScope.SlideDirection.End,
                                animationSpec = tween(280)
                            ) + fadeOut(animationSpec = tween(280))
                        }
                    ) {
                        composable("home") {
                            HomeScreen(
                                onToolSelected = { tool ->
                                    navController.navigate(tool.id)
                                }
                            )
                        }

                        composable(PdfTool.MERGE.id) {
                            MergeScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.SPLIT.id) {
                            SplitScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.COMPRESS.id) {
                            CompressScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.EDIT_METADATA.id) {
                            MetadataScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.IMAGES_TO_PDF.id) {
                            ImagesToPdfScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.PDF_TO_IMAGES.id) {
                            PdfToImagesScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.ORGANIZE_PAGES.id) {
                            OrganizeScreen(onNavigateBack = { navController.popBackStack() })
                        }

                        composable(PdfTool.ADD_PAGE_NUMBERS.id) {
                            PageNumbersScreen(onNavigateBack = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }
}
