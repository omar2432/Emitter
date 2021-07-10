package com.example.emitter.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.emitter.R
import com.example.emitter.databinding.ActivityMainBinding
import com.example.emitter.model.MainRepository
import com.example.emitter.model.RetrofitService
import com.example.emitter.viewmodel.MainViewModel
import com.example.emitter.viewmodel.MyViewModelFactory
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService =
        RetrofitService.getInstance()
    val adapter = MainAdapter()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this,
            MyViewModelFactory(
                MainRepository(retrofitService)
            )
        ).get(MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.userList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setUserList(it)
        })

        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllUsers()

        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).setOnRefreshListener(refreshListener)
        //swipeRefreshLayout.setOnRefreshListener(refreshListener)
    }

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {
        viewModel.getAllUsers()
        findViewById<SwipeRefreshLayout>(R.id.swipeRefreshLayout).isRefreshing = false

    }

    public fun broadcastIntent(view: View)
    {
        val intent = Intent()
        intent.action = "com.ebookfrenzy.sendbroadcast"
        intent.flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        sendBroadcast(intent)
    }


}


