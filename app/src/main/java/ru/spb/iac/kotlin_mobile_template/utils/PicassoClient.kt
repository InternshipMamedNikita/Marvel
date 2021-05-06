package ru.spb.iac.kotlin_mobile_template.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import ru.spb.iac.kotlin_mobile_template.R
import ru.spb.iac.kotlin_mobile_template.services.App

/**
 *   Created by vladislav on 1/27/20.
 */
object PicassoClient {

    val TAG = PicassoClient::class.java.simpleName
    private var isSingletonCreated = false

    fun init() {
        if (isSingletonCreated) return
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val CLIENT_BUILDER =
            OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        val builder = Picasso.Builder(App.context)
            .downloader(OkHttp3Downloader(CLIENT_BUILDER.build()))
            .loggingEnabled(true)
        Picasso.setSingletonInstance(builder.build())
        isSingletonCreated = true
    }

    @BindingAdapter("bind:back")
    fun back(view: ImageView?, back: String?) {
        Picasso.get().invalidate(back)
        Picasso.get().load(back)
//            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }

    @BindingAdapter("bind:photo")
    fun photo(view: ImageView?, photo: String?) {
        Picasso.get().invalidate(photo)
        Picasso.get().load(photo)
//            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }

    // ?
    @JvmStatic
    @BindingAdapter("bind:backFromMemory")
    fun backFromMemory(view: ImageView?, back: String?) {
        if (back.isNullOrEmpty())
            Picasso.get().load("https://nplus1.ru/i/logo.png")
                .fit()
                .into(view)
        else
            Picasso.get().load(back)
                .placeholder(R.drawable.arrow_left)
                .fit()
                .into(view)
    }

    @BindingAdapter("bind:photoFromMemory")
    fun photoFromMemory(view: ImageView?, photo: String?) {
        Picasso.get().load(photo)
//            .placeholder(R.drawable.ic_launcher_background)
            .into(view)
    }

    @BindingAdapter("bind:image", "bind:placeHolder")
    fun image(view: ImageView?, photo: String?, placeHolder: Drawable?) {
        Picasso.get().invalidate(photo)
        Picasso.get().load(photo)
            .placeholder(placeHolder!!)
            .centerCrop()
            .fit()
            .into(view)
    }

    @BindingAdapter("bind:imageFromMemory", "bind:placeHolder")
    fun imageFromMemory(view: ImageView?, photo: String?, placeHolder: Drawable?) {
        Picasso.get().load(photo)
            .placeholder(placeHolder!!)
            .centerCrop()
            .fit()
            .into(view)
    }
}