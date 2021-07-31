package com.example.catan

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs

private const val TAG = "BoardFragment"

class BoardFragment : Fragment() {
    private val args: BoardFragmentArgs by navArgs()
    private val viewModel by viewModels<BoardFragmentViewModel>()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel.initialize(args.size)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.newBoard()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_board, container, false)
        val currentHexTextView = view.findViewById<TextView>(R.id.current_hex)
        val prevHexButton = view.findViewById<Button>(R.id.previous_hex)
        val nextHexButton = view.findViewById<Button>(R.id.next_hex)

        currentHexTextView.text = viewModel.currentHex().toString()
        prevHexButton.setOnClickListener {
            currentHexTextView.text = viewModel.prevHex().toString()
        }
        nextHexButton.setOnClickListener {
            currentHexTextView.text = viewModel.nextHex().toString()
        }
        return view
    }
}
