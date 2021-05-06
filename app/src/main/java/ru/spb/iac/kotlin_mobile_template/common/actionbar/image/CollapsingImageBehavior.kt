package ru.spb.iac.kotlin_mobile_template.common.actionbar.image

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import ru.spb.iac.kotlin_mobile_template.R

/**
 * Created by nixbyte on 17,Декабрь,2019
 */

class CollapsingImageBehavior(context: Context, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View?>() {
    private var mTargetId = 0
    private var mView: IntArray? = null
    private lateinit var mTarget: IntArray

    init {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView)
            mTargetId = a.getResourceId(R.styleable.CircleImageView_civ_collapsedTarget, 0)
            a.recycle()
        }
        check(mTargetId != 0) { "collapsedTarget attribute not specified on view for behavior" }
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val appBarLayout = dependency as AppBarLayout
        val range = appBarLayout.totalScrollRange
        val factor = -dependency.getY() / range
        setup(parent, child)
        val left = mView!![X] + (factor * (mTarget[X] - mView!![X])).toInt()
        val top = mView!![Y] + (factor * (mTarget[Y] - mView!![Y])).toInt()
        val width =
            mView!![WIDTH] + (factor * (mTarget[WIDTH] - mView!![WIDTH])).toInt()
        val height =
            mView!![HEIGHT] + (factor * (mTarget[HEIGHT] - mView!![HEIGHT])).toInt()
        child.x = left.toFloat()
        child.y = top.toFloat()
        if (width == child.width) {
            return false
        }
        val lp =
            child.layoutParams as CoordinatorLayout.LayoutParams
        lp.width = width
        lp.height = height
        child.layoutParams = lp
        return true
    }

    private fun setup(parent: CoordinatorLayout, child: View) {
        if (mView != null) return
        mView = IntArray(4)
        mTarget = IntArray(4)
        mView!![X] = child.x.toInt()
        mView!![Y] = child.y.toInt()
        mView!![WIDTH] = child.width
        mView!![HEIGHT] = child.height
        val target = parent.findViewById<View>(mTargetId)
            ?: throw IllegalStateException("target view not found")
        mTarget[WIDTH] += target.width
        mTarget[HEIGHT] += target.height
        var view = target
        while (view !== parent) {
            mTarget[X] += view.x.toInt()
            mTarget[Y] += view.y.toInt()
            view = view.parent as View
        }
    }

    companion object {
        private val TAG = CollapsingImageBehavior::class.java.simpleName
        private const val X = 0
        private const val Y = 1
        private const val WIDTH = 2
        private const val HEIGHT = 3
    }
}