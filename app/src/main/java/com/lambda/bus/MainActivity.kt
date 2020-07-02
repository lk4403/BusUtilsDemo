package com.lambda.bus

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.FragmentUtils
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class MainActivity : AppCompatActivity() {
    private lateinit var mItemContainer: LinearLayoutCompat
    private var mCurrentShowFragment: Fragment? = null
    private var mTabNum = 0
    private var mItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mItemContainer = findViewById(R.id.main)

        val listener: (View) -> Unit = { view ->
            val f = if (view.tag == null) {
                val tag = "No.${mTabNum++}"
                val fragment = TabFragment()
                fragment.arguments = Bundle().apply { putString("title", tag); }
                view.tag = fragment
                FragmentUtils.add(
                    supportFragmentManager,
                    fragment,
                    R.id.container,
                    tag
                )
                fragment
            } else {
                FragmentUtils.show(view.tag as Fragment)
                view.tag as Fragment
            }

            if (mCurrentShowFragment != null) FragmentUtils.hide(mCurrentShowFragment!!)
            mCurrentShowFragment = f
        }
        findViewById<View>(R.id.b1).setOnClickListener(listener)
        findViewById<View>(R.id.b2).setOnClickListener(listener)
        findViewById<View>(R.id.b3).setOnClickListener(listener)

        findViewById<View>(R.id.b1).performClick()

        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    @Subscribe(
        threadMode = ThreadMode.MAIN,
        sticky = true
    )
    fun onEvent(event: String) {
        append(mItemContainer, "$event - ${mItemCount++}")
    }
}
