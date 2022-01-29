package com.yhg.pagingdatasample.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.yhg.pagingdatasample.R
import com.yhg.pagingdatasample.databinding.ActivityMainBinding
import com.yhg.pagingdatasample.ui.adapter.MainAdapter
import com.yhg.pagingdatasample.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var mainAdapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mainAdapter = MainAdapter()
        binding.rvContent.adapter = mainAdapter
        initObserverSetting()
    }

    private fun initObserverSetting(){
        mainViewModel.contentDataList.observe(this, {
            mainAdapter.submitData(lifecycle, it)
        })
    }
}