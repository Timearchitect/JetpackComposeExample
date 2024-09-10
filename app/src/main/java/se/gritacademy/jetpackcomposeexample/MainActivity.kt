package se.gritacademy.jetpackcomposeexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.SemanticsProperties.ToggleableState
import androidx.compose.ui.state.ToggleableState.On
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import se.gritacademy.jetpackcomposeexample.ui.theme.JetpackComposeExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackComposeExampleTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column (
                        Modifier
                            .fillMaxSize()
                            .alpha(0.9f)
                            .padding(24.dp),
                        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        Button(onClick = { /*TODO*/ }

                        ) {
                            Text("click here")
                        }
                        Button(onClick = { /*TODO*/ }

                        ) {
                            Text("click here")
                        }
                        Button(onClick = { /*TODO*/ }

                        ) {
                            Text("click here")
                        }
                        var checked by remember { mutableStateOf(true) }
                        var checked2 by remember { mutableStateOf(true) }

                        Checkbox(checked = checked, onCheckedChange = {
                            checked = it
                            Toast.makeText(
                                this@MainActivity,
                                "CHECKING...",
                                Toast.LENGTH_SHORT
                            ).show()})
                        Text(
                            if (checked) "Checkbox is checked" else "Checkbox is unchecked"
                        )


                        val childCheckedStates = remember { mutableStateListOf(false, false, false) }

                        // Compute the parent state based on children's states
                        val parentState = when {
                            childCheckedStates.all { it } -> On
                            childCheckedStates.none { it } -> androidx.compose.ui.state.ToggleableState.Off
                            else -> androidx.compose.ui.state.ToggleableState.Indeterminate
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("Select all")
                            TriStateCheckbox(
                                state = parentState,
                                onClick = {
                                    // Determine new state based on current state
                                    val newState = parentState != On
                                    childCheckedStates.forEachIndexed { index, _ ->
                                        childCheckedStates[index] = newState
                                    }
                                }
                            )
                        }


                        // Child Checkboxes
                        childCheckedStates.forEachIndexed { index, checked ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Text("Option ${index + 1}")
                                Checkbox(
                                    checked = checked,
                                    onCheckedChange = { isChecked ->
                                        // Update the individual child state
                                        childCheckedStates[index] = isChecked
                                    }
                                )
                            }
                        }
                        slide()
                        Switch(
                            checked = checked2,
                            onCheckedChange = {

                                checked2 = it
                            }
                        )
                    }
                }
            }
        }
    }


}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
private fun slide() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Slider(
        value = sliderPosition,
        onValueChange = { sliderPosition = it }
    )
    Text(text = sliderPosition.toString())
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackComposeExampleTheme {

        Column (   Modifier
            .fillMaxSize()
            .padding(24.dp),
            verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){

            Greeting("dfsdfdsf")
            Button(onClick = { /*TODO*/ }){
                Text("click here")
            }
            Button(onClick = { /*TODO*/ }){
                Text("click here")
            }
            Button(onClick = { /*TODO*/ }){
                Text("click here")
            }

            Checkbox(checked = true, onCheckedChange = { })
            Checkbox(checked = false, onCheckedChange = {
            })
            Text(
                "Checkbox is checked"
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("Option 1")
                Checkbox(
                    checked = false,
                    onCheckedChange = {
                    })
                Text("Option 2")
                Checkbox(
                    checked = false,
                    onCheckedChange = {
                    })
                Text("Option 3")
                Checkbox(
                    checked = false,
                    onCheckedChange = {
                    })

            }


        }

    }
}