package TestingArea;

/**
 * @author yaron fuks
 * */
import CouponSystem.CouponSystem;
import CouponSystemExceptions.CouponSystemException;
import Dao.CompanyDAO;
import Dao.CustomerDAO;
import DaoDB.CompanyDaoDB;
import DaoDB.CustomerDaoDB;
import Facade.AdminFacade;
import Facade.ClientType;

public class AdminFacadeTest {
	public static void main(String[] args) throws CouponSystemException {
		/**
		 * 
		 * Get Instance from Coupon System
		 * 
		 */
		CouponSystem system = CouponSystem.getInstance();

		/**
		 * Get an Company & Customer DAO Object
		 */
		CompanyDAO companyDAO = new CompanyDaoDB();
		CustomerDAO customerDAO = new CustomerDaoDB();

		/**
		 * In This Class we will check the AdminFacade Methods
		 */

		/**
		 * Login as Admin Facade
		 */
		AdminFacade adminFacade = (AdminFacade) system.login("admin", "1234", ClientType.ADMIN);

		/**
		 * Create Company
		 */
		// adminFacade.createCompany(new Company("dell", "1111", "dell@dell.com"));
		// adminFacade.createCompany(new Company("hp", "2222", "hp@hp.com"));
		// adminFacade.createCompany(new Company("lenovo", "3333",
		// "lenovo@lenovo.com"));
		// adminFacade.createCompany(new Company("adidas", "4444",
		// "adidas@adidas.com"));
		// adminFacade.createCompany(new Company("nike", "5555", "nike@nike.com"));
		// adminFacade.createCompany(new Company("asics", "6666", "asics@asics.com"));

		/**
		 * Delete Company
		 */
		// adminFacade.deleteCompany(new Company(306, "", "", ""));

		/**
		 * Update Company
		 */
		// adminFacade.updateCompany(new Company(306, "asics", "9999",
		// "asics@asicsoffice.com"));

		/**
		 * Read All Companies
		 */
		// adminFacade.readAllCompany();

		/**
		 * Read Company
		 */
		// adminFacade.readCompany(302);

		/**
		 * Create Customer
		 */
		// adminFacade.createCustomer(new Customer("ivory", "1122"));
		// adminFacade.createCustomer(new Customer("ksp", "3344"));
		// adminFacade.createCustomer(new Customer("bug", "5566"));
		// adminFacade.createCustomer(new Customer("mega_sport", "9988"));
		// adminFacade.createCustomer(new Customer("sakal", "7766"));
		// adminFacade.createCustomer(new Customer("sport4us", "5544"));

		/**
		 * Delete Customer
		 */
		// adminFacade.deleteCustomer(new Customer(305, "", ""));

		/**
		 * Update Customer
		 */
		// adminFacade.updateCustomer(new Customer(306, "sport4il", "1234"));

		/**
		 * Read all Customers
		 */
		// adminFacade.readAllCustomer();

		/**
		 * Read Customer
		 */
		// adminFacade.readCustomer(302);

		// stop the task
		// system.ShutDown();
	}
}
