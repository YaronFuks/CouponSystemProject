package CreateDbTables;
/**
 * @author yaron fuks
 */

/**
 * this class creates all the Data base tables
 */
public class MainCreateTables {

	public static void main(String[] args) {

		DbCrateTables createAllTables = new DbCrateTables();
		createAllTables.createCompanyTable();
		createAllTables.createCustomerTable();
		createAllTables.createCouponTable();
		createAllTables.createCustomer_CouponTable();
		createAllTables.createCompany_CouponTable();

	}

}
