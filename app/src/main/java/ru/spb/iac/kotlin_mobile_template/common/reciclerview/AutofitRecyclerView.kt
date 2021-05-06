package ru.spb.iac.kotlin_mobile_template.common.reciclerview

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import ru.spb.iac.kotlin_mobile_template.R
import kotlin.math.max
import java.util.*

/**
 * Created by nixbyte on 20,Декабрь,2019
 */

class AutofitRecyclerView<T>  @JvmOverloads  constructor(recyclerContext: Context, attributeSet : AttributeSet? = null,
                                                         defStyleAttr : Int = 0): RecyclerView(recyclerContext, attributeSet, defStyleAttr) {
    val TAG = AutofitRecyclerView::class.java.simpleName

    private var columnWidth: Int = 0
    private var defaultColumnCount: Int = 0
    private var columnSpace: Int = 0
    private var pagginated: Boolean = false

    private var autoLoadingAdapter : AutoLoadingRecyclerViewAdapter<T>? = null
    private var loading : Loading<T>? = null

    var offsetAndLimit : OffsetAndLimit
    var stopPaginationCondition : (OffsetAndLimit) -> Boolean = { _ -> true}

    var scrollingChanel = PublishSubject.create<OffsetAndLimit>()
    private var scrollingObserver : DisposableObserver<OffsetAndLimit>? = null
    var swipeRefreshLayout : SwipeRefreshLayout? = null
    private var loadNewItemsSubscription: Disposable? = null
    private var itemDecoration: SpacingItemDecoration? = null

    init {
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.AutofitRecyclerView, 0, defStyleAttr)
        try {
            val limit = typedArray.getInt(R.styleable.AutofitRecyclerView_page_limit, 10)
            pagginated = typedArray.getBoolean(R.styleable.AutofitRecyclerView_pagginated, false)
            columnWidth = typedArray.getDimensionPixelSize(R.styleable.AutofitRecyclerView_columnWidth, 110)
            defaultColumnCount = typedArray.getInteger(R.styleable.AutofitRecyclerView_columnCount, 1)
            columnSpace = typedArray.getDimensionPixelSize(R.styleable.AutofitRecyclerView_columnSpace, 0)
            offsetAndLimit = OffsetAndLimit(0, limit)

        } finally {
            typedArray.recycle()
        }
    }

    fun startLoadingItems() {
        loadNewItems(offsetAndLimit)
        if (pagginated) {
            startScrollChanel()
        }
    }

    fun setObservableDataSource(loading: Loading<T>) {
        this.loading = loading
    }

    fun loadNewItems(offsetAndLimit: OffsetAndLimit = this.offsetAndLimit) {
        val loadNewItemsSubscriber =  object : DisposableObserver<List<T>>() {
            override fun onNext(list: List<T>) {
                offsetAndLimit.offset ++
                adapter?.addNewItems(list)
                if (offsetAndLimit.offset == 1 && !stopPaginationCondition(offsetAndLimit) && list.size < offsetAndLimit.limit && pagginated) {
                    loadNewItems(offsetAndLimit)
                } else {
                    adapter?.itemCount?.minus(1)?.let {
                        adapter?.notifyItemRangeChanged(it, it + list.size)
                    }
                    if (!stopPaginationCondition(offsetAndLimit)) {
                        subscribeToLoadingChanel()
                    }
                }
            }

            override fun onError(e: Throwable) {
                swipeRefreshLayout?.isRefreshing = false
                subscribeToLoadingChanel()
            }

            override fun onComplete() {
                swipeRefreshLayout?.isRefreshing = false
            }
        }

        swipeRefreshLayout?.isRefreshing = true
        loadNewItemsSubscription = loading?.getLoadingObservable(offsetAndLimit)
            ?.subscribeOn(Schedulers.newThread())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(loadNewItemsSubscriber)
    }

    fun onDestroy() {
        loadNewItemsSubscription?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    fun setAdapter(adapter : AutoLoadingRecyclerViewAdapter<T>) {
        autoLoadingAdapter = adapter
        super.setAdapter(autoLoadingAdapter)
    }

    override fun getAdapter(): AutoLoadingRecyclerViewAdapter<T>? {
        return autoLoadingAdapter
    }

    private fun startScrollChanel() {
        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclervView: RecyclerView, dx: Int, dy: Int) {
                val lastVisibleItemPosition = getLastVisibleItemPosition()
                val positionForStartUpdate = adapter?.itemCount?.minus(1) ?: 0
                if (lastVisibleItemPosition >= positionForStartUpdate) {
                    scrollingChanel.onNext(offsetAndLimit)
                }
            }
        })
    }

    fun subscribeToLoadingChanel() {
        scrollingObserver = scrollingChanel
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableObserver<OffsetAndLimit>() {
                override fun onComplete() {}
                override fun onNext(offAndLim: OffsetAndLimit) {
                    this.dispose()
                    loadNewItems(offAndLim)
                }
                override fun onError(e: Throwable) {}
            })
    }

    override fun setLayoutManager(layout: LayoutManager?) {
        super.setLayoutManager(layout)
        val controller = if (layout is GridLayoutManager) {
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_grid_animation_down)
        } else {
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_list_animation)
        }
        layoutAnimation = controller
        scheduleLayoutAnimation()
    }

    fun getLastVisibleItemPosition(): Int {
        val recyclerViewLMClass = layoutManager!!.javaClass
        if (recyclerViewLMClass == LinearLayoutManager::class.java || LinearLayoutManager::class.java.isAssignableFrom(recyclerViewLMClass)) {
            val linearLayoutManager = layoutManager as LinearLayoutManager?
            return linearLayoutManager!!.findLastVisibleItemPosition()
        } else if (recyclerViewLMClass == StaggeredGridLayoutManager::class.java || StaggeredGridLayoutManager::class.java.isAssignableFrom(recyclerViewLMClass)) {
            val staggeredGridLayoutManager = layoutManager as StaggeredGridLayoutManager?
            val into = staggeredGridLayoutManager!!.findLastVisibleItemPositions(null)
            val intoList = ArrayList<Int>()
            for (i in into) {
                intoList.add(i)
            }
            return Collections.max(intoList)
        }
        throw RuntimeException("Unknown LayoutManager class: $recyclerViewLMClass")
    }

    fun invalidateData() {
        onDestroy()
        offsetAndLimit.offset = 0
        adapter?.clear()
        adapter?.notifyDataSetChanged()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        super.onMeasure(widthSpec, heightSpec)
        if (columnWidth > 0) {
            val spanCount = max(defaultColumnCount, measuredWidth / columnWidth)
            if (layoutManager is GridLayoutManager) {

                (layoutManager as GridLayoutManager).spanCount = spanCount
                itemDecoration?.let { removeItemDecoration(itemDecoration as SpacingItemDecoration) }
                itemDecoration = SpacingItemDecoration((layoutManager as GridLayoutManager).spanCount, columnSpace, true, 0)
                addItemDecoration(itemDecoration as SpacingItemDecoration)
            } else {
                itemDecoration?.let { removeItemDecoration(itemDecoration as SpacingItemDecoration) }
                itemDecoration = SpacingItemDecoration(1, columnSpace, true, 0)
                addItemDecoration(itemDecoration!!)
            }
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        adapter = null
    }
}


