package com.example.money_pocket

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.money_pocket.Adapter.DataAdapter
import com.example.money_pocket.Helper.DbHelper
import com.example.money_pocket.Model.IncomeExpenseModel
import com.example.money_pocket.databinding.ActivityMainBinding

lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {

    lateinit var dataList: ArrayList<IncomeExpenseModel>
    lateinit var db: DbHelper


    override fun onResume() {
        super.onResume()
        dataList = DbHelper(this).getIncomeExpense()
        revSetUP()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //GetData
        db = DbHelper(this)
        dataList = db.getIncomeExpense()

       // revSetUP()

        initBinding()
    }

    private fun initBinding() {
        revSetUP()


        binding.imgAddCategory.setOnClickListener {
            var intent = Intent(this,Category_Activity::class.java)
            startActivity(intent)

        }

        binding.btnAdd.setOnClickListener {
            var intent = Intent(this,Income_Expense_Activity::class.java)
            startActivity(intent)
        }
    }

    private fun revSetUP() {
        var adapter = DataAdapter(this, dataList)
        var lm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvData.layoutManager = lm
        binding.rvData.adapter = adapter
    }
}