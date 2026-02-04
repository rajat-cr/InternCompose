package com.coderroots.interncomposeclass

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import com.coderroots.interncomposeclass.ui.theme.InternComposeClassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
           LoginScreen()
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LoginScreen(){
    val context = LocalContext.current
    var enterName by remember { mutableStateOf("") }
    var enterPassword by remember { mutableStateOf("") }
    Column(
        Modifier.fillMaxSize().verticalScroll(rememberScrollState(),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text("Login Screen", fontSize = 25.sp)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = enterName,
            onValueChange = {enterName = it},
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            placeholder={
                Text("Enter enterName")
            },
            singleLine = true
        )
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(
            value = enterPassword,
            onValueChange = {enterPassword = it},
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, end = 10.dp),
            placeholder={
                Text("Enter Password")
            },
            singleLine = true
        )
        Spacer(Modifier.height(10.dp))
        Button(
            onClick = {

                Toast.makeText(context,"Click Action", Toast.LENGTH_SHORT).show()
            },
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(start = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(7.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_200)
            )
        ) {
            Text("Login",
                color = colorResource(R.color.black))
        }










    }



}
