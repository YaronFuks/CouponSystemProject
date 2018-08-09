package TestingArea;

/**
 * @author yaron fuks
 */

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Dao.CompanyDAO;
import Dao.CouponDAO;
import DaoDB.CompanyDaoDB;
import DaoDB.CouponDaoDB;
import Facade.ClientType;
import Facade.CompanyFacade;

public class CompanyFacadeTest {
	public static void main(String[] args) throws CouponSystemException {

		/**
		 * 
		 * Get Instance from Coupon System
		 * 
		 */
		CouponSystem system = CouponSystem.getInstance();
		CouponDAO couponDAO = new CouponDaoDB();
		CompanyDAO companyDAO = new CompanyDaoDB();
		long companyId;
		String companyName;
		String companyPassword;

		/**
		 * In This Class we will check the CompanyFacade Methods
		 */

		/**
		 * Login as Company Facade
		 */
		CompanyFacade companyFacade = (CompanyFacade) system.login("dell", "1111", ClientType.COMPANY);
		// companyId = companyFacade.getCompany().getId();
		// companyName = companyFacade.getCompany().getCompName();
		// companyPassword = companyFacade.getCompany().getPassword();
		/**
		 * Create Coupon
		 */
		// Date startDate = DateMaker.Date(2018, 5, 14);
		// Date endDate = DateMaker.Date(2018, 6, 15);
		// try {
		// companyFacade.createCoupon(new Coupon("dell vostro 35", startDate, endDate,
		// 20, CouponType.COMPUTERS,
		// "10% off", 100, "i5 intel inside"));
		// } catch (CouponSystemException e) {
		// System.out.println("Can not Create Coupon");
		// }
		//
		// Date startDate2 = DateMaker.Date(2018, 5, 14);
		// Date endDate2 = DateMaker.Date(2018, 6, 15);
		// try {
		// companyFacade.createCoupon(new Coupon("dell vostro 70", startDate2, endDate2,
		// 20, CouponType.COMPUTERS,
		// "20% off", 60, "i7 intel inside"));
		// } catch (CouponSystemException e) {
		// System.out.println("Can not Create Coupon");
		// }
		//
		// Date startDate3 = DateMaker.Date(2018, 5, 14);
		// Date endDate3 = DateMaker.Date(2018, 6, 15);
		// try {
		// companyFacade.createCoupon(new Coupon("dell vostro 20", startDate3, endDate3,
		// 20, CouponType.COMPUTERS,
		// "50% off", 60, "i3 intel inside"));
		// } catch (CouponSystemException e) {
		// System.out.println("Can not Create Coupon");
		// }

		/**
		 * Delete Coupon
		 */
		// Coupon coupon = new Coupon();
		// coupon.setId(313);
		// try {
		// companyFacade.deleteCoupon(coupon);
		// } catch (CouponSystemException e) {
		//
		// }

		/**
		 * Updade Coupon
		 ***/

		// Date startDate = DateMaker.Date(2018, 5, 14);
		// Date endDate = DateMaker.Date(2018, 6, 20);
		// Coupon coupon = new Coupon("dell vostro 20", startDate, endDate, 20,
		// CouponType.COMPUTERS, "sale", 1250, "i3");
		// coupon.setId(314);
		// try {
		// companyFacade.updateCoupon(coupon);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * Read Login Company
		 */
		// System.out.println(companyFacade.getCompany());

		/**
		 * Read Company Coupons
		 */
		// try {
		// ArrayList<Coupon> list = companyFacade.readAllCoupon();
		// for (Coupon c : list) {
		// System.out.println(c);
		// }
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * Read Company Coupons by Type
		 */
		// try {
		// ArrayList<Coupon> list =
		// companyFacade.readAllCouponByType(CouponType.COMPUTERS);
		// for (Coupon c : list) {
		// System.out.println(c);
		// }
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * Read Company Coupons by Until Price
		 */
		// try {
		// ArrayList<Coupon> list = companyFacade.readAllCouponByUntilPrice(200.00);
		// for (Coupon c : list) {
		// System.out.println(c);
		// }
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * Read Company Coupons by Until Date
		 */
		// try {
		// ArrayList<Coupon> list = companyFacade.readAllCouponByUntilDate("2018-6-15");
		// for (Coupon c : list) {
		// System.out.println(c);
		// }
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		// stop the task
		// system.ShutDown();
	}
}
