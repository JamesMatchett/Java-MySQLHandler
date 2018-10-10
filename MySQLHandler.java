package app;
import java.sql.*;

public class MySQLHandler {
	
	private String _Hostname;
	private String _Username;
	private String _Password;
	
	Connection C;
	public Boolean Connected = false;
	
	
	public MySQLHandler(String Hostname, String Username, String Password) throws SQLException {
		Connect(Hostname, Username, Password);
	}
	
	public void Connect(String Hostname, String Username, String Password)
	throws SQLException{
		set_Hostname(Hostname);
		set_Username(Username);
		set_Password(Password);
		Connect();
		 
	}
	
	//reconnects a connection with the currently stored credentials
	public void Reconnect() {
		Connect();
	}
	
	private void Connect() {
		try {
			this.C = DriverManager.getConnection(_Hostname,
					_Username, _Password);
			Connected = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			//System.out.println("1 is "+e.getErrorCode());
			//e.printStackTrace();
			Connected = false;
		}
	}
	
	public void Statement(String Statement) throws SQLException {
		try {
			if(C.isClosed() || !Connected) {
				Connect();
			}
			Statement st = C.createStatement();
			st.executeUpdate(Statement);
			
		} finally {}
	}
	
	public ResultSet Query(String Quer) {
	
		try {
			if(C.isClosed() || !Connected) {
				Connect();
			}
			
			return(C.createStatement().executeQuery(Quer));
		}
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;	
	}
	
	public void Disconnect() throws SQLException {
		C.close();
		Connected = false;
	}
	
	public String get_Hostname() {
		return _Hostname;
	}
	private void set_Hostname(String _Hostname) {
		this._Hostname = _Hostname;
	}
	public String get_Username() {
		return _Username;
	}
	private void set_Username(String _Username) {
		this._Username = _Username;
	}
	public String get_Password() {
		return _Password;
	}
	private void set_Password(String _Password) {
		this._Password = _Password;
	}
}