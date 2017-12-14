package com.laquysoft.cleangitapp.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.laquysoft.cleangitapp.presentation.browse.BrowseUserDetailViewModelFactory
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.detail.BrowseUsersDetailViewModel
import com.laquysoft.cleangitapp.presentation.model.UserDetailView
import com.laquysoft.cleangitapp.ui.R
import com.laquysoft.cleangitapp.ui.mapper.UserDetailMapper
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

fun Context.UserDetailIntent(userId: String): Intent {
    return Intent(this, UserDetailActivity::class.java).apply {
        putExtra(EXTRA_USER_ID, userId)
    }
}

private const val EXTRA_USER_ID = "user_id"

class UserDetailActivity : AppCompatActivity() {

    @Inject lateinit var mapper: UserDetailMapper
    @Inject lateinit var userDetailViewModel: BrowseUsersDetailViewModel
    @Inject lateinit var userViewModelFactory: BrowseUserDetailViewModelFactory

    var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        //setSupportActionBar(toolbar)
        AndroidInjection.inject(this)

        //browseUsersViewModel = ViewModelProviders.of(this, viewModelFactory)
        //        .get(BrowseUsersDetailViewModel::class.java)

        userDetailViewModel = ViewModelProviders.of(this, userViewModelFactory)
                .get(BrowseUsersDetailViewModel::class.java)

        userId = intent.getStringExtra(EXTRA_USER_ID)


    }

    override fun onStart() {
        super.onStart()
        userDetailViewModel.getUser(userId).observe(this,
                Observer<Resource<UserDetailView>> {
                    if (it != null) this.handleDataState(it.status, it.data, it.message)
                })
    }

    private fun handleDataState(resourceState: ResourceState, data: UserDetailView?,
                                message: String?) {
        when (resourceState) {
            ResourceState.LOADING -> setupScreenForLoadingState()
            ResourceState.SUCCESS -> setupScreenForSuccess(data)
            ResourceState.ERROR -> setupScreenForError(message)
        }
        Log.d("UserDetailActivity", " handlestate " + resourceState)
    }

    private fun setupScreenForLoadingState() {
//        progress.visibility = View.VISIBLE
//        recycler_browse.visibility = View.GONE
//        view_empty.visibility = View.GONE
//        view_error.visibility = View.GONE
    }

    private fun setupScreenForSuccess(data: UserDetailView?) {
//        view_error.visibility = View.GONE
//        progress.visibility = View.GONE
//        if (data != null) {
//            updateDetailView(data)
//            recycler_browse.visibility = View.VISIBLE
//        } else {
//            view_empty.visibility = View.VISIBLE
//        }
        if (data != null) {
            updateDetailView(data)
        }
    }

    private fun setupScreenForError(message: String?) {
//        progress.visibility = View.GONE
//        recycler_browse.visibility = View.GONE
//        view_empty.visibility = View.GONE
//        view_error.visibility = View.VISIBLE
        Log.e("UserDetailActivity", " setupScreenForError " + message)

    }

    private fun updateDetailView(userDetail: UserDetailView) {
        val userDetailView = mapper.mapToViewModel(userDetail)
        name.text = userDetailView.name
    }


}
