class Courier(
    name: String,
    phone: String,
    email: String,
    address: String,
    password: String,
    private var pricePerKg: Float
    ): Person(name, phone, email, password, address) {

    fun getPricePerKg(): Float = pricePerKg
    fun setPricePerKg(value: Float) {
        if(value < 0) pricePerKg = 10f
        else pricePerKg = value
    }


    var users = ArrayList<User>()
    var packages = ArrayList<Package>()

    fun addPackage(packet: Package, user: User) {
        // User's packages seperately
        for (row in users){
            if(row.getEmail() == user.getEmail()) {
                row.packages.add(packet)
            }
        }
        // All packages in courier
        packages.add(packet)
    }

    fun removePackage(packet: Package, user: User) {
        // User's packages
        for (row in users) {
            if (row.getEmail() == user.getEmail()) {
                row.packages.remove(packet)
            }
        }

        // All packages in courier
        packages.remove(packet)
    }

    fun createUser(user: User) {
        users.add(User(user.getName(), user.getSurname(), user.getAge(), user.getGender(),
                       user.getPhone(), user.getEmail(), user.getAddressName(), user.getPersonalNo(), user.getPassword()))
    }

    fun deleteUser(user: User) {
        for (row in users) {
            if (row.getEmail() == user.getEmail())
                users.remove(user)
        }
    }


    fun getTotalRevenue(): Float {
        var totalRevenue: Float = 0f
        for (packet in packages) {
            totalRevenue += packet.getWeight() * pricePerKg
        }
        return String.format("%.2f", totalRevenue).toFloat()
    }

    fun calculateDeliveryCost(trackingNumber: String): Any {
        for (row in packages) {
            if(row.getTrackingNumber() == trackingNumber) {
                if (row is OversizedPackage)
                    return String.format("%.2f", pricePerKg * row.getVolume()).toFloat()
                return String.format("%.2f", pricePerKg * row.getWeight()).toFloat()
            }
        }

        return "Invalid tracking number!!!"
    }
}