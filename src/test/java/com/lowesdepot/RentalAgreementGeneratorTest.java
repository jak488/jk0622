package com.lowesdepot;


import com.lowesdepot.RentalAgreementGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 * Unit test for RentalAgreementGenerator.java
 * <p/>
 * A unit test aims to test all code and code paths of a specific class.
 */
public class RentalAgreementGeneratorTest {
	
	@Test
    public void test1() throws ValidationException, ParseException {
    	String date_string = "09/03/15";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");   
        Date checkoutDate = formatter.parse(date_string);  
        try {
        	RentalAgreementGenerator rag = new RentalAgreementGenerator();
            RentalAgreement agr = rag.Checkout("JAKR", checkoutDate, 5, 101);
        	fail("Validation Exception not thrown; discount is over 100%");
        } catch (ValidationException ve) {
        	assertEquals("discount percent must be between 0 and 100 inclusive", ve.getMessage());
        }
    }

	@Test
    public void test2() throws ValidationException, ParseException {
    	String date_string = "07/02/20";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");   
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("LADW", checkoutDate, 3, 10);
       	assertEquals("LADW", agr.getToolCode());
        assertEquals("Ladder", agr.getToolType());
        assertEquals("Werner", agr.getToolBrand());
        assertEquals(2, (int) agr.getChargeDays());
        assertEquals(3, (int) agr.getRentalDays());
       	assertEquals("3.98", String.format("%.2f", agr.getPreDiscountCharge()));
       	assertEquals("1.99", String.format("%.2f", agr.getDailyRentalCharge()));
        assertEquals(10, (int) agr.getDiscountPercent());
        assertEquals(checkoutDate, agr.getCheckoutDate());
        Date dueDate = formatter.parse("07/05/20");
        assertEquals(dueDate, agr.getDueDate());
       	assertEquals("0.40", String.format("%.2f", agr.getDiscountAmount()));
       	assertEquals("3.58", String.format("%.2f", agr.getFinalCharge()));
        agr.printRentalAgreement();
    }
	
	@Test
    public void test3() throws ValidationException, ParseException {
    	String date_string = "07/02/15";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");     
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("CHNS", checkoutDate, 5, 25);
        assertEquals("CHNS", agr.getToolCode());
        assertEquals("Chainsaw", agr.getToolType());
        assertEquals("Stihl", agr.getToolBrand());
       	assertEquals(3, (int) agr.getChargeDays());
        assertEquals(5, (int) agr.getRentalDays());
       	assertEquals("4.47", String.format("%.2f", agr.getPreDiscountCharge()));
       	assertEquals("1.49", String.format("%.2f", agr.getDailyRentalCharge()));
        assertEquals(25, (int) agr.getDiscountPercent());
        assertEquals(checkoutDate, agr.getCheckoutDate());
        Date dueDate = formatter.parse("07/07/15");
        assertEquals(dueDate, agr.getDueDate());
       	assertEquals("1.12", String.format("%.2f", agr.getDiscountAmount()));
       	assertEquals("3.35", String.format("%.2f", agr.getFinalCharge()));
        agr.printRentalAgreement();
    }
	
	@Test
    public void test4() throws ValidationException, ParseException {
    	String date_string = "09/03/15";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy"); 
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("JAKD", checkoutDate, 6, 0);
        assertEquals("JAKD", agr.getToolCode());
        assertEquals("Jackhammer", agr.getToolType());
        assertEquals("DeWalt", agr.getToolBrand());
       	assertEquals(4, (int) agr.getChargeDays());
        assertEquals(6, (int) agr.getRentalDays());
       	assertEquals("11.96", String.format("%.2f", agr.getPreDiscountCharge()));
       	assertEquals("2.99", String.format("%.2f", agr.getDailyRentalCharge()));
        assertEquals(0, (int) agr.getDiscountPercent());
        assertEquals(checkoutDate, agr.getCheckoutDate());
        Date dueDate = formatter.parse("09/09/15");
        assertEquals(dueDate, agr.getDueDate());
       	assertEquals("0.00", String.format("%.2f", agr.getDiscountAmount()));
       	assertEquals("11.96", String.format("%.2f", agr.getFinalCharge()));
        agr.printRentalAgreement();
    }
	
	@Test
    public void test5() throws ValidationException, ParseException {
    	String date_string = "07/02/15";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");   
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("JAKR", checkoutDate, 9, 0);
        assertEquals("JAKR", agr.getToolCode());
        assertEquals("Jackhammer", agr.getToolType());
        assertEquals("Ridgid", agr.getToolBrand());
       	assertEquals(5, (int) agr.getChargeDays());
       	assertEquals(9, (int) agr.getRentalDays());
        assertEquals(checkoutDate, agr.getCheckoutDate());
        Date dueDate = formatter.parse("07/11/15");
        assertEquals(dueDate, agr.getDueDate());
       	assertEquals("2.99", String.format("%.2f", agr.getDailyRentalCharge()));
       	assertEquals("14.95", String.format("%.2f", agr.getPreDiscountCharge()));
       	assertEquals(0, (int) agr.getDiscountPercent());
       	assertEquals("0.00", String.format("%.2f", agr.getDiscountAmount()));
       	assertEquals("14.95", String.format("%.2f", agr.getFinalCharge()));
        agr.printRentalAgreement();
    }
	
	@Test
    public void test6() throws ValidationException, ParseException {
    	String date_string = "07/02/20";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");     
        Date checkoutDate = formatter.parse(date_string);  
        RentalAgreementGenerator rag = new RentalAgreementGenerator();
        RentalAgreement agr = rag.Checkout("JAKR", checkoutDate, 4, 50);
        assertEquals("JAKR", agr.getToolCode());
        assertEquals("Jackhammer", agr.getToolType());
        assertEquals("Ridgid", agr.getToolBrand());
       	assertEquals(1, (int) agr.getChargeDays());
       	assertEquals(4, (int) agr.getRentalDays());
        assertEquals(checkoutDate, agr.getCheckoutDate());
        Date dueDate = formatter.parse("07/06/20");
        assertEquals(dueDate, agr.getDueDate());
       	assertEquals("2.99", String.format("%.2f", agr.getDailyRentalCharge()));
       	assertEquals("2.99", String.format("%.2f", agr.getPreDiscountCharge()));
       	assertEquals(50, (int) agr.getDiscountPercent());
       	assertEquals("1.49", String.format("%.2f", agr.getDiscountAmount()));
       	assertEquals("1.50", String.format("%.2f", agr.getFinalCharge()));
        agr.printRentalAgreement();
    }
	
	// adding extra test to check what happens when we enter an invalid tool code
	@Test
    public void test7() throws ValidationException, ParseException {
    	String date_string = "09/03/15";
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");  
        Date checkoutDate = formatter.parse(date_string);  
        try {
        	RentalAgreementGenerator rag = new RentalAgreementGenerator();
            RentalAgreement agr = rag.Checkout("INVALID", checkoutDate, 5, 30);
        	fail("Validation Exception not thrown; toolcode invalid");
        } catch (ValidationException ve) {
        	assertEquals("Invalid toolCode", ve.getMessage());
        }
    }
	
}
