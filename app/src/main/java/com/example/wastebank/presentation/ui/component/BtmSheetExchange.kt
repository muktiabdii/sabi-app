package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.wastebank.presentation.ui.theme.BrownMain
import com.example.wastebank.presentation.ui.theme.GreyLine
import com.example.wastebank.presentation.ui.theme.Typography
import com.example.wastebank.presentation.viewmodel.AuthViewModel
import com.example.wastebank.presentation.viewmodel.MoneyExchangeViewModel

@Composable
fun BtmSheetExchange(
    moneyExchangeViewModel: MoneyExchangeViewModel,
    authViewModel: AuthViewModel,
    points: Int,
    amount: Int,
    selectedBank: String,
    accountNumber: String,
    adminFee: Int,
    totalAmount: Int,
    password: String,
    currentStep: Int,
    onExchangeClick: () -> Unit,
    onNext: () -> Unit,
    onDismiss: () -> Unit
) {
    // tampilkan masukkan password
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
            .background(Color.White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(24.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // garis atas
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(5.dp)
                    .background(BrownMain, RoundedCornerShape(2.dp))
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                when (currentStep) {
                    1 -> FirstContent(
                        points = points,
                        amount = amount,
                        onPointsChange = { moneyExchangeViewModel.updatePointAndAmount(it) }
                    )
                    2 -> SecondContent(
                        selectedBank = selectedBank,
                        accountNumber = accountNumber,
                        amount = amount,
                        adminFee = adminFee,
                        totalAmount = totalAmount,
                        onBankSelected = { moneyExchangeViewModel.updateBankName(it) },
                        onAccountNumberChange = { moneyExchangeViewModel.updateAccountNumber(it) },
                        onConfirm = { showDialog = true }
                    )
                }
            }

            ButtonAuth(
                text = if (currentStep == 1) "LANJUT" else "TUKAR POIN SEKARANG",
                onClick = {
                    if (currentStep == 1) {
                        onNext()
                    } else {
                        showDialog = true // menampilkan dialog
                    }
                }
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // tampilkan dialog masukkan password
    if (showDialog) {
        RedeemPointDialog(
            password,
            onPasswordChange = { authViewModel.updatePassword(it) },
            onDismiss = { showDialog = false },
            onConfirm = {
                showDialog = false
                onDismiss()
            }
        )
    }


}

@Composable
fun FirstContent(
    points: Int,
    amount: Int,
    onPointsChange: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
    ) {
        // tukar poin
        Text(
            text = "Tukar Poin",
            style = Typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(8.dp))

        // garis pembatas
        HorizontalDivider(thickness = 1.dp, color = GreyLine)
        Spacer(modifier = Modifier.height(16.dp))

        // jumlah poin
        Text(
            text = "Jumlah Poin",
            style = Typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // input jumlah poin
        NumberTextFieldAuth(
            value = points, // Int dari ViewModel
            onValueChange = onPointsChange,
            placeholder = "Masukkan jumlah poin yang ingin ditukar"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // nominal
        Text(
            text = "Nominal",
            style = Typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextNominal(amount)

    }
}

@Composable
fun SecondContent(
    selectedBank: String,
    accountNumber: String,
    amount: Int,
    adminFee: Int,
    totalAmount: Int,
    onBankSelected: (String) -> Unit,
    onAccountNumberChange: (String) -> Unit,
    onConfirm: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
    ) {
        // tukar poin
        Text(
            text = "Tukar Poin",
            style = Typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(12.dp))

        HorizontalDivider(thickness = 1.dp, color = GreyLine)
        Spacer(modifier = Modifier.height(16.dp))

        // bank tujuan
        Text(
            text = "Bank Tujuan",
            style = Typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // dropdown pilih bank
        DropdownBank(
            bankList = listOf(
                "BANK BRI", "BANK MANDIRI", "BANK BNI", "BANK BTN",
                "BANK RAYA INDONESIA", "BANK BCA", "BANK CIMB NIAGA"
            ),
            selectedBank = selectedBank,
            onBankSelected = onBankSelected
        )
        Spacer(modifier = Modifier.height(16.dp))

        // nomor rekening
        Text(
            text = "Nomor Rekening",
            style = Typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // input nomor rekening
        var errorMessage by remember { mutableStateOf("") }

        TextFieldAuth(
            value = accountNumber,
            onValueChange = { newValue ->
                val filteredValue = newValue.filter { it.isDigit() } // Hanya angka
                onAccountNumberChange(filteredValue) // Update state

                errorMessage = when (selectedBank) {
                    "BANK BRI" -> if (filteredValue.length != 15) "Nomor rekening BANK BRI harus 15 digit" else ""
                    "BANK MANDIRI", "BANK BTN", "BANK RAYA INDONESIA" -> if (filteredValue.length < 10) "Nomor rekening $selectedBank minimal 10 digit" else ""
                    "BANK BNI", "BANK BCA" -> if (filteredValue.length != 10) "Nomor rekening $selectedBank harus 10 digit" else ""
                    "BANK CIMB NIAGA" -> if (filteredValue.length != 13) "Nomor rekening BANK CIMB NIAGA harus 13 digit" else ""
                    else -> ""
                }
            },
            placeholder = "Masukkan nomor rekening",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

// Menampilkan pesan error jika ada
        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = Typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // info biaya
        Text(text = "Nominal", style = Typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Uang", style = Typography.bodyLarge)
            Text(text = "$amount", style = Typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Admin", style = Typography.bodyLarge)
            Text(text = "$adminFee", style = Typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(8.dp))

        HorizontalDivider(thickness = 1.dp, color = GreyLine)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Total",
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "$totalAmount",
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun NumberTextFieldAuth(
    value: Int,
    onValueChange: (Int) -> Unit,
    placeholder: String
) {
    var errorMessage by remember { mutableStateOf("") }
    var textValue by remember { mutableStateOf(value.toString()) } // Simpan input teks

    Column {
        TextFieldAuth(
            value = textValue,
            onValueChange = { newValue ->
                val filteredValue = newValue.filter { it.isDigit() } // Hanya angka
                textValue = filteredValue // Update teks field

                val intValue = filteredValue.toIntOrNull()

                if (intValue != null) {
                    if (intValue < 1000) {
                        onValueChange(intValue)
                        errorMessage = "Minimal 1000 poin"
                    } else {
                        onValueChange(intValue) // Update state jika valid
                        errorMessage = ""
                    }
                }
            },
            placeholder = placeholder,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = Color.Red,
                style = Typography.bodyMedium
            )
        }
    }
}




//@Preview(showBackground = false)
//@Composable
//fun PreviewBtmSheetExchange() {
//    var currentStep by remember { mutableStateOf(1) }
//    var points by remember { mutableStateOf("") }
//    var selectedBank by remember { mutableStateOf("") }
//
//    BtmSheetExchange(
//        currentStep = currentStep,
//        points = points,
//        selectedBank = selectedBank,
//        amount = "Rp60.000,00",
//        adminFee = "Rp2.500,00",
//        totalAmount = "Rp62.500,00",
//        onPointsChange = { points = it },
//        onBankSelected = { selectedBank = it },
//        onExchangeClick = { },
//        onNext = { currentStep = 2 },
//        onDismiss = {}
//    )
//}

//@Preview(showBackground = false)
//@Composable
//fun PreviewFirstContent() {
//    var points by remember { mutableStateOf("") }
//
//    FirstContent(
//        points = points,
//        onPointsChange = { points = it },
//        onNext = {}
//    )
//}

//@Preview(showBackground = false)
//@Composable
//fun PreviewSecondContent() {
//    var selectedBank by remember { mutableStateOf("") }
//    var accountNumber by remember { mutableStateOf("") }
//
//    val amount = 60000
//    val adminFee = 2500
//    val totalAmount = 62500
//
//    SecondContent(
//        selectedBank = selectedBank,
//        onBankSelected = { selectedBank = it },
//        accountNumber = accountNumber,
//        onAccountNumberChange = { accountNumber = it },
//        amount = amount,
//        adminFee = adminFee,
//        totalAmount = totalAmount,
//        onConfirm = { },
//    )
//}
