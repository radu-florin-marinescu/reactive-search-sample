package com.verdite.samples.reactivesearch.data.dao

import androidx.room.*
import com.verdite.samples.reactivesearch.data.model.Stock

@Dao
interface StockDao {

    @Query("SELECT * FROM stocks ORDER BY name ASC")
    suspend fun get(): List<Stock>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: List<Stock>)

    @Query("DELETE FROM stocks")
    suspend fun delete()

    @Query("SELECT * FROM stocks WHERE name LIKE  '%' || :term  || '%' ")
    suspend fun search(term: String): List<Stock>

    @Transaction
    suspend fun replace(data: List<Stock>) {
        delete()
        insert(data)
    }
}