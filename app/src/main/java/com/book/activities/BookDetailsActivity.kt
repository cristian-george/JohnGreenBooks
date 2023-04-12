package com.book.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.text.LineBreaker
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import coil.load
import com.book.databinding.ActivityBookDetailsBinding
import com.book.models.Book

class BookDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailsBinding

    private var book: Book? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bookDescription.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
        binding.bookDescription.movementMethod = ScrollingMovementMethod()

        if (intent.extras != null) {
            book = intent.getSerializableExtra("book", Book::class.java)
        }

        binding.bookTitle.text = book?.title ?: ""
        binding.bookDescription.text = book?.description ?: ""
        binding.bookGenre.text = book?.genre ?: ""
        binding.bookYear.text = "Year " + book?.year.toString()
        binding.bookPages.text = book?.pages.toString() + " pages"
        binding.bookImage.load(book?.image)


        binding.btnWebsite.setOnClickListener {
            val myIntent = Intent(it.context, BookWebsiteActivity::class.java)
            myIntent.putExtra("book", book)
            startActivity(myIntent)
        }
    }
}