package com.laquysoft.cleangitapp.ui.browse

import com.laquysoft.cleangitapp.ui.model.UserViewModel


/**
 * Created by joaobiriba on 15/12/2017.
 */

interface UserClickCallback {
    fun onClick(user: UserViewModel)
}