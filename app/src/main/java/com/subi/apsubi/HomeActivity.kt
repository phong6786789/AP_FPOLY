package com.subi.apsubi

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import kotlinx.android.synthetic.main.activity_home.*
import okhttp3.Request


class HomeActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            tvName.text = "Hello ${acct.displayName}"
            tvToken.text = acct.idToken
        }


        buttonLogout()
    }

    private fun buttonLogout() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        button_sign_out.setOnClickListener {
            googleSignInClient.signOut().addOnCompleteListener {
                finish()
            }.addOnFailureListener {
                Toast.makeText(this, "Đăng xuất lỗi", Toast.LENGTH_SHORT).show()
            }
        }
    }


}