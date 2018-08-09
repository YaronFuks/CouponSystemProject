package DaoDB;

/**
 * @author yaron fuks
 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionPool.ConnectionPool;
import CouponSystemExceptions.CouponSystemException;
import Dao.CouponDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CouponDaoDB implements CouponDAO {

	/**
	 * Create a Connection Pool object Named cp and get the one connection Instance
	 */

	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public void createCoupon(Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement(
				"insert into Coupon (title , start_date , end_date , amount , type , message , price , image) VALUES (?,?,?,?,?,?,?,?)");) {

			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, coupon.getStartDate());
			pstmt.setDate(3, coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());

			pstmt.executeUpdate();
			System.out.println(coupon + " - Was Created");

		} catch (SQLException e) {
			throw new CouponSystemException("can not create coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public Coupon readCoupon(long couponId) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Coupon where id=" + couponId);) {
			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return coup;
	}

	@Override
	public void updateCoupon(Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement(
				"update Coupon set title=? , start_date=? , end_date=? , amount=? , type=? , message=? , price=? , image=? where id="
						+ coupon.getId());) {

			pstmt.setString(1, coupon.getTitle());
			pstmt.setDate(2, coupon.getStartDate());
			pstmt.setDate(3, coupon.getEndDate());
			pstmt.setInt(4, coupon.getAmount());
			pstmt.setString(5, coupon.getType().name());
			pstmt.setString(6, coupon.getMessage());
			pstmt.setDouble(7, coupon.getPrice());
			pstmt.setString(8, coupon.getImage());

			pstmt.executeUpdate();
			System.out.println(coupon + " - Was Updated");
		} catch (SQLException e) {
			throw new CouponSystemException("can not update coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void deleteCoupon(Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Coupon where id=?");) {

			pstmt.setLong(1, coupon.getId());

			pstmt.executeUpdate();
			System.out.println("Coupon number=" + coupon.getId() + " - Was Deleted");

		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete Coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public ArrayList<Coupon> readAllCoupons(Customer customer) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(
						"SELECT coupon.* from customer_coupon right join coupon on customer_coupon.coupon_id = coupon.id where customer_coupon.cust_id ="
								+ customer.getId());) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con);
		}
		return couponList; // return the connection to the Connection pool
	}
	/*
	 * "SELECT * FROM coupon INNER JOIN Company_Coupon ON Coupon.id = Company_Coupon.Coupon_Id WHERE Company_Coupon.Comp_Id="
	 * + company.getId() + " AND Coupon.type ='" + type + "'";
	 */

	@Override
	public ArrayList<Coupon> getCouponByType(Coupon.CouponType type, Company c) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(
						"SELECT coupon.* from company_coupon right join coupon on company_coupon.coupon_id = coupon.id where company_coupon.comp_id="
								+ c.getId() + "and type= '" + type + "'")) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList;
	}

	@Override
	public ArrayList<Coupon> getCouponByType(Coupon.CouponType type, Customer c) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(
						"SELECT coupon.* from customer_coupon right join coupon on customer_coupon.coupon_id = coupon.id where customer_coupon.cust_id="
								+ c.getId() + "and type= '" + type + "'")) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList;
	}

	@Override
	public ArrayList<Coupon> getCouponByUntilPrice(double price, Company c) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(
						"SELECT coupon.* from company_coupon right join coupon on company_coupon.coupon_id = coupon.id where company_coupon.comp_id="
								+ c.getId() + "and PRICE<=" + price + "");) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList;
	}

	@Override
	public ArrayList<Coupon> getCouponByUntilPrice(double price, Customer c) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(
						"SELECT coupon.* from customer_coupon right join coupon on customer_coupon.coupon_id = coupon.id where customer_coupon.cust_id="
								+ c.getId() + "and PRICE<=" + price + "");) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList;
	}

	@Override
	public ArrayList<Coupon> getCouponByUntilDate(String date) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Coupon where end_date<='" + date + "'");) {

			while (rs.next()) {
				coup = new Coupon(rs.getString(2), rs.getDate(3), rs.getDate(4), rs.getInt(5),
						Coupon.CouponType.valueOf(rs.getString(6)), rs.getString(7), rs.getDouble(8), rs.getString(9));
				coup.setId(rs.getLong(1));
				couponList.add(coup);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList;
	}

	@Override
	public void createCompanyCoupon(Company company, Coupon coupon) throws CouponSystemException {

		Connection con = cp.getConnection(); // Get the connection
		Connection con2 = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO company_coupon VALUES (?,?)");) {
			Statement st = con2.createStatement();

			pstmt.setLong(1, company.getId());

			ResultSet rs = st.executeQuery("select * from Coupon where title= '" + coupon.getTitle() + "'");
			if (rs.next()) {

				pstmt.setLong(2, rs.getLong("id"));
			}

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("can not create companyid and coupon id to company_coupon table", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void createCustomerCoupon(Customer customer, Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO customer_coupon VALUES (?,?)");) {

			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("can not read customerid and coupon id to customer_coupon table", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void deleteCompany(Company company) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Company_coupon where comp_id=?");) {

			pstmt.setLong(1, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool

		}

	}

	@Override
	public void deleteCustomer(Customer customer) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Customer_coupon where cust_id=?");) {

			pstmt.setLong(1, customer.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete customer", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool

		}

	}

	@Override
	public void deleteCouponFromCompany(Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Company_coupon where coupon_id=?");) {

			pstmt.setLong(1, coupon.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool

		}
	}

	@Override
	public void deleteCouponFromCustomer(Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Customer_coupon where coupon_id=?");) {

			pstmt.setLong(1, coupon.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete coupon", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool

		}

	}

	@Override
	public boolean isCouponExistsByName(String couponName) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement preparedStatement = con.prepareStatement("select * from Coupon where title=?")) {
			preparedStatement.setString(1, couponName);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			}
		}

		catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return false;
	}

	@Override
	public void purchaseCoupon(Customer customer, Coupon coupon) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("insert into customer_coupon values (?,?)")) {
			pstmt.setLong(1, customer.getId());
			pstmt.setLong(2, coupon.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException();

		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void deleteExpired() throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Coupon coup = new Coupon();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement()) {

			st.executeUpdate("delete from Coupon where end_date<='" + new Date(System.currentTimeMillis()) + "'");

		} catch (SQLException e) {
			throw new CouponSystemException("can not read the coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}
}
