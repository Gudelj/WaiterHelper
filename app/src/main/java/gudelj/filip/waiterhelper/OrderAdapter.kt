package gudelj.filip.waiterhelper

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_orders.view.*
import kotlinx.android.synthetic.main.order.view.*

class OrderAdapter(
    private val orders: MutableList<Order>
) : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        return OrderViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.order,
                parent,
                false
            )
        )
    }

    fun addOrder(order: Order){
        orders.add(order)
        notifyItemInserted(orders.size - 1)
    }

    fun deleteDoneOrders(){
        orders.removeAll{ order ->
            order.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvOrderName: TextView, isChecked: Boolean){
        if(isChecked){
            tvOrderName.paintFlags = tvOrderName.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvOrderName.paintFlags = tvOrderName.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val curOrder = orders[position]
        holder.itemView.apply {
            tvOrderName.text = curOrder.name;
            cbDone.isChecked = curOrder.isChecked
            toggleStrikeThrough(tvOrderName, curOrder.isChecked )
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tvOrderName, isChecked)
                curOrder.isChecked = !curOrder.isChecked
            }
        }
    }

    override fun getItemCount(): Int {
        return orders.size
    }
}