package com.example.myvideocall

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {

    private lateinit var myUserId:EditText
    private lateinit var loginButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        myUserId=findViewById(R.id.myUserId)
        loginButton=findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val myUserId=myUserId.text.toString()
            if(myUserId.isNotEmpty()) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("userID", myUserId)
                startActivity(intent)

                setupZegoUIKit(myUserId)

            }
        }
    }
    private fun setupZegoUIKit(userID:String){
        val application: Application = application  // Android's application context
        val appID:Long =1304533949  // yourAppID
        val  appSign:String="875d85b4cf815615533a62d6a828031a6e1660fe42427a3454e70a34d6c85a88"  // yourAppSign
        val  userName:String=userID  // yourUserID, userID should only contain numbers, English characters, and '_'.
        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()

        ZegoUIKitPrebuiltCallService.init(
            getApplication(),
            appID,
            appSign,
            userID,
            userName,
            callInvitationConfig
        )

    }
    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}