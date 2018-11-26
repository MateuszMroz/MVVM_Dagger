package com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.recycler_view

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.mroz.mateusz.mvvm_android_architecture_dagger2.dagger_global.context.ContextModule
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.DaggerPicassoComponent
import com.mroz.mateusz.mvvm_android_architecture_dagger2.list_profile.dagger.PicassoComponent
import com.squareup.picasso.Picasso


class PicassoLoadImage {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, url: String) {

            val picassoComponent: PicassoComponent = DaggerPicassoComponent.builder()
                    .contextModule(ContextModule(view.context))
                    .build()

            var picasso: Picasso = picassoComponent.getPicasso()
            picasso.load(url).into(view)
        }

    }
}