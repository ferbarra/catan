package com.example.catan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

private const val TAG = "GameSizeFragment"
class GameSizeFragment : Fragment() {

    private lateinit var regularBoardButton: Button
    private lateinit var extendedBoardButton: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_game_size, container, false)
        regularBoardButton = view.findViewById(R.id.regular_board_button) as Button
        extendedBoardButton = view.findViewById(R.id.extended_board_button) as Button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        regularBoardButton.setOnClickListener {
            val action = GameSizeFragmentDirections.actionGameSizeFragmentToBoardFragment(
                BoardSize.REGULAR
            )
            view.findNavController().navigate(action)
        }

        extendedBoardButton.setOnClickListener {
            val action = GameSizeFragmentDirections.actionGameSizeFragmentToBoardFragment(
                BoardSize.EXTENDED
            )
            view.findNavController().navigate(action)
        }
    }
}