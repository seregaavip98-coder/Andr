package com.example.mycarLauncher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Кнопка Навигация (Яндекс.Карты)
        findViewById<Button>(R.id.btnNav).setOnClickListener {
            launchAppByPackage("ru.yandex.yandexnavi") // Замените, если у вас другой навигатор
        }

        // 2. Кнопка Музыка
        findViewById<Button>(R.id.btnMusic).setOnClickListener {
            // Можно запустить стандартный плеер или конкретное приложение
            launchAppByPackage("com.google.android.music") 
        }

        // 3. Кнопка Радио
        findViewById<Button>(R.id.btnRadio).setOnClickListener {
            launchAppByPackage("com.android.fmradio") // Стандартное радио Android
        }

        // 4. Кнопка Телефон
        findViewById<Button>(R.id.btnPhone).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            startActivity(intent)
        }

        // 5. Кнопка Настройки
        findViewById<Button>(R.id.btnSettings).setOnClickListener {
            startActivity(Intent(android.provider.Settings.ACTION_SETTINGS))
        }

        // 6. Кнопка Все приложения (открывает стандартный drawer)
        findViewById<Button>(R.id.btnApps).setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_APP_MARKET) // Или используйте стандартный лаунчер
            // Если не сработает, можно вызвать: startActivity(Intent(Intent.ACTION_ALL_APPS))
        }
    }

    // Вспомогательная функция для запуска по пакету
    private fun launchAppByPackage(packageName: String) {
        try {
            val intent = packageManager.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                startActivity(intent)
            } else {
                // Если приложение не найдено, можно открыть Google Play
                val marketIntent = Intent(Intent.ACTION_VIEW)
                marketIntent.data = android.net.Uri.parse("market://details?id=$packageName")
                startActivity(marketIntent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}