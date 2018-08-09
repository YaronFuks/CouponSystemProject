package Dao;

/**
 * @author yaron fuks
 */

import java.util.Collection;

import CouponSystemExceptions.CouponSystemException;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public interface CustomerDAO {

	/**
	 * Create Customer Method by entering a
	 * 
	 * @param Customer
	 * 
	 * 
	 */

	void createCustomer(Customer customer) throws CouponSystemException;

	/**
	 * read Customer Method by
	 * 
	 * @param customerId
	 * 
	 *            that is entered and
	 * 
	 * @return Customer
	 * 
	 */

	Customer readCustomer(long customerId) throws CouponSystemException;

	/**
	 * Update Customer record Method by entering a
	 * 
	 * @param customer
	 * 
	 * 
	 */

	void updateCustomer(Customer customer) throws CouponSystemException;

	/**
	 * Delete Customer record Method by entering a
	 * 
	 * @param Customer
	 * 
	 * 
	 */

	void deleteCustomer(Customer customer) throws CouponSystemException;

	/**
	 * read All the Customers Method
	 *
	 * 
	 * @return List of Customers
	 * 
	 */

	Collection<Customer> readAllCustomer() throws CouponSystemException;

	/**
	 * read All the Customer Coupons Method by Customer id
	 *
	 * 
	 * @return List of Coupons from Customer_Coupon Table and from Coupon Table
	 * 
	 */

	Collection<Coupon> readCustomerCoupons(long customerId) throws CouponSystemException;

	/**
	 * Login method that get a Customer after successful login
	 *
	 * @param custName
	 * 
	 * @param password
	 * 
	 * @return Customer Dao
	 * 
	 */
	Customer login(String custName, String password) throws CouponSystemException;

	/**
	 * Check if the Customer name is exists in the Customer Table
	 *
	 * @param customerName
	 * 
	 * @return true if exists, or false if don't exists
	 * 
	 */

	boolean isCustomerExistsByName(String customerName) throws CouponSystemException;

}
