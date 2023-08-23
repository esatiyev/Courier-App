import java.time.LocalDate
class HazardousPackage(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    deliveryMethod: DeliveryMethod,
    step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE,
    private var isAllow: Boolean = true
) : Package(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod, step) {
    fun getIsAllow(): Boolean = isAllow
    fun setIsAllow(isAllow: Boolean) {
        this.isAllow = isAllow
    }

    override fun deliverPackage() {
        step = DeliveryStatus.DELIVERED
        println("Package delivered on ${LocalDate.now()}")
    }
}