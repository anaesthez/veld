package ru.nesterov.veld

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.nesterov.veld.design_system.theme.VeldTheme
import com.nesterov.veld.di.graph.AppDependenciesGraph
import ru.nesterov.veld.root.RootComponentImpl
import ru.nesterov.veld.root.RootScreen


class AndroidApp : Application() {
    val appAppDependenciesGraph by lazy { AppDependenciesGraph() }
    companion object {
        lateinit var INSTANCE: AndroidApp
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }
}

class AppActivity : ComponentActivity() {
    private val graph by lazy { (this.application as AndroidApp).appAppDependenciesGraph }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = RootComponentImpl(
            componentContext = defaultComponentContext(),
            appDependenciesGraph = graph,
        )
        enableEdgeToEdge()
        setContent { VeldTheme { RootScreen(root) } }
    }
}