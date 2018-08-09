package CreateDbTables;
/**
 * @author yaron fuks
 */

/**
 * this class drop all the Data base tables
 */
public class MainDropTables {

	public static void main(String[] args) {

		DbCrateTables dropalltables = new DbCrateTables();
		dropalltables.dropCompanyTable();
		dropalltables.dropCustomerTable();
		dropalltables.dropCouponTable();
		dropalltables.dropCompany_cuoponTable();
		dropalltables.dropCustomer_cuoponTable();

	}

}
