package com.example.order


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.order.ui.theme.BaseColor
import com.example.order.ui.theme.Orange400
import com.example.order.ui.theme.Pink80

@Composable
fun OrderView(
    @DrawableRes imageRes: Int = R.drawable.classicbeef,
    onTapButton: () -> Unit = {}
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .verticalScroll(scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.order_screen),
            contentDescription = "order screen",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
        )
        Box(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.order_background
                ),
                contentDescription = "hamburger",
            )
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "hamburger",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
        MainDishSection()
        SideMenuSection()
        SauceAmountSection()
        DrinkSelectionSection()
        OrderButtonSection("注文する", onTapButton)
    }
}

@Preview(showBackground = true, widthDp = 400, heightDp = 1200, backgroundColor = 0xFF000000)
@Composable
private fun OrderViewPreview() {
    OrderView()
}

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
                modifier = Modifier.toggleable(value = frenchFries,
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

@Composable
fun DrinkSelectionSection() {
    var expanded by remember { mutableStateOf(false) }
    var selectedDrink by remember { mutableStateOf("選択してください") }
    val drinks = listOf("アイスコーヒー", "アイスカフェオレ", "コーラ")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(BaseColor, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("ドリンクを選択してください", style = MaterialTheme.typography.titleLarge)
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedDrink,
                    onValueChange = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = true },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown,
                            "dropdown",
                            Modifier.clickable { expanded = true },
                        )
                    },
                    label = { Text("ドリンク") },
                    readOnly = true
                )
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    drinks.forEach { drink ->
                        DropdownMenuItem(text = { Text(text = drink) }, onClick = {
                            selectedDrink = drink
                            expanded = false
                        })
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
private fun DrinkSelectionSectionPreview() {
    DrinkSelectionSection()
}

@Composable
fun OrderButtonSection(text: String, onClick: () -> Unit = {}) {
    val gradient = Brush.verticalGradient(
        colors = listOf(Pink80, Orange400)
    )
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        contentPadding = PaddingValues(),
        modifier = Modifier
            .fillMaxWidth()
            .background(gradient, shape = MaterialTheme.shapes.extraLarge)
    ) {
        Text(
            text = text,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OrderButtonSectionPreview() {
    OrderButtonSection("注文する")
}
