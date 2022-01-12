package gudelj.filip.waiterhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_orders.*
import kotlinx.android.synthetic.main.activity_tip.*
import java.lang.reflect.Array.getDouble

class TipActivity : AppCompatActivity() {

    lateinit var sharedPrefTip : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tip)

        sharedPrefTip = this.getSharedPreferences("Tip", Context.MODE_PRIVATE)

        tvTotal.text = sharedPrefTip.getString("Baksa", "0.0")

        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnAddTip = findViewById<Button>(R.id.btnAddTip)
        val btnSubtract = findViewById<Button>(R.id.btnSubtract)
        val etAddTip = findViewById<EditText>(R.id.etAddTip)
        val etSubtract = findViewById<EditText>(R.id.etSubtract)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        var total = 0.0

        btnBack.setOnClickListener {
            val intent = Intent(this, OrdersActivity::class.java)
            startActivity(intent)
            sharedPrefTip.edit().putString("Baksa", tvTotal.text.toString()).apply()
        }

        btnAddTip.setOnClickListener {
            var tip = etAddTip.text.toString().toDouble()
            if(tvTotal.text.isNotEmpty()) {
                total = tvTotal.text.toString().toDouble() + tip
            } else{
                total += tip
            }
            tvTotal.text = total.toString()
            etAddTip.text.clear()
        }

        btnSubtract.setOnClickListener {
            var subtractor = etSubtract.text.toString().toDouble()
            if(tvTotal.text.isNotEmpty()) {
                total = tvTotal.text.toString().toDouble() - subtractor
            } else{
                total -= subtractor
            }
            tvTotal.text = total.toString()
            etSubtract.text.clear()
        }
    }
}