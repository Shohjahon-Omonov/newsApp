package uz.gita.mr_smart.newsexamapp

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.PointerIcon
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton
import uz.gita.mr_smart.newsexamapp.databinding.ActivityMain2Binding
import uz.gita.mr_smart.newsexamapp.myutill.logger
import kotlin.math.sqrt

class MainActivity2 : AppCompatActivity() {
    private val binding by viewBinding<ActivityMain2Binding>()
    private var isClick = -1
    private var delta = 0
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        binding.apply {
            mainButton.setOnClickListener {
                it.isClickable = false
                button1.isClickable = false
                button2.isClickable = false
                button3.isClickable = false
                delta = (mainButton.height - button1.height) / 2 + button1.height
                button3.animate()
                    .setDuration(1000)
                    .translationYBy(isClick * (it.height + 50).toFloat())
                    .start()

                "$delta".logger()

                button2.animate()
                    .setDuration(1000 + 850)
                    .translationYBy(isClick * (sqrt(((it.height + 50) * (it.height + 50)).toFloat() / 2)).toFloat())
                    .translationXBy(isClick * (sqrt(((it.height + 50) * (it.height + 50)).toFloat() / 2)).toFloat())
                    .start()

                button1.animate()
                    .setDuration(1000 + 850 * 2)
//                    .translationYBy(isClick * (it.height + delta * 2).toFloat())
                    .translationXBy(isClick * (it.height + 50).toFloat())
                    .start()

                it.animate()
                    .setDuration(1000 + 850 * 2)
                    .rotation(it.rotation + 45f * isClick)
                    .withEndAction {
                        it.isClickable = true
                        button3.isClickable = true
                        button2.isClickable = true
                        button1.isClickable = true
                    }
                    .start()

                isClick *= -1
            }
        }

    }
}