package com.smile.data.repository.datasource

import android.util.Log
import com.smile.data.db.CharacterDao
import com.smile.domain.repository.CharacterLocalDataSource
import com.smile.data.db.CharacterDatabase
import com.smile.data.mappers.CharacterEntityMapper
import com.smile.domain.entities.Character
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CharacterLocalDataSourceImpl @Inject constructor(
    private val dao: CharacterDao
) : CharacterLocalDataSource {


    override fun getCharacters(): List<Character> {
        Log.i("getCharacters", "getCharacters")
        return dao.getAll().let {
            Log.i("CharacterEntityMapper", "CharacterEntityMapper")
            CharacterEntityMapper().mapFrom(it)
        }
    }


    override fun save(characters: List<Character>) {
        delete()
        dao.save(
            CharacterEntityMapper().mapTo(characters)
        )
    }

    override fun saveImage(id: Int, image: String) {
        dao.updateImage(id, image)
    }

    override fun delete() {
        dao.nuke()
    }


}