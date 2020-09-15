package com.example.tabletest.models

import com.evrencoskun.tableview.sort.ISortableModel

data class DataCell(val value: String) : ISortableModel {
    override fun getContent(): Any {
        return value
    }

    override fun getId(): String = ""
}