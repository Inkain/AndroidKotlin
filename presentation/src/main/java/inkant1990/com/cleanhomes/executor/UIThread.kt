package inkant1990.com.cleanhomes.executor

import inkant1990.com.domain.executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class UIThread : PostExecutorThread {

    override fun getScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}