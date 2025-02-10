package com.example.order


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.order.ui.theme.BaseColor

@Composable
fun RadioButtonWithText(
    text: String, selected: Boolean, onSelect: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .selectable(selected = selected, onClick = onSelect),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = null)
        Text(
            text = text, style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RadioButtonWithTextPreview() {
    RadioButtonWithText(text = "ハンバーガー", selected = true, onSelect = {})
}

@Composable
fun MainDishSection() {
    var selectedDish by remember { mutableStateOf("ハンバーガー") }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .selectableGroup()
        ) {
            Text(text = "メインを選択", style = MaterialTheme.typography.titleLarge)
            RadioButtonWithText(
                text = "ハンバーガー", selected = selectedDish == "ハンバーガー"
            ) {
                selectedDish = "ハンバーガー"
            }
            RadioButtonWithText(
                text = "チーズバーガー", selected = selectedDish == "チーズバーガー"
            ) { selectedDish = "チーズバーガー" }

        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0x000000
)
@Composable
private fun MainDishSectionPreview() {
    MainDishSection()
}

@Composable
fun SideMenuSection() {
    var frenchFries by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("サイドメニュー", style = MaterialTheme.typography.titleLarge)
            Row(
                modifier = Modifier.toggleable(
                    value = frenchFries,
                    onValueChange = { frenchFries = it }),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = frenchFries, onCheckedChange = null)
                Text("フレンチフライ", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(
    showBackground = true, backgroundColor = 0xFF000000
)
@Composable
private fun SideMenuSectionPreview() {
    SideMenuSection()
}

@Composable
fun SauceAmountSection() {
    var sauceAmount by remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ソースの量を調整できます", style = MaterialTheme.typography.titleLarge)
            Slider(
                value = sauceAmount,
                onValueChange = { sauceAmount = it },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
            Text(
                text = when {
                    sauceAmount < 0.3f -> "少なめ"
                    sauceAmount > 0.7f -> "多め"
                    else -> "普通"
                }, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun SauceAmountSectionPreview() {
    SauceAmountSection()
}
