package kr.ac.kumoh.ce.s20170724.gunplaapplication

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kr.ac.kumoh.ce.s20170724.gunplaapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        const val QUEUE_TAG = "VolleyRequest"
    }
    private lateinit var mQueue: RequestQueue

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQueue = VolleyRequest
            .getInstance(this.applicationContext)
            .requestQueue

        requestMechanic()
    }

    override fun onStop() {
        super.onStop()
        mQueue.cancelAll(QUEUE_TAG)
    }

    private fun requestMechanic() {
        val url = "https://express-for-volley-aujsu.run.goorm.io/gunpladb/mechanic"

        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                binding.result.text = it.toString()
            },
            {
                Toast.makeText(this, it.toString(), Toast.LENGTH_LONG).show()
                binding.result.text = it.toString()
            }
        )

        request.tag = QUEUE_TAG
        mQueue.add(request)
    }
}