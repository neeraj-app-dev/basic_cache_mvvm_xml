package com.example.cache_mvvm_xml.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cache_mvvm_xml.data.Details
import com.example.cache_mvvm_xml.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel : MainViewModel by viewModels()
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerview()
        observeData()
        getList()
    }

    private fun getList(){
        viewModel.getDetailsList()
    }

    private fun observeData(){
        lifecycleScope.launch {
            viewModel.detailsList.collect{
                when(it) {
                    is MainViewState.Initial -> binding.progressBar.isVisible = true
                    is MainViewState.Loading -> viewModel.detailsListLiveData.value = it.data
                    is MainViewState.Success ->{
                        viewModel.detailsListLiveData.value = it.data
                        binding.progressBar.isVisible = false
                    }
                    is MainViewState.Error ->{
                        viewModel.detailsListLiveData.value = it.data
                        binding.progressBar.isVisible = false
                        Snackbar.make(binding.root, it.message, 4000).show()
                    }
                }
            }
        }
    }

    private fun setRecyclerview(){
        with(binding.recyclerview){
            layoutManager = LinearLayoutManager(this@MainActivity)
             addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
             adapter = DetailsListAdapter(viewModel.detailsListLiveData, this@MainActivity)
        }

    }
}