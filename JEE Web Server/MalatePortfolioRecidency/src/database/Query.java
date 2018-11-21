package database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import model.datetime.SimpleDate;
import model.datetime.SimpleDatetime;

public class Query {

	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private CallableStatement cstmt = null;
	private Statement stmt = null;
	
	public Query(){
		setConnection(DatabaseConnectionPool.getInstance().getConnection());
	}
	
	public Query(Connection con){
		setConnection(con);
	}
	
	/**
	 * 	Runs a query and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br></br>
	 * 
	 * 	Cannot run update/insert/delete queries. </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param query - Query to be run.
	 * 
	 */
	public ResultSet runQuery(String query) throws SQLException{
		if(con != null)
		if(!con.isClosed()){
			pstmt= con.prepareStatement(query);
			rs = pstmt.executeQuery();
			}
		return rs;
	}
	
	/**
	 * 	Runs a query and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Query: </br>
	 *  <i>
	 *  SELECT * FROM table WHERE text = ? AND int = ?; </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run update/insert/delete queries. </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param query - Query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runQuery(String query, ArrayList<Object> input) throws SQLException{
		if(con != null)
		if(!con.isClosed()){
			pstmt = con.prepareStatement(query);
			setPreparedStatementAttributes(input);
			rs = pstmt.executeQuery();
			}
		return rs;
		
	}
	
	/**
	 * 	Runs a query and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses object as its input but can be set to null if no input is needed. </br></br>
	 *  Query: </br>
	 *  <i>
	 *  SELECT * FROM table WHERE text = ?; </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input = "text"; // String </br>
	 *  </i>
	 * 	Cannot run update/insert/delete queries. </br>
	 * 	Use instead: {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param query - Query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runQuery(String query, Object input) throws SQLException{
		if(con != null)
		if(!con.isClosed()){
			pstmt = con.prepareStatement(query);
			setPreparedStatementAttribute(1, input);
			rs = pstmt.executeQuery();
			}
		return rs;
		
	}
	
	/**
	 * 	Runs a query and returns true or false depending on whether query was a success. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed.</br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Query: </br>
	 *  <i>
	 *  UPDATE table SET name = ?, age = ? WHERE name = ?; </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "name"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  input[2] = "name"; // String </br>
	 *  </i>
	 * 	Cannot run queries that returns a ResultSet / Table. </br>
	 * 	Use instead: {@link #runQuery()}.</br></br>
	 * 
	 * @param query - query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public boolean runInsertUpdateDelete(String query, ArrayList<Object> input) throws SQLException{
		boolean result = false;
		if(con != null)
		if(!con.isClosed()){
			pstmt = con.prepareStatement(query);
			setPreparedStatementAttributes(input);
			result = pstmt.execute();
			}
		return result;
		
	}
	
	/**
	 * 	Runs a query and returns true or false depending on whether query was a success. </br>
	 *  Uses object as its input but can be set to null if no input is needed.</br></br>
	 *  Query: </br>
	 *  <i>
	 *  INSERT table(name) values(?); </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input = "name"; // String </br>
	 *  or
	 *  input = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run queries that returns a ResultSet / Table. </br>
	 * 	Use instead: {@link #runQuery()}.</br></br>
	 * 
	 * @param query - query to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public boolean runInsertUpdateDelete(String query, Object input) throws SQLException{
		boolean result = false;
		if(con != null)
		if(!con.isClosed()){
			pstmt = con.prepareStatement(query);
			setPreparedStatementAttribute(1, input);
			result = pstmt.execute();
			}
		return result;
		
	}
	
	/**
	 * 	Runs a stored procedure and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses array list of objects as its input but can be set to null if no input is needed. </br> 
	 *  Arraylist of input should be ordered by the '?' in the query. Example: </br></br>
	 *  Call: </br>
	 *  <i>
	 *  CALL procedureName (?, ?) </br></br>
	 *  </i>
	 *  Input Array:</br>
	 *  <i>
	 *  input[0] = "text"; // String </br>
	 *  input[1] = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run queries. </br>
	 * 	Use instead: {@link #runQuery()} or {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param procedure - procedure to be run.
	 * @param input - An ArrayList of objects. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runStoredProcedure(String procedure, ArrayList<Object> input) throws SQLException{
		if(con != null)
		if(!con.isClosed()){
			cstmt = con.prepareCall (procedure);
			setCallableStatementAttributes(input);
			rs = cstmt.executeQuery();
			}
		return rs;
	}
	
	/**
	 * 	Runs a stored procedure and returns a result set. </br>
	 * 	Result set must be closed after use for security. </br>
	 *  Uses object as its input but can be set to null if no input is needed. </br></br>
	 *  Call: </br>
	 *  <i>
	 *  CALL procedureName (?, ?) </br></br>
	 *  </i>
	 *  Input:</br>
	 *  <i>
	 *  input = "text"; // String </br>
	 *  or
	 *  input = 5; // int </br></br>
	 *  </i>
	 * 	Cannot run queries. </br>
	 * 	Use instead: {@link #runQuery()} or {@link #runInsertUpdateDelete()}.</br></br>
	 * 
	 * @param procedure - procedure to be run.
	 * @param input - An object. Can contain: String, int, float & etc.
	 * 
	 */
	public ResultSet runStoredProcedure(String procedure, Object input) throws SQLException{
		if(con != null)
		if(!con.isClosed()){
			cstmt = con.prepareCall (procedure);
			setCallableStatementAttribute(1, input);
			rs = cstmt.executeQuery();
			}
		return rs;
	}

