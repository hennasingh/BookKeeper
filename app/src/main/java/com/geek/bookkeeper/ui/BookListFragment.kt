package com.geek.bookkeeper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.geek.bookkeeper.R
import com.geek.bookkeeper.databinding.FragmentBookListBinding
import io.realm.mongodb.User


class BookListFragment : Fragment() {

    private var user: User? = null
    var listBinding: FragmentBookListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        listBinding = FragmentBookListBinding.inflate(layoutInflater, container, false)
        return listBinding?.root
    }

}