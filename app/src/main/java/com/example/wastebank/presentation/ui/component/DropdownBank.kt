package com.example.wastebank.presentation.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
    var selectedBankState by remember { mutableStateOf(selectedBank) }
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
                    text = if (selectedBankState.isBlank()) "Cari bank" else selectedBankState,
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
                offset = with(LocalDensity.current) { IntOffset(10.dp.roundToPx(), 40.dp.roundToPx()) },
                properties = PopupProperties(focusable = true),
                onDismissRequest = { expanded = false },
            ) {
                Column(
                    modifier = Modifier
                        .border(1.dp, BrownMain, RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .width(335.dp)
                        .heightIn(min = 100.dp, max = 290.dp)
                        .verticalScroll(rememberScrollState())
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                ) {
                    bankList.forEachIndexed { index, bank ->
                        DropdownMenuItem(
                            text = { Text(bank) },
                            onClick = {
                                coroutineScope.launch {
                                    delay(100)
                                    // simpan bank dipilih
                                    selectedBankState = bank
                                    // perbarui ke parent
                                    onBankSelected(bank)
                                    expanded = false
                                }
                            }
                        )
                        if (index != bankList.lastIndex) {
                            HorizontalDivider()
                        }
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
