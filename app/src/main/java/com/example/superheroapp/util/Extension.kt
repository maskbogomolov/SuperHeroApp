package com.example.superheroapp.util

import android.view.View

fun View.toVisible(){
    this.visibility = View.VISIBLE
}
fun View.toInvisible(){
    this.visibility = View.INVISIBLE
}
fun View.toGone(){
    this.visibility = View.GONE
}