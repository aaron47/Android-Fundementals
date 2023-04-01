package com.aaron.androidxmlcancer

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.aaron.androidxmlcancer.databinding.ActivityPreviewOrderBinding

class PreviewOrderActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewOrderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hamburger = intent.getParcelableExtra<Hamburger>("EXTRA_HAMBURGER")
        val order =
            "Your order consists of a ${hamburger?.meat} burger with: ${hamburger?.toppings}"

        binding.tvOrderInfo.text = order

        // wrong, don't do this, causes memory leaks

        // binding.btnOrderAgain.setOnClickListener {
        // Intent(this, MainActivity::class.java).also {
        // startActivity(it)
        // }
        // }

        binding.btnOrderAgain.setOnClickListener {
            finish()
        }

        binding.btnAddImg.setOnClickListener {
            // get any type of content from the device
            // Intent(Intent.ACTION_GET_CONTENT).also {
                // mime type that will look for all images on the device
               // it.type = "image/*"
                // startActivityForResult(it, 0)
            // }
            uploadPhoto.launch("image/*")
        }
    }

    private val uploadPhoto = registerForActivityResult(ActivityResultContracts.GetContent()) {
       it.let {
           binding.ivPhoto.setImageURI(it)
       }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
//            val uri = data?.data
//            binding.ivPhoto.setImageURI(uri)
//        }
//    }
}