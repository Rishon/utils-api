package systems.rishon.selautils.paper.runnable

import io.papermc.paper.threadedregions.scheduler.FoliaAsyncScheduler
import io.papermc.paper.threadedregions.scheduler.FoliaGlobalRegionScheduler
import io.papermc.paper.threadedregions.scheduler.ScheduledTask
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitScheduler
import systems.rishon.selautils.paper.folia.FoliaSupport
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

class SchedulerUtil(private val plugin: Plugin) {

    private var isRunningFolia: Any? = null

    init {
        if (isRunningFolia == null) this.isRunningFolia = FoliaSupport.isFolia() // We need to call this only once
    }

    // Bukkit Scheduler
    val scheduler: BukkitScheduler = Bukkit.getScheduler()

    // Folia
    val asyncScheduler: FoliaAsyncScheduler = FoliaAsyncScheduler()
    val regionScheduler: FoliaGlobalRegionScheduler = FoliaGlobalRegionScheduler()

    fun cancelAllTasks() {
        asyncScheduler.cancelTasks(this.plugin)
        regionScheduler.cancelTasks(this.plugin)
    }

    fun runTaskAsync(runnable: Consumer<Any?>) {
        if (this.isRunningFolia == true) {
            asyncScheduler.runNow(this.plugin) { t: ScheduledTask? -> runnable.accept(t) }
        } else {
            this.scheduler.runTaskAsynchronously(this.plugin, runnable)
        }
    }

    fun runTaskTimerAsync(runnable: Consumer<Any?>, ticks: Long) {
        if (this.isRunningFolia == true) {
            asyncScheduler.runAtFixedRate(
                this.plugin, { t: ScheduledTask? -> runnable.accept(t) }, 0, ticks * 5000L, TimeUnit.MICROSECONDS
            )
        } else {
            this.scheduler.runTaskTimerAsynchronously(this.plugin, runnable, 0, ticks)
        }
    }

    fun runTaskTimerSync(runnable: Consumer<Any?>, ticks: Long) {
        if (this.isRunningFolia == true) {
            regionScheduler.runAtFixedRate(this.plugin, { t: ScheduledTask? -> runnable.accept(t) }, 0, ticks)
        } else {
            this.scheduler.runTaskTimer(this.plugin, runnable, 0, ticks)
        }
    }

    fun runTaskSync(runnable: Consumer<Any?>) {
        if (this.isRunningFolia == true) {
            regionScheduler.run(this.plugin) { t: ScheduledTask? -> runnable.accept(t) }
        } else {
            this.scheduler.runTask(this.plugin, runnable)
        }
    }
}