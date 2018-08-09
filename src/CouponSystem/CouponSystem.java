package CouponSystem;

/**
 * @author yaron fuks
 * */
import ConnectionPool.ConnectionPool;
import CouponSystemExceptions.CouponSystemException;
import DailyTaskThread.DailyCouponExpirationTask;
import Dao.CompanyDAO;
import Dao.CustomerDAO;
import DaoDB.CompanyDaoDB;
import DaoDB.CustomerDaoDB;
import Facade.AdminFacade;
import Facade.ClientFacade;
import Facade.ClientType;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import JavaBeans.Company;
import JavaBeans.Customer;

public class CouponSystem {

	private CompanyDAO companyDAO;
	private CustomerDAO customerDAO;
	private DailyCouponExpirationTask dailyTask;
	private static CouponSystem instance;

	/**
	 * in this method we check if we have a singelton of this object, if yes we
	 * return it, if no we create it and return it
	 */
	public static CouponSystem getInstance() {
		if (instance == null) {
			instance = new CouponSystem();
		}
		return instance;
	}

	/**
	 * here we making a new object of CompanyDaoDB and CustomerDaoDB and also
	 * initiate the DailyCouponExpirationTask
	 */
	private CouponSystem() {
		companyDAO = new CompanyDaoDB();
		customerDAO = new CustomerDaoDB();
		DailyCouponExpirationTask.initThread();
	}

	/**
	 * This is the Login Method i used a Switch case if its an Admin we check if the
	 * parameters are user_name=admin and password=1234 and if so it return
	 * AdminFacade.
	 * 
	 * if its a Company we check if the parameters are in the Company table and if
	 * so it will return ComapnyFacade if not we print "Company dosen't exists".
	 * 
	 * if its a Customer we check if the parameters are in the Customer table and if
	 * so it will return CustomerFacade if not we print "Customer dosen't exists"
	 * 
	 */
	public ClientFacade login(String name, String password, ClientType type) throws CouponSystemException {

		switch (type) {
		case ADMIN:
			if (name.equals("admin") && password.equals("1234"))
				return new AdminFacade();

			break;
		case COMPANY:
			Company company = null;
			try {
				company = companyDAO.login(name, password);
			} catch (CouponSystemException e) {
				System.out.println("Company dosen't exists");
				break;
			}
			if (company != null) {
				return new CompanyFacade(company);
			}
		case CUSTOMER:
			Customer customer = null;
			try {
				customer = customerDAO.login(name, password);
			} catch (CouponSystemException e) {
				System.out.println("Customer dosen't exists");
				break;
			}
			if (customer != null) {
				return new CustomerFacade(customer);
			}

			break;
		}

		return null;

	}

	/**
	 * 
	 * In this method we will do the ShutDown that include close all the connections
	 * and stop the dailyTask
	 */
	public void ShutDown() {
		ConnectionPool cp = ConnectionPool.getInstance();
		cp.closeAllConnections();
		dailyTask.StopTask();

	}

}
