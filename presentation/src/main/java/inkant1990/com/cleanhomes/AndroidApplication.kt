package inkant1990.com.cleanhomes

import android.app.Application


class AndroidApplication : Application() {
    companion object {
        lateinit var instance :AndroidApplication
    }
    init {
        instance=this
    }
    override fun onCreate() {
        super.onCreate()

    }


}