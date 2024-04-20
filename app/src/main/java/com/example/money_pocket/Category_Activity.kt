package com.example.money_pocket

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.money_pocket.Adapter.CategoryAdapter
import com.example.money_pocket.Helper.DbHelper
import com.example.money_pocket.Model.CategoryModel
import com.example.money_pocket.databinding.ActivityCategoryBinding

lateinit var Categorybinding:ActivityCategoryBinding
class Category_Activity : AppCompatActivity() {

    var list = arrayListOf<CategoryModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Categorybinding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(Categorybinding.root)
        var db = DbHelper(this)
        list = db.getCategory()
        intBinding()
    }

    private fun intBinding() {

        var adapter = CategoryAdapter(this@Category_Activity,list)
        var layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL,false)
        Categorybinding.rvCategory.layoutManager=layoutManager
        Categorybinding.rvCategory.adapter = adapter

        Categorybinding.btnAddCategory.setOnClickListener {

            var db = DbHelper(this@Category_Activity)
            if (Categorybinding.edtCategory.text.isEmpty())
            {
                Categorybinding.edtCategory.setError("Plz Enter Category")
            }
            else
            {
                db.addCategory(Categorybinding.edtCategory.text.toString())
                finish()

            }

        }


    }
}