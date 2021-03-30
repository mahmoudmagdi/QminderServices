package com.intdv.qminderservices

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.intdv.qminderservices.fragments.TicketFragment
import com.intdv.qminderservices.model.Line

class LinesAdapter(private val context: Context, private val linesList: List<Line>) :
    RecyclerView.Adapter<LinesAdapter.LineViewHolder>() {

    override fun getItemCount() = linesList.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return LineViewHolder(context, inflater, parent)
    }

    override fun onBindViewHolder(holder: LineViewHolder, position: Int) {
        val line = linesList[position]
        holder.bind(line)
    }

    class LineViewHolder(
        private val context: Context,
        inflater: LayoutInflater,
        parent: ViewGroup
    ) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_line, parent, false)),
        View.OnClickListener {

        private var line: Line? = null
        private var line_title: TextView? = null

        init {
            line_title = itemView.findViewById(R.id.line_title)
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            (context as MainActivity).setFragment(TicketFragment(line!!.id.toString()))
        }

        fun bind(line: Line) {
            this.line = line
            line_title?.text = line.name
        }
    }
}