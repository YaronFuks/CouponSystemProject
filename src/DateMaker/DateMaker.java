package DateMaker;

/**
 * @author yaron fuks
 */

import java.sql.Date;

public class DateMaker {

	/**
	 * in this class i made a working date maker
	 * 
	 * @param year
	 *            enter year (int type)
	 * 
	 * @param month
	 *            enter month (int type)
	 * 
	 * @param day
	 *            enter day (int type)
	 * 
	 * @return date in the formation of year-month-day
	 */
	public static Date Date(int year, int month, int day) {
		String s = year + "-" + month + "-" + day;
		Date date = Date.valueOf(s);
		return date;
	}
}