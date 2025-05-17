package com.example.bmstu_spotlight.auth_screen.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bmstu_spotlight.BMSTUSpotlightApp
import com.example.bmstu_spotlight.R
import com.example.bmstu_spotlight.auth_screen.data.FakeAuthRepository
import com.example.bmstu_spotlight.auth_screen.presentation.view_model.AuthViewModel
import com.example.bmstu_spotlight.menu_screen.presentation.components.CustomTopBar
import com.example.bmstu_spotlight.profile.domain.model.UserProfile
import com.example.bmstu_spotlight.ui.theme.BMSTUSpotlightAppNewTheme

@Composable
fun AuthView(
    viewModel : AuthViewModel
) {
    val mContext = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val mUsername = remember { mutableStateOf("") }
        val mPassword = remember { mutableStateOf("") }

        CustomTopBar(stringResource(R.string.login))

        Spacer(modifier = Modifier.height(25.dp))

        TextField(
            value = mUsername.value,
            onValueChange = { mUsername.value = it },
            label = { Text(text = stringResource(R.string.username)) },
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.scrim,
                unfocusedTextColor = MaterialTheme.colorScheme.scrim,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent)
        )

        TextField(
            value = mPassword.value,
            onValueChange = { mPassword.value = it },
            label = { Text(text = stringResource(R.string.password)) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(28.dp),
            colors = TextFieldDefaults.colors(
                focusedTextColor = MaterialTheme.colorScheme.scrim,
                unfocusedTextColor = MaterialTheme.colorScheme.scrim,
                unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                focusedContainerColor = MaterialTheme.colorScheme.onSecondaryContainer,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            if(mUsername.value.isEmpty() and mPassword.value.isNotEmpty()){
                Toast.makeText(mContext, mContext.getString(R.string.empty_username), Toast.LENGTH_SHORT).show()
            }
            if (mPassword.value.isEmpty() and mUsername.value.isNotEmpty()){
                Toast.makeText(mContext, mContext.getString(R.string.empty_password), Toast.LENGTH_SHORT).show()
            }
            if(mUsername.value.isEmpty() and mPassword.value.isEmpty()){
                Toast.makeText(mContext, mContext.getString(R.string.empty_username_and_pass), Toast.LENGTH_SHORT).show()
            }
            if(mUsername.value.isNotEmpty() and mPassword.value.isNotEmpty()){
                Toast.makeText(mContext, mContext.getString(R.string.successful_enter), Toast.LENGTH_SHORT).show()
            }
            viewModel.setUsername(mUsername.toString())
            viewModel.setPassword(mPassword.toString())
            viewModel.login()
        },
            modifier = Modifier.fillMaxWidth().padding(8.dp).background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(20.dp))
            //colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFF0F9D58)),
        ) {
            Text(text= stringResource(R.string.enter), color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BMSTUSpotlightAppNewTheme {
        AuthView(AuthViewModel(FakeAuthRepository()))
    }
}