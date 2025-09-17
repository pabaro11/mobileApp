package com.example.healthagent

import android.os.Bundle
import android.widget.*
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val timeInput = findViewById<EditText>(R.id.timeInput)
        val spinner = findViewById<Spinner>(R.id.bodyPartSpinner)
        val recommendBtn = findViewById<Button>(R.id.recommendBtn)
        val completeBtn = findViewById<Button>(R.id.completeBtn)
        val resultText = findViewById<TextView>(R.id.resultText)

        val bodyParts = arrayOf("등", "가슴", "어깨", "하체", "팔", "유산소")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bodyParts)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 부위별 루틴 (10분당 1종류)
        val routines = mapOf(
            "등" to mapOf(
                10 to "풀업 1세트",
                20 to "풀업 2세트\n바벨로우 2세트",
                30 to "풀업 3세트\n바벨로우 3세트\n페이스풀 2세트"
            ),
            "가슴" to mapOf(
                10 to "푸시업 1세트",
                20 to "푸시업 2세트\n덤벨프레스 2세트",
                30 to "푸시업 3세트\n덤벨프레스 3세트\n플라이 2세트"
            ),
            "어깨" to mapOf(
                10 to "덤벨 숄더프레스 1세트",
                20 to "덤벨 숄더프레스 2세트\n측면 레터럴 2세트",
                30 to "덤벨 숄더프레스 3세트\n측면 레터럴 3세트\n업라이트로우 2세트"
            ),
            "하체" to mapOf(
                10 to "스쿼트 1세트",
                20 to "스쿼트 2세트\n런지 2세트",
                30 to "스쿼트 3세트\n런지 3세트\n레그프레스 2세트"
            ),
            "팔" to mapOf(
                10 to "이두컬 1세트",
                20 to "이두컬 2세트\n트라이셉스 익스텐션 2세트",
                30 to "이두컬 3세트\n트라이셉스 익스텐션 3세트\n덤벨 킥백 2세트"
            ),
            "유산소" to mapOf(
                10 to "러닝머신 10분",
                20 to "러닝머신 20분",
                30 to "러닝머신 20분\n싸이클 10분"
            )
        )

        recommendBtn.setOnClickListener {
            val time = timeInput.text.toString().toIntOrNull()
            val selectedPart = spinner.selectedItem.toString()

            if (time == null) {
                resultText.text = "운동 시간을 숫자로 입력해주세요."
                return@setOnClickListener
            }

            // 10분 단위로 루틴 선택
            val roundedTime = when {
                time <= 10 -> 10
                time <= 20 -> 20
                else -> 30
            }

            val routineForPart = routines[selectedPart]?.get(roundedTime)
            resultText.text = routineForPart ?: "해당 시간에 맞는 루틴이 없습니다."
        }

        completeBtn.setOnClickListener {
            val current = resultText.text
            if (current.isNotEmpty()) {
                resultText.text = "$current\n\n✅ 운동 완료 기록 저장됨!"
            }
        }
    }
}
