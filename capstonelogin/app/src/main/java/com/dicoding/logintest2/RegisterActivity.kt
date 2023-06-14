package com.dicoding.logintest2
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {
    private lateinit var editTextUsername: EditText
    private lateinit var editTextFullname: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var buttonRegister: Button
    private lateinit var apiService: ApiService
    private lateinit var buttonLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inisialisasi tampilan
        editTextUsername = findViewById(R.id.editTextUsername)
        editTextFullname = findViewById(R.id.editTextFullName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonRegister = findViewById(R.id.buttonRegister)
        buttonLogin = findViewById(R.id.buttonLogin)


        // Inisialisasi Retrofit dan ApiService
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api-login-register-guktqld2iq-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)

        // Tambahkan listener untuk tombol Register
        buttonRegister.setOnClickListener {
            val username = editTextUsername.text.toString()
            val fullName = editTextFullname.text.toString()
            val email = editTextEmail.text.toString()
            val password = editTextPassword.text.toString()

            registerUser(username, fullName, email, password)
        }

        buttonLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun registerUser(username: String, fullName: String, email: String, password: String) {
        val user = User(username, fullName, email, password)
        val call = apiService.registerUser(user)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    val intent = Intent(this@RegisterActivity, MainActivity::class.java).apply {
                        putExtra("username", username)
                        putExtra("fullname", fullName)
                        putExtra("password", password)
                        putExtra("email", email)
                    }
                    startActivity(intent)
                    finish()
                } else {
                    // Registrasi gagal
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                // Kegagalan panggilan API
            }
        })
    }

}
