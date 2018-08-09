package Dao;

/**
 * @author yaron fuks
 */

import java.util.ArrayList;

import CouponSystemExceptions.CouponSystemException;
import JavaBeans.Company;
import JavaBeans.Coupon;

public interface CompanyDAO {

	/**
	 * Create Company Method by entering a
	 * 
	 * @param
	 * 
	 * 
	 */

	void createCompany(Company company) throws CouponSystemException;

	/**
	 * read Company Method by
	 * 
	 * @param companyId
	 * 
	 *            that is entered and
	 * 
	 * @return Company
	 * 
	 */
	Company readCompany(long companyId) throws CouponSystemException;

	/**
	 * read Company Method by
	 * 
	 * @param companyName
	 * 
	 *            that is entered and
	 * 
	 * @return Company
	 * 
	 */
	Company readCompanyByName(String companyName) throws CouponSystemException;

	/**
	 * Update Company record Method by entering a
	 * 
	 * @param Company
	 * 
	 * 
	 */

	void updateCompany(Company company) throws CouponSystemException;

	/**
	 * Delete Company record Method by entering a
	 * 
	 * @param Company
	 * 
	 * 
	 */

	void deleteCompany(Company company) throws CouponSystemException;

	/**
	 * read All the Companies Method
	 *
	 * 
	 * @return List of Companies
	 * 
	 */

	ArrayList<Company> readAllCompany() throws CouponSystemException;

	/**
	 * read All the Company Coupons Method by Company id
	 *
	 * 
	 * @return List of Coupons from Company_Coupon Table
	 * 
	 */
	ArrayList<Coupon> getCoupons(long id) throws CouponSystemException;

	/**
	 * read All the Company Coupons Method by Company id
	 *
	 * 
	 * @return List of Coupons from Company_Coupon Table and from Coupon Table
	 * 
	 */

	ArrayList<Coupon> readCompanyCoupons(long companyId) throws CouponSystemException;

	/**
	 * Login method that get a Company after successful login
	 *
	 * @param compName
	 * 
	 * @param password
	 * 
	 * @return Company Dao
	 * 
	 */
	Company login(String compName, String password) throws CouponSystemException;

	/**
	 * Check if the Company name is exists in the Company Table
	 *
	 * @param companyName
	 * 
	 * @return true if exists, or false if don't exists
	 * 
	 */

	boolean isCompanyExistsByName(String companyName) throws CouponSystemException;

}
