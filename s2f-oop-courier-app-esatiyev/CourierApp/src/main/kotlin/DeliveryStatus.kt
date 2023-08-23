enum class DeliveryStatus(val status: String, val value: Int) {
    IN_OVERSEAS_WAREHOUSE("The package is at the warehouse. ", 1),
    IN_TRANSIT("The package is in transit.", 2),
    OUT_FOR_DELIVERY("The package is out for delivery. ", 3),
    DELIVERED("The package is delivered.", 4)
}