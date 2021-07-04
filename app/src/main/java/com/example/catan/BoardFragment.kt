package com.example.catan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs

private const val TAG = "BoardFragment"

class BoardFragment : Fragment() {
    private val args: BoardFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board, container, false)
        val boardSizeTextView = view.findViewById<TextView>(R.id.board_size)
        boardSizeTextView.text = if (args.size == BoardSize.EXTENDED) "Extended" else "Regular"
        return view
    }
}

enum class BoardSize {
    REGULAR,
    EXTENDED
}