/**
 * 
 */
package com.lowesdepot;

/**
 * @author jack.kearns
 *
 */
public enum Tool {
	
	CHNS("CHNS", Type.Chainsaw, "Stihl"), LADW("LADW", Type.Ladder, "Werner"), JAKD("JAKD", Type.Jackhammer, "DeWalt"), JAKR("JAKR",Type.Jackhammer, "Ridgid");  

	public final String code;
	public final Type type;
	public final String brand;
	
	/**
	 * Constructor
	 */
	private Tool(String code, Type type, String brand) {
		this.code = code;
		this.type = type;
		this.brand = brand;
	}
	
	

}
