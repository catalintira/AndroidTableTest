package com.example.tabletest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.evrencoskun.tableview.TableView
import com.evrencoskun.tableview.listener.ITableViewListener
import com.evrencoskun.tableview.sort.SortState
import com.example.tabletest.models.ColumnHeader
import com.example.tabletest.models.DataCell
import com.example.tabletest.models.RowHeader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ITableViewListener {

    private val tableAdapter = TableAdapter()
    private lateinit var tableRecyclerView: TableView
    private var numberToChange = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tableRecyclerView = table_view_athletes

        table_view_athletes.apply {
            setAdapter(tableAdapter)
            adapter?.setAllItems(colHeaders, rowHeaders, cellData)
            rowHeaderWidth = 320
            tableViewListener = this@MainActivity
        }

        btn.setOnClickListener {
            tableAdapter.setAllItems(colHeaders, rowHeaders.subList(0, 3), cellData.subList(0, 3))
            tableAdapter.notifyDataSetChanged()
        }

        btn_c.setOnClickListener {
            tableAdapter.removeColumn(2)
        }

        btn_a.setOnClickListener {
            tableAdapter.removeRow(1)
        }

        compare.setOnClickListener {
            tableAdapter.changeHeaderType(1)
            tableAdapter.notifyDataSetChanged()
        }


    }

    private fun getRowOfData() =
        listOf(
            DataCell("1,14"),
            DataCell("6:03"),
            DataCell("150"),
            DataCell("7,5"),
            DataCell((++numberToChange).toString()),
            DataCell("143,9"),
            DataCell("67"),
            DataCell("0,76")
        )

    val rowHeaders = listOf(
        RowHeader("Asdvasdva", "RHR", "Sleep"),
        RowHeader("Stefan P.", "63 bpm", "7,5 h"),
        RowHeader("Ruxandra T.", "-", "-"),
        RowHeader("Mircea C.", "72 bpm", "-"),
        RowHeader("Atti", "-", "-")
    )

    val colHeaders = listOf(
        ColumnHeader("Total Distance", "(km)"),
        ColumnHeader("Avg Pace", "(min/km)"),
        ColumnHeader("Avg Activity HR", "(bpm)"),
        ColumnHeader("Avg Sleep", "(hours)"),
        ColumnHeader("VO2 Max", "(ml/kg/min)"),
        ColumnHeader("Avg Cadence", "(spm)"),
        ColumnHeader("Resting HR", "(bpm)"),
        ColumnHeader("Longest", "(km)")
    )

    val cellData = listOf(
        getRowOfData(), getRowOfData(), getRowOfData(), getRowOfData(), getRowOfData()
    )

    override fun onCellLongPressed(cellView: RecyclerView.ViewHolder, column: Int, row: Int) { }

    override fun onColumnHeaderLongPressed(
        columnHeaderView: RecyclerView.ViewHolder,
        column: Int
    ) { }

    override fun onRowHeaderClicked(rowHeaderView: RecyclerView.ViewHolder, row: Int) { }

    override fun onColumnHeaderClicked(columnHeaderView: RecyclerView.ViewHolder, column: Int) {
        tableRecyclerView.apply {
            if (getSortingStatus(column) == SortState.ASCENDING) {
                sortColumn(column, SortState.DESCENDING)
            } else {
                sortColumn(column, SortState.ASCENDING)
            }
        }
    }

    override fun onCellClicked(cellView: RecyclerView.ViewHolder, column: Int, row: Int) { }

    override fun onColumnHeaderDoubleClicked(
        columnHeaderView: RecyclerView.ViewHolder,
        column: Int
    ) { }

    override fun onCellDoubleClicked(cellView: RecyclerView.ViewHolder, column: Int, row: Int) { }

    override fun onRowHeaderLongPressed(rowHeaderView: RecyclerView.ViewHolder, row: Int) { }

    override fun onRowHeaderDoubleClicked(rowHeaderView: RecyclerView.ViewHolder, row: Int) { }

}
