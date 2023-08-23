import java.time.LocalDate

class FragilePackage(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    deliveryMethod: DeliveryMethod,
    isFragile: Boolean = true,
    step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE
) : Package(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod, step) {
    protected var isFragile = isFragile
    fun getIsFragile(): Boolean = isFragile
    fun setIsFragile(value: Boolean) {
        isFragile = value
    }

    override fun deliverPackage() {
        step = DeliveryStatus.DELIVERED
        println("Package delivered on ${LocalDate.now()}")
    }
}