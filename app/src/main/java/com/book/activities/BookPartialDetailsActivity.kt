package com.book.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.book.databinding.ActivityBookPartialDetailsBinding
import com.book.models.Book

class BookPartialDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookPartialDetailsBinding

    private var book: Book? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookPartialDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (intent.extras != null) {
            book = intent.getSerializableExtra("book", Book::class.java)
        }
        binding.currentBookTitle.text = book?.title ?: ""
        binding.currentBookPages.text = book?.pages.toString() + " pages"
        binding.currentBookGenre.text = book?.genre ?: ""
        binding.currentBookYear.text = "Year: " + book?.year.toString()
        binding.currentBookImage.load(book?.image)

        binding.btnBookDescription.setOnClickListener {
            val myIntent = Intent(it.context, BookDetailsActivity::class.java)
            myIntent.putExtra("book", book)
            startActivity(myIntent)
        }

    }
}