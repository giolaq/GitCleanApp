package com.laquysoft.cleangitapp.ui.browse

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.laquysoft.cleangitapp.domain.model.User
import com.laquysoft.cleangitapp.ui.R
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Flowable
import com.laquysoft.cleangitapp.ui.test.TestApplication
import com.laquysoft.cleangitapp.ui.test.util.UserFactory
import com.laquysoft.cleangitapp.ui.test.util.RecyclerViewMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BrowseActivityTest {

    @Rule @JvmField
    val activity = ActivityTestRule<BrowseActivity>(BrowseActivity::class.java, false, false)

    @Test
    fun activityLaunches() {
        stubUserRepositoryGetUsers(Flowable.just(UserFactory.makeUserList(2)))
        activity.launchActivity(null)
    }

    @Test
    fun usersDisplay() {
        val users = UserFactory.makeUserList(1)
        stubUserRepositoryGetUsers(Flowable.just(users))
        activity.launchActivity(null)

        checkUserDetailsDisplay(users[0], 0)
    }

    @Test
    fun usersAreScrollable() {
        val users = UserFactory.makeUserList(20)
        stubUserRepositoryGetUsers(Flowable.just(users))
        activity.launchActivity(null)

        users.forEachIndexed { index, user ->
            onView(withId(R.id.recycler_browse)).perform(RecyclerViewActions.
                    scrollToPosition<RecyclerView.ViewHolder>(index))
            checkUserDetailsDisplay(user, index) }
    }

    private fun checkUserDetailsDisplay(user: User, position: Int) {
        onView(RecyclerViewMatcher.withRecyclerView(R.id.recycler_browse).atPosition(position))
                .check(matches(hasDescendant(withText(user.login))))
    }

    private fun stubUserRepositoryGetUsers(single: Flowable<List<User>>) {
        whenever(TestApplication.appComponent().userRepository().getUsers())
                .thenReturn(single)
    }

}
