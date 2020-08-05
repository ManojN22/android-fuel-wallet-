package com.example.myappl

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.bind
import android.widget.EditText
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_qr.*


class qr : AppCompatActivity() {

    val man =BottomNavigationView.OnNavigationItemSelectedListener{item->
        when(item.itemId)
        {
            R.id.pay->{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.rightin,R.anim.leftout)

                return@OnNavigationItemSelectedListener true

            }

            R.id.QR->{


                return@OnNavigationItemSelectedListener true

            }

            R.id.stat-> {
                val intent =Intent(this, trasanctiondetail::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.leftin,R.anim.rightout)


                return@OnNavigationItemSelectedListener true

            }
        }

        return@OnNavigationItemSelectedListener false
     }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr)


        bottomnav1.setOnNavigationItemSelectedListener(man)

        button2.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
            newk=editText2.text.toString().toInt()

        }

    }

        override fun onBackPressed() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }



}


