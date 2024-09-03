package com.rangerforce.navigationexample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rangerforce.navigationexample.ui.theme.NavigationExampleTheme

@Composable
fun FirstScreen(
    modifier: Modifier = Modifier,
    handleNavigation: () -> Unit,
    sharedViewModel: SharedViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Text(text = "1", fontSize = 32.sp)
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(value = name, onValueChange = { name = it })
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(value = age.toString(), onValueChange = { age = it.toIntOrNull() ?: 0 })
        Spacer(modifier = modifier.height(16.dp))
        Button(onClick = {
            sharedViewModel.name = name
            sharedViewModel.age = age
            handleNavigation()
        }) {
            Text(text = "Next Screen")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FirstScreenPreview() {
    NavigationExampleTheme {
        FirstScreen(handleNavigation = {})
    }
}
