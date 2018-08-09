package Dao;

/**
 * @author yaron fuks
 */

import java.util.ArrayList;

import CouponSystemExceptions.CouponSystemException;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Coupon.CouponType;
import JavaBeans.Customer;

public interface CouponDAO {

	/**
	 * Create Coupon Method by entering a
	 * 
	 * @param Coupon
	 * 
	 * 
	 */

	void createCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * read Coupon Method by
	 * 
	 * @param couponId
	 * 
	 *            that is entered and
	 * 
	 * @return Coupon
	 * 
	 */

	Coupon readCoupon(long couponId) throws CouponSystemException;

	/**
	 * Update Coupon record Method by entering a
	 * 
	 * @param coupon
	 * 
	 * 
	 */

	void updateCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * Delete Coupon record Method by entering a
	 * 
	 * @param Coupon
	 * 
	 * 
	 */

	void deleteCoupon(Coupon coupon) throws CouponSystemException;

	/**
	 * read All the Coupons Method
	 *
	 * 
	 * @return List of Coupons
	 * 
	 */

	ArrayList<Coupon> readAllCoupons(Customer customer) throws CouponSystemException;

	/**
	 * read Coupon Method from company_coupon by Type
	 * 
	 * @param CouponType
	 * 
	 *            that is entered
	 * 
	 * @return Coupon
	 * 
	 */

	ArrayList<Coupon> getCouponByType(Coupon.CouponType type, Company c) throws CouponSystemException;

	/**
	 * read Coupon Method from company_coupon by Until Price
	 * 
	 * @param price
	 * 
	 *            that is entered
	 * 
	 * @return Coupon
	 * 
	 */
	ArrayList<Coupon> getCouponByUntilPrice(double price, Company c) throws CouponSystemException;

	/**
	 * read Coupon Method by Until Date
	 * 
	 * @param date
	 * 
	 *            that is entered
	 * 
	 * @return Coupon
	 * 
	 */

	ArrayList<Coupon> getCouponByUntilDate(String date) throws CouponSystemException;

	/**
	 * Delete Coupons record Method by checking if current date > end date of the
	 * coupon
	 * 
	 * 
	 */

	void deleteExpired() throws CouponSystemException;

	/**
	 * Insert into Company_Coupon Table record with the Company id and Coupon id
	 * Method
	 * 
	 * @param company
	 * @param coupon
	 * 
	 */

	void createCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException;

	/**
	 * Insert into Customer_Coupon Table record with the Customer id and Coupon id
	 * Method
	 * 
	 * @param customer
	 * @param coupon
	 * 
	 */

	void createCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException;

	/**
	 * Delete Company record from the Company_Coupon Table Method
	 * 
	 * @param company
	 * 
	 */

	void deleteCompany(Company company) throws CouponSystemException;

	/**
	 * Delete Customer record from the Customer_Coupon Table Method
	 * 
	 * @param customer
	 * 
	 */

	void deleteCustomer(Customer customer) throws CouponSystemException;

	/**
	 * Delete Coupon record from the Company_Coupon Table Method
	 * 
	 * @param coupon
	 * 
	 */

	void deleteCouponFromCompany(Coupon coupon) throws CouponSystemException;

	/**
	 * Delete Coupon record from the Customer_Coupon Table Method
	 * 
	 * @param coupon
	 * 
	 */

	void deleteCouponFromCustomer(Coupon coupon) throws CouponSystemException;

	/**
	 * Check if the Coupon name is exists in the Coupon Table
	 *
	 * @param couponName
	 * 
	 * @return true if exists, or false if don't exists
	 * 
	 */

	public boolean isCouponExistsByName(String couponName) throws CouponSystemException;

	/**
	 * Insert into Customer_Coupon Table record with the Customer id and Coupon id
	 * Method
	 * 
	 * @param customer
	 * @param coupon
	 * 
	 */
	void purchaseCoupon(Customer customer, Coupon coupon) throws CouponSystemException;

	/**
	 * read Coupon Method from customer_coupon by Type
	 * 
	 * @param CouponType
	 * 
	 *            that is entered
	 * 
	 * @return Coupon
	 * 
	 */
	ArrayList<Coupon> getCouponByType(CouponType type, Customer c) throws CouponSystemException;

	/**
	 * read Coupon Method from customer_coupon by Until Price
	 * 
	 * @param price
	 * 
	 *            that is entered
	 * 
	 * @return Coupon
	 * 
	 */
	ArrayList<Coupon> getCouponByUntilPrice(double price, Customer c) throws CouponSystemException;
}
