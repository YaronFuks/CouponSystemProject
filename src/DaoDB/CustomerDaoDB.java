package DaoDB;

/**
 * @author yaron fuks
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import ConnectionPool.ConnectionPool;
import CouponSystemExceptions.CouponSystemException;
import Dao.CustomerDAO;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerDaoDB implements CustomerDAO {

	/**
	 * Create a Connection Pool object Named cp and get the one connection Instance
	 */

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public void createCustomer(Customer customer) throws CouponSystemException {

		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con
				.prepareStatement("INSERT INTO Customer (cust_name , password) VALUES (?,?)")) {

			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());

			pstmt.executeUpdate();
			System.out.println(customer + " - Was Created");

		} catch (SQLException e) {
			throw new CouponSystemException("can not Create new Customer", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public Customer readCustomer(long customerId) throws CouponSystemException {

		Connection con = cp.getConnection(); // Get the connection

		Customer cust = new Customer();
		try (Statement st = con.createStatement();

				ResultSet rs = st.executeQuery("select * from Customer where id=" + customerId)) {

			while (rs.next()) {
				cust = new Customer(rs.getString(2), rs.getString(3));
				cust.setId(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the Customer", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return cust; // return the customer to read it

	}

	@Override
	public void updateCustomer(Customer customer) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con
				.prepareStatement("update Customer set cust_name=? , password=? where id=?");) {

			pstmt.setString(1, customer.getCustName());
			pstmt.setString(2, customer.getPassword());
			pstmt.setLong(3, customer.getId());

			pstmt.executeUpdate();
			System.out.println(customer + " - Was Updated");

		} catch (SQLException e) {
			throw new CouponSystemException("can not update customer", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void deleteCustomer(Customer customer) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Customer where id=?");) {
			pstmt.setLong(1, customer.getId());

			pstmt.executeUpdate();
			System.out.println(customer + " - Was Deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("can not delete company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public Collection<Customer> readAllCustomer() throws CouponSystemException {

		Connection con = cp.getConnection(); // Get the connection

		Customer cust = new Customer();
		Collection<Customer> customerList = new ArrayList<>();
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("select * from Customer");) {
			while (rs.next()) {
				cust = new Customer(rs.getString(2), rs.getString(3));
				cust.setId(rs.getLong(1));
				customerList.add(cust);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read Customers", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return customerList; // read the all customer list to read it

	}

	@Override
	public Collection<Coupon> readCustomerCoupons(long customerId) throws CouponSystemException {

		String sql1 = "select coupon_id from customer_coupon where customer_id=" + customerId;
		String sql2 = "select * from coupon where coupon_id=?";

		Connection con = cp.getConnection(); // Get the connection
		Collection<Long> ids = new ArrayList<>();
		Collection<Coupon> couponList = new ArrayList<>();

		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql1);) {
			while (rs.next()) {
				ids.add(rs.getLong(1));
			}
			PreparedStatement pstmt = con.prepareStatement(sql2);
			for (long couponId : ids) {
				pstmt.setLong(1, couponId);
				ResultSet rs2 = pstmt.executeQuery();
				Coupon c = new Coupon();
				c.setId(rs.getLong("id"));
				c.setTitle(rs.getString("title"));
				c.setStartDate(rs.getDate("start_date"));
				c.setEndDate(rs.getDate("end_date"));
				c.setAmount(rs.getInt("amount"));
				c.setType(Coupon.CouponType.valueOf(rs.getString("type")));
				c.setMessage(rs.getString("message"));
				c.setPrice(rs.getDouble("price"));
				c.setImage(rs.getString("image"));

				couponList.add(c);
			}
			return couponList; // return the coupon list to read it

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the customer coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
	}

	@Override
	public Customer login(String cust_Name, String password) throws CouponSystemException {
		Customer customer = null;
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con
				.prepareStatement("select * from customer where cust_name =? and password =?");) {

			pstmt.setString(1, cust_Name);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				customer = new Customer(rs.getLong(1), rs.getString(2), rs.getString(3));

			} else
				throw new CouponSystemException("Customers dont exists");

		} catch (SQLException e) {
			throw new CouponSystemException("can not login with this parametars", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return customer; // return customer to login
	}

	@Override
	public boolean isCustomerExistsByName(String customerName) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement preparedStatement = con.prepareStatement("select * from customer where cust_name=?")) {
			preparedStatement.setString(1, customerName);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			}
		}

		catch (SQLException e) {
			throw new CouponSystemException("can not read the customers", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return false;
	}
}
