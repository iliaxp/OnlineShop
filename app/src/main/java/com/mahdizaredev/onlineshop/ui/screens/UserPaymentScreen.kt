package com.mahdizaredev.onlineshop.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mahdizaredev.onlineshop.MainActivity
import com.mahdizaredev.onlineshop.db.viewmodels.BasketEntityViewModel
import com.mahdizaredev.onlineshop.db.viewmodels.UserEntityViewModel
import com.mahdizaredev.onlineshop.models.customer.Customer
import com.mahdizaredev.onlineshop.models.customer.User
import com.mahdizaredev.onlineshop.models.customer.UserVM
import com.mahdizaredev.onlineshop.models.invoices.InvoiceItem
import com.mahdizaredev.onlineshop.models.invoices.PaymentTransaction
import com.mahdizaredev.onlineshop.ui.theme.Dark
import com.mahdizaredev.onlineshop.viewmodels.customers.UserViewModel
import com.mahdizaredev.onlineshop.viewmodels.invoices.TransactionViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun UserPaymentScreen(
    navController: NavController,
    basketViewModel: BasketEntityViewModel,
    userEntityViewModel: UserEntityViewModel,
    mainActivity: MainActivity,
    transactionViewModel: TransactionViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),

    ) {

    val context = LocalContext.current
    val currentUser by remember { mutableStateOf(userEntityViewModel.currentUser) }
    val isLoggedIn by remember { mutableStateOf(userEntityViewModel.currentUser.value != null) }

    var firstName by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.firstName!! else "")) }
    var firstNameError by remember { mutableStateOf(false) }
    var lastName by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.lastName!! else "")) }
    var lastNameError by remember { mutableStateOf(false) }
    var phone by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.phone!! else "")) }
    var phoneError by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.username!! else "")) }
    var usernameError by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf(TextFieldValue()) }
    var passwordError by remember { mutableStateOf(false) }
    var postalCode by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.postalCode!! else "")) }
    var postalCodeError by remember { mutableStateOf(false) }
    var address by remember { mutableStateOf(TextFieldValue(if(isLoggedIn) currentUser.value!!.address!! else "")) }
    var addressError by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    var focusManager = LocalFocusManager.current

    Column {
        Row {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
            }
            Spacer(modifier = Modifier.width(5.dp))
            Text(
                text = "Complete Your Information", textAlign = TextAlign.Center,
                fontSize = 22.sp,
                modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
            )
        }
        LazyColumn {
            item {
                Column(Modifier.padding(20.dp)) {
                    OutlinedTextField(
                        value = firstName,
                        onValueChange = {
                            firstName = it
                            firstNameError = false
                        },
                        label = { Text(text = "First Name") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        trailingIcon = {
                            if (firstNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = firstNameError
                    )
                    if (firstNameError) {
                        Text("Please enter your first name", color = Color.Red, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = lastName,
                        onValueChange = {
                            lastName = it
                            lastNameError = false
                        },
                        label = { Text(text = "Last Name") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        trailingIcon = {
                            if (lastNameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = lastNameError
                    )
                    if (lastNameError) {
                        Text("Please enter your first name", color = Color.Red, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = username,
                        enabled = currentUser.value == null,
                        onValueChange = {
                            username = it
                            usernameError = false
                        },
                        label = { Text(text = "Username") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        trailingIcon = {
                            if (usernameError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = usernameError
                    )
                    if (usernameError) {
                        Text("Please enter your username", color = Color.Red, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            phoneError = false
                        },
                        label = { Text(text = "Phone Number") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Phone,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        trailingIcon = {
                            if (phoneError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = phoneError
                    )
                    if (phoneError) {
                        Text("Please enter your Phone Number", color = Color.Red, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    if (currentUser.value == null) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                                passwordError = false
                            },
                            label = { Text(text = "Password") },
                            modifier = Modifier.fillMaxWidth(),

                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }),

                            trailingIcon = {
                                if (passwordError) {
                                    Icon(
                                        imageVector = Icons.Filled.Warning,
                                        contentDescription = "error",
                                        tint = Color.Red
                                    )
                                }
                            },
                            isError = passwordError
                        )
                        if (passwordError) {
                            Text("Please enter your password", color = Color.Red, fontSize = 12.sp)
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                    OutlinedTextField(
                        value = postalCode,
                        onValueChange = {
                            postalCode = it
                            postalCodeError = false
                        },
                        label = { Text(text = "Postal Code") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        singleLine = true,
                        trailingIcon = {
                            if (postalCodeError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = postalCodeError
                    )
                    if (postalCodeError) {
                        Text("Please enter your postal Code", color = Color.Red, fontSize = 12.sp)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    OutlinedTextField(
                        value = address,
                        onValueChange = {
                            address = it
                            addressError = false
                        },

                        label = { Text(text = "Address") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        keyboardActions = KeyboardActions(onNext = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }),
                        trailingIcon = {
                            if (addressError) {
                                Icon(
                                    imageVector = Icons.Filled.Warning,
                                    contentDescription = "error",
                                    tint = Color.Red
                                )
                            }
                        },
                        isError = addressError
                    )
                    if (addressError) {
                        Text("Please enter your address", color = Color.Red, fontSize = 12.sp)
                    }


                    Spacer(modifier = Modifier.height(20.dp))
                    if (!isLoading) {
                        Button(
                            onClick = {
                                if (firstName.text.isEmpty()) {
                                    firstNameError = true
                                }
                                if (lastName.text.isEmpty()) {
                                    lastNameError = true
                                }
                                if (username.text.isEmpty()) {
                                    usernameError = true
                                }
                                if (phone.text.isEmpty()) {
                                    phoneError = true
                                }
                                if (currentUser.value == null && password.text.isEmpty()) {
                                    passwordError = true
                                }
                                if (address.text.isEmpty()) {
                                    addressError = true
                                }
                                if (postalCode.text.isEmpty()) {
                                    postalCodeError = true
                                }
                                if (firstNameError || lastNameError || usernameError || passwordError || phoneError || addressError) {
                                    return@Button
                                }
                                var userInfo = UserVM(
                                    id = if (currentUser.value == null) null else currentUser.value!!.userId,
                                    customerId = if (currentUser.value == null) null else currentUser.value!!.customerId,
                                    username = username.text,
                                    password = password.text,
                                    firstName = firstName.text,
                                    lastName = lastName.text,
                                    phone = phone.text,
                                    address = address.text,
                                    postalCode = postalCode.text,
                                )
                                var items = ArrayList<InvoiceItem>()
                                basketViewModel.dataList.value.forEach {
                                    items.add(InvoiceItem.convertFromBasket(it))
                                }
                                var request = PaymentTransaction(
                                    user = userInfo,
                                    items = items
                                )
                                isLoading = true
                                transactionViewModel.goToPayment(request) { response ->
                                    if (response.status == "OK" && response.data!!.isNotEmpty()) {
                                        if (currentUser.value == null) {
                                            userViewModel.login(
                                                UserVM(
                                                    username = username.text,
                                                    password = password.text
                                                )
                                            ) { userResponse ->
                                                isLoading = false
                                                if (userResponse.status == "OK") {
                                                    val user = userResponse.data!![0]
                                                    CoroutineScope(Dispatchers.IO).launch {
                                                        userEntityViewModel.insert(user.convertToEntity())
                                                    }
                                                }
                                            }

                                        }
                                        CoroutineScope(Dispatchers.IO).launch {
                                            basketViewModel.deleteAll()
                                            mainActivity.finish()
                                        }
                                        val intent =
                                            Intent(
                                                Intent.ACTION_VIEW,
                                                Uri.parse(response.data!![0])
                                            )
                                        context.startActivity(intent)
                                    } else if (response.message!!.isNotEmpty()) {
                                        Toast.makeText(
                                            context,
                                            response.message,
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                    }
                                    isLoading = false
                                }

                            },
                            shape = RoundedCornerShape(15.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                contentColor = Dark
                            )
                        ) {
                            Text(
                                text = "\$Pay", fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }
                    }
                    if (isLoading) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(15.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}