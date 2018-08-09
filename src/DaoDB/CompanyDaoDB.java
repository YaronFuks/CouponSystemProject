package DaoDB;

/**
 * @author yaron fuks
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnectionPool.ConnectionPool;
import CouponSystemExceptions.CouponSystemException;
import Dao.CompanyDAO;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyDaoDB implements CompanyDAO {

	/**
	 * Create a Connection Pool object Named cp and get the one connection Instance
	 */
	private ConnectionPool cp = ConnectionPool.getInstance();

	@Override
	public void createCompany(Company company) throws CouponSystemException {

		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("INSERT INTO Company VALUES (DEFAULT,?,?,?) ");) {

			pstmt.setString(1, company.getCompName()); // using Getter from Company bean to get the Company Name
			pstmt.setString(2, company.getPassword()); // using Getter from Company bean to get the Company Password
			pstmt.setString(3, company.getEmail()); // using Getter from Company bean to get the Company Email

			pstmt.executeUpdate();
			System.out.println("Company = " + company + " -Was Created");
		} catch (SQLIntegrityConstraintViolationException er) {
			throw new CouponSystemException("Company name already exist");

		} catch (SQLException e) {
			throw new CouponSystemException("can not Create company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public Company readCompany(long companyId) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Company comp = new Company(); // create new Company Object
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Company where id=" + companyId)) {

			while (rs.next()) {
				/**
				 * enter the Company Object the values
				 */
				comp = new Company(rs.getString(2), rs.getString(3), rs.getString(4));
				comp.setId(rs.getLong(1));

			}

		} catch (SQLException e) {

			throw new CouponSystemException("can not read Company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return comp; // return the Company Object filled with values

	}

	/// Company company
	public ArrayList<Coupon> getCoupons(long companyId) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		CouponDaoDB couponDao = new CouponDaoDB(); // create new couponDao Object
		ArrayList<Coupon> couponList = new ArrayList<>(); // create a ArrayList named couponList

		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from company_coupon where comp_id=" + companyId)) {
			while (rs.next()) {
				Coupon c = couponDao.readCoupon(rs.getLong(2));
				couponList.add(c);// add Coupon to list
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return couponList; // return the couponList Object filled with values
	}

	@Override
	public Company readCompanyByName(String companyName) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Company comp = new Company();
		try (Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Company where comp_name=" + "'" + companyName + "'");) {

			while (rs.next()) {
				comp = new Company(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));

			}
		} catch (SQLException e) {
			throw new CouponSystemException("can not read company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return comp;
	}

	@Override
	public ArrayList<Company> readAllCompany() throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		Company comp = new Company(); // create new Company Object
		ArrayList<Company> companyList = new ArrayList<>();
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery("select * from Company")) {
			while (rs.next()) {
				comp = new Company(rs.getString(2), rs.getString(3), rs.getString(4));
				comp.setId(rs.getLong(1));
				companyList.add(comp);
			}

		} catch (SQLException e) {
			throw new CouponSystemException("can not read company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return companyList;
	}

	@Override
	public void updateCompany(Company company) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con
				.prepareStatement("update Company set comp_name=? , password=? , email= ? where id=?");) {

			pstmt.setString(1, company.getCompName());
			pstmt.setString(2, company.getPassword());
			pstmt.setString(3, company.getEmail());
			pstmt.setLong(4, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("can not Update company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public void deleteCompany(Company company) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con.prepareStatement("delete from Company where id=?");) {

			pstmt.setLong(1, company.getId());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			throw new CouponSystemException("can not Delete company", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public ArrayList<Coupon> readCompanyCoupons(long companyId) throws CouponSystemException {

		String sql1 = "select coupon_id from company_coupon where company_id=" + companyId;
		String sql2 = "select * from coupon where coupon_id=?";

		Connection con = cp.getConnection(); // Get the connection
		ArrayList<Long> ids = new ArrayList<>();
		ArrayList<Coupon> couponList = new ArrayList<>();
		try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql1)) {
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
			return couponList;

		} catch (SQLException e) {

			throw new CouponSystemException("can not read company coupons", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}

	}

	@Override
	public Company login(String compName, String password) throws CouponSystemException {
		Company company = null; // Create new company object
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement pstmt = con
				.prepareStatement("select * from company where comp_name = ? and password = ?");) {
			pstmt.setString(1, compName);
			pstmt.setString(2, password);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				company = new Company(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));

			} else
				throw new CouponSystemException("Company dont exists");

		} catch (SQLException e) {
			throw new CouponSystemException("can not Login with this parametars", e);
		} finally {

			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return company;
	}

	@Override
	public boolean isCompanyExistsByName(String companyName) throws CouponSystemException {
		Connection con = cp.getConnection(); // Get the connection

		try (PreparedStatement preparedStatement = con.prepareStatement("select * from company where comp_name=?")) {
			preparedStatement.setString(1, companyName);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return true;
			}
		}

		catch (SQLException e) {
			throw new CouponSystemException("can not read the companies", e);
		} finally {
			cp.returnConnection(con); // return the connection to the Connection pool
		}
		return false;
	}

}
