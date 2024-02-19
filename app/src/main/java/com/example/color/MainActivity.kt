package com.example.color


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.core.graphics.toColorInt
import com.example.color.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.InputColor.setOnClickListener {
            changeConstraints(R.id.InputColor)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor, listTextView(),binding.Color)
        }
        binding.InputColor2.setOnClickListener {
            changeConstraints(R.id.InputColor2)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor2, listTextView(),binding.Color2)
        }
        binding.InputColor3.setOnClickListener {
            changeConstraints(R.id.InputColor3)
            binding.ColorList.visibility = View.VISIBLE
            setColorInInputColor(binding.InputColor3, listTextView(),binding.Color3)
        }
        binding.toggleButton2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.Color.text = changeLang(getColorIdInTextView(binding.InputColor).toColorInt(),true)
                binding.Color2.text = changeLang(getColorIdInTextView(binding.InputColor2).toColorInt(),true)
                binding.Color3.text = changeLang(getColorIdInTextView(binding.InputColor3).toColorInt(),true)
            } else {
                binding.Color.text = changeLang(getColorIdInTextView(binding.InputColor).toColorInt())
                binding.Color2.text = changeLang(getColorIdInTextView(binding.InputColor2).toColorInt())
                binding.Color3.text = changeLang(getColorIdInTextView(binding.InputColor3).toColorInt())

            }
        }

    }

    private fun setColorInInputColor(
        InputColorTextView: TextView,
        listTextView: List<TextView>,
        setTextView: TextView
    ) {
        for (textViewColor in listTextView) {
            textViewColor.setOnClickListener {
                setTextBackground(InputColorTextView, getColorIdInTextView(textViewColor))
                if(binding.toggleButton2.isChecked) {
                    setTextView.text = changeLang(getColorIdInTextView(textViewColor).toColorInt(),true)
                }else {
                    setTextView.text = changeLang(getColorIdInTextView(textViewColor).toColorInt())
                }
                result()
            }
        }
    }

    //Получаем цвет заднего фона из слоя
    private fun getColorIdInTextView(text: TextView): String {
        val cd = text.background as ColorDrawable
        var result = Integer.toHexString(cd.color).drop(2)
        return "#$result"
    }

    // устанавливаем в окно выбраный цвет и прячем окно с выбором цвета
    private fun setTextBackground(text: TextView, color: String) {
        text.setBackgroundColor(color.toColorInt())
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
            getColorIdInTextView(binding.InputColor).toColorInt(),
            getColorIdInTextView(binding.InputColor2).toColorInt(), 0.5f
        )
        val result = ColorUtils.blendARGB(
            sumColor,
            getColorIdInTextView(binding.InputColor3).toColorInt(),
            0.3f
        )
        binding.ResultColorView.setBackgroundColor(result)
        binding.textView7.text = Integer.toHexString(result).drop(2)
    }

    private fun changeLang(color: Int, langCheck:Boolean = false):String {
        val mapEng = HashMap<Int, String>()
        mapEng[Color.BLUE] = "Blue"
        mapEng[Color.BLACK] = "Black"
        mapEng[Color.WHITE] = "White"
        mapEng[Color.YELLOW] = "Yellow"
        mapEng[Color.GREEN] = "Green"
        mapEng[Color.CYAN] = "Cyan"
        mapEng[Color.MAGENTA] = "Magenta"
        mapEng[Color.RED] = "Red"
        mapEng[Color.GRAY] = "Gray"
        mapEng[Color.DKGRAY] = "Dark Grey"
        mapEng[Color.LTGRAY] = "Light Grey"

        val map = HashMap<Int, String>()
        map[Color.BLUE] = "Синий"
        map[Color.BLACK] = "Черный"
        map[Color.WHITE] = "Белый"
        map[Color.YELLOW] = "Желтый"
        map[Color.GREEN] = "Зеленый"
        map[Color.CYAN] = "Голубой"
        map[Color.MAGENTA] = "Розовый"
        map[Color.RED] = "красный"
        map[Color.GRAY] = "Серый"
        map[Color.DKGRAY] = "Темно серый"
        map[Color.LTGRAY] = "Светло серый"
        return if(langCheck){
            mapEng.getValue(color)
        }else{
            map.getValue(color)
        }
    }
}



