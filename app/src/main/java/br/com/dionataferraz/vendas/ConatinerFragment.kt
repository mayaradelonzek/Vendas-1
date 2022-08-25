package br.com.dionataferraz.vendas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.dionataferraz.vendas.databinding.FragmentConatinerBinding

private const val ARG_PARAM1 = "param1"

class ConatinerFragment : Fragment() {
    lateinit var binding: FragmentConatinerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentConatinerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            binding.textView.text = getString(ARG_PARAM1)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String) =
            ConatinerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}