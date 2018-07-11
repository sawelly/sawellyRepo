package com.sawelly.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBUtil {
	/**
	 * 1、所使用的mysql驱动包为mysql-connector-java-5.0.8-bin.jar
	 * 2、Statement 用于执行静态 SQL 语句并返回它所生成结果的对象
	 * 在默认情况下，同一时间每个 Statement 对象在只能打开一个 ResultSet 对象。
	 * 因此,如果读取一个 ResultSet 对象与读取另一个交叉，
	 * 则这两个对象必须是由不同的Statement 对象生成的。
	 * 如果存在某个语句的打开的当前 ResultSet 对象，
	 * 则Statement 接口中的所有执行方法都会隐式关闭它。
	 * 3、ResultSet 表示数据库结果集的数据表，通常通过执行查询数据库的语句生成。
	 * ResultSet 对象具有指向其当前数据行的指针。最初，指针被置于第一行之前。
	 * next 方法将指针移动到下一行；
	 * 因为该方法在 ResultSet 对象中没有下一行时返回 false，
	 * 所以可以在 while 循环中 使用它来迭代结果集。
	 **/
	static Connection conn = null;
	public static Connection getConnectionByJDBC() {
		try {
			//装载驱动包类
			Class.forName("com.mysql.jdbc.Driver"); //加载驱动
		}catch(ClassNotFoundException e) {
			System.out.println("装载驱动包出现异常!请查正！");
			e.printStackTrace();
		}
		try{
			/** 建立jdbc连接，但要注意此方法的第一个参数，
			 * 如果127.0.0.1出现CommunicationsException异常，
			 * 可能就需要改为localhost才可以
			 **/
			//jdbc:mysql://localhost:3306/test，test是数据库
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/sawelly?useUnicode=true&characterEncoding=utf8","root","root");
		}catch(SQLException e) {
			System.out.println("链接数据库发生异常!");
			e.printStackTrace();
		}
		return conn;
	}
	/**
	 * insert into db
	 * @param chinese
	 * @param portuga
	 */
	public static void insertIntoDB(String col1,String col2){
		String sql = "insert into user(USERNAME,PASSWORD) values(?,?)";

		try{
			//创建一个jdbc声明
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1,col1); 
			pstmt.setString(2,col2); 
			pstmt.executeUpdate(); 
			System.out.println("insert into sawelly username="+col1+"------>password="+col2);
		}catch (SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{

		}

	}

	public static void updateResultDB(String chinese, String portuga) {
		if(portuga.length() <=0) return;
		String ss = "";
		String sql = "update importresult set portuga=? where chinese=?";
		try{
			//创建一个jdbc声明
			PreparedStatement pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, portuga);
			pstmt.setString(2, chinese);
	        int rows = pstmt.executeUpdate();
			pstmt.executeUpdate(); 
			System.out.println("update db chinese="+chinese+"------>portuga="+portuga);
		}catch (SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{

		}

	}

	public static void test(){
		String sql = "select * from user";
		getConnectionByJDBC();
		try{
			//创建一个jdbc声明
			Statement stmt = conn.createStatement();
			//执行查询
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String username = rs.getString("username");
				String password= rs.getString("password");
				System.out.println(username +""+ password);
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			//预防性关闭连接（避免异常发生时在try语句块关闭连接没有执行)
			try{
				if(conn != null) conn.close();
			} catch(SQLException e){
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args){
		DBUtil testjdbc = new DBUtil();
		testjdbc.test();
	}
}


