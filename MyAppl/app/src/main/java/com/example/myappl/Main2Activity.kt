package com.example.myappl

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_qr.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        qrshow(newk.toString())
        textView5.text="Rs "+newk.toString()
        var key =  ref.child("user").child(ph).child("transation").push().key
        ref.child("user").child(ph).child("transation").child(key.toString()).setValue(trans(key.toString(), newk,"22/07/2001"))
    }
    fun qrshow(m :String){


        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(m.toString(), BarcodeFormat.QR_CODE, 170, 170)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)

        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
     imageView.setImageBitmap(bitmap)   }
}
