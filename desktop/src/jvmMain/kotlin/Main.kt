import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.example.common.Counter
import com.example.common.CounterViewModel
import com.example.common.di.injectedServices
import org.kodein.di.compose.withDI
import org.kodein.di.instance

// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Counter Desktop app") {
        MaterialTheme {
            counterApp()
        }
    }
}

@Composable
fun counterApp() = withDI(injectedServices) {
    val viewModel: CounterViewModel by injectedServices.instance()
    val counterState = viewModel.getCounterFlow().collectAsState(
        initial = Counter()
    )

    Scaffold {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Value: ${counterState.value.valor}",
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Last action: ${counterState.value.mensaje}",
                modifier = Modifier.padding(8.dp)
            )
            Row {
                Button(
                    onClick = { viewModel.incrementCounterLaunch() },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+")
                }
                Button(
                    onClick = { viewModel.decrementCounterLaunch() },
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("-")
                }
            }
        }
    }
}
