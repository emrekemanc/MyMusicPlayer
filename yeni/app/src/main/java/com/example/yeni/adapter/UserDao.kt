package com.example.yeni.adapter

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.yeni.model.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable


@Dao
interface UserDao {
    @Query("SELECT * FROM User")
     fun getAll(): Flowable<List<User>>
     @Query("SELECT * FROM User WHERE id In (:ids)")
     fun getir(vararg ids:Int):Flowable<User>
    @Insert
     fun insertAll(vararg users: User):Completable

     @Delete
     fun delete(user: User):Completable
     @Query("DELETE FROM User WHERE id In (:ids)")
     fun sil(vararg ids:Int):Completable

}
