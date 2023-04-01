package com.aaron.androidxmlcancer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.aaron.androidxmlcancer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnOrder: Button
    private lateinit var rgMeats: RadioGroup
    private lateinit var cbCheese: CheckBox
    private lateinit var cbOnions: CheckBox
    private lateinit var cbSalad: CheckBox
    private lateinit var tvOrder: TextView
//    private lateinit var imgAddBtn: Button
//    private lateinit var ivImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnOrder = binding.btnOrder
        rgMeats = binding.rgMeats
        cbCheese = binding.cbCheese
        cbOnions = binding.cbOnions
        cbSalad = binding.cbSalad
        tvOrder = binding.tvOrder


        btnOrder.setOnClickListener {
            val checkedMeatRadioButtonId = rgMeats.checkedRadioButtonId
            val meat = findViewById<RadioButton>(checkedMeatRadioButtonId)
            val cheese = cbCheese.isChecked
            val onions = cbOnions.isChecked
            val salad = cbSalad.isChecked

            val order =
                "You ordered a burger with: ${meat.text} " + (if (cheese) " Cheese " else "") + (if (onions) " Onions " else "") + (if (salad) " Salad" else "")

            tvOrder.text = order
            //  makeText expects a context, we can either use the applicationContext, or just provide the activity as context(this)
            //  Toast.makeText(this, "Order Succeeded!", Toast.LENGTH_LONG).show()

            // custom toast (custom_toast.xml)
            Toast(this).apply {
                duration = Toast.LENGTH_LONG
                view = layoutInflater.inflate(R.layout.custom_toast, findViewById<ConstraintLayout>(R.id.clToast))
                show()
            }
        }

        binding.btnPreviewOrder.setOnClickListener {
            val checkedMeatRadioButtonId = rgMeats.checkedRadioButtonId
            val meat = findViewById<RadioButton>(checkedMeatRadioButtonId)
            val cheese = cbCheese.isChecked
            val onions = cbOnions.isChecked
            val salad = cbSalad.isChecked
            val toppings =
                (if (cheese) "Cheese " else "") + (if (onions) " Onions" else "") + (if (salad) " Salad" else "")
            val hamburger = Hamburger(meat.text.toString(), toppings)

            Intent(this, PreviewOrderActivity::class.java).also {
                it.putExtra("EXTRA_HAMBURGER", hamburger)
                startActivity(it)
            }
        }
//        imgAddBtn.setOnClickListener {
//            // set image src
//            ivImage.setImageResource(R.drawable.mapleleaf2)
//        }


        binding.btnReqPerms.setOnClickListener {
            requestPermissions()
        }

        binding.btnDialogs.setOnClickListener {
            Intent(this, DialogActivity::class.java).also {
                startActivity(it)
            }
        }
    }


    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasAccessCoarseLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun hasAccessBackgroundLocationPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_BACKGROUND_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        var permissionsToRequest = mutableListOf<String>()
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (!hasAccessCoarseLocationPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (!hasAccessBackgroundLocationPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toTypedArray(), 0)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d("MainActivity", "${permissions[i]} granted")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.miAddContact -> Toast.makeText(this, "You clicked on add contact!", Toast.LENGTH_SHORT).show()
            R.id.miFavorites -> Toast.makeText(this, "You clicked on Favorites!", Toast.LENGTH_SHORT).show()
            R.id.miFeedback -> Toast.makeText(this, "You clicked on Feedback!", Toast.LENGTH_SHORT).show()
            R.id.miClose -> finish()
            R.id.miSettings -> Toast.makeText(this, "You clicked on Settings!", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}

