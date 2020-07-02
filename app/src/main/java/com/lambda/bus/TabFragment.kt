package com.lambda.bus

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.BusUtils

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
        BusUtils.register(this)
    }

    override fun onDestroyView() {
        BusUtils.unregister(this)
        super.onDestroyView()
    }

    @BusUtils.Bus(
        tag = TAG,
        threadMode = BusUtils.ThreadMode.MAIN,
        sticky = true
    )
    fun onEvent(event: String) {
        append(mItemContainer, "$event - ${mItemCount++}")
    }
}