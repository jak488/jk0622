# Rental agreement generator for fictional hardware store LowesDepot

A simple backend application for generating rental agreements at checkout.
To run the checkout process, initialize the RentalAgreementGenerator and call rentalAgreementGenerator.Checkout().
This will return a RentalAgreement instance.
To print the rental agreement to the console, call rentalAgreement.printRentalAgreement().

Program will throw an exception if an invalid tool code is provided, if rental days < 1, or if discount percent > 100 or < 0
Note: Program will allow for checkout dates in the past, and that charge days can be 0 if the entire rental period if over a weekend for example.

Written in:
* Java 8
* Tests using JUnit 5
* Maven

## Running the tests

* To run the unit tests, run 'mvn test' from the project's root directory.
* To run the integration tests as well, run 'mvn verify' from the project's root directory.

## Tests & Integration Tests

* Unit test cases can be found in `src/test/java/RentalAgreementGeneratorTest.java` 
* Unit tests cover the six test cases in the problem statement, plus an additional test case for invalid tool codes.

* Integration test cases can be found in in `src/test/java/RentalAgreementGeneratorTestIT.java`
* Two integration tests check the console log output of RentalAgreement.printRentalAgreement()

