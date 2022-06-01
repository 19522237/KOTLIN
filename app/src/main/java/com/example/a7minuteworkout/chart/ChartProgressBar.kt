package com.example.a7minuteworkout.chart

import android.content.Context
import android.content.res.Resources
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.graphics.drawable.ScaleDrawable
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.marginRight
import com.example.a7minuteworkout.R
import java.util.*

class ChartProgressBar : FrameLayout {
    private var mMaxValue = 0f
    private var mBarWidth = 0
    private var mBarHeight = 0
    private var mEmptyColor = 0
    private var mProgressColor = 0
    private var mProgressClickColor = 0
    private var mBarRadius = 0
    private var mContext: Context
    private var mPinTextColor = 0
    private var mPinBackgroundColor = 0
    private var mPinPaddingTop = 0
    private var mPinPaddingBottom = 0
    private var mPinPaddingEnd = 0
    private var mPinPaddingStart = 0
    private var mBarTitleColor = 0
    private var mBarTitleTxtSize = 0f
    private var mPinTxtSize = 0f
    private var mMetrics: DisplayMetrics
    private var oldFrameLayout: FrameLayout? = null
    private var isBarCanBeClick = false
    var data: ArrayList<BarData>? = null
        private set
    private var isOldBarClicked = false
    var isBarsEmpty = false
        private set
    private var mPinMarginTop = 0
    private var mPinMarginBottom = 0
    private var mPinMarginEnd = 0
    private var mPinMarginStart = 0
    private var mPinDrawable = 0
    private val pins = ArrayList<TextView>()
    private var mBarTitleMarginTop = 0
    private var mBarTitleSelectedColor = 0
    private var mProgressDisableColor = 0
    private var listener: OnBarClickedListener? = null
    private var mBarCanBeToggle = false

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
        setAttrs(attrs, 0)
        mMetrics = Resources.getSystem().displayMetrics
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        mContext = context
        setAttrs(attrs, defStyleAttr)
        mMetrics = Resources.getSystem().displayMetrics
    }

    private fun setAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray =
            mContext.obtainStyledAttributes(attrs, R.styleable.ChartProgressBar, defStyleAttr, 0)
        mBarWidth = typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdBarWidth, 0)
        mBarHeight = typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdBarHeight, 0)
        mBarRadius = typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdBarRadius, 0)
        mEmptyColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdEmptyColor,
            ContextCompat.getColor(mContext, R.color.empty)
        )
        mProgressColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdProgressColor,
            ContextCompat.getColor(mContext, R.color.progress)
        )
        mProgressClickColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdProgressClickColor,
            ContextCompat.getColor(mContext, R.color.progress_click)
        )
        mProgressDisableColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdProgressDisableColor,
            ContextCompat.getColor(mContext, android.R.color.darker_gray)
        )
        mBarTitleSelectedColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdBarTitleSelectedColor,
            ContextCompat.getColor(mContext, R.color.progress_click)
        )
        mPinTextColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdPinTextColor,
            ContextCompat.getColor(mContext, R.color.pin_text)
        )
        mPinBackgroundColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdPinBackgroundColor,
            ContextCompat.getColor(mContext, R.color.pin_background)
        )
        mPinPaddingTop =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinPaddingTop, 3)
        mPinPaddingBottom =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinPaddingBottom, 3)
        mPinPaddingEnd =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinPaddingEnd, 3)
        mPinPaddingStart =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinPaddingStart, 3)
        isBarCanBeClick = typedArray.getBoolean(R.styleable.ChartProgressBar_hdBarCanBeClick, false)
        mBarTitleColor = typedArray.getResourceId(
            R.styleable.ChartProgressBar_hdBarTitleColor,
            ContextCompat.getColor(mContext, R.color.bar_title_color)
        )
        mMaxValue = typedArray.getFloat(R.styleable.ChartProgressBar_hdMaxValue, 1f)
        mBarTitleTxtSize =
            typedArray.getDimension(R.styleable.ChartProgressBar_hdBarTitleTxtSize, 14f)
        mPinTxtSize = typedArray.getDimension(R.styleable.ChartProgressBar_hdPinTxtSize, 6f)
        mPinMarginTop =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinMarginTop, 0)
        mPinMarginBottom =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinMarginBottom, 0)
        mPinMarginEnd =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinMarginEnd, 0)
        mPinMarginStart =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdPinMarginStart, 0)
        mBarTitleMarginTop =
            typedArray.getDimensionPixelSize(R.styleable.ChartProgressBar_hdBarTitleMarginTop, 0)
        mPinDrawable = typedArray.getResourceId(R.styleable.ChartProgressBar_hdPinDrawable, 0)
        mBarCanBeToggle =
            typedArray.getBoolean(R.styleable.ChartProgressBar_hdBarCanBeToggle, false)
        typedArray.recycle()
    }

    fun setDataList(dataList: ArrayList<BarData>?) {
        data = dataList
    }

    fun setOnBarClickedListener(listener: OnBarClickedListener?) {
        this.listener = listener
    }

    fun build() {
        removeAllViews()
        val linearLayout = LinearLayout(mContext)
        val params = LinearLayout.LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        val d = resources.getDrawable(mPinDrawable)
        var h = d.intrinsicHeight
        if (mPinMarginBottom != 0) h += mPinMarginBottom / 2
        linearLayout.setPadding(0, h, 0, 0)
        linearLayout.layoutParams = params
        addView(linearLayout)
        var i = 0
        for (data in data!!) {
            val barValue = (data.barValue * 100).toInt()
            val bar = getBar(data.barTitle, barValue, i)
            linearLayout.addView(bar)
            i++
        }
        viewTreeObserver.addOnGlobalLayoutListener(
            object : OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        viewTreeObserver
                            .removeOnGlobalLayoutListener(this)
                    } else {
                        viewTreeObserver
                            .removeGlobalOnLayoutListener(this)
                    }
                    setPins()
                }
            })
    }

    private fun getBar(title: String?, value: Int, index: Int): FrameLayout {
        val maxValue = (mMaxValue * 100).toInt()
        val linearLayout = LinearLayout(mContext)
        val params = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        params.gravity = Gravity.CENTER
        linearLayout.layoutParams = params
        linearLayout.orientation = LinearLayout.VERTICAL
        linearLayout.gravity = Gravity.CENTER

        //Adding bar
        val bar = Bar(mContext, null, android.R.attr.progressBarStyleHorizontal)
        bar.progress = value
        bar.visibility = VISIBLE
        bar.isIndeterminate = false
        bar.max = maxValue
        bar.progressDrawable = ContextCompat.getDrawable(mContext, R.drawable.progress_bar_shape)
        val progressParams = LayoutParams(
            mBarWidth,
            mBarHeight
        )
        progressParams.gravity = Gravity.CENTER
        bar.layoutParams = progressParams
        val anim = BarAnimation(bar, 0F, value.toFloat())
        anim.duration = 250
        bar.startAnimation(anim)
        val layerDrawable = bar.progressDrawable as LayerDrawable
        layerDrawable.mutate()
        val emptyLayer = layerDrawable.getDrawable(0) as GradientDrawable
        val scaleDrawable = layerDrawable.getDrawable(1) as ScaleDrawable
        emptyLayer.setColor(ContextCompat.getColor(mContext, mEmptyColor))
        emptyLayer.cornerRadius = mBarRadius.toFloat()
        val progressLayer = scaleDrawable.drawable as GradientDrawable?
        if (progressLayer != null) {
            progressLayer.setColor(ContextCompat.getColor(mContext, mProgressColor))
            progressLayer.cornerRadius = mBarRadius.toFloat()
        }
        linearLayout.addView(bar)

        //Adding txt below bar
        val txtBar = TextView(mContext)
        val txtParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        txtBar.textSize = getSP(mBarTitleTxtSize)
        txtBar.text = title
        txtBar.gravity = Gravity.CENTER
        txtBar.setTextColor(ContextCompat.getColor(mContext, mBarTitleColor))
        txtBar.setPadding(0, mBarTitleMarginTop, 0, 0)
        txtBar.layoutParams = txtParams
        linearLayout.addView(txtBar)
        val rootFrameLayout = FrameLayout(mContext)
        val rootParams = LinearLayout.LayoutParams(
            0,
            LayoutParams.MATCH_PARENT,
            1f
        )
        rootParams.gravity = Gravity.CENTER


        //rootParams.setMargins(0, h, 0, h);
        rootFrameLayout.layoutParams = rootParams


        //Adding bar + title
        rootFrameLayout.addView(linearLayout)
        if (isBarCanBeClick) rootFrameLayout.setOnClickListener(barClickListener)
        rootFrameLayout.tag = index
        return rootFrameLayout
    }

    private fun setPins() {
        pins.clear()
        val maxValue = (mMaxValue * 100).toInt()
        var childCount = childCount
        var linearLayout: LinearLayout? = null
        for (i in 0 until childCount) {
            val view = getChildAt(i)
            if (view is LinearLayout) {
                linearLayout = view
                break
            }
        }
        if (linearLayout != null) {
            childCount = linearLayout.childCount
            for (i in 0 until childCount) {
                val view = linearLayout.getChildAt(i)
                val data = data!![i]
                val value = (data.barValue * 100).toInt()
                val pinTxt = data.pinText
                val barFrame = view as FrameLayout
                val frameCount = barFrame.childCount
                for (j in 0 until frameCount) {
                    val v = barFrame.getChildAt(j)
                    if (v is LinearLayout) {
                        val count = v.childCount
                        for (k in 0 until count) {
                            if (v.getChildAt(k) is Bar) {
                                val bar = v.getChildAt(k) as Bar

                                // Adding value Txt when click on a bar
                                val pinTxtView = TextView(mContext)
                                val valueParams = LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                )
                                val pinDrawableId =
                                    if (mPinDrawable != 0) mPinDrawable else R.drawable.pin_shape
                                pinTxtView.setBackgroundResource(pinDrawableId)
                                pinTxtView.setPadding(
                                    mPinMarginStart,
                                    mPinPaddingTop,
                                    130,
                                    mPinPaddingBottom
                                )
                                pinTxtView.setTextColor(
                                    ContextCompat.getColor(
                                        mContext,
                                        mPinTextColor
                                    )
                                )
                                val bounds = Rect()
                                pinTxtView.text = pinTxt
                                pinTxtView.maxLines = 1
                                pinTxtView.textSize = 10f
                                pinTxtView.gravity = Gravity.CENTER
                                val textPaint: Paint = pinTxtView.paint
                                textPaint.getTextBounds(pinTxt, 0, pinTxt!!.length, bounds)
                                val pinBackgroundHeight = bounds.height()
                                val pinBackgroundWidth = bounds.width()
                                val x =
                                    (view.getX() - pinBackgroundWidth / 2 + view.getMeasuredWidth() / 2).toInt()
                                val y = view.getY().toInt()
                                val pinPosition =
                                    mBarHeight - value * mBarHeight / maxValue - pinBackgroundHeight / 2
                                pinTxtView.layoutParams = valueParams
                                pinTxtView.x = (x + mPinMarginStart - mPinMarginEnd).toFloat()
                                pinTxtView.y =
                                    (y + pinPosition + mPinMarginTop - mPinMarginBottom).toFloat()
                                addView(pinTxtView)
                                pins.add(pinTxtView)
                                pinTxtView.visibility = INVISIBLE
                                pinTxtView.tag = i
                            }
                        }
                    }
                }
            }
        }
    }

    fun setMaxValue(mMaxValue: Float) {
        this.mMaxValue = mMaxValue
    }

    private fun getSP(size: Float): Float {
        return size / mMetrics.scaledDensity
    }

    private val barClickListener = OnClickListener { view ->
        if (isBarsEmpty) return@OnClickListener
        val frameLayout = view as FrameLayout
        if (oldFrameLayout === frameLayout && mBarCanBeToggle) {
            if (isOldBarClicked) clickBarOff(frameLayout) else clickBarOn(frameLayout)
        } else {
            if (oldFrameLayout != null) clickBarOff(oldFrameLayout)
            clickBarOn(frameLayout)
        }
        oldFrameLayout = frameLayout
        if (listener != null) listener!!.onBarClicked(frameLayout.tag as Int)
    }

    private fun clickBarOn(frameLayout: FrameLayout) {
        pins[frameLayout.tag as Int].visibility = VISIBLE
        isOldBarClicked = true
        val childCount = frameLayout.childCount
        for (i in 0 until childCount) {
            val childView = frameLayout.getChildAt(i)
            if (childView is LinearLayout) {
                val linearLayout = childView
                val bar = linearLayout.getChildAt(0) as Bar
                val titleTxtView = linearLayout.getChildAt(1) as TextView
                val layerDrawable = bar.progressDrawable as LayerDrawable
                layerDrawable.mutate()
                val scaleDrawable = layerDrawable.getDrawable(1) as ScaleDrawable
                val progressLayer = scaleDrawable.drawable as GradientDrawable?
                if (mPinBackgroundColor != 0) {
                    progressLayer?.setColor(ContextCompat.getColor(mContext, mProgressClickColor))
                } else {
                    progressLayer?.setColor(
                        ContextCompat.getColor(
                            mContext,
                            android.R.color.holo_green_dark
                        )
                    )
                }
                if (mBarTitleSelectedColor > 0) {
                    titleTxtView.setTextColor(
                        ContextCompat.getColor(
                            mContext,
                            mBarTitleSelectedColor
                        )
                    )
                } else {
                    titleTxtView.setTextColor(
                        ContextCompat.getColor(
                            mContext,
                            android.R.color.holo_green_dark
                        )
                    )
                }
            }
        }
    }

    private fun clickBarOff(frameLayout: FrameLayout?) {
        pins[frameLayout!!.tag as Int].visibility = INVISIBLE
        isOldBarClicked = false
        val childCount = frameLayout.childCount
        for (i in 0 until childCount) {
            val childView = frameLayout.getChildAt(i)
            if (childView is LinearLayout) {
                val linearLayout = childView
                val bar = linearLayout.getChildAt(0) as Bar
                val titleTxtView = linearLayout.getChildAt(1) as TextView
                val layerDrawable = bar.progressDrawable as LayerDrawable
                layerDrawable.mutate()
                val scaleDrawable = layerDrawable.getDrawable(1) as ScaleDrawable
                val progressLayer = scaleDrawable.drawable as GradientDrawable?
                progressLayer?.setColor(ContextCompat.getColor(mContext, mProgressColor))
                titleTxtView.setTextColor(ContextCompat.getColor(mContext, mBarTitleColor))
            }
        }
    }

    fun removeBarValues() {
        if (oldFrameLayout != null) removeClickedBar()
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                val childView = rootFrame.getChildAt(j)
                if (childView is LinearLayout) {
                    //bar
                    val barContainerLinear = childView
                    val barContainerCount = barContainerLinear.childCount
                    for (k in 0 until barContainerCount) {
                        val view = barContainerLinear.getChildAt(k)
                        if (view is Bar) {
                            val anim = BarAnimation(view,
                                (data!![i].barValue * 100).toInt().toFloat(), 0F
                            )
                            anim.duration = 250
                            view.startAnimation(anim)
                        }
                    }
                }
            }
        }
        isBarsEmpty = true
    }

    fun removeClickedBar() {
        clickBarOff(oldFrameLayout)
    }

    fun resetBarValues() {
        if (oldFrameLayout != null) removeClickedBar()
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                val childView = rootFrame.getChildAt(j)
                if (childView is LinearLayout) {
                    //bar
                    val barContainerLinear = childView
                    val barContainerCount = barContainerLinear.childCount
                    for (k in 0 until barContainerCount) {
                        val view = barContainerLinear.getChildAt(k)
                        if (view is Bar) {
                            val anim = BarAnimation(view, 0F,
                                (data!![i].barValue * 100).toInt().toFloat()
                            )
                            anim.duration = 250
                            view.startAnimation(anim)
                        }
                    }
                }
            }
        }
        isBarsEmpty = false
    }

    fun disableBar(index: Int) {
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                if (rootFrame.tag as Int != index) continue
                rootFrame.isEnabled = false
                rootFrame.isClickable = false
                val childView = rootFrame.getChildAt(j)
                if (childView is LinearLayout) {
                    //bar
                    val barContainerLinear = childView
                    val barContainerCount = barContainerLinear.childCount
                    for (k in 0 until barContainerCount) {
                        val view = barContainerLinear.getChildAt(k)
                        if (view is Bar) {
                            val layerDrawable = view.progressDrawable as LayerDrawable
                            layerDrawable.mutate()
                            val scaleDrawable = layerDrawable.getDrawable(1) as ScaleDrawable
                            val progressLayer = scaleDrawable.drawable as GradientDrawable?
                            if (progressLayer != null) {
                                if (mProgressDisableColor > 0) progressLayer.setColor(
                                    ContextCompat.getColor(
                                        mContext,
                                        mProgressDisableColor
                                    )
                                ) else progressLayer.setColor(
                                    ContextCompat.getColor(
                                        mContext,
                                        android.R.color.darker_gray
                                    )
                                )
                            }
                        } else {
                            val titleTxtView = view as TextView
                            if (mProgressDisableColor > 0) titleTxtView.setTextColor(
                                ContextCompat.getColor(
                                    mContext,
                                    mProgressDisableColor
                                )
                            ) else titleTxtView.setTextColor(
                                ContextCompat.getColor(
                                    mContext,
                                    android.R.color.darker_gray
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun enableBar(index: Int) {
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                if (rootFrame.tag as Int != index) continue
                rootFrame.isEnabled = true
                rootFrame.isClickable = true
                val childView = rootFrame.getChildAt(j)
                if (childView is LinearLayout) {
                    //bar
                    val barContainerLinear = childView
                    val barContainerCount = barContainerLinear.childCount
                    for (k in 0 until barContainerCount) {
                        val view = barContainerLinear.getChildAt(k)
                        if (view is Bar) {
                            val layerDrawable = view.progressDrawable as LayerDrawable
                            layerDrawable.mutate()
                            val scaleDrawable = layerDrawable.getDrawable(1) as ScaleDrawable
                            val progressLayer = scaleDrawable.drawable as GradientDrawable?
                            if (progressLayer != null) {
                                if (mProgressColor > 0) progressLayer.setColor(
                                    ContextCompat.getColor(
                                        mContext,
                                        mProgressColor
                                    )
                                ) else progressLayer.setColor(
                                    ContextCompat.getColor(
                                        mContext,
                                        android.R.color.darker_gray
                                    )
                                )
                            }
                        } else {
                            val titleTxtView = view as TextView
                            if (mProgressDisableColor > 0) titleTxtView.setTextColor(
                                ContextCompat.getColor(
                                    mContext,
                                    mBarTitleColor
                                )
                            ) else titleTxtView.setTextColor(
                                ContextCompat.getColor(
                                    mContext,
                                    android.R.color.darker_gray
                                )
                            )
                        }
                    }
                }
            }
        }
    }

    fun selectBar(index: Int) {
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                if (rootFrame.tag as Int != index) continue
                if (oldFrameLayout != null) clickBarOff(oldFrameLayout)
                clickBarOn(rootFrame)
                oldFrameLayout = rootFrame
            }
        }
    }

    fun deselectBar(index: Int) {
        val barsCount = (getChildAt(0) as LinearLayout).childCount
        for (i in 0 until barsCount) {
            val rootFrame = (getChildAt(0) as LinearLayout).getChildAt(i) as FrameLayout
            val rootChildCount = rootFrame.childCount
            for (j in 0 until rootChildCount) {
                if (rootFrame.tag as Int != index) continue
                clickBarOff(rootFrame)
            }
        }
    }
}