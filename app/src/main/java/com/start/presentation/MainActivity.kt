package com.start.presentation

import android.os.Bundle
import androidx.core.view.WindowCompat
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.start.databinding.ActivityMainBinding
import com.start.presentation.common.base.BaseActivity
import com.start.presentation.worker.AnswerWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        WorkManager
            .getInstance(this)
            .enqueueUniquePeriodicWork(
                "updates", ExistingPeriodicWorkPolicy.REPLACE,
                PeriodicWorkRequestBuilder<AnswerWorker>(
                    repeatInterval = 15,
                    repeatIntervalTimeUnit = TimeUnit.MINUTES
                ).build()
            )
    }

}