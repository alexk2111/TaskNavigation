package com.sigmasoftware.akucherenko.tasknavigation

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.navigation.*
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.navigation.NavigationView

class MessageReceiver : BroadcastReceiver() {
    companion object {
        const val MESSAGE_RECEIVER_ACTION =
            "com.sigmasoftware.akucherenko.tasknavigation.MESSAGE_RECEIVER_ACTION"
    }


    override fun onReceive(context: Context?, intent: Intent?) {
        var title: String = "Title"
        var message: String = "Message"
        var data: String = "Body"
        if (intent != null) {
//            Toast.makeText(context, intent.action, Toast.LENGTH_SHORT).show()
            title = intent.getStringExtra(MyFirebaseMessagingService.MESSAGE_TITLE).toString()
            message = intent.getStringExtra(MyFirebaseMessagingService.MESSAGE_BODY).toString()
            data = intent.getStringExtra(MyFirebaseMessagingService.MESSAGE_DATA).toString()
        }


        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(message + data)

        builder.setPositiveButton("Ok") { _, _ ->
            val args = Bundle();
            args.putString("argument", data)
            Toast.makeText(context, data, Toast.LENGTH_SHORT).show()
            val pendingIntent = NavDeepLinkBuilder(context!!)
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.messageFragment)
                .setArguments(args)
                .createPendingIntent()
            pendingIntent.send()
        }

        builder.setNegativeButton(
            "cancel"
        ) { dialog, _ -> dialog.cancel() }
        builder.show()
    }
}