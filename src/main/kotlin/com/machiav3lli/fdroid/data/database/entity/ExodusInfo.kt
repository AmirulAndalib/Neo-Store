package com.machiav3lli.fdroid.data.database.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.machiav3lli.fdroid.ROW_PACKAGE_NAME
import com.machiav3lli.fdroid.TABLE_EXODUS_INFO
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Entity(
    tableName = TABLE_EXODUS_INFO,
    indices = [
        Index(value = [ROW_PACKAGE_NAME]),
    ]
)
data class ExodusInfo(
    @PrimaryKey
    val packageName: String = "",
    override val handle: String = String(),
    override val app_name: String = String(),
    override val uaid: String = String(),
    override val version_name: String = String(),
    override val version_code: String = String(),
    override val source: String = String(),
    override val icon_hash: String = String(),
    override val apk_hash: String = String(),
    override val created: String = String(),
    override val updated: String = String(),
    override val report: Int = 0,
    override val creator: String = String(),
    override val downloads: String = String(),
    override val trackers: List<Int> = emptyList(),
    override val permissions: List<String> = emptyList(),
) : ExodusData(
    handle, app_name, uaid, version_name, version_code, source,
    icon_hash, apk_hash, created, updated, report, creator, downloads, trackers, permissions,
)

@Serializable
open class ExodusData(
    open val handle: String = String(),
    open val app_name: String = String(),
    open val uaid: String = String(),
    open val version_name: String = String(),
    open val version_code: String = String(),
    open val source: String = String(),
    open val icon_hash: String = String(),
    open val apk_hash: String = String(),
    open val created: String = String(),
    open val updated: String = String(),
    open val report: Int = 0,
    open val creator: String = String(),
    open val downloads: String = String(),
    open val trackers: List<Int> = emptyList(),
    open val permissions: List<String> = emptyList(),
) {
    fun toExodusInfo(packageName: String) = ExodusInfo(
        packageName, handle, app_name, uaid, version_name, version_code, source,
        icon_hash, apk_hash, created, updated, report, creator, downloads, trackers, permissions
    )

    fun toJSON() = Json.encodeToString(this)

    companion object {
        private val jsonConfig = Json { ignoreUnknownKeys = true }
        fun fromJson(json: String) = jsonConfig.decodeFromString<ExodusData>(json)
        fun listFromJson(json: String) = jsonConfig.decodeFromString<List<ExodusData>>(json)
    }
}
