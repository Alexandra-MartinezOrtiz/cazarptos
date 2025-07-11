package com.alexandra.martinez.cazaripatos

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException

class ExternalFileHelper(private val context: Context) : FileHandler {

    private val fileName = "datos_externos.txt"

    private fun getExternalFile(): File? {
        return if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(context.getExternalFilesDir(null), fileName)
        } else {
            null
        }
    }

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val file = getExternalFile() ?: return
        val contenido = "${datosAGrabar.first};${datosAGrabar.second}"
        try {
            file.writeText(contenido)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        val file = getExternalFile() ?: return "" to ""
        return try {
            val contenido = file.readText()
            val partes = contenido.split(";")
            if (partes.size == 2) partes[0] to partes[1] else "" to ""
        } catch (e: IOException) {
            e.printStackTrace()
            "" to ""
        }
    }
}
