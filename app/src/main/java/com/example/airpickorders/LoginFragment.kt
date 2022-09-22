package com.example.airpickorders

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.airpickorders.databinding.FragmentLoginBinding
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    // This property only valid between onCreateView and onDestroyView
    private val binding get() = _binding!!

    private val viewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewStateObserver = Observer<User> { user ->
//        }
//        viewModel.user.observe(viewLifecycleOwner, viewStateObserver)

        binding.loginButton.setOnClickListener{

            val user = User(binding.usernameText.text.toString(),binding.passwordText.text.toString())
            val login = viewModel.login(user)
            if(login) Toast.makeText(context,"successfully logged in",Toast.LENGTH_SHORT).show() else Toast.makeText(context,"logged out",Toast.LENGTH_SHORT).show()

        }
        binding.registerButton.setOnClickListener{

            val user = User(binding.usernameText.text.toString(),binding.passwordText.text.toString())
            viewModel.register(user)
            Toast.makeText(context,"successfully register in",Toast.LENGTH_SHORT).show()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}