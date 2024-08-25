package com.example.guessitapp.screens.game

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.guessitapp.R
import com.example.guessitapp.databinding.FragmentGameBinding


// TODO: Fragment where the game played
class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // TODO: Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        Log.i("GameFragment", "Called: ViewModelProviders.of")
        // TODO: gameViewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)


        // TODO: Set the view model for data binding - this allows the bound layout access to all of the
        // TODO: data in the view model
        binding.gameViewModel = gameViewModel

        // TODO: Specify the current activity as the lifecycle owner of the binding. this is used so that
        //TODO: the binding can observe live data updates
        binding.lifecycleOwner = this

        // TODO: Sets up event listening to navigate the player when the game is finished
        gameViewModel.eventGameFinished.observe(viewLifecycleOwner, Observer { isFinished ->

            if (isFinished) {

                val currentScore = gameViewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                this.findNavController().navigate(action)
                gameViewModel.onGameFinshComplete()
            }
        })

        // TODO: Buzzes when triggered with different buzz events
        /*
        gameViewModel.eventBuzz.observe(viewLifecycleOwner, Observer { buzzType ->
            if (buzzType != GameViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                gameViewModel.onBuzzComplete()
            }
        })
        */
        return binding.root
    }

    // TODO: Given a pattern, this method makes sure the device buzzes
    /*
    private fun buzz(pattern: LongArray) {

        val buzzer = activity?.getSystemService<Vibrator>()

        buzzer?.let {
            // TODO: Vibrate for 500 milliseconds
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                // TODO: Deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }
   */

}
