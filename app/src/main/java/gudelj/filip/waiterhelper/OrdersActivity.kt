package gudelj.filip.waiterhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : AppCompatActivity() {

    private lateinit var orderAdapter: OrderAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        val btnTip = findViewById<Button>(R.id.btnTip)

        btnTip.setOnClickListener {
            val intent = Intent(this, TipActivity::class.java)
            startActivity(intent)
        }

        orderAdapter = OrderAdapter(mutableListOf())

        rvOrders.adapter = orderAdapter
        rvOrders.layoutManager = LinearLayoutManager(this)

        btnAdd.setOnClickListener {
            val orderName = etOrderName.text.toString()
            if(orderName.isNotEmpty()){
                val order = Order(orderName)
                orderAdapter.addOrder(order)
                etOrderName.text.clear()
            }
        }
        btnDelete.setOnClickListener {
            orderAdapter.deleteDoneOrders()
        }
    }
}