package com.example.guessitapp.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

/*
private val CORRECT_BUZZ_PATTERN = longArrayOf(100, 100, 100, 100, 100)
private val PANIC_BUZZ_PATTERN = longArrayOf(0, 200)
private val GAME_OVER_BUZZ_PATTERN = longArrayOf(0, 2000)
private val NO_BUZZ_PATTERN = longArrayOf(0)
*/
// TODO: ViewModel containing all the logic needed to run the game
class GameViewModel: ViewModel() {

    // TODO: These are three different types of buzzing in the game. buzz pattern is the number of
    // TODO: milliseconds each interval of buzzing and non-buzzing takes.
    /*
    enum class BuzzType(val pattern: LongArray) {
        CORRECT(CORRECT_BUZZ_PATTERN),
        GAME_OVER(GAME_OVER_BUZZ_PATTERN),
        COUNTDOWN_PANIC(PANIC_BUZZ_PATTERN),
        NO_BUZZ(NO_BUZZ_PATTERN)
    }
    */
    companion object {

        //TODO: These represents different important times in the game, such as game length

        // TODO: This is when the game is over
        private const val DONE = 0L

        // TODO: This is the time when the phone will start buzzing each second
        // private const val COUNTDOWN_PANIC_SECONDS = 10L

        // TODO: This is the number of milliseconds in a second
        private const val ONE_SECONDS = 1000L

        // TODO: This is the total time of the game
        private const val COUNTDOWN_TIME = 10000L

        // TODO: This is the total time of the game
        // private const val COUNTDOWN_TIME = 60000L

    }

    val timer: CountDownTimer

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> get() = _currentTime

    val currentTimeString = currentTime.map { time ->
        DateUtils.formatElapsedTime(time)
    }

    // TODO: The current word
    private val _word = MutableLiveData<String>()
    val word: LiveData<String> get() = _word


    // TODO: The current score
    private var _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    // TODO: The list of words - the front of the list is the next word to guess
    private lateinit var wordList: MutableList<String>

    private val _eventGameFinished = MutableLiveData<Boolean>()
    val eventGameFinished: LiveData<Boolean> get() = _eventGameFinished

    // TODO: Event that triggers the phone to buzz using different pattern, determined by buzz type
    /*
    private val _eventBuzz = MutableLiveData<BuzzType>()
    val eventBuzz: LiveData<BuzzType> get() = _eventBuzz
    */
    // TODO: The list of words - the front of the list is the next word to guess
    init {

        resetList()
        nextWord()

        _score.value = 0

        // TODO: Creates a timer whitch triggers the end of the game when it finishes
        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECONDS) {

            override fun onTick(millisUnitFinished: Long) {
                _currentTime.value = (millisUnitFinished / ONE_SECONDS)
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventGameFinished.value = true
            }

        }

        timer.start()
    }

    /*
    init {

        Log.i("GameViewModel", "GameViewModel created")

        resetList()
        nextWord()

        _score.value = 0

        // TODO: Creates a timer whitch triggers the end of the game when it finishes
        timer = object: CountDownTimer(COUNTDOWN_TIME, ONE_SECONDS) {

            override fun onTick(millisUnitFinished: Long) {
                _currentTime.value = (millisUnitFinished / ONE_SECONDS)

                if (millisUnitFinished / ONE_SECONDS <= COUNTDOWN_PANIC_SECONDS) {
                    _eventBuzz.value = BuzzType.COUNTDOWN_PANIC
                }
            }

            override fun onFinish() {
                _currentTime.value = DONE
                _eventBuzz.value = BuzzType.GAME_OVER
                _eventGameFinished.value = true
            }
        }

        timer.start()

    }
    */

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
        Log.i("GameViewModel", "GameViewModel destroyed!")
    }

    // TODO: Resets the list of words and randomizes the order
    fun resetList() {

        wordList = mutableListOf("queen", "hospital", "basketball", "cat", "change", "snail", "soup", "calender", "sad", "desk",
            "guitar", "home", "railway", "zebra", "jelly", "car", "crow", "trade", "bag", "roll", "bubble")

        wordList.shuffle()

    }

    // TODO: Moves to the next word in the list
    fun nextWord() {

        // TODO: Select and remove a word from the list
        if (wordList.isEmpty()) {
            // TODO: gameFinished() should happen here
            resetList()
        }
            _word.value = wordList.removeAt(0)

    }

    // TODO: Methods for buttons presses
    fun onSkip() {
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = (score.value)?.plus(1)
        //TODO: _eventBuzz.value = BuzzType.CORRECT
        nextWord()
    }

    fun onGameFinshComplete() {
        _eventGameFinished.value = false
    }

    /*
    fun onBuzzComplete() {
        _eventBuzz.value = BuzzType.NO_BUZZ
    }
    */
}