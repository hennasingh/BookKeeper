package com.geek.bookkeeper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.geek.bookkeeper.R
import com.geek.bookkeeper.bookKeeperApp
import com.geek.bookkeeper.databinding.FragmentBookListBinding
import com.geek.bookkeeper.model.Book
import io.realm.Realm
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration


class BookListFragment : Fragment() {

    private var user: User? = null
    var listBinding: FragmentBookListBinding? = null
    private lateinit var realmList: Realm
    private lateinit var adapter: BookListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        user = bookKeeperApp.currentUser()

        if(user!=null) {

            val config = SyncConfiguration.Builder(user!!, user!!.id).build()
            realmList = Realm.getInstance(config)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        listBinding = FragmentBookListBinding.inflate(layoutInflater, container, false)
        return listBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listBinding!!.rvBookList.layoutManager = LinearLayoutManager(context)
        listBinding!!.rvBookList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        adapter = BookListAdapter(realmList.where(Book::class.java).sort("name").findAll())
        listBinding!!.rvBookList.adapter = adapter
    }

    override fun onStop() {
        super.onStop()
        // its recommended to close realm references even if the user does not logout
        realmList.close()
    }
}