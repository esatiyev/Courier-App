import java.time.LocalDate

class ExpressPackage(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    deliveryMethod: DeliveryMethod = DeliveryMethod.Express,
    step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE
) : RegularPackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod, step) {

    override fun deliverPackage() {
        step = DeliveryStatus.DELIVERED
        println("Package delivered on ${LocalDate.now()}")
    }
}