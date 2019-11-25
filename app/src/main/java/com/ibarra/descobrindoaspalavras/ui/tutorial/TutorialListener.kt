package com.ibarra.descobrindoaspalavras.ui.tutorial

import android.app.Activity
import java.io.Serializable

interface TutorialListener<T : Activity> : Serializable {
    fun onArticleSelected(activity: T, position: Int)
}