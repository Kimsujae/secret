package kr.ac.kumoh.s20160250.secret

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.widget.addTextChangedListener

class DiaryActivity: AppCompatActivity() {

    private val diaryEditText: EditText by lazy {
        findViewById<EditText>(R.id.diaryEditText)
    }
    private val handler =android.os.Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        val diaryEditText = findViewById<EditText>(R.id.diaryEditText)

        val detailPreferences =getSharedPreferences("diary", Context.MODE_PRIVATE)
        diaryEditText.setText(detailPreferences.getString("detail",""))

        val runable = Runnable{
            getSharedPreferences("diary",Context.MODE_PRIVATE).edit {
                putString("detail",diaryEditText.text.toString())
            }
            Log.d("DiaryActivity","save!!${diaryEditText.text.toString()}")
        }
        diaryEditText.addTextChangedListener {
            Log.d("DiaryActivity","TextChange :: $it")
            handler.removeCallbacks(runable)
            handler.postDelayed(runable,500)
        }
    }
}