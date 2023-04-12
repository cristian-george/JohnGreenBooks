package com.book.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.book.R
import com.book.adapters.BookAdapter
import com.book.databinding.ActivityMainBinding
import com.book.models.Book
import com.book.xml.XMLBookData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var adapter: BookAdapter? = null
    private lateinit var books: Array<Book?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        books = XMLBookData.readXMLBooks(this.resources.openRawResource(R.raw.books))
        adapter = BookAdapter(this, books)
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { _, view, i, _ ->
            val myIntent = Intent(view.context, BookPartialDetailsActivity::class.java)
            myIntent.putExtra("book", books[i])
            startActivity(myIntent)
        }
    }
}