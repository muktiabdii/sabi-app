package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import com.example.wastebank.R
import com.example.wastebank.presentation.ui.theme.BrownMain

@Composable
fun DropdownBank(
    bankList: List<String>,
    selectedBank: String,
    onBankSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column {
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = BrownMain, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(34.dp)
                .clickable { expanded = !expanded }
                .padding(horizontal = 12.dp, vertical = 6.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (selectedBank.isEmpty()) "Cari bank" else selectedBank,
                    color = if (selectedBank.isEmpty()) Color.Gray else Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_down),
                    contentDescription = "dropdown icon",
                    tint = BrownMain
                )
            }
        }

        // dropdown popup
        if (expanded) {
            Popup(
                alignment = Alignment.TopStart,
                offset = with(LocalDensity.current) { IntOffset(0, 40.dp.roundToPx()) },
                properties = PopupProperties(focusable = true),
                onDismissRequest = { expanded = false }
            ) {
                Column(
                    modifier = Modifier
                        .border(1.dp, BrownMain, RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .heightIn(max = 231.dp)
                        .padding(vertical = 4.dp)
                ) {
                    bankList.forEach { bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                coroutineScope.launch {
                                    delay(100)
                                    onBankSelected(bank)
                                    expanded = false
                                }
                            }
                        )
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

@Composable
fun FetchAndDisplayDropdownBank() {
    var selectedBank by remember { mutableStateOf("") }
    var bankList by remember { mutableStateOf(listOf<String>()) }

    // fetching data
    LaunchedEffect(Unit) {
        bankList = fetchBankList()
    }

    DropdownBank(
        bankList = bankList,
        selectedBank = selectedBank,
        onBankSelected = { selectedBank = it })
}

// fungsi fetching data
suspend fun fetchBankList(): List<String> {
    delay(1000)
    return listOf(
        "BANK BRI", "BANK MANDIRI", "BANK BNI", "BANK BTN",
        "BANK RAYA INDONESIA", "BANK BCA", "BANK CIMB NIAGA"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewDropdownBank() {
    FetchAndDisplayDropdownBank()
}
