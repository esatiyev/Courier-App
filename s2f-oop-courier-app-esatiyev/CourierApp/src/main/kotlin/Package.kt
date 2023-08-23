import java.time.LocalDate
import kotlin.random.Random

abstract class Package(
    packageName: String,
    trackingNumber: String,
    sender: String,
    recipient: String,
    weight: Float,
    price: Float,
    protected var deliveryMethod: DeliveryMethod,
    protected var step: DeliveryStatus = DeliveryStatus.IN_OVERSEAS_WAREHOUSE
) : PackageDelivery  {

    protected var rate: Int = 0
    protected var deliveryTime: String = "0"
    fun getRating(): Int = rate

    fun setRating(value: Int) {
        rate = value
    }
    fun getDeliveryTimeFormatted(): String = deliveryTime
    fun setDeliveryTimeFormatted(value: String) {
        deliveryTime = value
    }
    override fun getEstimatedDeliveryTime(): String{
        if (this.deliveryTime == "0"){
            val distance: Int = Random.nextInt(1, 1000)
            val averageSpeed: Int = this.deliveryMethod.speed
            this.deliveryTime = if(distance / averageSpeed == 0 || distance / averageSpeed == 1) "1 day" else "${distance / averageSpeed} days"
        }

        var days: Long = 0
        var s: String = ""
        for (i in deliveryTime) {
            if(i.isDigit()) {
                s += i
            }

         /*   if (i != deliveryTime[deliveryTime.length - 1])
            if(!(i+1).isDigit()) {
                days = s.toLong()
            }*/
        }
        days = s.toLong()
        return LocalDate.now().plusDays(days).toString()
    }


    private var packageName: String? = packageName
    fun getPackageName() = packageName
    fun setPackageName(value: String) {
        packageName = value
    }

    private var trackingNumber: String = trackingNumber
    fun getTrackingNumber(): String = trackingNumber
    fun setTrackingNumber(value: String) {
        trackingNumber = value
    }

    private var sender: String = sender
    fun getSender() = sender
    fun setSender(value: String) {
        sender = value
    }

    private var recipient: String = recipient
    fun getRecipient(): String = recipient
    fun setRecipient(value: String) {
        recipient = value
    }

    private var weight: Float = weight
    fun getWeight(): Float = weight
    fun setWeight(value: Float) {
        weight = if(value <= 0) 1f
        else value
    }

    private var price: Float = price
    fun  getPrice(): Float = price
    fun setPrice(value: Float) {
        price = if(value <= 0) 0f
        else value
    }

    fun getStepp(): DeliveryStatus = step
    fun setStepp(value: DeliveryStatus) {
        step = value
    }

    fun updatePackageStep(value: Int){
        val statusValues = DeliveryStatus.values()
        if (value < statusValues.size) {
            this.step = statusValues.elementAt(value)
        }
        else {
            println("This package is already delivered.")
        }

    }
    fun trackPackage() : Unit = println("Package with tracking number $trackingNumber is currently $step.")
}