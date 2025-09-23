package com.pathify.pathifymapper.model

import android.net.Uri

// App-level state holds all buildings the mapper manages.
data class AppState(
    val buildings: MutableList<Building> = mutableListOf()
)

data class Building(
    val id: String,
    var name: String,
    var geoLat: Double? = null,
    var geoLon: Double? = null,
    val floors: MutableList<FloorRef> = mutableListOf()
)

data class FloorRef(
    val id: String,
    val displayName: String,

    var imageUri: String? = null,

    var calibrationMetersPerPx: Float? = null, // placeholder for a later phase
    var rotationDeg: Float? = null
)