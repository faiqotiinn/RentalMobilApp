package com.example.rentalmobilapp.ui

import android.content.Context
import android.location.Address
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.rentalmobilapp.R
import com.example.rentalmobilapp.application.CarApp
import com.example.rentalmobilapp.databinding.FragmentSecondBinding
import com.example.rentalmobilapp.model.Car
import kotlin.math.log

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!
    private lateinit var applicationContext: Context
    private val carViewModel: CarViewModel by viewModels {
        CarViewModelFactory((applicationContext as CarApp).repository)
    }
    private val args : SecondFragmentArgs by navArgs()
    private var car : Car? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        applicationContext = requireContext().applicationContext
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        car = args.car
        if (car != null){
            binding.deleteButton.visibility = View.VISIBLE
            binding.saveButton.text = ("Ubah")
            binding.nameEditText.setText(car?.name)
            binding.addressEditText.setText(car?.address)
            binding.typecarEditText.setText(car?.typecar)
        }
        val name = binding.nameEditText.text
        val address = binding.addressEditText.text
        val typecar = binding.typecarEditText.text

        binding.saveButton.setOnClickListener {
            if (name.isEmpty()){
                Toast.makeText(context, "nama tidak boleh kosong", Toast.LENGTH_SHORT).show()

            } else if (address.isEmpty()) {
                Toast.makeText(context, "alamat tidak boleh kosong", Toast.LENGTH_SHORT).show()
            } else if (typecar.isEmpty()) {
                    Toast.makeText(context, "jenis mobil tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }

            else {
                if (car == null) {
                    val car = Car(0, name.toString(), address.toString(), typecar.toString())
                    carViewModel.insert(car)
                } else {
                    val car =
                        Car(car?.id!!, name.toString(), address.toString(), typecar.toString())
                    carViewModel.update(car)
                }
            }

                findNavController().popBackStack()//untuk dismiss halaman ini

        }
        binding.deleteButton.setOnClickListener {
            car?.let {carViewModel.delete(it) }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}