package com.example.android

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.Counter
import com.example.common.CounterViewModel
import com.example.common.di.injectedServices
import org.kodein.di.compose.withDI
import org.kodein.di.instance

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                CounterApp()
            }
        }
    }
}

@Composable
fun CounterApp() = withDI(injectedServices){
    val viewModel: CounterViewModel by injectedServices.instance()
    val counterState = viewModel.getCounterFlow().collectAsState(
        initial = Counter()
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Counter App") }) },
    ) {
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
                    onClick = {viewModel.incrementCounterLaunch()},
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("+")
                }
                Button(
                    onClick = {viewModel.decrementCounterLaunch()},
                    modifier = Modifier.padding(4.dp)
                ) {
                    Text("-")
                }
            }
        }
    }
}