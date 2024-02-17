package com.example.color


import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import com.example.color.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var color = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.InputColor.setOnClickListener {
            changeConstraints(R.id.InputColor)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor, listTextView(), binding.Color)
        }
        binding.InputColor2.setOnClickListener {
            changeConstraints(R.id.InputColor2)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor2, listTextView(), binding.Color2)
        }
        binding.InputColor3.setOnClickListener {
            changeConstraints(R.id.InputColor3)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor3, listTextView(), binding.Color3)
        }
//        binding.toggleButton2.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//
//                }
//            } else {
//                colorName(binding.Color)
//            }
//        }

    }

    private fun setColorInInputColor(
        InputColorTextView: TextView,
        listTextView: List<TextView>,
        InputColorName: TextView
    ) {
        for (textViewColor in listTextView) {
            textViewColor.setOnClickListener {
                setTextBackground(InputColorTextView, getColorIdInTextView(textViewColor))
                color = getColorIdInTextView(textViewColor).toString()
//                colorName(InputColorName)
                result()
            }
        }
    }

    //Получаем цвет заднего фона из слоя
    private fun getColorIdInTextView(text: TextView): Int {
        val cd = text.background as ColorDrawable
        return cd.color
    }

    // устанавливаем в окно выбраный цвет и прячем окно с выбором цвета
    private fun setTextBackground(text: TextView, color: Int) {
        text.setBackgroundColor(color)
        binding.ColorList.visibility = View.INVISIBLE
    }

    //Создаем список из слоёв к которым привязан цвет
    private fun listTextView(): MutableList<TextView> {
        var textViewList = mutableListOf<TextView>()
        textViewList.add(binding.blackView)
        textViewList.add(binding.textView15)
        textViewList.add(binding.textView16)
        textViewList.add(binding.textView17)
        textViewList.add(binding.textView18)
        textViewList.add(binding.textView19)
        textViewList.add(binding.textView20)
        textViewList.add(binding.textView21)
        textViewList.add(binding.textView22)
        textViewList.add(binding.textView23)
        textViewList.add(binding.textView24)
        return textViewList
    }

    private fun changeConstraints(value: Int) { //Меняем положение ScrollView относительно окну
        // InputColor
        val view = binding.ColorList
        val params = view.layoutParams as ConstraintLayout.LayoutParams
        params.topToTop = value
        params.bottomToBottom = value
        params.startToEnd = value
        params.marginEnd = 8
        view.requestLayout()

    }

    private fun result() {   // Мешает цвета
        val sumColor = ColorUtils.blendARGB(
            getColorIdInTextView(binding.InputColor),
            getColorIdInTextView(binding.InputColor2), 0.5f
        )
        val result = ColorUtils.blendARGB(sumColor, getColorIdInTextView(binding.InputColor3), 0.3f)
        binding.ResultColorView.setBackgroundColor(result)
    }

//    private fun colorName(text: TextView) {
//        when (color) {
//            "-16777216" -> text.text = "черный"
//            "-8355712" -> text.text = "Темно-Серый"
//            "-5658199" -> text.text = "Серый"
//            "-28948993" -> text.text = "Светло-Серый"
//            "-1" -> text.text = "Белый"
//            "-65536" -> text.text = "Красный"
//            "-16711936" -> text.text = "Зеленый"
//            "-16776961" -> text.text = "Синий"
//            "-256" -> text.text = "Желтый"
//            "-16711681" -> text.text = "Голубой"
//            "-65281" -> text.text = "Розовый"
//        }
}



