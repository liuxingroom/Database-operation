package mysql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *  链接MySQL数据库信息（使用jdbc链接数据的操作）
 *  1.首先声明  数据库连接（Connection）、执行sql语句（PreparedStatement）、结果集（ResultSet） 这三个对象
 *  2.加载数据路驱动        Class.forName("com.mysql.jdbc.Driver");
 *  3.获取操作数据的链接    connection=DriverManager.getConnection(url, user, password);
 *  4.查询数据库并获取结果集信息       resultSet=preparedStatement.executeQuery();
 *  5.遍历结果集获取查询的数据信息
 *  6.依次关闭  结果集、sql语句执行对象、数据库链接  等对象
 *  
 */
public class Insert {
	
	//主方法
	public static void main(String[] args) {
		//connection  用于链接数据库
		Connection connection=null;
		//statement  用于发送sql语句到数据库进执行sql语句  (使用该对象可以防止sql注入)
		PreparedStatement preparedStatement=null;
		//使用Statemnet对象(使用该对象无法防止sql注入)
		Statement statement=null;
		//数据库连接url
		String url="jdbc:mysql://127.0.0.1:3306/study";
		//数据库连接用户名
		String user="root";
		//数据库练级密码
		String password="root";
		//sql语句
		String sql="select * from user";
		//查询的结果集
		ResultSet resultSet=null;
		
		try {
            //加载数据库驱动
			Class.forName("com.mysql.jdbc.Driver");
			//获取数据库连接
			connection=DriverManager.getConnection(url, user, password);
			//获取sql执行对象
			preparedStatement= connection.prepareStatement(sql);
			//执行失去了语句
			resultSet=preparedStatement.executeQuery();
			for(int i=0;resultSet.next();i++){
				System.out.println(resultSet.getString("id"));
				System.out.println(resultSet.getString("name"));
				System.out.println(resultSet.getString("age"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//关闭结果集的链接
				resultSet.close();
				//关闭sql语句执行对象的链接
				preparedStatement.close();
				//关闭数据库连接
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
