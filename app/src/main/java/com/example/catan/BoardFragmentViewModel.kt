package com.example.catan

import androidx.lifecycle.ViewModel
import java.lang.IllegalStateException

class BoardFragmentViewModel : ViewModel() {

    // Size should only set once and should never change. I'm only making this a lateinit because
    // I don't know how to pass arguments to a view model. size should be a val argument.
    private lateinit var size: BoardSize
    private lateinit var board: List<Hex>

    private var currentHexIndex = 0

    val position: Int get() = currentHexIndex + 1 // users prefer base 1 instead of 0

    private val hexCount: Int get() = if (size == BoardSize.EXTENDED) 30 else 19

    /**
     * Initializes all required members. Ideally this would be done via arguments but I don't
     * know how. Only call initialize once per fragment.
     */
    fun initialize(size: BoardSize) {
        this.size = size
    }

    /**
     * Sets a new board.
     * Returns the first hex of the new board
     */
    fun newBoard() {
        board = createBoard()
        currentHexIndex = 0
    }

    fun currentHex(): Hex {
        return board[currentHexIndex]
    }

    /**
     * Finds the next hex to be placed on the board
     */
    fun nextHex() : Hex {
        if (currentHexIndex == hexCount - 1) {
            return board[currentHexIndex]
        }

        val newHexIndex = currentHexIndex + 1
        val nextHex = board[newHexIndex]
        currentHexIndex = newHexIndex
        return nextHex
    }

    /**
     * Finds the previous hex to be placed on the board
     */
    fun prevHex() : Hex {
        if (currentHexIndex == 0) {
            return board[currentHexIndex]
        }

        val newHexIndex = currentHexIndex - 1
        val prevHex = board[newHexIndex]
        currentHexIndex = newHexIndex
        return prevHex
    }

    private fun createBoard() : List<Hex> {
    val board = mutableListOf<Hex>()
        val availableHexes = if (size == BoardSize.EXTENDED) {
            mutableMapOf(
                Hex.DESSERT to 2,
                Hex.FIELD to 6,
                Hex.FOREST to 6,
                Hex.PASTURE to 6,
                Hex.MOUNTAIN to 5,
                Hex.HILL to 5,
            )
        } else {
            mutableMapOf(
                Hex.DESSERT to 1,
                Hex.FIELD to 4,
                Hex.FOREST to 4,
                Hex.PASTURE to 4,
                Hex.MOUNTAIN to 3,
                Hex.HILL to 3,
            )
        }
        val hexTypesCount = Hex.values().size
        for (x in 0 until hexCount) {
            while (true) {
                // generate random number between 0 and 5 to represent a hex.
                val hexValue = (0 until hexTypesCount).random()
                val hex = Hex.from(hexValue)
                    ?: throw IllegalStateException("$hexValue does not correspond to Hex enum.")

                val remaining = availableHexes[hex]
                    ?: throw IllegalStateException("$hexValue does not have a defined quantity.")

                if (remaining == 0) continue // keep looking

                board.add(hex)
                availableHexes[hex] = remaining - 1
                break
            }
        }
        return board
    }

}

enum class Hex(val value: Int) {
    DESSERT(0),
    FIELD(1),
    FOREST(2),
    PASTURE(3),
    MOUNTAIN(4),
    HILL(5);

    companion object {
        fun from(value: Int) = values().firstOrNull { it.value == value }
    }
}

enum class BoardSize {
    REGULAR,
    EXTENDED
}
