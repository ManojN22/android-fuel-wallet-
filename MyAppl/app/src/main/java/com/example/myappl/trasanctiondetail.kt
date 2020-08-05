package com.example.myappl

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_trasanctiondetail.*
import kotlinx.android.synthetic.main.activity_trasanctiondetail.bottomnav

class trasanctiondetail : AppCompatActivity() {
    val man2 = BottomNavigationView.OnNavigationItemSelectedListener{ item->
        when(item.itemId)
        {
            R.id.pay->{

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.rightin,R.anim.leftout)
                return@OnNavigationItemSelectedListener true

            }

            R.id.QR->{

                val intent = Intent(this, qr::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.rightin,R.anim.leftout)


                return@OnNavigationItemSelectedListener true

            }

            R.id.stat-> {
                return@OnNavigationItemSelectedListener true

            }
        }

        return@OnNavigationItemSelectedListener false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trasanctiondetail)
        bottomnav.setOnNavigationItemSelectedListener(man2)


list1.adapter= mycustom(this)
    }
    private class mycustom(context: Context):BaseAdapter(){
     private val mcontext: Context
      init {
      mcontext=context
      }
        override fun getCount(): Int {
          return list.size-1
        }

        override fun getItem(position: Int): Any {
          return "hello"
        }

        override fun getItemId(position: Int): Long {
         return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
          val layoutInfalter = LayoutInflater.from(mcontext)
           val rowmain= layoutInfalter.inflate(R.layout.temp,viewGroup,false)

            val ids = rowmain.findViewById<TextView>(R.id.textView7)
            val dates = rowmain.findViewById<TextView>(R.id.textView8)
            val amounts = rowmain.findViewById<TextView>(R.id.textView9)
            ids.text= list[list.size-position-1].transid
            dates.text= list[list.size-position-1].date
            amounts.text= list[list.size-position-1].amoun.toString()
        return rowmain
        }


    }
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }

    }

