package com.example.userpermissions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.userpermissions.ui.theme.UserPermissionsTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UserPermissionsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                  //  Permission()
                    Permissions2()


                }
            }
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun Permissions2() {
    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (permissionState.allPermissionsGranted) {
            Text(text = "All Permission Granted")
        } else {
            Button(
                onClick = {
                    permissionState.launchMultiplePermissionRequest()
                }
            ) {
                Text(text = "Request Permission")
            }
        }
    }

}



//@Composable
//fun Permission() {
//    val permissionList = listOf(
//        android.Manifest.permission.CAMERA,
//        android.Manifest.permission.RECORD_AUDIO
//    )
//
//    val isPermissionGranted = remember { mutableStateOf(false) }
//
//    val launcher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestMultiplePermissions(),
//        onResult = {
//            isPermissionGranted.value = it.all {
//                it.value
//            }
//        }
//    )
//
//    Column (
//        modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//
//    ) {
//        if (isPermissionGranted.value == false) {
//            Button(
//                onClick = {
//                    launcher.launch(permissionList.toTypedArray())
//                }
//            ) {
//                Text(text = "Request Permission")
//            }
//        } else {
//            Text(text = "All Permission Granted",
//                )
//        }
//    }
//
//}