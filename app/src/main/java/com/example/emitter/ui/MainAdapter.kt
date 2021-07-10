package com.example.emitter.ui

import android.app.AlertDialog
import android.content.ComponentName
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.emitter.databinding.AdapterUserBinding
import com.example.emitter.model.User


class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {


    var users = mutableListOf<User>()

    fun setUserList(users: List<User>) {
        this.users = users.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterUserBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val user = users[position]

        holder.binding.name.text = user.name
        holder.binding.email.text = "Email: " + user.email
        holder.binding.website.text = "Website: " + user.website

        holder.itemView.setOnClickListener(){

            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setMessage("Are you sure you want to send the data to the MiddleMan app?")
                .setCancelable(false)
                .setNegativeButton("No") { dialog, id ->
                    // Dismiss the dialog
                    dialog.dismiss()
                }
                .setPositiveButton("Yes") { dialog, id ->
                    Toast.makeText(holder.itemView.context,user.name+" SENT",Toast.LENGTH_SHORT).show()


                    val context=holder.itemView.context
                    val intent = Intent( context, SendBroadcastAct::class.java)
                    intent.putExtra("Name", user.name)
                    context.startActivity(intent)


                }
            val alert = builder.create()
            alert.show()
        }


    }

    override fun getItemCount(): Int {
        return users.size
    }
}


class MainViewHolder(val binding: AdapterUserBinding) : RecyclerView.ViewHolder(binding.root) {

}