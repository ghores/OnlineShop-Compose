package com.example.onlineshop.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshop.ui.component.app.AppTextField
import com.example.onlineshop.viewmodel.BasketViewModel
import com.example.onlineshop.viewmodel.PaymentViewModel

@Composable
fun UserPaymentScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel(),
    paymentViewModel: PaymentViewModel = hiltViewModel()
) {
    val basketItems = basketViewModel.basketItems.collectAsState()

    val focusManager = LocalFocusManager.current
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var lastNameError by remember { mutableStateOf(false) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumberError by remember { mutableStateOf(false) }
    var userName by remember { mutableStateOf(TextFieldValue("")) }
    var userNameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var passwordError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue("")) }
    var postalCodeError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var addressError by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Complete Your Information",
                textAlign = TextAlign.Center,
                fontSize = 22.sp
            )
        }
        LazyColumn {
            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    AppTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = false
                        },
                        label = "First Name",
                        focusManager = focusManager,
                        isError = firstNameError,
                        errorText = "Please enter your first name"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError = false
                        },
                        label = "Last Name",
                        focusManager = focusManager,
                        isError = lastNameError,
                        errorText = "Please enter your last name"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = phoneNumber,
                        onValueChange = {
                            phoneNumber = it
                            phoneNumberError = false
                        },
                        label = "Phone Number",
                        focusManager = focusManager,
                        isError = phoneNumberError,
                        errorText = "please enter your phone number",
                        keyboardType = KeyboardType.Phone
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                            userNameError = false
                        },
                        label = "UserName",
                        focusManager = focusManager,
                        isError = userNameError,
                        errorText = "please enter your username"
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            passwordError = false
                        },
                        label = "Password",
                        focusManager = focusManager,
                        isError = passwordError,
                        errorText = "please enter your password",
                        keyboardType = KeyboardType.Password,
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = postalCode,
                        onValueChange = {
                            postalCode = it
                            postalCodeError = false
                        },
                        label = "Postal Code",
                        focusManager = focusManager,
                        isError = postalCodeError,
                        errorText = "please enter your postal code",
                        keyboardType = KeyboardType.Number
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    AppTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError = false
                        },
                        label = "Address",
                        focusManager = focusManager,
                        isError = addressError,
                        errorText = "please enter your address",
                        singleLine = false,
                        imeAction = ImeAction.Done
                    )
                }
            }
            item {
                Box(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Button(
                        onClick = {
                            firstNameError = firstName.text.isEmpty()
                            lastNameError = lastName.text.isEmpty()
                            phoneNumberError = phoneNumber.text.isEmpty()
                            userNameError = userName.text.isEmpty()
                            passwordError = password.text.isEmpty()
                            postalCodeError = postalCode.text.isEmpty()
                            addressError = address.text.isEmpty()
                            if (
                                !firstNameError &&
                                !lastNameError &&
                                !phoneNumberError &&
                                !userNameError &&
                                !passwordError &&
                                !postalCodeError &&
                                !addressError
                            ) {
                                navController.navigate("home")
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) { Text(text = "\$Pay") }
                }
            }
        }
    }
}