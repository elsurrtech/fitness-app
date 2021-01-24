package com.quiqle.quiqlefitness

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class CListAdaptor(private val getContext: Context,private val customListItem:ArrayList<CList>):
    ArrayAdapter<CList>(getContext,0,customListItem)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listlayout = convertView
        val holder: ViewHolder

        if(listlayout == null){
            val inflateList = (getContext as Activity).layoutInflater
            listlayout = inflateList!!.inflate(R.layout.custom_list,parent,false)
            holder = ViewHolder()
            holder.mTextViewOne = listlayout!!.findViewById(R.id.text1)
            holder.mTextViewTwo = listlayout!!.findViewById(R.id.text2)
            holder.mImageListView = listlayout!!.findViewById(R.id.image)

            listlayout.setTag(holder)

        }

        else{
            holder = listlayout.getTag() as ViewHolder
        }

        val listItem = customListItem[position]

        holder.mTextViewOne!!.setText(listItem.mClistTextOne)
        holder.mTextViewTwo!!.setText(listItem.mClistTextTwo)
        holder.mImageListView!!.setImageResource(listItem.mCListImage)
        marqueeText(holder.mTextViewOne!!)

        return listlayout
    }

    class ViewHolder{
        internal var mTextViewOne: TextView? = null
        internal var mTextViewTwo: TextView? = null
        internal var mImageListView: ImageView? = null

    }

    fun marqueeText(t: TextView){
        t.setSingleLine()
        t.ellipsize = TextUtils.TruncateAt.MARQUEE
        t.marqueeRepeatLimit = -1
        t.isSelected = true

    }

}