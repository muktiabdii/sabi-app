package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.HorizontalDivider
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

@Composable
fun BtmSheetExchange(
    currentStep: Int,
    points: String,
    selectedBank: String,
    amount: String,
    adminFee: String,
    totalAmount: String,
    onPointsChange: (String) -> Unit,
    onBankSelected: (String) -> Unit,
    onExchangeClick: (String) -> Unit,
    onNext: () -> Unit,
    onDismiss: () -> Unit
) {
    var accountNumber by remember { mutableStateOf("") }
    // perubahan nilai nomor rekening
    val onAccountNumberChange: (String) -> Unit = { newValue -> accountNumber = newValue }
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
                    1 -> FirstContent(points, onPointsChange, onNext)
                    2 -> SecondContent(
                        selectedBank = selectedBank,
                        onBankSelected = onBankSelected,
                        accountNumber = accountNumber,
                        onAccountNumberChange = { accountNumber = it },
                        amount = amount,
                        adminFee = adminFee,
                        totalAmount = totalAmount,
                        onConfirm = { showDialog = true }
                    )
                }
            }

            ButtonAuth(
                text = if (currentStep == 1) "LANJUT" else "TUKAR POIN SEKARANG",
                backgroundColor = BrownMain,
                textColor = Color.White,
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
    points: String,
    onPointsChange: (String) -> Unit,
    onNext: () -> Unit
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
        TextFieldAuth(
            value = points,
            onValueChange = onPointsChange,
            placeholder = "Masukkan jumlah koin yang akan ditukar"
        )
        Spacer(modifier = Modifier.height(16.dp))

        // nominal
        Text(
            text = "Nominal",
            style = Typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        // input nominal
        var nominal by remember { mutableStateOf("") }
        TextFieldNominal(
            value = nominal,
            onValueChange = { nominal = it }
        )
    }
}

@Composable
fun SecondContent(
    selectedBank: String, // bank dipilih
    onBankSelected: (String) -> Unit,
    accountNumber: String, // nomor rekening
    onAccountNumberChange: (String) -> Unit,
    amount: String, // uang diterima
    adminFee: String, // biaya admin
    totalAmount: String, // total biaya
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
        TextFieldAuth(
            value = accountNumber,
            onValueChange = onAccountNumberChange,
            placeholder = "Masukkan nomor rekening",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))

        // info biaya
        Text(text = "Nominal", style = Typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Uang", style = Typography.bodyLarge)
            Text(text = amount, style = Typography.bodyLarge)
        }
        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Admin", style = Typography.bodyLarge)
            Text(text = adminFee, style = Typography.bodyLarge)
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
                text = totalAmount,
                style = Typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun PreviewBtmSheetExchange() {
    var currentStep by remember { mutableStateOf(1) }
    var points by remember { mutableStateOf("") }
    var selectedBank by remember { mutableStateOf("") }

    BtmSheetExchange(
        currentStep = currentStep,
        points = points,
        selectedBank = selectedBank,
        amount = "Rp60.000,00",
        adminFee = "Rp2.500,00",
        totalAmount = "Rp62.500,00",
        onPointsChange = { points = it },
        onBankSelected = { selectedBank = it },
        onExchangeClick = { },
        onNext = { currentStep = 2 },
        onDismiss = {}
    )
}

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
//    val amount = "Rp60.000,00"
//    val adminFee = "Rp2.500,00"
//    val totalAmount = "Rp62.500,00"
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
