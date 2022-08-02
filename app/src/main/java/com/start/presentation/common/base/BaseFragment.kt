package com.start.presentation.common.base

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.start.presentation.extension.*

abstract class BaseFragment : Fragment() {

    val fragment: BaseFragment get() = this

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyInsets(view)
    }

    open fun onCatchBackPressed(): Boolean = false

    fun backPress() = requireActivity().onBackPressed()

    open fun onBaseActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean = false

    open fun applyInsets(view: View) {
        view.doOnApplyWindowInsets { v, insets ->
            v.setPadding(
                insets.maxInsetLeft,
                insets.maxInsetTop,
                insets.maxInsetRight,
                insets.maxInsetBottom
            )
            WindowInsetsCompat.CONSUMED
        }
    }

}