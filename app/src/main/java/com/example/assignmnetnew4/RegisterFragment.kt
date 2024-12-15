package com.example.assignmnetnew4
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment() {

    private var listener: EventInterface? = null

    interface EventInterface {
        fun onLoginPressed()

    }
    public var credentialsManager: CredentialsManager? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        require(context is EventInterface) {
            "Activity must implement this fragment's eventsinterface"
        }
        listener = context

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.account_sign_up, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.LogInText).setOnClickListener(){
            listener?.onLoginPressed()
            Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show()
        }
    }


}


