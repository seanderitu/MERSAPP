package com.example.mers


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.mers.User

class CustomAdapter(var context: Context, var data:ArrayList<User>):BaseAdapter() {
    private class ViewHolder(row:View?){
        var mTxtName:TextView
        var mTxtUsername:TextView
        var mTxtEmail:TextView
        var mTxtPhonenumber:TextView
        var mTxtPassword:TextView

        init {
            this.mTxtName = row?.findViewById(R.id.name)as TextView
            this.mTxtUsername = row?.findViewById(R.id.username) as TextView
            this.mTxtEmail = row?.findViewById(R.id.email) as TextView
            this.mTxtPhonenumber = row?.findViewById(R.id.phonenumber) as TextView
            this.mTxtPassword = row?.findViewById(R.id.password) as TextView

        }
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view:View?
        var viewHolder:ViewHolder
        if (convertView == null){
            var layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.my_item,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        var item:User = getItem(position) as User
        viewHolder.mTxtName.text = item.name
        viewHolder.mTxtUsername.text = item.username
        viewHolder.mTxtEmail.text = item.email
        viewHolder.mTxtPhonenumber.text = item.phonenumber
        viewHolder.mTxtPassword.text = item.password
        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }
}
