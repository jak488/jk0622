/**
 * 
 */
package com.lowesdepot;

import java.util.Date;

/**
 * @author jack.kearns
 *
 */
public class RentalAgreementGenerator {
	
	public RentalAgreement Checkout(String toolCode, Date checkoutDate, Integer rentalDays, Integer discountPercent) throws ValidationException {
		return new RentalAgreement(toolCode, checkoutDate, rentalDays, discountPercent);
	}
	

}
