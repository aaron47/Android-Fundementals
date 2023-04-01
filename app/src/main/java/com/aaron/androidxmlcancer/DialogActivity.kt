package com.aaron.androidxmlcancer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.aaron.androidxmlcancer.databinding.ActivityDialogBinding

class DialogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setMessage("Do you want to add this guy to your contacts list?")
            .setIcon(R.drawable.ic_add_contact)
            .setPositiveButton("Yes") { _, _ ->
                Toast.makeText(this, "You added Goblingon to your contacts list!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("No") { _, _ ->
                Toast.makeText(this, "You have not added Goblingon to your contacts list!", Toast.LENGTH_SHORT).show()
            }
            .create()

        binding.btnDialog1.setOnClickListener {
            addContactDialog.show()
        }

        val options = arrayOf("First Item", "Second Item", "Third Icon")
        val singleChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these :D")
            .setSingleChoiceItems(options, 0) { _, i ->
                Toast.makeText(this, "You clicked ${options[i]}", Toast.LENGTH_SHORT).show()
            }
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "Choice Accepted, Loading...", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Close") { _, _ ->
                Toast.makeText(this, "Closed Dialog", Toast.LENGTH_SHORT).show()
            }
            .create()

        binding.btnDialog2.setOnClickListener {
            singleChoiceDialog.show()
        }

        val multiChoiceDialog = AlertDialog.Builder(this)
            .setTitle("Choose one of these :D")
            .setMultiChoiceItems(options, booleanArrayOf(false, false, false)) { _, i, isChecked ->
                if (isChecked) {
                    Toast.makeText(this, "You checked ${options[i]}", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked ${options[i]}", Toast.LENGTH_SHORT).show()
                }
            }
            .setPositiveButton("Accept") { _, _ ->
                Toast.makeText(this, "Choice(s) Accepted, Loading...", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Close") { _, _ ->
                Toast.makeText(this, "Closed Dialog", Toast.LENGTH_SHORT).show()
            }
            .create()

        binding.btnDialog3.setOnClickListener {
            multiChoiceDialog.show()
        }

        // give values to the spinner programatically using Kotlin
        val customList = listOf("First", "Second", "Third", "Fourth")
        val adapter =
            ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, customList)
        binding.spMonths.adapter = adapter

        // has the values from the R.values.strings
        binding.spMonths.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Toast.makeText(
                    this@DialogActivity,
                    "You selected: ${adapterView?.getItemAtPosition(position).toString()}",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }
}