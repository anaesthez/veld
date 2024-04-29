import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.nesterov.veld.design_system.theme.VeldTheme
import ru.nesterov.veld.App
import platform.UIKit.UIViewController
import ru.nesterov.veld.root.RootComponentImpl
import ru.nesterov.veld.root.RootScreen

fun MainViewController(): UIViewController = ComposeUIViewController {
    val root = RootComponentImpl(componentContext = DefaultComponentContext(lifecycle = LifecycleRegistry()),)
    VeldTheme { RootScreen(root) }
}
