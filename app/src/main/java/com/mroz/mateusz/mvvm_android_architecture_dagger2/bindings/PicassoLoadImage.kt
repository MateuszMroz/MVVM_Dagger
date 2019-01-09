package com.mroz.mateusz.mvvm_android_architecture_dagger2.bindings

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.mroz.mateusz.mvvm_android_architecture_dagger2.R
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

           /* picasso.load(url)
                    .placeholder(R.drawable.progress_image)
                    .error(R.drawable.error)
                    .into(view)*/

            //Glide is faster than picasso
            Glide.with(view.context)
                    .load(url)
                    .into(view)
        }



    }
}