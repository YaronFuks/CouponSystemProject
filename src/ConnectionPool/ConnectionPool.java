package ConnectionPool;

/**
 * @author yaron fuks
 * */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * This class create all the Connection Pool methods
 * 
 * 
 */
public class ConnectionPool {

	// Class attributes
	private Set<Connection> connections = new HashSet<>();

	// Create one instance of an object
	private static ConnectionPool instance;

	// Url for connections
	private static final int max_con = 10;
	private final String url = "jdbc:derby://localhost:1527/system_db";

	/**
	 * Private CTOR of Connection Pool
	 * 
	 */
	private ConnectionPool() {
		try {
			Class.forName("org.apache.derby.jdbc.ClientDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (int i = 0; i < max_con; i++) {
			try {
				Connection con = DriverManager.getConnection(url);
				connections.add(con);
			} catch (SQLException e) {
				System.err.println("Could not get the connection " + e.getMessage());
			}

		}
	}

	/**
	 * this method Get the connections
	 * 
	 * @return the connection
	 * 
	 * @throws SQLException
	 */

	public synchronized Connection getConnection() {
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Some one interrupted waiting");
			}
		}

		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove();
		return con;
	}

	/**
	 * Return connection parameter
	 * 
	 * @param c
	 *            (Connection)
	 * 
	 * @return Connection
	 * 
	 */
	public synchronized void returnConnection(Connection c) {
		connections.add(c);
		notifyAll();
	}

	/**
	 * Close all connections
	 */
	public synchronized void closeAllConnections() {
		System.out.println("closing all connections");
		// Counter for checking if all connections are close
		int counter = 0;

		// Checking if the remove-counter is less then the max connection
		while (counter < max_con) {

			// While its empty wait...
			while (connections.isEmpty()) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.err.println("Someone interrupt waiting " + e.getMessage());
				}
			}

			// Running over the available connections
			// Closing the connection and adding 1 to the counter

			Iterator<Connection> itCon = connections.iterator();
			while (itCon.hasNext()) {
				Connection currentConnection = itCon.next();

				try {
					currentConnection.close();
					counter++;
				} catch (SQLException e) {
					System.err.println("Could not close the current connection " + e.getMessage());

				}
			}
		}
	}

	/**
	 * Get the one connection pool object
	 * 
	 * @return instance of Connection
	 */
	public synchronized static ConnectionPool getInstance() {
		if (instance == null) {
			instance = new ConnectionPool();
		}
		return instance;
	}
}
