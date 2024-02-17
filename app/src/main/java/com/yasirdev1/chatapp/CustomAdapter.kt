package com.yasirdev1.chatapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.yasirdev1.chatapp.R

class CustomAdapter(context: Context, private val titles: Array<String>, private val subtitles: Array<String>) :
    ArrayAdapter<String>(context, R.layout.custom_list_item, titles) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.custom_list_item, parent, false)

        val titleTextView = rowView.findViewById(R.id.titleTextView) as TextView
        val subtitleTextView = rowView.findViewById(R.id.subtitleTextView) as TextView

        titleTextView.text = titles[position]
        subtitleTextView.text = subtitles[position]

        return rowView
    }
}
