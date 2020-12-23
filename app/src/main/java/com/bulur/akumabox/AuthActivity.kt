package com.bulur.akumabox

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //sleep es opcional, normalmente no se pone un tiempo de carga
        //Thread.sleep(2000)

        //establecemos el Theme
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        //setup
        setup()
    }

    //Setup
    private fun setup(){
        title = "Autenticación"
        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }

        //Boton de login
        /*
        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }
        */

        //Boton de login, haciendo que si el usuario especifico es el que accede va a una pagina especifica, la idea es que el usuario de Dani vaya a una ventana que le de la opcion de añadir lootbox y la de randomizar los objetos
        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(), passwordEditText.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful && emailEditText.text.toString()=="jondeleonsola@gmail.com"){
                        showInfo(it.result?.user?.email ?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    //Funcion de alerta si hay algun problema al entrar con usuario y contraseña
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    //Funcion que muestra una pagina donde se ve con que correo has entrado y un boton para cerrar sesión
    private fun showHome(email: String){
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(homeIntent)
    }

    //Funcion para mostrar una pagina, ahora no hace nada, la idea es que sea la pagina principal o bien otras paginas
    private fun showInfo(email: String){
        val infoIntent = Intent(this, InfoActivity::class.java).apply {
            putExtra("email", email)
        }
        startActivity(infoIntent)
    }
}