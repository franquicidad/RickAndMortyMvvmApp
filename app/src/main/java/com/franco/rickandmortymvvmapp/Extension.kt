package com.franco.rickandmortymvvmapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.lifecycle.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*

fun <T> CoroutineScope.collectFlow(flow: Flow<T>, body: suspend (T) -> Unit) {
    flow.onEach { body(it) }
        .launchIn(this)
}

@ExperimentalCoroutinesApi
val View.onClickEvents: Flow<View>
    get() = callbackFlow {
        val onClickListener = View.OnClickListener { offer(it) }
        setOnClickListener(onClickListener)
        awaitClose { setOnClickListener(null) }
    }.conflate()


var View.visible: Boolean
    get() = visibility == View.VISIBLE
    set(value) {
        visibility = if (value) View.VISIBLE else View.GONE
    }

@ExperimentalCoroutinesApi
val RecyclerView.lastVisibleEvents: Flow<Int>
    get() = callbackFlow<Int> {
        val lm = layoutManager as GridLayoutManager

        val listener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                offer(lm.findLastVisibleItemPosition())
            }
        }
        addOnScrollListener(listener)
        awaitClose { removeOnScrollListener(listener) }
    }.conflate()


fun ImageView.loadUrl(completeUrl: String) {

    Glide
            .with(this)
            .load(completeUrl)

            .into(this)


}
fun ViewGroup.inflate(@LayoutRes layout: Int): View {
    val view = LayoutInflater.from(context).inflate(layout, this, false)
    return view
}