package com.lowesdepot;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lowesdepot.RentalAgreementGenerator;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Integration test for RentalAgreementGenerator.
 * <p>
 * Checks the log output from RentalAgreement object
 */
public class RentalAgreementGeneratorTestsIT {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void before() {
        // By putting our own PrintStream in the place of the normal System.out,
        // the output produced by the application can be verified.
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    public void cleanUp() {
        // Restore the original System.out to prevent weirdness in any following tests.
        System.setOut(originalOut);
    }
    
    @Test
    public void integrationTest1() throws ValidationException, ParseException {
    	String date_string = "07/02/2020";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");      
        //Parsing the given String to Date object
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("LADW", checkoutDate, 3, 10);
        agr.printRentalAgreement();
        assertThat(out.toString(), containsString("Tool code: LADW"));
        assertThat(out.toString(), containsString("Tool type: Ladder"));
        assertThat(out.toString(), containsString("Rental days: 3"));
        assertThat(out.toString(), containsString("Checkout date: 07/02/20")); 
        assertThat(out.toString(), containsString("Due date: 07/05/20")); 
        assertThat(out.toString(), containsString("Daily rental charge: $1.99"));
        assertThat(out.toString(), containsString("Charge days: 2"));
        assertThat(out.toString(), containsString("Pre-discount charge: $3.98"));
        assertThat(out.toString(), containsString("Discount percentage: 10%"));
        assertThat(out.toString(), containsString("Discount amount: $0.40"));
        assertThat(out.toString(), containsString("Final charge: $3.58"));
    }
    
    @Test
    public void integrationTest2() throws ValidationException, ParseException {
    	String date_string = "07/02/15";
        //Instantiating the SimpleDateFormat class
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");      
        //Parsing the given String to Date object
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("CHNS", checkoutDate, 5, 25);
        agr.printRentalAgreement();
        assertThat(out.toString(), containsString("Tool code: CHNS"));
        assertThat(out.toString(), containsString("Tool type: Chainsaw"));
        assertThat(out.toString(), containsString("Rental days: 5"));
        assertThat(out.toString(), containsString("Checkout date: 07/02/15")); 
        assertThat(out.toString(), containsString("Due date: 07/07/15")); 
        assertThat(out.toString(), containsString("Daily rental charge: $1.49"));
        assertThat(out.toString(), containsString("Charge days: 3"));
        assertThat(out.toString(), containsString("Pre-discount charge: $4.47"));
        assertThat(out.toString(), containsString("Discount percentage: 25%"));
        assertThat(out.toString(), containsString("Discount amount: $1.12"));
        assertThat(out.toString(), containsString("Final charge: $3.35"));
    }
}
