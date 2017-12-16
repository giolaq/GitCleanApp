package com.laquysoft.cleangitapp.ui.widget

import android.support.v4.widget.SearchViewCompat.setOnQueryTextListener
import android.support.v7.widget.SearchView
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject



/**
 * Created by joaobiriba on 16/12/2017.
 */
object RxSearch {

    fun fromSearchView(searchView: SearchView): Observable<String> {
        val subject: BehaviorSubject<String> = BehaviorSubject.create()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (!newText.isEmpty()) {
                    subject.onNext(newText)
                }
                return true
            }
        })
        return subject
    }
}