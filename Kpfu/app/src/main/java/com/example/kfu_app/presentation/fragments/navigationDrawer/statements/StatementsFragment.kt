package com.example.kfu_app.presentation.fragments.navigationDrawer.statements

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.kfu_app.ApplicationDelegate
import com.example.kfu_app.data.repositories.FirebaseStatementsRepository
import com.example.kfu_app.databinding.FragmentStatementsBinding
import com.example.kfu_app.utils.clickSound
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

class StatementsFragment : Fragment(){

    @Inject
    lateinit var firebaseStatementsRepository: FirebaseStatementsRepository

    private var _binding: FragmentStatementsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ApplicationDelegate.getScreenComponent().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatementsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnConfirm.setOnClickListener {

        if ((binding.rbTreb.isChecked || binding.rbPf.isChecked) && !binding.etFio.text.isEmpty() &&
            !binding.etEmail.text.isEmpty() && !binding.etInstitute.text.isEmpty() && binding.cbAgreement.isChecked){
            val type: String

            if (binding.rbPf.isChecked){
                type = "Пенсионный фонд"
            } else type = "По месту требования"

            val statement = StatementItem(UUID.randomUUID().toString(), binding.etFio.text.toString(), binding.etInstitute.text.toString(), binding.etEmail.text.toString(),type)
            lifecycleScope.launch{
            firebaseStatementsRepository.setStatement(statement)
            }
            val toast = Toast.makeText(requireContext(),"Заявление отправлено", Toast.LENGTH_SHORT)
            toast.show().also {
                requireContext().clickSound()
            }
        }

       else{
            val toast = Toast.makeText(requireContext(),"Пожалуйста, заполните все поля", Toast.LENGTH_SHORT)
            toast.show().also {
                requireContext().clickSound()
            }
       }

       }
        binding.ivBack.setOnClickListener {
            findNavController().popBackStack().also {
                requireContext().clickSound()
            }
        }

    }
}