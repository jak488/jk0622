/**
 * 
 */
package com.lowesdepot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jack.kearns
 *
 */
class RentalAgreement {
	
	private String toolCode;
	private String toolType;
	private String toolBrand;
	private Date checkoutDate;
	private Integer rentalDays;
	private Date dueDate;
	private double dailyRentalCharge;
	private Integer chargeDays;
	private double preDiscountCharge;
	private Integer discountPercent;
	private double discountAmount;
	private double finalCharge;
	
	public String getToolCode() {
		return this.toolCode;
	}
	
	public String getToolType() {
		return this.toolType;
	}
	
	public String getToolBrand() {
		return this.toolBrand;
	}
	
	public Date getCheckoutDate() {
		return this.checkoutDate;
	}
	
	public Integer getRentalDays() {
		return this.rentalDays;
	}
	
	public Date getDueDate() { 
		return this.dueDate;
	}
	
	public double getDailyRentalCharge() {
		return this.dailyRentalCharge;
	}
	
	public Integer getChargeDays() {
		return this.chargeDays;
	}
	
	public double getPreDiscountCharge() {
		return this.preDiscountCharge;
	}
	
	public Integer getDiscountPercent() {
		return this.discountPercent;
	}
	
	public double getDiscountAmount() {
		return this.discountAmount;
	}
	
	public double getFinalCharge() {
		return this.finalCharge;
	}
	
	/**
	 * Constructor
	 * @throws ValidationException 
	 */
	public RentalAgreement(String toolCode, Date checkoutDate, Integer rentalDays, Integer discountPercent) throws ValidationException {
		validateInputs(rentalDays, discountPercent, toolCode);
		
		Tool tool = Tool.valueOf(toolCode);
		this.toolCode = tool.code;
		this.toolType = tool.type.name();
		this.toolBrand = tool.brand;
		this.checkoutDate = checkoutDate;
		this.rentalDays = rentalDays;
		this.dailyRentalCharge = tool.type.dailyCharge;
		this.discountPercent = discountPercent;
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.checkoutDate);
		cal.add(Calendar.DATE, this.rentalDays);
		this.dueDate = cal.getTime();
		
		this.chargeDays = this.calcChargeDays(tool.type, checkoutDate, rentalDays);
		
		this.preDiscountCharge = roundToTwoDecimals(this.chargeDays * this.dailyRentalCharge);
		this.discountAmount = roundToTwoDecimals(this.preDiscountCharge * this.discountPercent * 0.01f);
		this.finalCharge = this.preDiscountCharge - this.discountAmount;
	}
	
	public void printRentalAgreement() {
		System.out.println("Tool code: " + this.toolCode);
		System.out.println("Tool type: " + this.toolType);
		System.out.println("Tool brand: " + this.toolBrand);
		System.out.println("Rental days: " + this.rentalDays);

		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
		System.out.println("Checkout date: " + sdf.format(this.checkoutDate));
		System.out.println("Due date: " + sdf.format(this.dueDate));
		
		System.out.println("Daily rental charge: $" + String.format("%.02f", this.dailyRentalCharge));
		System.out.println("Charge days: " + this.chargeDays);
		System.out.println("Pre-discount charge: $" + String.format("%.02f", this.preDiscountCharge));
		System.out.println("Discount percentage: " + this.discountPercent + "%");
		System.out.println("Discount amount: $" + String.format("%.02f", this.discountAmount));
		System.out.println("Final charge: $" + String.format("%.02f", this.finalCharge));
	}
	
	private void validateInputs(Integer rentalDays, Integer discountPercent, String toolCode) throws ValidationException {
		if(rentalDays < 1) {
			throw new ValidationException("rental days must be greater than or equal to 1");
		}
		if(discountPercent > 100 || discountPercent < 0) {
			throw new ValidationException("discount percent must be between 0 and 100 inclusive");
		}
		
		try {
			Tool tool = Tool.valueOf(toolCode);
		} catch(IllegalArgumentException iae) {
			throw new ValidationException("Invalid toolCode");
		}
	}
	
	private double roundToTwoDecimals(double unroundedNumber) {
		BigDecimal bd = new BigDecimal(unroundedNumber);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
	
	private Integer calcChargeDays(Type type, Date checkoutDate, Integer rentalDays) {
		Integer chargeDays = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(checkoutDate);
		for(int i = 0; i < rentalDays; i++) {
			cal.add(Calendar.DATE, 1);
			if(!type.weekendCharge && (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
				continue;
			}
			else if(!type.holidayCharge && this.isHoliday(cal)) {
				continue;
			}
			else {
				chargeDays++;
			}
		}
		return chargeDays;
	}
	
	private boolean isHoliday(Calendar cal) {
		// first, check for Independence Day.  Not observed on weekends
		if(4 == cal.get(Calendar.DAY_OF_MONTH) && Calendar.JULY == cal.get(Calendar.MONTH) && Calendar.SATURDAY != cal.get(Calendar.DAY_OF_WEEK)
				&& Calendar.SUNDAY != cal.get(Calendar.DAY_OF_WEEK)) {
			return true;
		}
		
		// then, check for Friday July 3 and Monday July 5 in case the 4th occurs on a weekend
		if(3 == cal.get(Calendar.DAY_OF_MONTH) && Calendar.JULY == cal.get(Calendar.MONTH) && Calendar.FRIDAY == cal.get(Calendar.DAY_OF_WEEK)) {
			return true;
		}
		else if(5 == cal.get(Calendar.DAY_OF_MONTH) && Calendar.JULY == cal.get(Calendar.MONTH) && Calendar.MONDAY == cal.get(Calendar.DAY_OF_WEEK)) {
			return true;
		}
		
		// now, check for Labor Day
		LocalDate septFirst = LocalDate.of(Calendar.getInstance().get(Calendar.YEAR), Month.SEPTEMBER , 1 ); 
		LocalDate firstMonday = septFirst.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)) ;
		if(9 == cal.get(Calendar.MONTH) && firstMonday.getDayOfMonth() == cal.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}
	
	

}
