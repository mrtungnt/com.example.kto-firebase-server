package com.example.plugins

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserRecord
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

fun Application.configRouting() {
    routing {
        var c = 0
        get("/") {
            println(++c)
            delay((6).toDuration(DurationUnit.SECONDS))
            call.respondText("Hello World!")
        }

        post {
            println(this.coroutineContext)
            launch {
                println(context)
                val phoneNumber = call.receiveText()
                var userRecord: UserRecord
                val request = UserRecord.CreateRequest()
                    .setPhoneNumber(phoneNumber)

                userRecord = FirebaseAuth.getInstance().createUser(request)

                /*val userIdText = when (userRecord?.uid) {
                    null -> "No user"
                    else -> "User ${userRecord?.uid}"
                }*/
                call.respondText(
                    "${userRecord.uid} registered with phoneNumber $phoneNumber",
                    status = HttpStatusCode.Created
                )
            }
        }
    }
}