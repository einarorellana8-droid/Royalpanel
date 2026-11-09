package com.royalpanel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.media.projection.MediaProjectionManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.net.Uri
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    private val captureRequest = 4101
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(28, 36, 28, 36)
            setBackgroundColor(0xFF101729.toInt())
        }

        val title = TextView(this).apply {
            text = "👑 ROYAL PANEL"
            textSize = 28f
            setTextColor(0xFF65B9FF.toInt())
            gravity = Gravity.CENTER
        }
        status = TextView(this).apply {
            text = "ANDROID AUTO TRACKER · V0.1\n\nCaptura de pantalla autorizada para futuras funciones de análisis visual."
            textSize = 16f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
            setPadding(0, 28, 0, 28)
        }
        val start = Button(this).apply {
            text = "INICIAR CAPTURA AUTORIZADA"
            setOnClickListener { requestCapture() }
        }
        val stop = Button(this).apply {
            text = "DETENER CAPTURA"
            setOnClickListener {
                stopService(Intent(this@MainActivity, ScreenCaptureService::class.java))
                status.text = "Captura detenida."
            }
        }
        val note = TextView(this).apply {
            text = "\nIMPORTANTE: esta versión inicial solo establece la captura autorizada. Todavía no reconoce cartas ni lee el elixir del juego."
            textSize = 13f
            setTextColor(0xFFB9C9E1.toInt())
            gravity = Gravity.CENTER
        }
        layout.addView(title)
        layout.addView(status)
        layout.addView(start)
        layout.addView(stop)
        layout.addView(note)
        setContentView(layout)
    }

    private fun requestCapture() {
        val manager = getSystemService(Context.MEDIA_PROJECTION_SERVICE) as MediaProjectionManager
        startActivityForResult(manager.createScreenCaptureIntent(), captureRequest)
    }

    @Deprecated("Deprecated by Android, retained for broad device compatibility")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == captureRequest && resultCode == RESULT_OK && data != null) {
            val serviceIntent = Intent(this, ScreenCaptureService::class.java).apply {
                putExtra(ScreenCaptureService.EXTRA_RESULT_CODE, resultCode)
                putExtra(ScreenCaptureService.EXTRA_DATA, data)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) startForegroundService(serviceIntent)
            else startService(serviceIntent)
            status.text = "Solicitud autorizada. La captura está activa mientras Android mantenga el servicio."
        } else if (requestCode == captureRequest) {
            status.text = "Permiso de captura cancelado. No se inició la captura."
        }
    }
}
