package TestingArea;

/**
 * @author yaron fuks
 */

import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Facade.ClientType;
import Facade.CustomerFacade;

public class CustomerFacadeTest {

	public static void main(String[] args) throws CouponSystemException {

		CouponSystem system = CouponSystem.getInstance();

		/**
		 * In This Class we will check the CustomerFacade Methods
		 */

		/**
		 * Login as Customer Facade
		 */

		CustomerFacade customerFacade = (CustomerFacade) system.login("ivory", "1122", ClientType.CUSTOMER);
		// System.out.println(customerFacade.getCustomer());

		/**
		 * Purchase Coupon
		 */
		// Coupon coupon = new Coupon();
		// coupon.setId(314);
		// try {
		// customerFacade.purchaseCoupon(coupon);
		// } catch (CouponSystemException e) {
		// System.out.println(e.getMessage());
		// }

		/**
		 * view Purchased Coupon
		 */
		// try {
		// ArrayList<Coupon> list = customerFacade.getAllPurchasedCoupon();
		// for (Coupon c : list)
		// System.out.println(c);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * view Purchased Coupon by Type
		 */
		// try {
		// ArrayList<Coupon> list =
		// customerFacade.getAllPurchasedCouponByType(CouponType.COMPUTERS);
		// for (Coupon c : list)
		// System.out.println(c);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		/**
		 * view Purchased Coupon by Until Price
		 */
		// try {
		// ArrayList<Coupon> list = customerFacade.getAllPurchasedCouponByPrice(100);
		// for (Coupon c : list)
		// System.out.println(c);
		// } catch (CouponSystemException e) {
		// e.printStackTrace();
		// }

		// stop the task
		// system.ShutDown();

	}
}
