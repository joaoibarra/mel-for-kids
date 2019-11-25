package com.ibarra.descobrindoaspalavras.ui.tutorial

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ibarra.descobrindoaspalavras.R
import kotlinx.android.synthetic.main.main_fragment.*

class TutorialFragment(val imageId: Int,
                       val title: String,
                       val description: String,
                       val audio: String) : Fragment() {

    companion object {
        fun newInstance(imageId: Int,
                                       title: String,
                                       description: String,
                                       audio: String): Fragment {
            return TutorialFragment(imageId, title, description, audio)
        }
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel

        Glide.with(this).load(imageId).into(dog)

        activity?.title = title
        tvDescription.text = description

        btnNext.setOnClickListener {
            (activity as TutorialActivity).setFragment()
        }
    }

}
