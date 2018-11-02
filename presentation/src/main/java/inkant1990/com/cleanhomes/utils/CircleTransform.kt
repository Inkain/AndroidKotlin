package inkant1990.com.cleanhomes.utils

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.squareup.picasso.Transformation

class CircleTransform : Transformation {

   override fun transform(source: Bitmap): Bitmap {
        val paint = Paint()
        paint.isAntiAlias = true
        paint.shader = BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        canvas.drawCircle(
            (source.width / 2).toFloat(),
            (source.height / 2).toFloat(),
            if (source.height < source.width) source.height / 2.2f else source.width / 2f,
            paint
        )

        if (source != output)
            source.recycle()

        return output
    }

   override fun key(): String {
        return "circle"
    }
}