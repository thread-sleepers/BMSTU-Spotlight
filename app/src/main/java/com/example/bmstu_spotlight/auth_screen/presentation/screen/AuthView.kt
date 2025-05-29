package com.example.bmstu_spotlight.auth_screen.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthState
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import kotlinx.coroutines.delay

@Composable
fun AuthView(
    state: AuthState,
    onLogin: (String, String) -> Unit,
    onLoginSuccessful: () -> Unit
) {
    val isSigningIn = remember { mutableStateOf(false) }
    LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(top = 60.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val email = remember { mutableStateOf("") }
        val password = remember { mutableStateOf("") }
        val context = LocalContext.current

        CustomTopBar(stringResource(R.string.login))

        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            value = email.value,
            onValueChange = { email.value = it },
            label = { Text(text = stringResource(R.string.email)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.inverseSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.inverseSurface,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        TextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.inverseSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.inverseSurface,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {
                isSigningIn.value = true
                if (email.value.isEmpty() && password.value.isNotEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.empty_email),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (password.value.isEmpty() && email.value.isNotEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.empty_password),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (email.value.isEmpty() && password.value.isEmpty()) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.empty_email_and_pass),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                if (email.value.isNotEmpty() && password.value.isNotEmpty()) {
                    onLogin(email.value, password.value)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
        ) {
            when (state) {
                AuthState.LOADING -> {
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        if (isSigningIn.value) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(28.dp), // Update to size instead of width
                                color = MaterialTheme.colorScheme.background,
                                trackColor = MaterialTheme.colorScheme.surfaceVariant
                            )
                        } else {
                            Text(text = stringResource(R.string.enter), color = Color.White)
                        }
                    }
                }

                AuthState.AUTHENTICATED -> {
                    Text(text = stringResource(R.string.enter), color = Color.White)
                    LaunchedEffect(Unit) {
                        onLoginSuccessful()
                    }
                }

                AuthState.UNAUTHENTICATED -> {
                    Text(text = stringResource(R.string.enter), color = Color.White)
                    if (isSigningIn.value) {
                        Toast.makeText(
                            context,
                            context.getString(R.string.invalid_credentials),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    isSigningIn.value = false
                }
            }
        }
    }
}

