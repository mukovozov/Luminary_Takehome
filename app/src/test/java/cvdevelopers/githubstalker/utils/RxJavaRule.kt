package cvdevelopers.githubstalker.utils

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.internal.schedulers.ExecutorScheduler
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement
import java.util.concurrent.TimeUnit

class RxRule : TestRule {
    private val testScheduler = createTestScheduler()

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {

            override fun evaluate() {
                RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
                RxJavaPlugins.setIoSchedulerHandler { testScheduler }
                RxJavaPlugins.setSingleSchedulerHandler { testScheduler }
                RxJavaPlugins.setNewThreadSchedulerHandler { testScheduler }
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { testScheduler }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    RxAndroidPlugins.reset()
                }
            }
        }
    }

    private fun createTestScheduler(): Scheduler {
        return object : Scheduler() {
            override fun scheduleDirect(run: Runnable, delay: Long, unit: TimeUnit): Disposable {
                return super.scheduleDirect(run, 0, unit)
            }

            override fun createWorker(): Worker = ExecutorScheduler.ExecutorWorker(
                Runnable::run,
                false,
                true
            )
        }
    }
}