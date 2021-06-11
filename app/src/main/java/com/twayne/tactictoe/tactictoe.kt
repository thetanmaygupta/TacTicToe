package com.twayne.tactictoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import com.twayne.tactictoe.databinding.ActivityTactictoeBinding

class tactictoe : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityTactictoeBinding? = null
    lateinit var board: Array<Array<MaterialButton?>>
    lateinit var PLAYER: String
    private var winnerFound: Boolean = false

    private var movesPlayed = 0
    private var currentBoardState = Array(3) {IntArray(3)}
    private var playerXPoints = 0
    private var playerOPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTactictoeBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val chosenSymbol = intent.getStringExtra("SYMBOL")

        PLAYER = chosenSymbol!!

        winnerFound = false

        binding?.tvTurn?.text = "Turn: Player $PLAYER"

        board = arrayOf(
            arrayOf(binding?.btn1, binding?.btn2, binding?.btn3),
            arrayOf(binding?.btn4, binding?.btn5, binding?.btn6),
            arrayOf(binding?.btn7, binding?.btn8, binding?.btn9)
        )

        for (row in board) {
            for (btn in row) {
                btn?.setOnClickListener(this)
            }
        }

        initBoard()

        binding?.btnPlayAgain?.setOnClickListener {
            initBoard()
            movesPlayed = 0
            PLAYER = chosenSymbol
            binding?.tvResult?.text = ""
            winnerFound = false
        }

        binding?.btnReset?.setOnClickListener {
            initBoard()
            movesPlayed = 0
            playerXPoints = 0
            playerOPoints = 0
            updatePlayerPoints(0, "X")
            updatePlayerPoints(0, "O")
            PLAYER = chosenSymbol
            binding?.tvResult?.text = ""
            winnerFound = false
        }
    }

    private fun initBoard() {
        for (i in 0..2) {
            for (j in 0..2) {
                currentBoardState[i][j] = -1
            }
        }
        for (row in board) {
            for (btn in row) {
                btn?.isEnabled = true
                btn?.text = ""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn1 -> {
                updateBox(0, 0, PLAYER)
            }
            R.id.btn2 -> {
                updateBox(0, 1, PLAYER)
            }
            R.id.btn3 -> {
                updateBox(0, 2, PLAYER)
            }
            R.id.btn4 -> {
                updateBox(1, 0, PLAYER)
            }
            R.id.btn5 -> {
                updateBox(1, 1, PLAYER)
            }
            R.id.btn6 -> {
                updateBox(1, 2, PLAYER)
            }
            R.id.btn7 -> {
                updateBox(2, 0, PLAYER)
            }
            R.id.btn8 -> {
                updateBox(2, 1, PLAYER)
            }
            R.id.btn9 -> {
                updateBox(2, 2, PLAYER)
            }
        }
        PLAYER = if (PLAYER == "X") "O" else "X"
        movesPlayed++
        updateDisplay("Turn: Player $PLAYER")
        checkWinner()

        if (movesPlayed == 9 && !winnerFound) {
            updateResult("Game Draw")
        }

    }

    private fun checkWinner() {
        for (i in 0..2) {
            if (currentBoardState[i][0] == currentBoardState[i][1] && currentBoardState[i][0] == currentBoardState[i][2]) {
                if (currentBoardState[i][0] == 1) {
                    updateResult( "PLAYER X WON")
                    playerXPoints++
                    updatePlayerPoints(playerXPoints, "X")
                    winnerFound = true
                    break
                }else if (currentBoardState[i][0] == 0) {
                    updateResult("PLAYER O WON")
                    playerOPoints++
                    updatePlayerPoints(playerOPoints, "O")
                    winnerFound = true
                    break
                }
            }
        }

        for (i in 0..2) {
            if (currentBoardState[0][i] == currentBoardState[1][i] && currentBoardState[0][i] == currentBoardState[2][i]) {
                //now checking if that value is 1 or 0 to determine if PLAYER X or O has won
                if (currentBoardState[0][i] == 1) {
                    updateResult( "PLAYER X WON")
                    playerXPoints++
                    updatePlayerPoints(playerXPoints, "X")
                    winnerFound = true
                    break
                }else if (currentBoardState[0][i] == 0) {
                    updateResult("PLAYER O WON")
                    playerOPoints++
                    updatePlayerPoints(playerOPoints, "O")
                    winnerFound = true
                    break
                }
            }
        }

        if (currentBoardState[0][0] == currentBoardState[1][1] && currentBoardState[0][0] == currentBoardState[2][2]) {
            if (currentBoardState[0][0] == 1) {
                updateResult("PLAYER X WON")
                playerXPoints++
                updatePlayerPoints(playerXPoints, "X")
                winnerFound = true

            }else if (currentBoardState[0][0] == 0) {
                updateResult("PLAYER O WON")
                playerOPoints++
                updatePlayerPoints(playerOPoints, "O")
                winnerFound = true
            }
        }

        if (currentBoardState[0][2] == currentBoardState[1][1] && currentBoardState[0][2] == currentBoardState[2][0]){
            if (currentBoardState[0][2] == 1) {
                updateResult("PLAYER X WON")
                playerXPoints++
                updatePlayerPoints(playerXPoints, "X")
                winnerFound = true

            }else if (currentBoardState[0][2] == 0) {
                updateResult("PLAYER O WON")
                playerOPoints++
                updatePlayerPoints(playerOPoints, "O")
                winnerFound = true
            }
        }
    }

    private fun updateBox(row: Int, col: Int, player: String) {
        val value = if (player == "X") 1 else 0
        board[row][col]?.text = player
        board[row][col]?.isEnabled = false
        currentBoardState[row][col] = value
    }

    private fun updateDisplay(s: String) {
        binding?.tvTurn?.text = s
    }

    private fun updatePlayerPoints(points: Int, player: String) {
        if (player == "X") {
            binding?.tvPlayerXPoints?.text = "$points"
        }else if (player == "O") {
            binding?.tvPlayerOPoints?.text = "$points"
        }
    }

    private fun updateResult(s: String) {
        binding?.tvResult?.text = s
        disableAllButtons()
    }

    private fun disableAllButtons() {
        for (row in board) {
            for (btn in row) {
                btn?.isEnabled = false
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}