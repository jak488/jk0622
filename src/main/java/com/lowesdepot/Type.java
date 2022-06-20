/**
 * 
 */
package com.lowesdepot;

/**
 * @author jack.kearns
 *
 */
public enum Type {
	
	Ladder(1.99, true, false), Chainsaw(1.49, false, true), Jackhammer(2.99, false, false);  

	public final double dailyCharge;
	public final boolean weekendCharge;
	public final boolean holidayCharge;
	
	/**
	 * Constructor
	 */
	private Type(double dailyCharge, boolean weekendCharge, boolean holidayCharge) {
		this.dailyCharge = dailyCharge;
		this.weekendCharge = weekendCharge;
		this.holidayCharge = holidayCharge;
	}
	
	

}
