package com.example.tabletest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tabletest.models.ColumnHeader
import com.example.tabletest.models.DataCell
import com.example.tabletest.models.RowHeader
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val tableAdapter = TableAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        table_view_athletes.apply {
            setAdapter(tableAdapter)
            adapter?.setAllItems(colHeaders, rowHeaders, cellData)
            rowHeaderWidth = 320
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
            DataCell("56,0"),
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
        getRowOfData(),getRowOfData(),getRowOfData(),getRowOfData(),getRowOfData()
    )
}
