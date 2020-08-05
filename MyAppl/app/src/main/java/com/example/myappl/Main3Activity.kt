package com.example.myappl

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.os.postDelayed
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main3.*
import org.jetbrains.anko.toast
import java.io.*

import java.io.FileOutputStream
import java.io.FileReader
import java.lang.StringBuilder


private var loda:data= data()








@Suppress("DEPRECATION")
class Main3Activity : AppCompatActivity() {





    override fun onCreate(savedInstanceState: Bundle?) {

        val phfile = "user.txt"
        val pafile ="pass.txt"
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        var flag: Boolean = false
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netinfo = cm.activeNetworkInfo


        try {
        var filephin: FileInputStream? = null
        var filepain: FileInputStream? = null
        filephin = openFileInput(phfile)
        filepain = openFileInput(pafile)
        var phinphone: InputStreamReader = InputStreamReader(filephin)
        var painpass: InputStreamReader = InputStreamReader(filepain)
        val Bufphone: BufferedReader = BufferedReader(phinphone)
        val Bufpass: BufferedReader = BufferedReader(painpass)
        val phstringbuf: StringBuilder = StringBuilder()
        val pastringbuf: StringBuilder = StringBuilder()
        var text: String? = null
        while ({ text = Bufphone.readLine();text }() != null) {
            phstringbuf.append(text)
        }
        loda.phoneno = phstringbuf.toString()

        while ({ text = Bufpass.readLine();text }() != null) {
            pastringbuf.append(text)
        }
        loda.password = pastringbuf.toString()

    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    }

        if(netinfo!=null&&netinfo.isConnected) {
            progressBar.visibility= View.VISIBLE
            editText3.visibility= View.INVISIBLE
            editText.visibility= View.INVISIBLE
            button3.visibility= View.INVISIBLE



            if (loda.phoneno!=""&& loda.password!="")
            { var fla=false
                ph= loda.phoneno.toString()
                var pass =loda.password.toString()


                val intent1 = Intent(this, MainActivity::class.java)
                ref.child("dir").addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot!!.exists()) {

                            for (e in snapshot.children) {
                                var trm = e.getValue(data::class.java)
                                if (trm != null) {
                                    if (trm.phoneno == ph && trm.password == pass) {
                                        flag = true
                                        fla=true
                                        startActivity(intent1)

                                        break




                                }
                            }
                        }

                    }

                        }



                    override fun onCancelled(error: DatabaseError) {
                        progressBar.visibility= View.INVISIBLE
                        editText3.visibility= View.VISIBLE
                        editText.visibility= View.VISIBLE
                        button3.visibility= View.VISIBLE

                    }
                })











            }
            else{
                progressBar.visibility= View.INVISIBLE
                editText3.visibility= View.VISIBLE
                editText.visibility= View.VISIBLE
                button3.visibility= View.VISIBLE

            }



            button3.setOnClickListener { v ->
                val hand:Handler
                fun clic(){
                progressBar.visibility= View.VISIBLE
                editText3.visibility= View.INVISIBLE
                editText.visibility= View.INVISIBLE
                button3.visibility= View.INVISIBLE
                var listof: data
                var finis=false
                ph = editText3.text.toString()
                var pass = editText.text.toString()
                var now: data = data(ph, pass)
                ref.child("dir").addValueEventListener(object : ValueEventListener {

                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot!!.exists()) {

                            for (e in snapshot.children) {
                                var trm = e.getValue(data::class.java)
                                if (trm != null) {
                                    if (trm.phoneno == ph && trm.password == pass) {
                                        flag = true
                                        listof = trm

                                        val phfile = "user.txt"
                                        val pafile ="pass.txt"
                                        val fileoutstre: FileOutputStream
                                        val fileoutpass: FileOutputStream
                                        try {
                                            fileoutstre =
                                                openFileOutput(phfile, Context.MODE_PRIVATE)
                                            fileoutpass =openFileOutput(pafile,Context.MODE_PRIVATE)
                                            fileoutstre.write(listof.phoneno.toByteArray())
                                            fileoutpass.write(listof.password.toByteArray())


                                        }
                                        catch(e:FileNotFoundException)
                                        {
                                            e.printStackTrace()
                                        }
                                        catch (e:IOException)
                                        {
                                            e.printStackTrace()
                                        }
                                            toast("file saved")
                                         break
                                    }


                                }
                            }

                            if (flag==false){
                                toast("password incorrect")
                                progressBar.visibility= View.INVISIBLE
                                editText3.visibility= View.VISIBLE
                                editText.visibility= View.VISIBLE
                                button3.visibility= View.VISIBLE
                            }

                        }

                    }


                    override fun onCancelled(error: DatabaseError) {

                    }
                })



                if (flag == true) {

                    flag=false
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }


            }
                clic()

                   clic()

            }



        }
        else
        {
            val intent = Intent(this, qr::class.java)
            startActivity(intent)

        }

    }


}





