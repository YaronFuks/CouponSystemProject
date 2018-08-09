package Facade;

/**
 * @author yaron fuks
 */

import java.util.ArrayList;

import CouponSystemExceptions.CouponSystemException;
import Dao.CompanyDAO;
import Dao.CouponDAO;
import DaoDB.CompanyDaoDB;
import DaoDB.CouponDaoDB;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyFacade implements ClientFacade {
	/**
	 * create attributes of Coupon DAO & Company DAO
	 */
	private CouponDAO couponDao = new CouponDaoDB();
	private CompanyDAO companyDao = new CompanyDaoDB();
	private Company company;

	public CompanyFacade(Company company) {
		super();
		this.company = company;
	}

	/**
	 * Create Coupon Method first we check if a Coupon with this name already
	 * exists, if it exists throw Exception else create the Coupon in the Coupon
	 * Table also create a record in the Company_coupon Table of the coupon Id with
	 * the Company Id
	 * 
	 * @param coupon
	 * 
	 * 
	 */
	public void createCoupon(Coupon coupon) throws CouponSystemException {
		if (couponDao.isCouponExistsByName(coupon.getTitle())) {
			throw new CouponSystemException("coupon title allready exists can not do duplicate name");
		}
		couponDao.createCoupon(coupon);
		couponDao.createCompanyCoupon(company, coupon);
	}

	/**
	 * Delete Coupon method that delete the Coupon from Coupon Table also delete the
	 * Coupon from Customer_coupon Table
	 * 
	 */

	public void deleteCoupon(Coupon coupon) throws CouponSystemException {
		couponDao.deleteCouponFromCompany(coupon);
		couponDao.deleteCouponFromCustomer(coupon);
		couponDao.deleteCoupon(coupon);
	}

	/**
	 * Update Coupon record Method by entering a new Coupon Object, the columns that
	 * it will update is End_Date and Price
	 * 
	 * @param coupon
	 * 
	 * 
	 */
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Coupon curCoupon = new Coupon();
		curCoupon = couponDao.readCoupon(coupon.getId());
		curCoupon.setEndDate(coupon.getEndDate());
		curCoupon.setPrice(coupon.getPrice());
		couponDao.updateCoupon(curCoupon);
	}

	/**
	 * read Coupon Method by
	 * 
	 * @param couponId
	 * 
	 *            that is entered and
	 * 
	 * @return Coupon
	 * 
	 *         if the Coupon don't exists it throw Exception
	 * 
	 */
	public Coupon readCoupon(long couponId) throws CouponSystemException {

		Coupon readCoup = couponDao.readCoupon(couponId);
		if (readCoup == null) {
			throw new CouponSystemException("Coupon with the id:" + couponId + " not exists");
		}
		return readCoup;
	}

	/**
	 * read All Coupons Method
	 * 
	 * 
	 * @return ArrayList of all the Coupons
	 * 
	 */

	public ArrayList<Coupon> readAllCoupon() throws CouponSystemException {
		ArrayList<Coupon> list = companyDao.getCoupons(company.getId());

		return list;
	}

	/**
	 * read All Coupons Method by Type makes a list of coupons that are one kind of
	 * type from CouponType Enum
	 * 
	 * 
	 * @return ArrayList of all the Coupons from this kind of type
	 * 
	 */
	public ArrayList<Coupon> readAllCouponByType(Coupon.CouponType type) throws CouponSystemException {

		ArrayList<Coupon> list = couponDao.getCouponByType(type, this.company);
		return list;
	}

	/**
	 * read All Coupons Method by Until Price makes a list of coupons that are under
	 * price the the client enters
	 * 
	 * 
	 * 
	 * @return ArrayList of all the Coupons under the price given
	 * 
	 */
	public ArrayList<Coupon> readAllCouponByUntilPrice(double price) throws CouponSystemException {

		ArrayList<Coupon> list = couponDao.getCouponByUntilPrice(price, this.company);
		return list;
	}

	/**
	 * read All Coupons Method by Until date makes a list of coupons that the end
	 * date is before the date given by client
	 *
	 * 
	 * @return ArrayList of all the Coupons that end date is before
	 * 
	 */
	public ArrayList<Coupon> readAllCouponByUntilDate(String date) throws CouponSystemException {

		ArrayList<Coupon> list = couponDao.getCouponByUntilDate(date);
		return list;
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

	public Company readCompanyold() throws CouponSystemException {

		Company readComp = companyDao.readCompany(company.getId());
		if (readComp == null) {
			throw new CouponSystemException("Company with the id:" + " not exists");
		}
		System.out.println(readComp);
		return readComp;

	}

	public Company getCompany() {
		return company;
	}

}
