package com.example.implicitintents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {
    private lateinit var mWebsiteEditText: EditText
    private lateinit var mLocationEditText: EditText
    private lateinit var mShareTextEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mWebsiteEditText = findViewById(R.id.website_edittext)
        mLocationEditText = findViewById(R.id.location_edittext)
        mShareTextEditText = findViewById(R.id.share_edittext)
    }

    fun openWebsite(view: View) {
        val url = mWebsiteEditText.text.toString()
        val webpage = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can\'t handle this!")
        }
    }

    fun openLocation(view: View) {
        val loc = mLocationEditText.text.toString()
        val adressUri = Uri.parse("geo:0,0?q=$loc")
        val intent = Intent(Intent.ACTION_VIEW, adressUri)

        if (intent.resolveActivity(packageManager) != null){
            startActivity(intent)
        } else {
            Log.d("ImplicitIntents", "Can\'t handle this!")
        }
    }

    fun shareText(view: View) {
        val txt = mShareTextEditText.text.toString()
        val mimeType = "text/plain"

        ShareCompat.IntentBuilder.from(this).setType(mimeType)
            .setChooserTitle(getString(R.string.share_text_with))
            .setText(txt)
            .startChooser()
    }

    fun takePicture(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null)
            startActivity(intent)
        else
            Log.d("ImplicitIntents", "Can\'t handle this!")
    }
}