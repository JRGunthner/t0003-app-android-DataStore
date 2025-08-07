package com.jgtche.datastore

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val prefManager = PreferencesManager(this)
        lifecycleScope.launch {
            findViewById<TextView>(R.id.textView_one).text = "Salvando valores..."
            delay(2000)
            prefManager.save("key", "Armazenabdo valor")
            val value = prefManager.read("")
            findViewById<TextView>(R.id.textView_one).text = value
            delay(2000)
            prefManager.remove("key")
            findViewById<TextView>(R.id.textView_one).text = "Valor removido!"
        }
    }
}
