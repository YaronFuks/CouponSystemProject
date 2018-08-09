package Facade;

/**
 * @author yaron fuks
 */

import java.util.ArrayList;

import CouponSystemExceptions.CouponSystemException;
import Dao.CouponDAO;
import Dao.CustomerDAO;
import DaoDB.CouponDaoDB;
import DaoDB.CustomerDaoDB;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerFacade implements ClientFacade {

	/**
	 * create attributes of Coupon DAO & Customer DAO
	 */
	private CustomerDAO customerDao = new CustomerDaoDB();
	private CouponDAO couponDao = new CouponDaoDB();

	private Customer customer;

	public CustomerFacade(Customer customer) {
		super();
		this.customer = customer;

	}

	/**
	 * Purchase Coupon Method first we check if the Coupon have more then 1 Coupon
	 * left, if not we Throw Exception, if yes we will purchase the Coupon and put a
	 * record of this purchase in Customer_coupon Table also we will set the amount
	 * of the coupon (amount-1) and update the amount
	 * 
	 * @param coupon
	 * 
	 * 
	 */
	public void purchaseCoupon(Coupon coupon) throws CouponSystemException {

		Coupon c = couponDao.readCoupon(coupon.getId());
		if (c.getAmount() < 1) {
			throw new CouponSystemException("No amount left to purchase");
		}

		couponDao.purchaseCoupon(customer, coupon);
		c.setAmount(c.getAmount() - 1);
		couponDao.updateCoupon(c);

	}

	/**
	 * This method returns All Purchased Coupon as a list
	 */
	public ArrayList<Coupon> getAllPurchasedCoupon() throws CouponSystemException {
		ArrayList<Coupon> couponList = couponDao.readAllCoupons(customer);
		return couponList;
	}

	/**
	 * This method returns All Purchased Coupon by single Type that we choose as a
	 * list
	 */
	public ArrayList<Coupon> getAllPurchasedCouponByType(Coupon.CouponType type) throws CouponSystemException {
		ArrayList<Coupon> couponList = couponDao.getCouponByType(type, this.customer);
		return couponList;
	}

	/**
	 * This method returns All Purchased Coupon by Until Price that we choose as a
	 * list
	 */
	public ArrayList<Coupon> getAllPurchasedCouponByPrice(double price) throws CouponSystemException {
		ArrayList<Coupon> couponList = couponDao.getCouponByUntilPrice(price, this.customer);
		return couponList;
	}

	/**
	 * This method returns the Current Customer
	 */
	public Customer getCustomer() {
		return customer;
	}

}
