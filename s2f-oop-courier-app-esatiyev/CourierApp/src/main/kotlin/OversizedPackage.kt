
import java.time.LocalDate
import kotlin.random.Random

class OversizedPackage(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    deliveryMethod: DeliveryMethod,
    step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE,
    private var length: Int = Random.nextInt(80,500),
    private var width: Int = kotlin.random.Random.nextInt(80,500),
    private var height: Int = Random.nextInt(80,500)
) : Package(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod, step) {

    fun getWidth(): Int = width
    fun setWidth(width: Int) {
        this.width = width
    }

    fun getHeight(): Int = height
    fun setHeight(height: Int) {
        this.height = height
    }

    fun getLength(): Int = length
    fun setLength(length: Int) {
        this.length = length
    }

    fun getVolume(): Double {
        val volume: Double = length * width * height * 0.000001
        return String.format("%.2f", volume).toDouble()
    }

    override fun deliverPackage() {
        step = DeliveryStatus.DELIVERED
        println("Package delivered on ${LocalDate.now()}")
    }
}