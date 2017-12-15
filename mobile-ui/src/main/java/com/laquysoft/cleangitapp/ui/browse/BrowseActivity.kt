package com.laquysoft.cleangitapp.ui.browse

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.laquysoft.cleangitapp.presentation.browse.BrowseUsersViewModel
import com.laquysoft.cleangitapp.presentation.browse.BrowseUsersViewModelFactory
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.model.UserView
import com.laquysoft.cleangitapp.ui.R
import com.laquysoft.cleangitapp.ui.detail.EXTRA_USER_ID
import com.laquysoft.cleangitapp.ui.detail.UserDetailActivity
import com.laquysoft.cleangitapp.ui.detail.UserDetailFragment
import com.laquysoft.cleangitapp.ui.mapper.UserMapper
import com.laquysoft.cleangitapp.ui.widget.empty.EmptyListener
import com.laquysoft.cleangitapp.ui.widget.error.ErrorListener
import dagger.android.AndroidInjection
import javax.inject.Inject
import com.laquysoft.cleangitapp.ui.detail.UserDetailIntent
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.android.synthetic.main.item_list.*


class BrowseActivity : AppCompatActivity() {

    @Inject lateinit var browseAdapter: UserAdapter
    @Inject lateinit var mapper: UserMapper
    //@Inject lateinit var viewModelFactory: BrowseUsersViewModelFactory
    @Inject lateinit var userViewModelFactory: BrowseUsersViewModelFactory
    //private lateinit var browseUsersViewModel: BrowseUsersDetailViewModel
    private lateinit var browseUsersViewModel: BrowseUsersViewModel

    private var isTwoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        AndroidInjection.inject(this)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (item_detail_container != null) {
            isTwoPane = true
        }
        browseUsersViewModel = ViewModelProviders.of(this, userViewModelFactory)
                .get(BrowseUsersViewModel::class.java)

        setupBrowseRecycler()
        setupViewListeners()
    }

    override fun onStart() {
        super.onStart()
        browseUsersViewModel.getUsers().observe(this,
                Observer<Resource<List<UserView>>> {
                    if (it != null) this.handleDataState(it.status, it.data, it.message)
                })

        browseUsersViewModel.getOpenUser().observe( this,
                Observer<String> {
                    if (it != null) this.openUser(it)
                })
    }

    private fun openUser(userId: String) {
        if (isTwoPane) {
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USER_ID, userId)
                }
            }
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
        } else {
            startActivity(UserDetailIntent(userId))
        }

    }

    private fun setupBrowseRecycler() {
        recycler_browse.layoutManager = LinearLayoutManager(this)
        recycler_browse.adapter = browseAdapter
        browseAdapter.clickListener = { user ->
            browseUsersViewModel.onUserClick(mapper.mapToView(user))
        }
    }

    private fun handleDataState(resourceState: ResourceState, data: List<UserView>?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
    }

    private fun setupScreenForLoadingState() {
        progress.visibility = View.VISIBLE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: List<UserView>?) {
        view_error.visibility = View.GONE
        progress.visibility = View.GONE
        if (data != null && data.isNotEmpty()) {
            updateListView(data)
            recycler_browse.visibility = View.VISIBLE
        } else {
            view_empty.visibility = View.VISIBLE
        }
    }

    private fun updateListView(users: List<UserView>) {
        browseAdapter.users = users.map { mapper.mapToViewModel(it) }
        browseAdapter.notifyDataSetChanged()
    }

    private fun setupScreenForError(message: String?) {
        Log.e("BrowseActivity", message)
        progress.visibility = View.GONE
        recycler_browse.visibility = View.GONE
        view_empty.visibility = View.GONE
        view_error.visibility = View.VISIBLE
    }

    private fun setupViewListeners() {
        view_empty.emptyListener = emptyListener
        view_error.errorListener = errorListener
    }

    private val emptyListener = object : EmptyListener {
        override fun onCheckAgainClicked() {
            browseUsersViewModel.fetchUsers()
        }
    }

    private val errorListener = object : ErrorListener {
        override fun onTryAgainClicked() {
            browseUsersViewModel.fetchUsers()
        }
    }

}
