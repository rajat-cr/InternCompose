package com.coderroots.interncomposeclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class DashboardActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DashboardScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true)
@Composable
fun DashboardScreen(){
    var selectedIndex  by remember { mutableStateOf(0) }
    var navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
     val currentRoot = navBackStackEntry?.destination?.route?: "home"


     selectedIndex =  when(currentRoot){
        "home"->0
        "setting"->1
        "profile"->2
        else-> 0
    }

    Scaffold(
     topBar = {
         TopAppBar(
             title = {
                 Text("DashBoard Screen",
                     color = Color.White)
             },
            // modifier = Modifier.fillMaxWidth()
             colors = TopAppBarDefaults.topAppBarColors(
                 containerColor = colorResource(R.color.purple_200)
             )
         )
     },
        bottomBar = {
            BottomAppBar(
                containerColor = colorResource(R.color.purple_200),

            ) {
                Row(Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround) {

                    Column() {
                        Icon(
                            Icons.Default.Home,
                            contentDescription = "",
                            tint = if (selectedIndex == 0)
                                Color.White
                            else
                                Color.Black,
                            modifier = Modifier.size(40.dp).clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() },

                                ) {
                                //selectedIndex = 0
                                navController.navigate("home")
                            }
                        )
                        Spacer(Modifier.height(5.dp))
                        Text("Home",
                            color = if (selectedIndex == 0)
                                Color.White
                            else
                            Color.Black
                        )
                    }

                    Icon(
                        Icons.Default.Settings,
                        contentDescription = "",
                        tint = if(selectedIndex == 1)
                            Color.White
                        else
                            Color.Black,
                        modifier = Modifier.size(40.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ){
                          //  selectedIndex = 1
                            navController.navigate("setting")
                        }
                    )
                    Icon(
                        Icons.Default.AccountCircle,
                        contentDescription = "",
                        tint = if(selectedIndex == 2)
                            Color.White
                        else
                            Color.Black,
                        modifier = Modifier.size(40.dp).clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ){
                           // selectedIndex = 2
                            navController.navigate("profile")
                        }
                    )
                }
            }
        }

    ) { innerPadding->

//        Box(Modifier.fillMaxSize().padding(innerPadding).padding(start = 20.dp, end = 20.dp),
//contentAlignment = Alignment.Center
//            ){
//            ElevatedButton(
//                onClick = {
//
//                },
//                modifier = Modifier.fillMaxWidth(),
//                shape = RoundedCornerShape(7.dp),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = colorResource(R.color.purple_200)
//                )
//            ) {
//                Text("Hello Im new Here")
//
//            }
//        }

        NavHost(navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize().padding(innerPadding)){
            composable("home"){
                HomeScreen()
            }
            composable("setting"){
                SettingScreen()
            }
            composable("setting"){
                ProfileScreen()
            }

        }
    }
}


@Composable
fun HomeScreen(){
Text("Hello Home")
}

@Composable
fun SettingScreen(){

}
@Composable
fun ProfileScreen(){

}