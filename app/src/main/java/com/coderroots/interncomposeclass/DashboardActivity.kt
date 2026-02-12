package com.coderroots.interncomposeclass

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.coderroots.interncomposeclass.roomdb.StudentDao
import com.coderroots.interncomposeclass.roomdb.StudentDatabase
import com.coderroots.interncomposeclass.roomdb.StudentEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
                                navController.navigate("home"){
                                    popUpTo(navController.graph.startDestinationId){
                                        saveState =true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
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
                            navController.navigate("setting"){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState =true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
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
                            navController.navigate("profile"){
                                popUpTo(navController.graph.startDestinationId){
                                    saveState =true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
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
            composable("profile"){
                ProfileScreen()
            }

        }
    }
}



@Preview(showSystemUi = true)
@Composable
fun HomeScreen(){
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
//    var studentList = remember { mutableStateListOf<StudentEntity>() }
    val scope = rememberCoroutineScope()

    val database = StudentDatabase.getInstance(context)
    val studentDao = database?.studentDao()

   val students =  studentDao?.getStudents()?.collectAsState(initial = emptyList())

    

    Box(Modifier.fillMaxSize()){
        Column(Modifier.fillMaxSize().padding(10.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(students?.value!!.size){index->
                    val student = students?.value!![index]
                    Card(Modifier.fillMaxWidth().padding(top = 10.dp),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 3.dp
                        ),
                        shape = RoundedCornerShape(7.dp)
                        ) {
                        Text(text = student.name.toString(),
                            modifier = Modifier.fillMaxWidth().padding(20.dp),
                            fontSize = 16.sp
                           )
                    }

                }
            }

        }

        FloatingActionButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.align(Alignment.BottomEnd).padding(10.dp)
        ) {
            Icon(Icons.Default.Add,
                contentDescription = "")
        }
    }
    if(showDialog){
        ShowDialogScreen(
            showDialog = showDialog,
            dismiss = {showDialog = false},
            studentDao = studentDao,
           // studentList = studentList
        )
    }
}



@Composable
fun ShowDialogScreen(
    showDialog: Boolean,
    dismiss: () -> Unit,
    studentDao: StudentDao?,
  //  studentList: SnapshotStateList<StudentEntity>,
    ) {
    var name by remember { mutableStateOf("") }
    var rollNo by remember { mutableStateOf("") }
    val context = LocalContext.current
    val scope = rememberCoroutineScope() // Database Api, heavy  withCOntext
    if(showDialog) {
        Dialog(
            properties = DialogProperties(
                dismissOnClickOutside = false
            ),
            onDismissRequest = dismiss,
            content = {
                Column(
                    Modifier.fillMaxWidth().padding(10.dp).background(color = Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                )
                {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Add Student",
                        fontSize = 25.sp
                    )
                    Spacer(Modifier.height(10.dp))
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    )
                    Spacer(Modifier.height(10.dp))
                    TextField(
                        value = rollNo,
                        onValueChange = { rollNo = it },
                        singleLine = true,
                        maxLines = 1,
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp)
                    )
                    Spacer(Modifier.height(10.dp))

                    ElevatedButton(
                        onClick = {
                            if(name.isEmpty()){
                                Toast.makeText(context,"Enter Name", Toast.LENGTH_SHORT).show()
                            }else if(rollNo.isEmpty()){
                                Toast.makeText(context,"Enter RollNo", Toast.LENGTH_SHORT).show()
                            }else {
                                var student = StudentEntity(
                                    name = name,
                                    rollNo = rollNo
                                )
                                // Dispacthers.IO for Background
                                scope.launch {
                                   val data = withContext(Dispatchers.IO) {
                                        studentDao?.addStudent(student)
                                    }


                                    dismiss()
                                    Toast.makeText(context,"Student Saved", Toast.LENGTH_SHORT).show()
                                }

                            }
                        },
                        modifier = Modifier.fillMaxWidth().padding(10.dp),
                        shape = RoundedCornerShape(7.dp)
                    ) {
                        Text("Save")
                    }
                }
            }
        )
    }
}
@Composable
fun SettingScreen(){

}
@Composable
fun ProfileScreen(){

}