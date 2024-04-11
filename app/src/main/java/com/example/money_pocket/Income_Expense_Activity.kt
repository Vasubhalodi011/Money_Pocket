package com.example.money_pocket

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.R
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.money_pocket.Helper.DbHelper
import com.example.money_pocket.Model.CategoryModel
import com.example.money_pocket.Model.IncomeExpenseModel
import com.example.money_pocket.databinding.ActivityIncomeExpenseBinding

lateinit var binding1: ActivityIncomeExpenseBinding
class Income_Expense_Activity : AppCompatActivity() {

    private var id: String? = null
    lateinit var db: DbHelper
    var categoryList = arrayListOf<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding1 = ActivityIncomeExpenseBinding.inflate(layoutInflater)
        setContentView(binding1.root)

        db = DbHelper(this)
        categoryList = db.getCategory()
        initBinding()
        getIntentData()

    }
    private fun getIntentData() {

        var notes = intent.getStringExtra("notes")
        var title = intent.getStringExtra("title")
        var amount = intent.getStringExtra("amount")
        var date = intent.getStringExtra("date")
        id = intent.getStringExtra("id")
        var status = intent.getIntExtra("status", 0)
        var category = intent.getStringExtra("category")
        var time = intent.getStringExtra("time")

        if (amount != null) {
            binding1.btnDelete.visibility = View.VISIBLE
            binding1.edtAmount.setText("$amount")
            binding1.edtDate.setText("$date")
            binding1.edtTime.setText("$time")
            binding1.edtNotes.setText("$notes")
            binding1.edtTitle.setText("$title")
            var i = 0
            while (i < categoryList.size) {
                if (categoryList[i].name.equals(category)) {
                    binding1.spinner.setSelection(i)
                }
                i++
            }

        }
    }

    private fun initBinding() {


        //Spinner Category
        var nameList = arrayListOf<String>()
        categoryList.forEach {
            nameList.add(it.name)
        }
        var arrayAdapter = ArrayAdapter(
            this, R.layout.support_simple_spinner_dropdown_item, nameList
        )
        binding1.spinner.adapter = arrayAdapter

        // Button Click

        binding1.btnIncome.setOnClickListener {
            var index = binding1.spinner.selectedItemPosition

            var finalId = "0"

            if (id != null) {
                finalId = id!!
            }

            var model = IncomeExpenseModel(
                finalId,
                binding1.edtTitle.text.toString(),
                binding1.edtAmount.text.toString(),
                binding1.edtNotes.text.toString(),
                binding1.edtDate.text.toString(),
                binding1.edtTime.text.toString(),
                0,
                nameList[index]
            )

            if (id != null) {
                db.updateIncomeExpense(model)
            } else {
                db.addIncomeExpense(model)
            }
            finish()

        }

        binding1.btnExpense.setOnClickListener {
            var index = binding1.spinner.selectedItemPosition

            var finalId = "0"

            if (id != null) {
                finalId = id!!
            }

            var model = IncomeExpenseModel(
                finalId,
                binding1.edtTitle.text.toString(),
                binding1.edtAmount.text.toString(),
                binding1.edtNotes.text.toString(),
                binding1.edtDate.text.toString(),
                binding1.edtTime.text.toString(),
                1,
                nameList[index]
            )

            if (id != null) {
                db.updateIncomeExpense(model)
            } else {
                db.addIncomeExpense(model)
            }
            finish()


        }

        binding1.btnDelete.setOnClickListener {
            db.deleteIncomeExpense(id!!)
            finish()
        }
    }
}