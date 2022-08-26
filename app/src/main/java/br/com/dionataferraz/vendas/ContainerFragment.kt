package br.com.dionataferraz.vendas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.dionataferraz.vendas.databinding.FragmentContainerBinding


private const val ARG_PARAM1 = "param1"

class ContainerFragment : Fragment() {

    lateinit var binding: FragmentContainerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContainerBinding.inflate(inflater)
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
            ContainerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                }
            }
    }
}