abstract class Person(
    private var name: String,
    private var phone: String,
    private var email: String,
    private var password: String,
    protected var address: String
) {
    fun getName(): String = name
//    fun setName(value: String) {
//        name = value
//    }

    fun getPhone(): String = phone
    fun setPhone(phone: String) {
        this.phone = phone
    }
    fun getEmail(): String = email

    fun getPassword(): String = password
    fun setPassword(password: String) {
        this.password = password
    }

    fun getAddressName(): String = address
    fun setAddressName(address: String) {
        this.address = address
    }
}