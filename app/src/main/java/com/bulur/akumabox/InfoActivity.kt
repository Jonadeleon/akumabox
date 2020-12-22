package com.bulur.akumabox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*
import kotlinx.android.synthetic.main.activity_info.*

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        setup()
    }

    private fun setup(){
        title="Información"
        confirmButton.setOnClickListener {
            if (namePlayer.text.isNotEmpty() && archetypePlayer.text.isNotEmpty()){

                //CONTINUAR AQUI
                //Fire
            }
        }
    }
}