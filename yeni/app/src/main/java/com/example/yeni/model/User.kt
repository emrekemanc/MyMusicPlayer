package com.example.yeni.model

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity
data class User (

    @ColumnInfo (name = "muzikAdi") val muzikAdi:String,
    @ColumnInfo (name = "muzikGorsel") val muzikGorsel:ByteArray,
    @ColumnInfo (name = "muzik") val muzik:String,
    ){
    @PrimaryKey(true)
    var id=0

}
