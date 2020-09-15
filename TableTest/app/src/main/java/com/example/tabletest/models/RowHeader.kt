package com.example.tabletest.models

import com.evrencoskun.tableview.sort.ISortableModel

data class RowHeader(
    val name: String,
    val detail1: String,
    val detail2: String
) : ISortableModel {
    override fun getContent(): Any? {
        return name
    }

    override fun getId(): String = ""
}