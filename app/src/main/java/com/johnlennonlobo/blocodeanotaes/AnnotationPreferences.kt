package com.johnlennonlobo.blocodeanotaes

import android.content.Context
import android.content.SharedPreferences

class AnnotationPreferences(private val context:Context) {

    private val preferences = context.getSharedPreferences(FILE_NAME,0)
    private val editor = preferences.edit()

    fun saveAnnotation(annotation :String){
        editor.putString(KEY_NAME,annotation)
        editor.apply()
    }

    fun retrieveAnnotation(): String{
      return preferences.getString(KEY_NAME,"")!!
    }

    companion object{
        const val FILE_NAME = "anotation.preferences"
        const val KEY_NAME = "nome"
    }

}