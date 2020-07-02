package com.lambda.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class TabFragment : Fragment() {
    private lateinit var mItemContainer: LinearLayoutCompat
    private var mItemCount = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val tabView = inflater.inflate(R.layout.fragment_tab, container, false)
        tabView.findViewById<TextView>(R.id.title).text = arguments!!.getString("title")
        mItemContainer = tabView as LinearLayoutCompat
        return tabView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        EventBus.getDefault().register(this)
    }

    override fun onDestroyView() {
        EventBus.getDefault().unregister(this)
        super.onDestroyView()
    }

    @Subscribe(
        threadMode = ThreadMode.MAIN,
        sticky = true
    )
    fun onEvent(event: String) {
        append(mItemContainer, "$event - ${mItemCount++}")
    }
}