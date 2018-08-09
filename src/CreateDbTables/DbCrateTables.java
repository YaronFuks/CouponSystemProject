package CreateDbTables;

/**
 * @author yaron fuks
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 * this class have functions to connect to the localhost and has functions to
 * create and drop all of the Data base tables
 */

public class DbCrateTables {

	public static void main(String[] args) {
		// createCompanyTable();
		// createCustomerTable();
		// createCouponTable();
		// createCustomer_CouponTable();
		// createCompany_CouponTable();
		// dropCompanyTable();
		// dropCustomerTable();
		// dropCouponTable();
		// dropCustomer_cuoponTable();
		// dropCompany_cuoponTable();
	}

	/**
	 * set path to the data base link
	 */

	private static String url;
	private static File urlFile = new File("files/dbUrl");

	/**
	 * read the link to the scanner
	 */
	static {
		try (Scanner sc = new Scanner(urlFile);) {
			/**
			 * if the data base don't exist it creates it
			 */
			url = sc.nextLine() + ";create=true";
		} catch (FileNotFoundException e) {
			// throws an exception
			System.err.println("Error: could not find/read or create the file " + e.getMessage());
		}
	}

	/**
	 * create the table Company with the columns: Id (auto generated & Primary Key)
	 * - Long, Comp_Name - String, Password - String, Email - String
	 * 
	 */
	public static void createCompanyTable() {

		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "CREATE TABLE company(Id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Comp_Name VARCHAR(30), Password VARCHAR(30), Email VARCHAR(30))";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not create the table " + e.getMessage());
		}
	}

	/**
	 * create the table Customer with the columns: Id (auto generated & Primary Key)
	 * - Long, Cust_Name - String, Password - String
	 * 
	 */
	public static void createCustomerTable() {

		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "CREATE TABLE customer(Id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Cust_Name VARCHAR(30), Password VARCHAR(30))";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not create the table " + e.getMessage());
		}
	}

	/**
	 * create the table Coupon with the columns: Id (auto generated & Primary Key) -
	 * Long, Title - String (UNIQUE), Start_Date - DATE, End_Date - DATE, Amount -
	 * int, Type - String, Message - String, Price - double, Image - String
	 * 
	 * also Add Unique to the columns Comp_name in Company Table, Add Unique to the
	 * columns Cust_name in Customer Table
	 */
	public static void createCouponTable() {

		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "CREATE TABLE coupon(Id BIGINT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), Title VARCHAR(30), Start_Date DATE, End_Date DATE, Amount INTEGER, Type VARCHAR(20), Message VARCHAR(50), Price FLOAT, Image VARCHAR(100))";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
			sql = "ALTER TABLE Company ADD UNIQUE (comp_name)";
			stmt.executeUpdate(sql);
			System.out.println(sql);
			sql = "ALTER TABLE Customer ADD UNIQUE (cust_name)";
			stmt.executeUpdate(sql);
			System.out.println(sql);
			sql = "ALTER TABLE Coupon ADD UNIQUE (title)";
			stmt.executeUpdate(sql);
			System.out.println(sql);

		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not create the table " + e.getMessage());
		}

	}

	/**
	 * create the table Customer_Coupon with the columns: Cust_id - Long, Coupon_id
	 * - Long. Both Primary Key
	 */
	public static void createCustomer_CouponTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "CREATE TABLE customer_coupon(Cust_id BIGINT, Coupon_id BIGINT, PRIMARY KEY(Cust_id, Coupon_id))";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not create the table " + e.getMessage());
		}
	}

	/**
	 * create the table Company_Coupon with the columns: Comp_id - Long, Coupon_id -
	 * Long. Both Primary Key
	 */
	public static void createCompany_CouponTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "CREATE TABLE company_coupon(Comp_id BIGINT, Coupon_id BIGINT, PRIMARY KEY(Comp_id, Coupon_id))";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not create the table " + e.getMessage());
		}

	}

	/**
	 * drops the table company
	 */
	public static void dropCompanyTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "DROP TABLE company";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not drop the table " + e.getMessage());
		}
	}

	/**
	 * drops the table customer
	 */
	public static void dropCustomerTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "DROP TABLE customer";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not drop the table " + e.getMessage());
		}

	}

	/**
	 * drops the table coupon
	 */
	public static void dropCouponTable() {

		try (Connection con = DriverManager.getConnection(url);) {

			String sql = "DROP TABLE coupon";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);

		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not drop the table " + e.getMessage());
		}

	}

	/**
	 * drops the table customer coupon
	 */
	public static void dropCustomer_cuoponTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "DROP TABLE customer_coupon";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not drop the table " + e.getMessage());
		}

	}

	/**
	 * drops the table company coupon
	 */
	public static void dropCompany_cuoponTable() {
		try (Connection con = DriverManager.getConnection(url);) {
			String sql = "DROP TABLE company_coupon";
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			System.out.println(sql);
		} catch (SQLException e) {
			// throws an exception
			System.err.println("Error: could not drop the table " + e.getMessage());
		}

	}

}
