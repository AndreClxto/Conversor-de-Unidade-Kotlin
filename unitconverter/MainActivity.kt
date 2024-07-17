package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Selecione") }
    var outputUnit by remember { mutableStateOf("Selecione") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val iConversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember { mutableDoubleStateOf(1.00) }

    fun convertUnits(){
        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * iConversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue = result.toString()
    }

    fun abbreviationSelector(): String{
        val abbreviation: String = when (outputUnit){
            "Milímetros" -> "mm"
            "Centímetros" -> "cm"
            "Metros" -> "m"
            "Inches" -> "in"
            "Feet" -> "ft"
            else -> ""
        }
        return abbreviation
    }

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
        // Here all the UI elements will be stacked below each other
        Text("Conversor de Unidades", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            convertUnits()
            // Here goes what should happen when the value of our outlined text field changes
        },
        label = {Text("Digite o Valor")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            // Here all the UI elements will be stacked next to each other

            // Input Box
            Box {
                // Input Button
                Button(onClick = { iExpanded = true }) {
                    Text(inputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Milímetros") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Milímetros"
                            iConversionFactor.doubleValue = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Centímetros") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Centímetros"
                            iConversionFactor.doubleValue = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Metros") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Metros"
                            iConversionFactor.doubleValue = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Inches"
                            iConversionFactor.doubleValue = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            iExpanded = false
                            inputUnit = "Feet"
                            iConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        })
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            // Output Box
            Box {
                // Output Button
                Button(onClick = { oExpanded = true }) {
                    Text(outputUnit)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(
                        text = { Text("Milímetros") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Milímetros"
                            oConversionFactor.doubleValue = 0.001
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Centímetros") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Centímetros"
                            oConversionFactor.doubleValue = 0.01
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Metros") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Metros"
                            oConversionFactor.doubleValue = 1.0
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Inches"
                            oConversionFactor.doubleValue = 0.0254
                            convertUnits()
                        })
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            oExpanded = false
                            outputUnit = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Resultado: $outputValue ${abbreviationSelector()}",
                style = MaterialTheme.typography.headlineSmall
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
