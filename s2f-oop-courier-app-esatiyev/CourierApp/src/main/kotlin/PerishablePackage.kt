import java.time.LocalDate
import kotlin.random.Random

class PerishablePackage(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    deliveryMethod: DeliveryMethod,
    step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE,
    private var shelfLife: Int = Random.nextInt(10, 365),
    private var isPerishable: Boolean = true
) : Package(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod, step) {

    private var productionDate: LocalDate
    init {
        var day = Random.nextInt(1, 40).toLong()
        this.productionDate = LocalDate.now().minusDays(day)
    }

    fun getShelfLife(): Int = this.shelfLife

    fun getProductionDate(): LocalDate = this.productionDate

    fun getIsPerishable(): Boolean = this.isPerishable
    fun setIsPerishable(isPerishable: Boolean) {
        this.isPerishable = isPerishable
    }


    override fun deliverPackage() {
        step = DeliveryStatus.DELIVERED
        println("Package delivered on ${LocalDate.now()}")
    }
}