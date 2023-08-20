package com.donatas.dprofile.paginator

class Page(val value: Int)

fun Page.next(): Page {
    return Page(this.value + 1)
}
