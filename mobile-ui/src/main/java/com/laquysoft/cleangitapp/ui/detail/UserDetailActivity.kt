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
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject

fun Context.UserDetailIntent(userId: String): Intent {
    return Intent(this, UserDetailActivity::class.java).apply {
        putExtra(EXTRA_USER_ID, userId)
    }
}

const val EXTRA_USER_ID = "user_id"

class UserDetailActivity : AppCompatActivity() {

    @Inject lateinit var mapper: UserDetailMapper
    @Inject lateinit var userDetailViewModel: BrowseUsersDetailViewModel
    @Inject lateinit var userViewModelFactory: BrowseUserDetailViewModelFactory

    var userId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        AndroidInjection.inject(this)

        setSupportActionBar(detail_toolbar)


        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_USER_ID,
                            intent.getStringExtra(EXTRA_USER_ID))
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit()
        }

        userDetailViewModel = ViewModelProviders.of(this, userViewModelFactory)
                .get(BrowseUsersDetailViewModel::class.java)

        userId = intent.getStringExtra(EXTRA_USER_ID)


    }
}
