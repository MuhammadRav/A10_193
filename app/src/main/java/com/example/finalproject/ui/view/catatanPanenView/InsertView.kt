package com.example.finalproject.ui.view.catatanPanenView

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.finalproject.customWidget.CstTopAppBar
import com.example.finalproject.model.Tanaman
import com.example.finalproject.navigation.AlamatInsertCatatan
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.CatatanPanenInsertViewModel
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.InsertUiEvent
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.InsertUiState
import com.example.finalproject.ui.viewModel.catatanPanenViewModel.PenyediaCatatanPanenViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatatanPanenInsertScreen(
    navigateBack: ()-> Unit,
    modifier: Modifier = Modifier,
    viewModel: CatatanPanenInsertViewModel = viewModel(factory = PenyediaCatatanPanenViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CstTopAppBar(
                title = AlamatInsertCatatan.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ){ innerPadding ->
        InsertBody(
            insertUiState = viewModel.uiState,
            onCatatanPanenValueChange = viewModel::updateInsertCatatanPanenState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertCatatanPanen()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun InsertBody(
    insertUiState: InsertUiState,
    onCatatanPanenValueChange: (InsertUiEvent) -> Unit,
    onSaveClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ){
        FormInput(
            insertUiEvent = insertUiState.insertUiEvent,
//            tnmList = insertUiState.tnmList,
            onValueChange = onCatatanPanenValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInput(
    insertUiEvent: InsertUiEvent,
//    tnmList: List<Tanaman>,
    modifier: Modifier = Modifier,
    onValueChange: (InsertUiEvent)->Unit={},
    enabled: Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        OutlinedTextField(
            value = insertUiEvent.id_panen,
            onValueChange = { onValueChange(insertUiEvent.copy(id_panen = it))},
            label = { Text("ID Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.id_tanaman,
            onValueChange = { onValueChange(insertUiEvent.copy(id_tanaman = it))},
            label = { Text("ID Tanaman") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
//        DropdownMenuTanaman(
//            selectedTanaman = insertUiEvent.id_tanaman,
//            tnmList = tnmList,
//            onItemSelected = { selectedTanaman ->
//                onValueChange(insertUiEvent.copy(id_tanaman = selectedTanaman))
//            },
//            enabled = enabled
//        )
        OutlinedTextField(
            value = insertUiEvent.tanggal_panen,
            onValueChange = {onValueChange(insertUiEvent.copy(tanggal_panen = it))},
            label = { Text("Tanggal Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.jumlah_panen,
            onValueChange = { onValueChange(insertUiEvent.copy(jumlah_panen = it))},
            label = { Text("Jumlah Panen") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertUiEvent.keterangan,
            onValueChange = { onValueChange(insertUiEvent.copy(keterangan = it))},
            label = { Text("Keterangan") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled){
            Text(
                text = "Isi Semua Data",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}

//@Composable
//fun DropdownMenuTanaman(
//    selectedTanaman: String,
//    tnmList: List<Tanaman>,
//    onItemSelected: (String) -> Unit,
//    enabled: Boolean
//) {
//    var expanded by remember { mutableStateOf(false) }
//
//    OutlinedTextField(
//        value = selectedTanaman,
//        onValueChange = { },
//        label = { Text("ID Tanaman") },
//        modifier = Modifier.fillMaxWidth(),
//        enabled = enabled,
//        singleLine = true,
//        readOnly = true,
//        trailingIcon = {
//            IconButton(
//                onClick = { expanded = !expanded },
//                interactionSource = remember {
//                    MutableInteractionSource() }
//            ) {
//                Icon(Icons.Default.ArrowDropDown, contentDescription = null)
//            }
//        }
//    )
//
//    DropdownMenu(
//        expanded = expanded,
//        onDismissRequest = { expanded = false }
//    ) {
//        tnmList.forEach { tanaman ->
//            DropdownMenuItem(
//                onClick = {
//                    onItemSelected(tanaman.id_tanaman)
//                    expanded = false
//                }
//            ) {
//                Text(text = tanaman.id_tanaman)
//            }
//        }
//    }
//}