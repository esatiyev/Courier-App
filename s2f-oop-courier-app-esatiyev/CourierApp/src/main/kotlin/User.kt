class User(
    name: String,
    private var surname: String,
    private var age: Int,
    private var gender: String,
    phone: String,
    email: String,
    address: String,
    private var personalNo: String,
    password: String
) : Person(name, phone, email, password, address) {

    var packages = ArrayList<Package>()
    fun addPackage(packet: Package) {
        packages.add(packet)
    }

    fun removePackage(packet: Package) {
        packages.remove(packet)
    }

    fun getSurname(): String = surname
    fun getAge(): Int = age

    fun getGender(): String = gender


    fun getPersonalNo(): String = personalNo
}