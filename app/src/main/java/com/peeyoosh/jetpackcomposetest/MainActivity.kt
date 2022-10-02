package com.peeyoosh.jetpackcomposetest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.peeyoosh.jetpackcomposetest.ui.theme.JetpackComposeTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background,
                    elevation = 4.dp
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

private fun validateLogin(context: Context, username: String?, password: String?) {
    if (username.equals("piyush") && password.equals("123")) {
        Toast.makeText(context, "welcome", Toast.LENGTH_SHORT).show()
    } else {
        Toast.makeText(context, "login error", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun Greeting(name: String) {
    val username = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        verticalArrangement = Arrangement.Center, modifier = Modifier
            .fillMaxSize()
            .padding(all = 8.dp)
    ) {
        Text(
            text = "Hello $name!", color = Color.Blue, fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Monospace, fontSize = 25.sp, modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        Text(
            text = "Please input username & password",
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
        )
        UsernameTextField(username)
        PasswordTextField(password)
        LoginButton(username, password)
        ClearButton(username, password)
    }

}

@Composable
private fun UsernameTextField(username: MutableState<String>) {
    OutlinedTextField(
        value = username.value, onValueChange = {
            username.value = it
        },
        leadingIcon = {
            Icon(Icons.Default.Person, contentDescription = "")
        },
        label = {
            Text(text = "Username")
        },
        placeholder = {
            Text(text = "Enter username")
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(10.dp))
    )
}

@Composable
private fun PasswordTextField(password: MutableState<String>) {
    OutlinedTextField(
        value = password.value, onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(Icons.Default.Info, contentDescription = "")
        },
        label = {
            Text(text = "Password")
        },
        placeholder = {
            Text(text = "Enter password")
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(CornerSize(10.dp))
    )
}

@Composable
private fun ClearButton(
    username: MutableState<String>,
    password: MutableState<String>
) {
    OutlinedButton(
        onClick = {
            username.value = ""
            password.value = ""
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Text(text = "Clear")
    }
}

@Composable
private fun LoginButton(
    username: MutableState<String>,
    password: MutableState<String>
) {
    val context = LocalContext.current
    OutlinedButton(
        onClick = {
            validateLogin(context, username.value, password.value)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Login")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeTestTheme {
        Greeting("Android")
    }
}