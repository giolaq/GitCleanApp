package com.laquysoft.cleangitapp.ui.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.laquysoft.cleangitapp.ui.R

import kotlinx.android.synthetic.main.activity_user_detail.*

fun Context.UserDetailIntent(userId: String): Intent {
    return Intent(this, UserDetailActivity::class.java).apply {
        putExtra(EXTRA_USER_ID, userId)
    }
}

private const val EXTRA_USER_ID = "user_id"

class UserDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        //setSupportActionBar(toolbar)

        val userId: String = intent.getStringExtra(EXTRA_USER_ID)

    }

}
