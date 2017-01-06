package mysql.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



/**
 *  ����MySQL���ݿ���Ϣ��ʹ��jdbc�������ݵĲ�����
 *  1.��������  ���ݿ����ӣ�Connection����ִ��sql��䣨PreparedStatement�����������ResultSet�� ����������
 *  2.��������·����        Class.forName("com.mysql.jdbc.Driver");
 *  3.��ȡ�������ݵ�����    connection=DriverManager.getConnection(url, user, password);
 *  4.��ѯ���ݿⲢ��ȡ�������Ϣ       resultSet=preparedStatement.executeQuery();
 *  5.�����������ȡ��ѯ��������Ϣ
 *  6.���ιر�  �������sql���ִ�ж������ݿ�����  �ȶ���
 *  
 */
public class Insert {
	
	//������
	public static void main(String[] args) {
		//connection  �����������ݿ�
		Connection connection=null;
		//statement  ���ڷ���sql��䵽���ݿ��ִ��sql���  (ʹ�øö�����Է�ֹsqlע��)
		PreparedStatement preparedStatement=null;
		//ʹ��Statemnet����(ʹ�øö����޷���ֹsqlע��)
		Statement statement=null;
		//���ݿ�����url
		String url="jdbc:mysql://127.0.0.1:3306/study";
		//���ݿ������û���
		String user="root";
		//���ݿ���������
		String password="root";
		//sql���
		String sql="select * from user";
		//��ѯ�Ľ����
		ResultSet resultSet=null;
		
		try {
            //�������ݿ�����
			Class.forName("com.mysql.jdbc.Driver");
			//��ȡ���ݿ�����
			connection=DriverManager.getConnection(url, user, password);
			//��ȡsqlִ�ж���
			preparedStatement= connection.prepareStatement(sql);
			//ִ��ʧȥ�����
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
				//�رս����������
				resultSet.close();
				//�ر�sql���ִ�ж��������
				preparedStatement.close();
				//�ر����ݿ�����
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
