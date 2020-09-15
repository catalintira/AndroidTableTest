package com.example.tabletest

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.evrencoskun.tableview.adapter.AbstractTableAdapter
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder
import com.evrencoskun.tableview.adapter.recyclerview.holder.AbstractViewHolder
import com.example.tabletest.models.ColumnHeader
import com.example.tabletest.models.DataCell
import com.example.tabletest.models.RowHeader
import kotlinx.android.synthetic.main.layout_table_view_cell.view.*
import kotlinx.android.synthetic.main.layout_table_view_column_header.view.*
import kotlinx.android.synthetic.main.layout_table_view_row_header.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols


class TableAdapter : AbstractTableAdapter<ColumnHeader, RowHeader, DataCell>() {

    class ColumnHeaderViewHolder(itemView: View) : AbstractSorterViewHolder(itemView)
    class RowHeaderViewHolder(itemView: View) : AbstractViewHolder(itemView)
    class DataCellViewHolder(itemView: View, val viewType: Int) : AbstractViewHolder(itemView)

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
                image_view_status_icon.visibility = View.INVISIBLE

                if (rowPosition != 0) {
                    checkbox.visibility = View.VISIBLE
                }
            }
            image_view_status_icon.backgroundTintList =
                ColorStateList.valueOf(Color.GREEN)
        }
    }

    override fun onCreateCellViewHolder(parent: ViewGroup, viewType: Int): AbstractViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_cell, parent, false)
        return DataCellViewHolder(layout, viewType)
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

            // Mock test for the ranges testing
            try {
                val df = DecimalFormat("0.#").apply {
                    decimalFormatSymbols = DecimalFormatSymbols().apply {
                        decimalSeparator = ','
                    }
                }
                val displayedValue = df.parse(data.value).toFloat()
                if (displayedValue < 2) {
                    text_view_data.setTextColor(Color.RED)
                } else if (displayedValue > 100) {
                    text_view_data.setTextColor(Color.GREEN)
                } else {
                    text_view_data.setTextColor(Color.BLACK)
                }
            } catch (ex: Exception) { }

            if (rowPosition == 0) {
                text_view_data.setTypeface(text_view_data.typeface, Typeface.BOLD)

                if (columnPosition % 2 == 0) {
                    cell_container.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this.context,
                                R.color.selected_blue
                            )
                        )
                } else {
                    cell_container.backgroundTintList =
                        ColorStateList.valueOf(
                            ContextCompat.getColor(
                                this.context,
                                R.color.selected_blue_light
                            )
                        )
                }
            } else {
                if (columnPosition % 2 == 0) {
                    cell_container.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(this.context, R.color.white))
                } else {
                    cell_container.backgroundTintList =
                        ColorStateList.valueOf(ContextCompat.getColor(this.context, R.color.gray_light))
                }

            }
        }
    }

    override fun onCreateCornerView(parent: ViewGroup): View {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_table_view_corner, parent, false)
    }

    fun changeHeaderType(value: Int) {
        headerType = value
    }


    override fun getColumnHeaderItemViewType(position: Int): Int = position % 2

    override fun getRowHeaderItemViewType(position: Int): Int = headerType

    override fun getCellItemViewType(position: Int): Int = position % 2
}