import java.time.LocalDate
import kotlin.random.Random

fun main(args: Array<String>) {

    var users = ArrayList<User>()
    val user1 = User("Elton", "Satiyev", 18, "Male", "0100",
        "a", "a", "82tt", "a")
    users.add(user1)

    var USER: User
    var COURIER: Courier
    var uNumber = 0

    var packageName: String
    val sizeOfPackageType = 6

    var couriers = ArrayList<Courier>()
    var courierName: String

    var recipient: String
    var sender: String
    var price: Float
    var weight: Float
    var trackingNumber: String

    var option: Int
    var condition: Boolean = true

    while(true){
        condition = true
        println("1. Log into app as user")
        println("2. Log into app as admin")
        println("3. Log into app as courier")
        option = enterNumber(1, 3)


//        while(true) {
//
//        }
        var loginCondition = true
        while (loginCondition) {
            loginCondition = true

            if (option == 2) break

            if (option == 1) {
                println("1. Log in")
                println("2. Register")
                println("0. Back")
                when(enterNumber(0, 2)) {
                    0 -> {
                        loginCondition = false  // Back
                        condition = false
                    }

                    // Log in
                    1 -> {
                        val (uNumber1, condition1) = loginUser(users)
                        uNumber = uNumber1
                        condition = condition1
                        if (condition1 == false) continue
                        else loginCondition = false
                    }

                    // Register
                    2 -> {
                        users = register(users)
                        println(users[users.size - 1].getName())

                        for (row in couriers) {
                            row.createUser(users[users.size - 1])  // son elave edilen useri courierlere elave edir
                        }
                        continue
                    }
                }
            }

            else if (option == 3) {
                println("1. Log in")
                println("0. Back")

                when(enterNumber(0, 1)) {
                    0 -> {
                        loginCondition = false  // Back
                        condition = false
                    }
                    // Log in
                    1 -> {
                        val (uNumber1, condition1) = loginCourier(couriers)
                        uNumber = uNumber1
                        condition = condition1
                        if (condition1 == false) continue
                        else loginCondition = false
                    }

                }
            }

        }

        if(!condition) continue // eger 0 secib back deyirsə işləyir

        if (option == 1) USER = users[uNumber]  //USER teyin edildi
        else if (option == 3) COURIER = couriers[uNumber] // COURIER teyin edildi

        when (option) {
            // User Interface
            1 -> {
                USER = users[uNumber]

                var userInterface = true
                while (userInterface) {
                    for(row in couriers) {
                        for (col in row.users) {
                            if (col.getEmail() == USER.getEmail()) {
                                for (th in col.packages) {
                                    if(th.getStepp().name == "DELIVERED" && th.getRating() == 0) {
                                        couriers = rateDelivery(th.getTrackingNumber(), USER.getEmail(), couriers)
                                        for (i in USER.packages) {
                                            if(i.getTrackingNumber() == th.getTrackingNumber()) {
                                                i.setRating(th.getRating())
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    println("1. Create New Package")  // user
                    println("2. My Packages") // user
                    println("3. Profile")
                    println("4. Calculator & Tariffs")
                    println("0. Sign out")

                    when(enterNumber(0, 4)) {
                        0 -> userInterface = false // Sign out

                        // Create a new package
                        1 -> {
                            var createPackage = true
                            while (createPackage) {
                                println("Choose your package type")
                                println("1: Regular Package")
                                println("2: Express Package")
                                println("3: Fragile Package")
                                println("4. Oversized Package")
                                println("5. Hazardous Package")
                                println("6. Perishable Package")
                                println("0. Back")

                                val packageType: Int = enterNumber(0, sizeOfPackageType, "Choose your package type")

                                if (packageType == 0) break // 0. Back

                                println("Enter package name: ")
                                packageName = readln()


                                println("Enter sender name: ")
                                sender = readln()

                                println("Enter price of your product: ")
                                price = readln().toFloat()

                                recipient = USER.getName() + " " + USER.getSurname()
                                weight = calculateWeight()
                                trackingNumber = setTrackingNumber()

                                println("1. Standart Delivery: 7-10 days")
                                println("2. Express Delivery: 2-4 days")
                                println("3. Overnight Delivery: 1-2 days")
                                println("0. Back")

                                var deliveryMethod: DeliveryMethod = DeliveryMethod.Express
//                                if (packageType != 2){
                                    option = enterNumber(0, 3, "Select a delivery method!")
                                    if (option == 0) break
                                    deliveryMethod = if (option == 1) DeliveryMethod.Standart
                                    else if (option == 2) DeliveryMethod.Express
                                    else DeliveryMethod.Overnight
//                                }

                                when(packageType){
                                    // Regular Package
                                    1 -> {
                                        USER.addPackage(RegularPackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod))

                                        println("Regular Package is added successfully. You can track it with tracking number: $trackingNumber")
                                        createPackage = false
                                    }
                                    // Express Package
                                    2 -> {
                                        USER.addPackage(ExpressPackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod ))
                                        println("Express Package is added successfully. You can track it with tracking number: $trackingNumber")
                                        createPackage = false
                                    }
                                    // Fragile Package
                                    3 -> {
                                        USER.addPackage(FragilePackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod))
                                        println("Fragile Package is added successfully. You can track it with tracking number: $trackingNumber")

                                        createPackage = false
                                    }
                                    // Oversized Package
                                    4 -> {
                                        USER.addPackage(OversizedPackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod))

                                        println("Regular Package is added successfully. You can track it with tracking number: $trackingNumber")
                                        createPackage = false
                                    }
                                    // Hazardous Package
                                    5 -> {
                                        USER.addPackage(HazardousPackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod))

                                        println("Hazardous Package is added successfully. You can track it with tracking number: $trackingNumber")
                                        createPackage = false
                                    }
                                    // Perishable Package
                                    6 -> {
                                        USER.addPackage(PerishablePackage(packageName, trackingNumber, sender, recipient, weight, price, deliveryMethod))

                                        println("Perishable Package is added successfully. You can track it with tracking number: $trackingNumber")
                                        createPackage = false
                                    }
                                    else -> {
                                        println("Invalid package type!!!")  // never should be
                                    }
                                }

                            } // while(createPackage)

                        }

                        // My Packages
                        2 -> {
                            var myPackages = true
                            while (myPackages) {
                                println("1. Add package to specific courier") //user / packages
                                println("2. Remove the package from current courier") // user/ PAckages
                                println("3. Delete the package permanently") // user / packages
                                println("4. Show package information") // user / packages
                                println("0. Back")

                                option = enterNumber(0, 4)

                                var i = 1
                                if (option != 0)
                                for(row in USER.packages) {
                                    println("$i. Package: ${row.getPackageName()} ")
                                    println("    Sender: ${row.getSender()}")
                                    println("    Tracking number: ${row.getTrackingNumber()}")

                                    println()
                                    i++
                                }
                                if (option != 4) println("0. Back")

                                when(option) {
                                    0 -> myPackages = false // Back

                                    // Add package to specific courier
                                    1 -> {
                                        option = enterNumber(0, i - 1, "Choose a package")
                                        if (option == 0) continue // Back

                                        // check for this package is already added to any courier or not
                                        var breakExistPackage: Boolean = false

                                        secondfor@for (row in couriers) {
                                            for (col in row.packages) {
                                                if (col.getTrackingNumber() == USER.packages[option - 1].getTrackingNumber()) {
                                                    println("This package is already added to ${row.getName()} cargo." +
                                                            "by ${col.getRecipient()}")
                                                    breakExistPackage = true
                                                    break@secondfor
                                                }
                                            }
                                        }

                                        if (breakExistPackage) continue

                                        i = 1
                                        for (row in couriers) {
                                            println("$i. ${row.getName()}, price per kg: ${row.getPricePerKg()}")
                                            i++
                                        }
                                        println("0. Back\n")

                                        val courierNumber: Int = enterNumber(0, i - 1, "Choose a Courier: ")
                                        if(courierNumber == 0) continue

                                        //     println("Delivery time: " + USER.packages[option-1].getDeliveryTimeFormatted() )

                                        couriers[courierNumber - 1].addPackage(USER.packages[option - 1], USER)
                                        println("Package is added to ${couriers[courierNumber - 1].getName()} courier company.")
                                    }

                                    // Remove the package from current courier
                                    2 -> {
                                        option = enterNumber(0, i - 1, "Choose a package")
                                        if (option == 0) continue

                                        println("Do you really want to remove this package from this cargo company?")

                                        if (!YorN("Package isn't removed!")) continue

                                        // check that this package is already added to any courier or not, if not then "continue"
                                        var breakExistPackage: Boolean = true
                                        var removed = false
                                        //         if (couriers.isNotEmpty())
                                        secondfor@for (row in couriers) {
                                            //  if (row.packages.isEmpty()) continue
                                            for (col in row.users) {
                                                if (col.getName() == USER.getName()) {
                                                    for (th in col.packages){
                                                        if (th.getTrackingNumber() == USER.packages[option -1].getTrackingNumber()) {

                                                            if (th.getStepp().value == 1) {
                                                                row.removePackage(th, USER)
                                                                println("Package is removed successfully.")
                                                                removed = true
                                                            }
                                                            else {
                                                                println(th.getStepp().status + " So it cannot be removed!")
                                                            }

                                                            breakExistPackage = false

                                                            break@secondfor
                                                        }
                                                        println("s " + th.getStepp().value)
                                                    }
                                                }

                                            }

                                        }

                                        if (breakExistPackage) {
                                            println("This package isn't added to any courier!")
                                            continue
                                        }

                                        if (removed == false) continue

                                        println("Provide feedback. Why did you remove it from this cargo?")
                                        println("1: I saw more cheap courier than this  cargo.")
                                        println("2: Delivery time is long.")
                                        println("3: I will cancel the order.")

                                        println("4: Other.\n")

                                        option = enterNumber(1, 4, "Select a option: ")

                                        when(option) {
                                            in   1 ..  3 -> println("Thanks for your feedback.")

                                            4 -> {
                                                println("Enter your feedback:\n")
                                                var feedback = readln()
                                                // feedback courier-in datasina gede biler. Elave etmek olar bu ozelliyi de
                                                println("Thanks for your feedback.")
                                            }

                                            else -> println("\nelse\n") // should never happen
                                        }
                                    }

                                    // Delete the package permanently
                                    3 -> {
                                        option = enterNumber(0, i - 1, "Choose a package")
                                        if (option == 0) continue

                                        // check that this package is added to any courier or not
                                        var isExistPackage: Boolean = false

                                        secondfor@for (row in couriers) {
//                                        if (row.packages.isNotEmpty())
                                            for (col in row.packages) {
                                                if (col.getTrackingNumber() == USER.packages[option - 1].getTrackingNumber()) {
                                                    println("This package was added to ${row.getName()} cargo" +
                                                            "it cannot be deleted")
                                                    isExistPackage = true
                                                    break@secondfor
                                                }
                                            }
                                        }
                                        if (isExistPackage) {
                                            println("For delete this package permanently firstly, you must remove this package from the courier.")
                                            continue
                                        }

                                        println("Do you really want to delete this package?")
                                        if (!YorN("Package isn't deleted!")) continue

                                        USER.removePackage(USER.packages[option-1])
                                        println("Package is deleted successfully.")
                                    }

                                    // Show package information in couriers
                                    4 -> {
                                        var showPackageInfo = true
                                        while (showPackageInfo) {
                                            println("1. Show my all packages in couriers")
                                            println("2. Show my package in couriers")
                                            println("3. Track my package")
                                            println("0. Back")

                                            when(enterNumber(0, 3)) {
                                                // Back
                                                0 -> showPackageInfo = false

                                                // Show all packages in couriers
                                                1 -> {
                                                    for(row in couriers) {
                                                        for(col in row.users) {
                                                            if (col.getEmail() == USER.getEmail()) {
                                                                for (th in col.packages) {
                                                                    condition = true
                                                                    println("Package : ${th.getPackageName()}")
                                                                    println("   Recipient: ${th.getRecipient()}")
                                                                    println("   Sender: ${th.getSender()}")
                                                                    println("   Price: ${th.getPrice()}")
                                                                    println("   Weight: ${th.getWeight()}")
                                                                    println("   Delivery cost: ${ row.calculateDeliveryCost(th.getTrackingNumber()) }")
                                                                    println("   Courier: ${row.getName()}")
                                                                    println("   Tracking number: ${th.getTrackingNumber()}")

                                                                    if (th.getStepp().name == "DELIVERED")
                                                                        println("   Delivery Time: ${th.getDeliveryTimeFormatted()}")
                                                                    else
                                                                        println("   Estimated Delivery Time: ${th.getEstimatedDeliveryTime()}")

                                                                    println()
                                                                }
                                                            }
                                                        }
                                                    }
                                                }

                                                // Show a package
                                                2 -> {
                                                    i = 1
                                                    for (row in couriers) {
                                                        for (col in row.users) {
                                                            if(col.getEmail() == USER.getEmail()) {
                                                                for (th in col.packages) {
                                                                    println("$i. ${th.getPackageName()}: #${th.getTrackingNumber()}")
                                                                    i++
                                                                }
                                                            }
                                                        }
                                                    }

                                                    println("0. Back")

                                                    option = enterNumber(0, i - 1)

                                                    if (option == 0) continue // Back

                                                    i = 1
                                                    seconder@for (row in couriers) {
                                                        for (col in row.users)
                                                            if (col.getEmail() == USER.getEmail()) {
                                                                for (th in col.packages) {
                                                                    if (th.getTrackingNumber() ==  USER.packages[option-1].getTrackingNumber()) {
                                                                        println("Package : ${th.getPackageName()}")
                                                                        println("   Recipient: ${th.getRecipient()}")
                                                                        println("   Sender: ${th.getSender()}")
                                                                        println("   Price: ${th.getPrice()}")
                                                                        println("   Weight: ${th.getWeight()}")
                                                                        println("   Delivery cost: ${ row.calculateDeliveryCost(th.getTrackingNumber())}")
                                                                        println("   Courier: ${row.getName()}")
                                                                        println("   Tracking number: ${th.getTrackingNumber()}")

                                                                        if (th.getStepp().name == "DELIVERED")
                                                                            println("   Delivery Time: ${th.getDeliveryTimeFormatted()}")
                                                                        else
                                                                            println("   Estimated Delivery Time: ${th.getEstimatedDeliveryTime()}")
                                                                        println()
                                                                        println("   Delivery Status: " + th.getStepp().name)

                                                                        if (th is FragilePackage) {
                                                                            println("   Is it Fragile: " + th.getIsFragile())
                                                                        }
                                                                        else if (th is PerishablePackage) {
                                                                            println("   Is it Perishable: " + th.getIsPerishable())
                                                                            println("   Production date: " + th.getProductionDate())
                                                                            println("   Shelf life: " + th.getShelfLife())
                                                                        }
                                                                        else if (th is HazardousPackage) {
                                                                            println("   Is it allow to deliver? : " + th.getIsAllow())
                                                                        }
                                                                        else if (th is OversizedPackage) {
                                                                            println("   Width: " + th.getWidth() + "cm")
                                                                            println("   Height: " + th.getHeight() + "cm")
                                                                            println("   Length: " + th.getLength() + "cm")
                                                                            println("   Volume: " + th.getVolume() + "m³")
                                                                        }

                                                                        println()
                                                                        break@seconder
                                                                    }
                                                                    i++
                                                                }

                                                            }
                                                    }
                                                } // end show a package

                                                // Track my package
                                                3 -> {
                                                    var isRight = false
                                                    var trackingNumber: String
                                                    print("Enter tracking number: ")
                                                    trackingNumber = readln()

                                                    for (row in couriers) {
                                                        for (col in row.users) {
                                                            if (col.getEmail() == USER.getEmail()) {
                                                                for (th in col.packages) {
                                                                    if (th.getTrackingNumber() == trackingNumber) {
                                                                        th.trackPackage()
                                                                        isRight = true
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    if(isRight == false) {
                                                        println("Tracking number is invalid!")
                                                    }
                                                    println("Please press Enter to Back")
                                                    readln()
                                                } // end track my package

                                            }

                                        }
                                    } // End Show Package
                                }

                            }


                        }

                        // Profile
                        3 -> {
                            var profile = true
                            while (profile) {
                                println("Profile\n")
                                println("Name: " + USER.getName())
                                println("Surname: " + USER.getSurname())
                                println("E-mail: " + USER.getEmail())
                                println("Phone: " + USER.getPhone())  // changeable
                                println("Gender: " + USER.getGender())
                                println("Address: " + USER.getAddressName()) // changeable
                                println("Personal No: " + USER.getPersonalNo())

                                println("1. Change the password")
                                println("2. Change the phone number")
                                println("3. Change the address")
                                println("0. Back")

                                when (enterNumber(0, 3)) {
                                    0 -> profile = false // Back

                                    // Change the password
                                    1 -> {
                                        users = changePassword(users, uNumber)
                                        USER = users[uNumber]
                                    }

                                    // Change the phone number
                                    2 -> {
                                        users = changePhoneNumber(users, uNumber)
                                        USER = users[uNumber]
                                    }

                                    // Change the address
                                    3 -> {
                                        users[uNumber].setAddressName(readln())
                                        USER = users[uNumber]
                                        println("The address is changed successfully...")
                                    }
                                }

                            }
                        }

                        // Calculator & Tariffs
                        4 -> {
                            if (couriers.isEmpty())
                                println("There isn't any courier!")

                            else {
                                var i = 1
                                for (row in couriers) {
                                    println("$i. ${row.getName()}:")
                                    println("    Price per Kg: ${row.getPricePerKg()}")
                                    i++
                                }
                            }

                            println("Enter a key to back: ")
                            readln()
                        }

                        else -> println("\nelse\n") // never should be
                    }

                } // while(userInterface)
            }

            // Admin Interface
            2 -> {
                var adminInterface = true
                while (adminInterface) {
                    println("1. Create a new courier") //admin
                    println("2. Delete the courier permanently") // admin
                    println("3. Get total revenue of courier(s)") // admin
                    println("4. Package Information") //admin
                    println("0. Sign out")

                    option = enterNumber(0, 4)

                    when(option) {
                        0 -> adminInterface = false // Sign out

                        // Create a new courier at ArrayList of couriers
                        1 -> {
                            var breakSubjectRequest: Boolean = false // bunu labeled breakle ede bilsen sil !!!
                            println("Enter the courier name: ")
                            courierName = readln()

                            // check that this courier with this name has already exist or not
                            for(row: Courier in couriers){
                                if(row.getName() == courierName){
                                    println("This courier has already exist!!!")
                                    breakSubjectRequest = true
                                    //conti@subjectrequest
                                }
                            }
                            if(breakSubjectRequest) continue


                            val phone: String
                            println("Enter phone number (+994XXXXXXXXX: ")
                            print("+994")
                            phone = readln()

                            print("E-mail: ")
                            val email: String = readln()

                            print("Address: ")
                            val address: String = readln()

                            print("Password: ")
                            val password: String = readln()


                            println("Enter delivery price per kg: ")
                            var pricePerKg: Float
                            do{
                                pricePerKg = readln().toFloat()
                                if (pricePerKg <= 0f) println("Delivery price must be more than 0!!! Please enter valid price: ")
                            } while(pricePerKg <= 0f)

                            couriers.add(Courier(courierName, phone, email, address, password, pricePerKg))
                            println("Courier is added successfully. You can add package to this courier. Courier name: $courierName")

                            for (row in users) {
                                couriers[couriers.size - 1].createUser(row)  // en son elave edilen couriere butun userleri elave edir
                            }
                        }

                        // Delete the courier permanently
                        2 -> {

                            var i = 1
                            for (row in couriers) {
                                println("$i. ${row.getName()}")
                                i++
                            }
                            println("0. Back")

                            option = enterNumber(0, i-1, "Which one do you want to delete? Enter a number: ")

                            if(option == 0) continue // Back

                            var d: Int = 0
                            if (couriers[option - 1].packages.size != 0 ) {
                                for (row in couriers[option - 1].packages) {
                                    if (row.getStepp().name == "DELIVERED") d++
                                }
                                if (couriers[option - 1].packages.size == d){
                                    println("${couriers[option - 1].getName()} courier is deleted successfully.")
                                    couriers.remove(couriers[option-1])
                                }
                                else {
                                    println("The courier you want to delete has packages to deliver so it cannot be deleted.\n" +
                                            "Firstly, this courier has to deliver these packages!")
                                }
                            }
                            else {
                                println("${couriers[option - 1].getName()} courier is deleted successfully.")
                                couriers.remove(couriers[option-1])
                            }

                        }

                        // Get total revenue of a courier
                        3 -> {
                            var getTotalRevenue = true
                            while (getTotalRevenue) {
                                println("1. Show revenues of all couriers")
                                println("2. Show revenue of a courier")
                                println("0. Back")

                                option = enterNumber(0, 2)

                                when(option) {
                                    0 -> getTotalRevenue = false // Back

                                    // Show revenues of all couriers
                                    1 -> {
                                        for (row in couriers) {
                                            println("Courier: ${row.getName()} -> Revenue: ${row.getTotalRevenue()}")
                                        }

                                        println("Do you want to print it?")
                                        condition = YorN("Didn't print...")
                                        if (condition) println("Printed...")

                                        println("Please press Enter to back")
                                        readln()
                                    }

                                    // Show revenue of a courier
                                    2 -> {
                                        var i = 1

                                        for (row in couriers) {
                                            println("$i. " + row.getName())
                                            i++
                                        }
                                        println("0. Back")

                                        option = enterNumber(0, i, "Select a courier")

                                        if (option == 0) {
                                            continue
                                        } // Back

                                        i = 1
                                        for (row in couriers) {
                                            if (option == i) {
                                                println("Courier: ${row.getName()} ->" +
                                                        " Revenue: ${row.getTotalRevenue()}")

                                                println()
                                                break
                                            }
                                            i++
                                        }

                                        println("Do you want to print it?")
                                        condition = YorN("Didn't print...")
                                        if (condition) println("Printed...")

                                        println("Please press Enter to back")
                                        readln()



                                    }
                                }

                            }
                        }

                        // Package Information
                        4 -> {
                            var packageInformation = true
                            while (packageInformation) {
                                println("1. Show all packages:")
                                println("2. Show a package")
                                println("0. Back")

                                option = enterNumber(0, 2)

                                when(option) {
                                    // Back
                                    0 -> packageInformation = false

                                    // Show all packages
                                    1 -> {
                                        for(row in couriers) {
                                            for (col in row.users) {
                                                for(th in col.packages) {
                                                    println("Package : ${th.getPackageName()} ")
                                                    println("   Sender: ${th.getSender()}")
                                                    println("   Recipient: ${th.getRecipient()}" + " - " + col.getPersonalNo())
                                                    println("   Price: ${th.getPrice()}")
                                                    println("   Weight: ${th.getWeight()}")
                                                    println("   Delivery cost: ${ row.calculateDeliveryCost(th.getTrackingNumber()) }")
                                                    println("   Courier: ${row.getName()}")
                                                    println("   Tracking number: ${th.getTrackingNumber()}")
                                                    println()
                                                }

                                            }

                                            println("\n")
                                        }
                                        println("Please press Enter to back")
                                        readln()
                                    }

                                    // Show a package
                                    2 -> {
                                        var i = 0
                                        var array = ArrayList<Package>()
                                        for (row in couriers) {
                                            for (col in row.packages){
                                                println("${i+1}. ${col.getPackageName()}: #${col.getTrackingNumber()}")
                                                i++
                                                array.add(col)
                                            }
                                        }
                                        println("0. Back")

                                        option = enterNumber(0, i)

                                        if(option == 0) continue // Back

                                        val packet = array[option - 1]
                                        seconder@for (row in couriers) {
                                            for (col in row.users) {
                                                for (th in col.packages)
                                                    if (th == packet) {
                                                        println("Package : ${th.getPackageName()} ")
                                                        println("   Sender: ${th.getSender()}")
                                                        println("   Recipient: ${th.getRecipient()}")
                                                        println("   Price: ${th.getPrice()}")
                                                        println("   Weight: ${th.getWeight()}")
                                                        println("   Delivery cost: ${ row.calculateDeliveryCost(th.getTrackingNumber()) }")
                                                        println("   Courier: ${row.getName()}")
                                                        println("   Tracking number: ${th.getTrackingNumber()}")
                                                        println()
                                                        break@seconder
                                                    }
                                                i++
                                            }
                                        }
                                    }
                                }

                            }
                        }

                        else -> println("\nelse\n") // never should be
                    }

                }


            }

            // Courier Interface
            3 -> {
                COURIER = couriers[uNumber]

                var courierInterface = true
                while (courierInterface) {
                    println("1. Package Information")
                    println("2. Package Operations")
                    println("3. Get Total Revenue")
                    println("0. Sign Out")

                    option = enterNumber(0, 3)

                    when(option){
                        0 -> courierInterface = false // Sign Out

                        // Package Information
                        1 -> {
                            var packageInformation = true
                            while (packageInformation) {
                                println("1. Show all packages:")
                                println("2. Show a package")
                                println("0. Back")

                                option = enterNumber(0, 2)

                                when(option) {
                                    // Back
                                    0 -> packageInformation = false

                                    // Show all packages
                                    1 -> {
                                        for (col in COURIER.users) {
                                            for(th in col.packages) {
                                                println("Package : ${th.getPackageName()} ")
                                                println("   Sender: ${th.getSender()}")
                                                println("   Recipient: ${th.getRecipient()}" + " - " + col.getPersonalNo())
                                                println("   Price: ${th.getPrice()}")
                                                println("   Weight: ${th.getWeight()}")
                                                println("   Delivery cost: ${ COURIER.calculateDeliveryCost(th.getTrackingNumber()) }")
                                                println("   Courier: ${COURIER.getName()}")
                                                println("   Tracking number: ${th.getTrackingNumber()}")
                                                println()
                                            }

                                        }

                                        println("\n")

                                    }

                                    // Show a package
                                    2 -> {
                                        var i = 0
                                        var array = ArrayList<Package>()

                                        for (col in COURIER.packages){
                                            println("${i+1}. ${col.getPackageName()}: #${col.getTrackingNumber()}")
                                            i++
                                            array.add(col)
                                        }

                                        println("0. Back")

                                        option = enterNumber(0, i)

                                        if(option == 0) continue // Back

                                        val packet = array[option - 1]

                                        seconder@for (col in COURIER.users) {
                                            for (th in col.packages)
                                                if (th == packet) {
                                                    println("Package : ${th.getPackageName()} ")
                                                    println("   Sender: ${th.getSender()}")
                                                    println("   Recipient: ${th.getRecipient()}")
                                                    println("   Price: ${th.getPrice()}")
                                                    println("   Weight: ${th.getWeight()}")
                                                    println("   Delivery cost: ${ COURIER.calculateDeliveryCost(th.getTrackingNumber()) }")
                                                    println("   Courier: ${COURIER.getName()}")
                                                    println("   Tracking number: ${th.getTrackingNumber()}")
                                                    println()
                                                    break@seconder
                                                }
                                            i++
                                        }

                                    }
                                }

                            }
                        }

                        // Package Operations
                        2 -> {
                            var packageOperations = true
                            while (packageOperations) {
                                var i: Int = 1

                                for(col in COURIER.packages) {
                                    println("$i. Package : ${col.getPackageName()} , #${col.getTrackingNumber()}")
                                    i++
                                }

                                println("0. Back")

                                var packageOption: Int = enterNumber(0, i - 1)

                                if(packageOption == 0) {
                                    packageOperations = false
                                    continue
                                } // Back

                                println("1. Update Package Step")
                                println("2. Deliver the package")
                                println("0. Back")

                                option = enterNumber(0, 2)

                                when(option) {
                                    0 -> continue //Back

                                    // Update Package Step
                                    1 -> {
                                        i = 1

                                        for(col in COURIER.packages) {
                                            if(packageOption == i) {
                                                // check that previous step is DELIVERED or not
                                                condition = true
                                                if (col.getStepp().name == "DELIVERED") {
                                                    condition = false
                                                }
                                                //
                                                else {
                                                    println("Package step is updated.")
                                                }
                                                val step = col.getStepp().ordinal
                                                col.updatePackageStep(step + 1)
                                                println("Current step: ${col.getStepp().name}")

                                                // update delivery time
                                                if (col.getStepp().name == "DELIVERED" && condition) {
                                                    col.setDeliveryTimeFormatted(LocalDate.now().toString())
                                                }
                                                break
                                            }
                                            i++
                                        }
                                    } // Update Package Step

                                    // Deliver Package
                                    2 -> {
                                        i = 1

                                        for(col in COURIER.packages) {
                                            if(packageOption == i) {
                                                // check that previous step is DELIVERED or not
                                                if (col.getStepp().name == "DELIVERED") {
                                                    println("This package is already delivered!")
                                                }
                                                //

                                                else {
                                                    col.deliverPackage()
                                                    println("Package is delivered.")
                                                    println("Current step: ${col.getStepp().name}")
                                                    // update delivery time
                                                    col.setDeliveryTimeFormatted(LocalDate.now().toString())
                                                }
                                                break
                                            }
                                            i++
                                        }

                                    } // Deliver Package

                                } // when

                            } // while(packageOperations)

                        } // Package Operation

                        // Get Total Revenue
                        3 -> {

                            println("Courier: ${COURIER.getName()} ->" +
                                    " Revenue: ${COURIER.getTotalRevenue()}")


                            println("Do you want to print it?")
                            condition = YorN("Didn't print...")
                            if (condition) println("Printed...")

                            println("Please press Enter to back")
                            readln()



                        }
                    }

                }

            }
        }



        println("\nSign Out!!!\n")
    }
//    println("\nPROCESS ENDED\n")
}

fun rateDelivery(trackingNumber: String, email: String, couriers: ArrayList<Courier>): ArrayList<Courier> {
    var option: Int = 0
    for (row in couriers) {
        // couriers.users.packages rate
        for (col in row.users) {
            if(col.getEmail() == email) {
                for (th in col.packages) {
                    if(th.getTrackingNumber() == trackingNumber) {
                        println("Package: " + th.getPackageName() + " - " + th.getTrackingNumber())
                        println("Rate us for better service:")
                        println("1. 1 point 😕")
                        println("2. 2 points🙁")
                        println("3. 3 points😑")
                        println("4. 4 points🙂")
                        println("5. 5 points😀")

                        option = enterNumber(1, 5)
                        println("Thanks for rating!")
                        th.setRating(option)
                    }
                }
            }
        }
        // couriers.packages rate
        for (col in row.packages) {
            if(col.getTrackingNumber() == trackingNumber) {
                col.setRating(option)
            }
        }
    }

    return couriers
}


fun YorN(message: String): Boolean {
    val sure: Boolean
    var x: String
    do{
        print("Enter Y/n: ")
        x = readln()
    } while(x != "Y" && x != "n")
    sure = x == "Y"

    if(!sure) {
        println(message)
        return false
    }
    return true
}

fun calculateWeight(): Float {
    val rangeStart = 0.1f
    val rangeStop = 101f
    return String.format("%.3f", Random.nextFloat() * (rangeStop - rangeStart) + rangeStart).toFloat()
}

fun setTrackingNumber(): String = Random.nextInt(1000, 10000).toString()

fun enterNumber(x: Int, y: Int) : Int {
    var option: Int
    print("Enter a number: ")
    do {
        option = readln().toInt()
        if (option !in x..y) print("Please enter a valid number: ")
        else return option
    } while (true)
}

fun enterNumber(x: Int, y: Int, message: String) : Int {
    var option: Int
    println(message)
    do {
        option = readln().toInt()
        if (option !in x..y) print("Please enter a valid number: ")
    } while (option !in x..y)
    return option
}

fun loginCourier(list: ArrayList<Courier>): Pair<Int, Boolean> {
    var cNumber = 0
    var condition: Boolean = true
    do {
        print("Name: ")
        val loggedName: String = readln()
        print("Password: ")
        val loggedPassword: String = readln()
        var auth = false

        for((i, row) in list.withIndex()) {
            if (loggedName == row.getName() && loggedPassword == row.getPassword()) {
                println("Hi,${row.getName()}.You are already login.")
                cNumber = i  // USERin users listinde hansi oldugunu teyin etmek ucundur
                auth = true
                condition = true
            }
        }

        if (!auth) {
            println("Username or password is incorrect!!!")
            println("1. Log in")
            println("0. Back")
            if (enterNumber(0, 1) == 0){
                condition = false
                break
            }
        }
    } while (!auth)

    return Pair(cNumber, condition)
}
fun loginUser(list: ArrayList<User>): Pair<Int, Boolean> {
    var uNumber = 0
    var condition: Boolean = true
    do {
        print("Email: ")
        val loggedName: String = readln()
        print("Password: ")
        val loggedPassword: String = readln()
        var auth = false

        for((i, row) in list.withIndex()) {
            if (loggedName == row.getEmail() && loggedPassword == row.getPassword()) {
                println("Hi,${row.getName()}.You are already login.")
                uNumber = i  // USERin users listinde hansi oldugunu teyin etmek ucundur
                auth = true
                condition = true
            }
        }

        if (!auth) {
            println("Username or password is incorrect!!!")
            println("1. Log in")
            println("0. Back")
            if (enterNumber(0, 1) == 0){
                condition = false
                break
            }
        }
    } while (!auth)

    return Pair(uNumber, condition)
}

fun register(list: ArrayList<User>): ArrayList<User> {
    println("Hi,please fill below.")
    print("Name: ")
    val name: String = readln()
    print("Surname: ")
    val surname: String = readln()
    print("Age: ")
    val age: Int = readln().toInt()
    print("Gender: ")
    val gender: String = readln()
    print("Phone Number: ")
    val phone: String = readln()
    print("Address: ")
    val address: String = readln()
    print("Personal No: ")
    val personalNo: String = readln()
    print("E-mail: ")
    val email: String = readln()
    print("Password: ")
    val password: String = readln()

    list.add(User(name, surname, age, gender, phone, email, address, personalNo, password))
    println("Account is created successfully. Please login!")

    return list
}

fun changePassword(list: ArrayList<User>, uNumber: Int): ArrayList<User> {
    print("Enter old password: ")
    var oldP: String
    do {
        oldP = readln()
        if (oldP != list[uNumber].getPassword()) {
            println("Password isn't correct")

            print("Do you want to continue?")
            if (!YorN("Password isn't changed"))  return list

            print("Enter old password: ")
        }
    } while (oldP != list[uNumber].getPassword())

    print("Enter new password: ")
    val newP = readln()
    list[uNumber].setPassword(newP)
    println("Password is changed successfully!")

    return list
}

fun changePhoneNumber(list: ArrayList<User>, uNumber: Int): ArrayList<User> {
    var newPhone: String
    println("Ex: +994-XX-XXX-XX-XX)")
    print("Enter new phone number: +994")
    do {
        newPhone = readln()
        if (newPhone.length != 9) {
            println("Phone number isn't correct!")

            print("Do you want to continue? Y/n: ")
            if (!YorN("Phone number isn't changed"))  return list

            print("Enter a valid phone number: +994")
        }
    } while (newPhone.length != 9)


    list[uNumber].setPhone("+994$newPhone")
    println("Phone number is changed successfully!")
    return list
}