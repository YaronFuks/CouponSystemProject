package DailyTaskThread;

/**
 * @author yaron fuks
 */

import CouponSystemExceptions.CouponSystemException;
import Dao.CouponDAO;
import DaoDB.CouponDaoDB;

/**
 * This class is a Thread that runs on CouponSystem starts and check all coupon
 * that the end date is passed and delete them from all tables that they exists
 * 
 */

public class DailyCouponExpirationTask extends Thread {

	private static boolean quit = false; // set the quit on false
	private static boolean checkCreation = false; // set the checkCreation on false
	private static CouponDAO couponDAO;
	private static DailyCouponExpirationTask dcet = null; // set the Object on null

	public DailyCouponExpirationTask() {
		quit = false;
		couponDAO = new CouponDaoDB();
	}

	/**
	 * initiate the Thread
	 * 
	 */
	public static boolean initThread() {
		if (checkCreation == false) {
			dcet = new DailyCouponExpirationTask();
			checkCreation = true;
			return true;
		}
		return false;

	}

	/**
	 * get Instance of the Thread
	 * 
	 */
	public static DailyCouponExpirationTask getInstance() {
		return dcet;

	}

	/**
	 * Stop the Thread
	 * 
	 */
	public static void StopTask() {
		quit = true;
		dcet.interrupt();
	}

	/**
	 * Delete the expired coupons by using the Method in the CouponDao
	 * 
	 */
	@Override
	public void run() {
		while (!quit) {
			try {
				couponDAO.deleteExpired();
			} catch (CouponSystemException e) {

				e.printStackTrace();
			}

			try {
				sleep(86400000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
