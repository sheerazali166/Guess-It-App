package com.example.guessitapp.screens.score

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.guessitapp.R
import com.example.guessitapp.databinding.FragmentScoreBinding


// TODO: Fragment where the final score is shown, after the game is over
class ScoreFragment : Fragment() {

    private lateinit var scoreViewModel: ScoreViewModel
    private lateinit var scoreViewModelFactory: ScoreViewModelFactory

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // TODO: Inflate view and obtain and instance of the binding class.
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_score, container, false)

        // TODO: Get args using by navArgs property delegate
        val scoreFragmentArgs by navArgs<ScoreFragmentArgs>()

        scoreViewModelFactory = ScoreViewModelFactory(scoreFragmentArgs.score)
        scoreViewModel = ViewModelProvider(this, scoreViewModelFactory)
            .get(ScoreViewModel::class.java)

        binding.scoreViewModel = scoreViewModel

        // TODO: Specity the current activity as the life cycle owner of the binding. this is used so that
        // TODO: the binding can observe live data updates
        binding.lifecycleOwner = this

        // TODO: Navigates back to title when button is pressed
        scoreViewModel.eventPlayAgain.observe(this, Observer { playAgain ->

            if (playAgain) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                scoreViewModel.onPlayAgainComplete()
            }
        })

        return binding.root
    }
}