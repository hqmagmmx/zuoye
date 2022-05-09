package daoIMP;

import bean.Student;
import connection.DataBaseConnection;
import dao.StudentDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOIMP implements StudentDAO{
	// 添加操作
	    public void insert(Student s){
	    	String sql = "INSERT INTO student (id, name) values (?,?)";
	    	PreparedStatement pstmt = null;
	   		DataBaseConnection conn = null;
	    //针对数据库的具体操作
	    	try{
	        	conn = new DataBaseConnection();
	       	 	pstmt = conn.getConnection().prepareStatement(sql);
	        	pstmt.setLong(1,s.getID());
	        //pstmt.setString(1,s.getID());
	        	pstmt.setString(2,s.getName());
	        	pstmt.executeUpdate();
	        	pstmt.close();
	        	conn.close();

	        	}
	     	catch(Exception e){
				e.printStackTrace();
		 	}
	    }

	    public void update(Student s){
	    	String sql = "UPDATE student set name=? where id=?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,s.getID());
				//pstmt.setString(1,s.getID());
				pstmt.setString(2,s.getName());
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}catch (Exception e){
				e.printStackTrace();
			}

		}

	    public void delete(Long iD){
	    	String sql = "DELETE FROM student where id =?";
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,iD);
				pstmt.executeUpdate();
				pstmt.close();
				conn.close();
			}catch (Exception e){
				e.printStackTrace();
			}

		}

	public List findAll(){

		List<Student> students = new ArrayList<Student>();
		String sql = "select * from student";

		try{
			PreparedStatement pstmt = null;
			DataBaseConnection conn = null;
			conn = new DataBaseConnection();

			pstmt = conn.getConnection().prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			//针对数据库的具体操作
			while(rs.next()){
				Student student = new Student();
				student.setID(rs.getInt("iD"));
				student.setName(rs.getString("name"));
				students.add(student);
			}
			pstmt.close();
			conn.close();
		}
		catch(Exception e){  }
		return students;

	}

	    public Student findByID(long iD){
	    	String sql = "SELECT * FROM student where id =?";
	    	PreparedStatement pstmt = null;
	    	DataBaseConnection conn = null;
	    	Student student = new Student();
			try{
				conn = new DataBaseConnection();
				pstmt = conn.getConnection().prepareStatement(sql);
				pstmt.setLong(1,iD);
				ResultSet resultSet = pstmt.executeQuery();
				while (resultSet.next()){
					student.setID(resultSet.getLong("id"));
					student.setName(resultSet.getString("name"));
				}
				pstmt.execute();
				pstmt.close();
				conn.close();
			}catch (Exception e){
				e.printStackTrace();
			}
			return student;
		}

	}
