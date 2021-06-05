package com.frenaud.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var ACTIVE_PLAYER = 1
    private var playerOne = ArrayList<Int>()
    private var playerTwo = ArrayList<Int>()
    private var occupiedCell = ArrayList<Int>()
    private val winningPattern : Array<List<Int>> = arrayOf(
        listOf(1, 2, 3),
        listOf(4, 5, 6),
        listOf(7, 8, 9),
        listOf(1, 4, 7),
        listOf(2, 5, 8),
        listOf(3, 6, 9),
        listOf(1, 5, 9),
        listOf(3, 5, 7),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        val currentButton = view as Button
        var cellId = 0

        when(currentButton.id) {
            R.id.b1 -> cellId = 1
            R.id.b2 -> cellId = 2
            R.id.b3 -> cellId = 3
            R.id.b4 -> cellId = 4
            R.id.b5 -> cellId = 5
            R.id.b6 -> cellId = 6
            R.id.b7 -> cellId = 7
            R.id.b8 -> cellId = 8
            R.id.b9 -> cellId = 9
        }
        gameLogic(currentButton, cellId)
    }

    private fun gameLogic(currentButton : Button, cellId: Int) {
        when (ACTIVE_PLAYER) {
            1 -> {
                currentButton.text = "O"
                currentButton.setBackgroundResource(R.color.green)
                playerOne.add(cellId)
                occupiedCell.add(cellId)
                ACTIVE_PLAYER = 2
                AIPlay()
            }
            2 -> {
                currentButton.text = "X"
                currentButton.setBackgroundResource(R.color.red)
                playerTwo.add(cellId)
                occupiedCell.add(cellId)
                ACTIVE_PLAYER = 1
            }
        }
        currentButton.isEnabled = false
        currentButton.isClickable = false
        checkWin()
    }

    private fun AIPlay() {
        val emptyCells = ArrayList<Int>()
        (1..9).forEach {
            if (!occupiedCell.contains(it)) {
                emptyCells.add(it)
            }
        }

        val index = Random.nextInt(emptyCells.size)
        val bSelected: Button = when (emptyCells[index]) {
            1 -> findViewById(R.id.b1)
            2 -> findViewById(R.id.b2)
            3 -> findViewById(R.id.b3)
            4 -> findViewById(R.id.b4)
            5 -> findViewById(R.id.b5)
            6 -> findViewById(R.id.b6)
            7 -> findViewById(R.id.b7)
            8 -> findViewById(R.id.b8)
            9 -> findViewById(R.id.b9)
            else -> findViewById(R.id.b1)
        }
        gameLogic(bSelected, emptyCells[index])
    }

    private fun checkWin() {
        winningPattern.forEach {
            if (playerOne.containsAll(it)) {
                Toast.makeText(this, "Player one WON !", Toast.LENGTH_SHORT).show()
                resetGame()
            }
            if (playerTwo.containsAll(it)) {
                Toast.makeText(this, "Player two WON !", Toast.LENGTH_SHORT).show()
                resetGame()
            }
        }
    }

    private fun resetGame() {
        ACTIVE_PLAYER = 1
        playerOne.clear()
        playerTwo.clear()
        occupiedCell.clear()

        for (cellId in 1..9) {
            val bSelected: Button = when (cellId) {
                1 -> findViewById(R.id.b1)
                2 -> findViewById(R.id.b2)
                3 -> findViewById(R.id.b3)
                4 -> findViewById(R.id.b4)
                5 -> findViewById(R.id.b5)
                6 -> findViewById(R.id.b6)
                7 -> findViewById(R.id.b7)
                8 -> findViewById(R.id.b8)
                9 -> findViewById(R.id.b9)
                else -> findViewById(R.id.b1)
            }
            bSelected.text = ""
            bSelected.setBackgroundResource(android.R.drawable.btn_default)
            bSelected.isEnabled = true
            bSelected.isClickable = true
        }
    }
}