package com.widyatama.nurseassistant.adapter.recyclerView

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.widyatama.core.base.BaseHolder
import com.widyatama.nurseassistant.R
import com.widyatama.nurseassistant.data.model.OrderStatus
import com.widyatama.nurseassistant.data.model.TimeLineModel
import com.widyatama.nurseassistant.util.DateTimeUtils
import com.widyatama.nurseassistant.util.VectorDrawableUtils
import kotlinx.android.synthetic.main.row_threatment.view.*

class TimeLineRVAdapter constructor(val context: Context, val list: ArrayList<TimeLineModel>)
    : RecyclerView.Adapter<TimeLineRVAdapter.Item>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.row_threatment,
                parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.setContent(list[position])
    }

    fun setItem(values: List<TimeLineModel>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<TimeLineModel>(itemView){

        override fun setContent(model: TimeLineModel) {
            itemView.text_timeline_title.text = model.message
            itemView.text_timeline_date.text = model.date
            when{
                model.status == OrderStatus.ACTIVE ->{
                    itemView.layoutCard.setBackgroundColor(context.resources.getColor(R.color.blueLight))
                    setMarker(itemView, R.drawable.ic_marker_active, R.color.blueLight)
                }
                model.status == OrderStatus.INACTIVE ->{
                    itemView.layoutCard.setBackgroundColor(context.resources.getColor(R.color.grayLight))
                    setMarker(itemView, R.drawable.ic_marker_inactive, R.color.grayLight)
                }
                model.status == OrderStatus.COMPLETED ->{
                    itemView.layoutCard.setBackgroundColor(context.resources.getColor(android.R.color.holo_green_light))
                    setMarker(itemView, R.drawable.ic_marker, android.R.color.holo_green_light)
                }
            }

            if (model.date.isNotEmpty()){
                itemView.text_timeline_date.visibility = View.VISIBLE
                itemView.text_timeline_date.text = DateTimeUtils.parseDateTime(model.date, "yyyy-MM-dd HH:mm", "hh:mm a, dd-MMM-yyyy")
            }


        }
    }

    private fun setMarker(holder: View, drawableResId: Int, colorFilter: Int) {
        holder.timeline.marker = VectorDrawableUtils.getDrawable(holder.context, drawableResId, ContextCompat.getColor(holder.context, colorFilter))
    }
}