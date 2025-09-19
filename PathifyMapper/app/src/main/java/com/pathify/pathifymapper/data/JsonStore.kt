package com.pathify.pathifymapper.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pathify.pathifymapper.model.AppState
import java.io.File
import java.nio.charset.StandardCharsets

class JsonStore(private val context: Context) {
    private val gson: Gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    private val fileName = "mapper_state.json"

    private fun file(): File = File(context.filesDir, fileName)

    fun loadState(): AppState {
        val f = file()
        if (!f.exists()) return AppState()
        val json = f.readText(StandardCharsets.UTF_8)
        return gson.fromJson(json, AppState::class.java) ?: AppState()
    }

    fun saveState(state:AppState) {
        val f = file()
        f.writeText(gson.toJson(state), StandardCharsets.UTF_8)
    }
}