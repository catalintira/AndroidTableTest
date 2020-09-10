package com.example.tabletest

import android.view.View
import android.view.ViewGroup
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tabletest.models.DataCell
import com.example.tabletest.models.ColumnHeader
import com.example.tabletest.models.RowHeader
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.layout_table_view_cell.view.*
import kotlinx.android.synthetic.main.layout_table_view_column_header.view.*
import kotlinx.android.synthetic.main.layout_table_view_row_header.view.*


class TableAdapter : AbstractTableAdapter<ColumnHeader, RowHeader, DataCell>() {

    class ColumnHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView)
    class RowHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView)
    class DataCellViewHolder(itemView: View) : AbstractViewHolder(itemView)

    private var headerType = 0

    override fun onCreateColumnHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_column_header, parent, false)
        return ColumnHeaderViewHolder(layout)
    }

    override fun onBindColumnHeaderViewHolder(
        holder: AbstractViewHolder,
        columnHeaderItemModel: ColumnHeader?,
        columnPosition: Int
    ) {
        val data = columnHeaderItemModel as ColumnHeader
        val columnHolder = holder as ColumnHeaderViewHolder

        columnHolder.itemView.apply {
            text_view_header.text = "${data.name}\n${data.detail}"
        }
    }

    override fun onCreateRowHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_row_header, parent, false)
        return RowHeaderViewHolder(layout)
    }

    override fun onBindRowHeaderViewHolder(
        holder: AbstractViewHolder,
        rowHeaderItemModel: RowHeader?,
        rowPosition: Int
    ) {
        val data = rowHeaderItemModel as RowHeader
        val rowHolder = holder as RowHeaderViewHolder

        rowHolder.itemView.apply {
            text_view_name.text = data.name
            text_view_details.text = data.detail1 + " | " + data.detail2
            if (headerType == 1) {
                image_view_profile_picture.visibility = View.INVISIBLE
                checkbox.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_cell, parent, false)
        return DataCellViewHolder(layout)
    }

    override fun onBindCellViewHolder(
        holder: AbstractViewHolder,
        itemModelCell: DataCell?,
        columnPosition: Int,
        rowPosition: Int
    ) {
        val data = itemModelCell as DataCell
        val cellHolder = holder as DataCellViewHolder

        cellHolder.itemView.apply {
            text_view_data.text = data.value
        }
    }

    override fun onCreateCornerView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_corner, parent, false)
    }

    fun changeHeaderType(value: Int) {
        headerType = value
    }


    override fun getColumnHeaderItemViewType(position: Int): Int = 0

    override fun getRowHeaderItemViewType(position: Int): Int = headerType

    override fun getCellItemViewType(position: Int): Int = 0
}