	/**
	 * Runs a query</br></br>
	 * 
	 * @param query - query to be run.
	 * 
	 * @deprecated  Does not make use of prepared statements and is insecure.</br>
	 *              Only use with scripts that cannot be run on prepared statements.</br>
	 *              Else use {@link #runQuery()}, {@link #runInsertUpdateDelete()} or {@link #runStoredProcedure()} instead. </br></br>
	 * 
	 */
	@Deprecated
	public boolean runStatement(String query) throws SQLException{
		boolean result = false;
		if(con != null)
		if(!con.isClosed()){
			stmt = con.createStatement();
			result = stmt.execute(query);
		}
		return result;
	}
	
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after every method call. </br></br>
	 */
	public void close() throws SQLException{
		if(rs != null){
			rs.close();
			rs = null;
			}
		if(pstmt != null){
			pstmt.close();
			pstmt = null;
			}
		if(cstmt != null){
			cstmt.close();
			cstmt = null;
			}
		if(stmt != null){
			stmt.close();
			stmt = null;
			}
		if(con != null){
			con.close();
			con = null;
			}
	}
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after using a ResultSet. </br></br>
	 */
	public void closeResultSet() throws SQLException{
		if(rs != null){
			rs.close();
			rs = null;
			}
	}
	
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after calling {@link #runQuery()} or {@link #runInsertUpdateDelete()}. </br></br>
	 */
	public void closePreparedStatement() throws SQLException{
		if(pstmt != null){
			pstmt.close();
			pstmt = null;
			}
	}
	
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after calling {@link #runStoredProcedure()}. </br></br>
	 */
	public void closeCallableStatement() throws SQLException{
		if(cstmt != null){
			cstmt.close();
			cstmt = null;
			}
	}
	/**
	 * Used for closing connection. </br>
	 * It should be manually call after calling {@link #runStatement()}. </br></br>
	 */
	@Deprecated
	public void closeStatement() throws SQLException{
		if(stmt != null){
			stmt.close();
			stmt = null;
			}
	}
	/**
	 * Used for closing connection. </br>
	 * It should be manually call last after calling any close() functions. </br></br>
	 */
	public void closeConnection() throws SQLException{
		if(con != null){
			con.close();
			con = null;
			}
	}
	
	/**
	 *Sets attributes for query methods using prepared statements
	 */
	private void setPreparedStatementAttributes(ArrayList<Object> input) throws SQLException{
		if(input != null)
			for(int i = 0; i < input.size(); i++){
				setPreparedStatementAttribute(i + 1, input.get(i));
				}
	}
	
	/**
	 *Sets attributes for query methods using prepared statements
	 */
	private void setPreparedStatementAttribute(int i, Object input) throws SQLException{
		if(input instanceof String)
			pstmt.setString(i,(String) input);
		else if(input instanceof byte[])
			pstmt.setBytes(i,(byte[]) input);
		else if(input instanceof Integer)
			pstmt.setInt(i,(Integer) input);
		else if(input instanceof Float)
			pstmt.setFloat(i,(Float) input);
		else if(input instanceof Double)
			pstmt.setDouble(i,(Double) input);
		else if(input instanceof Long)
			pstmt.setLong(i, (Long)input);
		else if(input instanceof Boolean)
			pstmt.setBoolean(i, (Boolean)input);
		else if(input instanceof Enum)
			pstmt.setString(i,((Enum<?>) input).toString());
		else if(input instanceof SimpleDate)
			pstmt.setDate(i, ((SimpleDate)input).toDate());
		else if(input instanceof SimpleDatetime)
			pstmt.setTimestamp(i, ((SimpleDatetime)input).toTimestamp());
		else if(input instanceof Timestamp)
			pstmt.setTimestamp(i, (Timestamp)input);
	}
	
	/**
	 *Sets attributes for query methods using callable statements
	 */
	private void setCallableStatementAttributes(ArrayList<Object> input) throws SQLException{
		if(input != null)
			for(int i = 0; i < input.size(); i++){
				setCallableStatementAttribute(i + 1, input.get(i));
				}
	}
	
	/**
	 *Sets attributes for query methods using callable statements
	 */
	private void setCallableStatementAttribute(int i, Object input) throws SQLException{
		if(input instanceof String)
			cstmt.setString(i,(String) input);
		else if(input instanceof byte[])
			cstmt.setBytes(i,(byte[]) input);
		else if(input instanceof Integer)
			cstmt.setInt(i,(Integer) input);
		else if(input instanceof Float)
			cstmt.setFloat(i,(Float) input);
		else if(input instanceof Double)
			cstmt.setDouble(i,(Double) input);
		else if(input instanceof Long)
			cstmt.setLong(i, (Long)input);
		else if(input instanceof Boolean)
			cstmt.setBoolean(i, (Boolean)input);
		else if(input instanceof Enum)
			cstmt.setString(i,((Enum<?>) input).toString());
		else if(input instanceof SimpleDate)
			cstmt.setDate(i, ((SimpleDate)input).toDate());
		else if(input instanceof SimpleDatetime)
			cstmt.setTimestamp(i, ((SimpleDatetime)input).toTimestamp());
	}

	public Connection getConnection() {
		return con;
	}

	public void setConnection(Connection con) {
		this.con = con;
	}
	
	public ResultSet getResultSet(){ return rs;}
}
