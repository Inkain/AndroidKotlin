package inkant1990.com.cleanhomes.utils

import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import inkant1990.com.cleanhomes.R

@BindingAdapter("android:src")
fun loadImage(view: ImageView, url: String) {
    Log.v("Error", url)
    Picasso.get().load(url).transform(CircleTransform()).error(R.drawable.errrorscull).resize(200, 200).transform(CircleTransform()).into(view)
}


@BindingAdapter("visibility")
fun View.visibility(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}