package com.example

import com.example.plugins.configRouting
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import io.ktor.server.application.*
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.io.FileInputStream

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    val serviceAccount =
        FileInputStream("/Users/tungnt/Downloads/fir-auth-fb92f-firebase-adminsdk-233w3-3d55ca5ba3.json")
    val options = FirebaseOptions.builder()
        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
        .build();
    FirebaseApp.initializeApp(options)
    configRouting()
}
