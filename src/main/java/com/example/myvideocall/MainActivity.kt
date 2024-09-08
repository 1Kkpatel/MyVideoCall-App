package com.example.myvideocall

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {

    private lateinit var targetUserId: EditText
    private lateinit var myUserIdText: TextView
    private lateinit var videoCallButton: ZegoSendCallInvitationButton
    private lateinit var voiceCallButton: ZegoSendCallInvitationButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        targetUserId = findViewById(R.id.targetUserId)
        myUserIdText = findViewById(R.id.myUserIdText)
        videoCallButton = findViewById(R.id.videoCallButton)
        voiceCallButton = findViewById(R.id.voiceCallButton)

        val myUserId=intent.getStringExtra("userID")
        myUserIdText.text="Hi $myUserId, \n Whom do you want to call?"

        targetUserId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetUserId=targetUserId.text.toString()
                if (targetUserId.isNotEmpty()) {
                    startVideoCall(targetUserId)
                    startVoiceCall(targetUserId)
                }


            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
    private fun startVideoCall(targetUserId: String) {
        val targetUserName:String=targetUserId

        videoCallButton.setIsVideoCall(true)
        videoCallButton.resourceID="zego_uikit_call"
        videoCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserName)))
    }
    private fun startVoiceCall(targetUserId: String) {
        val targetUserName:String=targetUserId

        voiceCallButton.setIsVideoCall(true)
        voiceCallButton.resourceID="zego_uikit_call"
        voiceCallButton.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserName)))
    }
}