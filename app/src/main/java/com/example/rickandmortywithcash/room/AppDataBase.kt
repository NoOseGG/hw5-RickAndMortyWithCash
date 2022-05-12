package com.example.rickandmortywithcash.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmortywithcash.room.entity.CharacterDbEntity

@Database(entities = [CharacterDbEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}