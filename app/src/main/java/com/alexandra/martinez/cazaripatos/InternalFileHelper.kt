// InternalFileHelper.kt
package com.alexandra.martinez.cazaripatos

import android.content.Context
import java.io.*

class InternalFileHelper(private val context: Context) : FileHandler {

    private val fileName = "datos_internos.txt"

    override fun SaveInformation(datosAGrabar: Pair<String, String>) {
        val data = "${datosAGrabar.first}\n${datosAGrabar.second}"
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(data.toByteArray())
        }
    }

    override fun ReadInformation(): Pair<String, String> {
        return try {
            val lines = context.openFileInput(fileName).bufferedReader().readLines()
            val email = lines.getOrNull(0) ?: ""
            val password = lines.getOrNull(1) ?: ""
            email to password
        } catch (e: IOException) {
            "" to ""
        }
    }
}
