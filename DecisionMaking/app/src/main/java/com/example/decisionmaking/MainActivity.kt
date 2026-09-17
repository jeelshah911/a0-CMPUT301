package com.example.decisionmaking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decisionmaking.ui.theme.DecisionMakingTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DecisionMakingTheme {
                var answerText by remember { mutableStateOf("Should we go?") }

                Scaffold { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        DisplayName()

                        Spacer(modifier = Modifier.height(250.dp))

                        DisplayText(answerText)

                        Spacer(modifier = Modifier.height(30.dp))

                        DisplayButtons(onAnswerSelected = { answer ->
                            answerText = answer
                        })
                    }
                }
            }
        }
    }

    @Composable
    fun DisplayButtons(onAnswerSelected: (String) -> Unit) {
        var count by remember { mutableStateOf(0) }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // 1st button
                Button(onClick = {
                    count++
                    val isYes = Random.nextDouble() < 0.50
                    val decision = if (isYes) "Yes, you should go!" else "No, you shouldn't go."
                    onAnswerSelected(decision)
                }) {
                    Text("Ok! (50%)")
                }

                // 2nd button
                Button(onClick = {
                    count++
                    val isYes = Random.nextDouble() < 0.25
                    val decision = if (isYes) "Yes, you should go!" else "No, you shouldn't go."
                    onAnswerSelected(decision)
                }) {
                    Text("Meh (25%)")
                }

                // 3rd button
                Button(onClick = {
                    count++
                    val isYes = Random.nextDouble() < 0.10
                    val decision = if (isYes) "Yes, you should go!" else "No, you shouldn't go."
                    onAnswerSelected(decision)
                }) {
                    Text("Nah! (10%)")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text("You have pressed the button $count times")
        }
    }
    @Composable
    fun DisplayName() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Student ID Number : 1761692")
            Text("CCID : jcshah1")
        }
    }

    @Composable
    fun DisplayText(currentText: String) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = currentText, fontSize = 24.sp)
        }
    }
}