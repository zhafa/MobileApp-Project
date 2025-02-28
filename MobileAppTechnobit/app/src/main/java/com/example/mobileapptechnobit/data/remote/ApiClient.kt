package com.example.mobileapptechnobit.data.remote
import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

object ApiClient {
    private lateinit var requestQueue: RequestQueue
    private const val urlLogin = "https://app.arunikaprawira.com/api/login"

    fun initialize(context: Context) {
        requestQueue = Volley.newRequestQueue(context.applicationContext)
    }

    fun login(email: String, password: String, onSuccess: (JSONObject) -> Unit, onError: (String) -> Unit) {
        Log.d("ApiClient", "Requesting URL: $urlLogin")

        val jsonBody = JSONObject()
        try {
            jsonBody.put("email", email)
            jsonBody.put("password", password)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        val jsonObjectRequest = object : JsonObjectRequest(
            Request.Method.POST, urlLogin, jsonBody,
            Response.Listener { response ->
                Log.d("ApiClient", "Response: $response")
                try {
                    onSuccess(response)
                } catch (e: JSONException) {
                    onError("Error Parsing JSON")
                }
            },
            Response.ErrorListener { error ->
                Log.e("ApiClient", "Error: ${error.message}")
                onError(error.message ?: "Unknown Error")
            }
        ) {
            override fun getHeaders(): Map<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        requestQueue.add(jsonObjectRequest)
    }
}