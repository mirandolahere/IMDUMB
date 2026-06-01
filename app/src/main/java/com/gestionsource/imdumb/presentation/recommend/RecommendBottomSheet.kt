package com.gestionsource.imdumb.presentation.recommend

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gestionsource.imdumb.databinding.BottomSheetRecommendBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar

class RecommendBottomSheet : BottomSheetDialogFragment() {

    private var _binding: BottomSheetRecommendBinding? = null
    private val binding get() = _binding!!

    private var movieDescription: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movieDescription = arguments?.getString(ARG_DESCRIPTION).orEmpty()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetRecommendBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.tvMovieDescription.text =
            Html.fromHtml(movieDescription, Html.FROM_HTML_MODE_LEGACY)

        binding.btnConfirm.setOnClickListener {
            Toast.makeText(
                requireContext(),
                "Recomendación enviada correctamente",
                Toast.LENGTH_SHORT
            ).show()

            dismiss()

        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val ARG_DESCRIPTION = "description"

        fun newInstance(description: String): RecommendBottomSheet {
            return RecommendBottomSheet().apply {
                arguments = Bundle().apply {
                    putString(ARG_DESCRIPTION, description)
                }
            }
        }
    }
}