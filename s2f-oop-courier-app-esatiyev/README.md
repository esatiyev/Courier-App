[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/DYC4-m1T)
Title: Courier App

Structure:

**Courier App Assignment**

You are tasked with building a Courier app that allows users to send and receive packages. Your app should be built using Object-Oriented Programming (OOP) principles, including the use of classes, abstract classes, interfaces, inheritance, encapsulation, and polymorphism.

**Requirements**

Your app should meet the following requirements:

1. The app should have an abstract class Package that represents a package. It should have the following attributes:
  - tracking\_number: a unique tracking number for the package (string)
  - sender: the name of the package sender (string)
  - recipient: the name of the package recipient (string)
  - weight: the weight of the package in kilograms (float)
  - price: the price of the package in dollars (float)
2. The app should have a Courier class that represents a courier company. It should have the following attributes:
  - packages: a list of packages (list of Package objects)
  - add\_package(): a method that adds a new package to the list of packages.
  - remove\_package(): a method that removes a package from the list of packages.
  - get\_total\_revenue(): a method that calculates and returns the total revenue earned by the courier company.
3. The app should have the following concrete subclasses of Package:
  - RegularPackage: represents a regular package, with a tracking number, sender, recipient, weight, and price.
  - FragilePackage: represents a fragile package, with a tracking number, sender, recipient, weight, and price. It should have an additional attribute is\_fragile that indicates whether the package is fragile or not.
4. The app should have an interface PackageDelivery that represents a package delivery service. It should have the following methods:
  - get\_delivery\_time(): calculates the estimated delivery time for the package.
  - deliver\_package(): delivers the package to the recipient.
5. The app should have a concrete class ExpressPackage that represents an express package delivery service. It should inherit from RegularPackage and implement the PackageDelivery interface. It should have the following attributes:
  - delivery\_time: the estimated delivery time for the package (datetime)
  - get\_delivery\_time(): calculates the estimated delivery time for the package based on the distance between the sender and the recipient.
  - deliver\_package(): delivers the package to the recipient and updates the delivery time.
6. The app should use encapsulation to ensure that the attributes of each class are protected and can only be accessed through getter and setter methods.
7. The app should use polymorphism to allow different packages to have different delivery services and estimated delivery times.

**Bonus Requirements**

If you finish the above requirements add additional features to your app. Here are some ideas:

- Allow users to track their packages using a tracking number.
- Add a feature that allows users to choose different delivery methods (e.g. standard, express, overnight).
- Create additional subclasses of Package for different types of packages (e.g. oversized, hazardous, perishable, etc.).
- Add a feature that allows users to rate their delivery experience and provide feedback.
