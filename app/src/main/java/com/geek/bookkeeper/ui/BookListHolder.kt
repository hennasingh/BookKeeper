package com.geek.bookkeeper.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.geek.bookkeeper.databinding.BooklistItemBinding
import com.geek.bookkeeper.model.Book

class BookListHolder(private val binding: BooklistItemBinding) : RecyclerView.ViewHolder(binding.root) {


    private val stringBuilder = StringBuilder("By ")

    fun bindValues(book: Book?){
        binding.bookName.text = book?.name
        val size = book!!.authors.size

        for( i in 0 until size){
            if( i == book.authors.size - 1){
                stringBuilder.append(book.authors[i]!!.name).append(" from ").append(book.authors[i]!!.country)
            }else {
                stringBuilder.append(book.authors[i]!!.name).append(" from ").append(book.authors[i]!!.country).append(" and ")
            }
        }
        binding.authorName.text = stringBuilder
    }
}