package com.example.myappl
import android.content.Context
import android.content.Intent

import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.zxing.BarcodeFormat
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_qr.*
import kotlin.system.exitProcess

public var ref = FirebaseDatabase.getInstance().getReference("APP")
public var ph =""
public var newk =1
public val list = ArrayList<trans>()
public fun chnge(amount:Int,liter:Int){
    ref.child("user").child(ph).child("transation").child(list[0].transid).setValue(trans(list[0].transid,amount,liter.toString()))
}


class MainActivity : AppCompatActivity() {


    val man1 = BottomNavigationView.OnNavigationItemSelectedListener{ item->
        when(item.itemId)
        {
            R.id.pay->{

                return@OnNavigationItemSelectedListener true

            }

            R.id.QR->{
                val intent =Intent(this, qr::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.leftin,R.anim.rightout)




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
        setContentView(R.layout.activity_main)
        bottomnav.setOnNavigationItemSelectedListener(man1)


        ref.child("user").child(ph).child("transation").addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                if(snapshot!!.exists()){
                    list.clear()
                 var i =0
                    for (e in snapshot.children){

                         i+=1
                        var trm  =e.getValue(trans ::class.java)
                        list.add(trm!!)
                        if(i==1) {
                            textView.text = trm.date
                            textView2.text = trm.amoun.toString()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })





        }
    override fun onBackPressed() {
        finishAffinity()
    }













        }







