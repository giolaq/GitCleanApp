package com.laquysoft.cleangitapp.ui.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.laquysoft.cleangitapp.presentation.browse.BrowseUserDetailViewModelFactory
import com.laquysoft.cleangitapp.presentation.data.Resource
import com.laquysoft.cleangitapp.presentation.data.ResourceState
import com.laquysoft.cleangitapp.presentation.detail.BrowseUsersDetailViewModel
import com.laquysoft.cleangitapp.presentation.model.UserDetailView
import com.laquysoft.cleangitapp.ui.R
import com.laquysoft.cleangitapp.ui.mapper.UserDetailMapper
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.activity_user_detail.*
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [UserDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [UserDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UserDetailFragment : Fragment() {

    private lateinit var userId: String

    @Inject lateinit var mapper: UserDetailMapper
    @Inject lateinit var userViewModelFactory: BrowseUserDetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        arguments?.let {
            if (it.containsKey(EXTRA_USER_ID)) {
                // Load the dummy content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                userId = it.getString(EXTRA_USER_ID)
                activity?.toolbar_layout?.title = userId
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val userDetailViewModel = ViewModelProviders.of(activity, userViewModelFactory).get(BrowseUsersDetailViewModel::class.java)
        userDetailViewModel.getUser(userId).observe(this,
                Observer<Resource<UserDetailView>> {
                    if (it != null) this.handleDataState(it.status, it.data, it.message)
                })
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_user_detail, container, false)
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