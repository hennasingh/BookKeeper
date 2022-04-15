package com.geek.bookkeeper.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.geek.bookkeeper.R
import com.geek.bookkeeper.databinding.BooklistItemBinding
import com.geek.bookkeeper.model.Book
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter

class BookListAdapter(books: OrderedRealmCollection<Book>): RealmRecyclerViewAdapter<Book, BookListHolder>(books, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListHolder {
        val binding = BooklistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookListHolder(binding)
    }

    override fun onBindViewHolder(holder: BookListHolder, position: Int) {
        val book = getItem(position)
        holder.bindValues(book)
    }
}