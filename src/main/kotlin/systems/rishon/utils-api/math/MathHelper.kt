package systems.rishon.selautils.math

import java.util.Calendar
import java.util.UUID
import kotlin.random.Random

object MathHelper {

    @JvmStatic
    fun sin(x: Double): Double {
        return Math.sin(x)
    }

    @JvmStatic
    fun cos(x: Double): Double {
        return Math.cos(x)
    }

    @JvmStatic
    fun tan(x: Double): Double {
        return Math.tan(x)
    }

    @JvmStatic
    fun sqrt(x: Double): Double {
        return Math.sqrt(x)
    }

    @JvmStatic
    fun ceil(x: Double): Double {
        return Math.ceil(x)
    }

    @JvmStatic
    fun floor(x: Double): Double {
        return Math.floor(x)
    }

    @JvmStatic
    fun round(x: Double): Long {
        return Math.round(x)
    }

    @JvmStatic
    fun abs(x: Double): Double {
        return Math.abs(x)
    }

    @JvmStatic
    fun max(x: Double, y: Double): Double {
        return Math.max(x, y)
    }

    @JvmStatic
    fun min(x: Double, y: Double): Double {
        return Math.min(x, y)
    }

    @JvmStatic
    fun pow(x: Double, y: Double): Double {
        return Math.pow(x, y)
    }

    @JvmStatic
    fun getInt(value: String, def: Int): Int {
        return try {
            value.toInt()
        } catch (e: NumberFormatException) {
            def
        }
    }

    @JvmStatic
    fun getDouble(value: String, def: Double): Double {
        return try {
            value.toDouble()
        } catch (e: NumberFormatException) {
            def
        }
    }

    @JvmStatic
    fun getRandomInt(min: Int, max: Int): Int {
        return Random.nextInt(min, max)
    }

    @JvmStatic
    fun getRandomDouble(min: Double, max: Double): Double {
        return Random.nextDouble(min, max)
    }

    @JvmStatic
    fun getRandomLong(min: Long, max: Long): Long {
        return Random.nextLong(min, max)
    }

    @JvmStatic
    fun abs(x: Int): Int {
        return Math.abs(x)
    }

    @JvmStatic
    fun abs(x: Long): Long {
        return Math.abs(x)
    }

    @JvmStatic
    fun abs(x: Float): Float {
        return Math.abs(x)
    }

    @JvmStatic
    fun getSeedForUUID(uuid: UUID): Long {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val second = calendar.get(Calendar.SECOND)
        val combinedString = "$uuid-$year-$month-$day-$hour-$minute-$second"
        val seed = abs(combinedString.hashCode().toLong())
        return seed
    }
}