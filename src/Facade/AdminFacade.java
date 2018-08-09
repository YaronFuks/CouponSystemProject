package Facade;

/**
 * @author yaron fuks
 */

import java.util.ArrayList;
import java.util.Collection;

import CouponSystemExceptions.CouponSystemException;
import Dao.CompanyDAO;
import Dao.CouponDAO;
import Dao.CustomerDAO;
import DaoDB.CompanyDaoDB;
import DaoDB.CouponDaoDB;
import DaoDB.CustomerDaoDB;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class AdminFacade implements ClientFacade {

	/**
	 * create attributes of all 3 DAO
	 */
	private CompanyDAO companyDao = new CompanyDaoDB();
	private CustomerDAO customerDao = new CustomerDaoDB();
	private CouponDAO couponDao = new CouponDaoDB();

	/**
	 * Create Company Method first we check if a Company with this name already
	 * exists, if it exists throw Exception else create the Company
	 * 
	 * @param company
	 * 
	 * 
	 */
	public void createCompany(Company company) throws CouponSystemException {
		if (companyDao.isCompanyExistsByName(company.getCompName())) {
			throw new CouponSystemException("Company name already exists can not do duplicate name");
		}
		companyDao.createCompany(company);
	}

	/**
	 * Create Customer Method first we check if a Customer with this name already
	 * exists, if it exists throw Exception else create the Company
	 * 
	 * @param customer
	 * 
	 * 
	 */
	public void createCustomer(Customer customer) throws CouponSystemException {
		if (customerDao.isCustomerExistsByName(customer.getCustName())) {
			throw new CouponSystemException("Customer name already exists can not do duplicate name");
		}
		customerDao.createCustomer(customer);
	}

	/**
	 * read Company Method by
	 * 
	 * @param companyId
	 * 
	 *            that is entered and
	 * 
	 * @return Company
	 * 
	 *         if the Company don't exists it throw Exception
	 * 
	 */
	public Company readCompany(long companyId) throws CouponSystemException {

		Company readComp = companyDao.readCompany(companyId);
		if (readComp == null) {
			throw new CouponSystemException("Company with the id:" + companyId + " not exists");
		}
		System.out.println(readComp);
		return readComp;

	}

	/**
	 * read Customer Method by
	 * 
	 * @param customerId
	 * 
	 *            that is entered and
	 * 
	 * @return Customer
	 * 
	 *         if the Customer don't exists it throw Exception
	 * 
	 */

	public Customer readCustomer(long customerId) throws CouponSystemException {

		Customer readCust = customerDao.readCustomer(customerId);
		if (readCust == null) {
			throw new CouponSystemException("Customer with the id:" + customerId + " not exists");
		}
		System.out.println(readCust);
		return readCust;

	}

	/**
	 * Delete Company method that runs on a loop and delete the Coupons purchased by
	 * Customers on Customer_coupon table also delete the Coupon from Coupon table
	 * also delete the Company from Company_coupon table and delete the Company from
	 * Company table
	 * 
	 */
	public void deleteCompany(Company company) throws CouponSystemException {
		ArrayList<Coupon> couponList = companyDao.getCoupons(company.getId());

		for (Coupon coupon : couponList) {
			couponDao.deleteCouponFromCustomer(coupon);
			couponDao.deleteCoupon(coupon);
		}

		couponDao.deleteCompany(company);// מחיקת קופונים של החברה
		companyDao.deleteCompany(company);// מחיקת חברה
	}

	/**
	 * Delete Customer method that delete the Customer from Customer Table also
	 * delete the Customer from Customer_coupon Table
	 * 
	 */

	public void deleteCustomer(Customer customer) throws CouponSystemException {

		customerDao.deleteCustomer(customer);
		couponDao.deleteCustomer(customer);
	}

	/**
	 * Update Company record Method by entering a new Company Object, the columns
	 * that it will update is Password and Email
	 * 
	 * @param company
	 * 
	 * 
	 */
	public void updateCompany(Company company) throws CouponSystemException {
		Company curComp = new Company();
		curComp = companyDao.readCompany(company.getId());
		curComp.setPassword(company.getPassword());
		curComp.setEmail(company.getEmail());
		companyDao.updateCompany(curComp);

	}

	/**
	 * Update Customer record Method by entering a new Customer Object, the column
	 * that it will update is Password
	 * 
	 * @param customer
	 * 
	 * 
	 */

	public void updateCustomer(Customer customer) throws CouponSystemException {
		Customer curCust = new Customer();
		curCust = customerDao.readCustomer(customer.getId());
		curCust.setPassword(customer.getPassword());
		customerDao.updateCustomer(curCust);
	}

	/**
	 * read All Companies Method
	 * 
	 * 
	 * @return ArrayList of all the Companies
	 * 
	 */

	public Collection<Company> readAllCompany() throws CouponSystemException {
		Collection<Company> companyList = companyDao.readAllCompany();
		for (Company company : companyList) {
			System.out.println(company);

		}
		return companyList;

	}

	/**
	 * read All Customers Method
	 * 
	 * 
	 * @return ArrayList of all the Customers
	 * 
	 */

	public Collection<Customer> readAllCustomer() throws CouponSystemException {
		Collection<Customer> customerList = customerDao.readAllCustomer();
		for (Customer customer : customerList) {
			System.out.println(customer);
		}
		return customerList;
	}

}