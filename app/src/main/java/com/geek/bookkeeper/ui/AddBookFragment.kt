package com.geek.bookkeeper.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.geek.bookkeeper.R
import com.geek.bookkeeper.bookKeeperApp
import com.geek.bookkeeper.databinding.FragmentAddBookBinding
import com.geek.bookkeeper.model.Author
import com.geek.bookkeeper.model.Book
import io.realm.Realm
import io.realm.mongodb.User
import io.realm.mongodb.sync.SyncConfiguration
import timber.log.Timber


class AddBookFragment : Fragment() {


    private var addBinding: FragmentAddBookBinding? = null
    private lateinit var realmClass: Realm
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        user = bookKeeperApp.currentUser()
        val config = SyncConfiguration.Builder(user!!, user!!.id).build()

        Realm.getInstanceAsync(config, object: Realm.Callback(){
            override fun onSuccess(realm: Realm) {
                realmClass = realm
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addBinding!!.addBookBtn.setOnClickListener {
            val authorOne = Author("Hector Garcia", "Spain", "hectorgarcia@gmail.com")
            val authorTwo = Author("Francesc Miralles", "Spain", "francescmiralles@gmail.com")
            val book = Book(name = "Ikigai", isRead = false, _partition = user!!.id)
            book.authors.add(authorOne)
            book.authors.add(authorTwo)
            realmClass.executeTransactionAsync({ realm ->
                realm.insert(book)
            }, {
                Timber.d("Book Object Added Successfuly")
                Toast.makeText(
                    requireContext(),
                    "Book Object added successfully",
                    Toast.LENGTH_LONG
                ).show()
            }, { throwError ->
                Timber.d("Error adding the bookObject to Database %s", throwError.localizedMessage)

                Toast.makeText(
                    requireContext(),
                    "Error adding the bookObject to Database, ${throwError.localizedMessage} ",
                    Toast.LENGTH_LONG
                ).show()
            })

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        addBinding = FragmentAddBookBinding.inflate(layoutInflater,container,false)
        return addBinding?.root
    }

    override fun onStop() {
        super.onStop()
        realmClass.close()
    }

}