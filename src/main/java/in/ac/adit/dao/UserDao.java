package in.ac.adit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.ac.adit.model.User;
import in.ac.adit.model.UserContact;

public class UserDao implements UserDaoInterface{
		final static String URL="jdbc:mysql://localhost:3306/phonebook_db";
		final static String USERNAME="root";
		final static String PASSWORD="";
		private Connection connection=null;
		private PreparedStatement pstmt=null;
		public UserDao() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean register(User userRegister) {
			try {
				pstmt=connection.prepareStatement("insert into register (fullname,email,password) values(?,?,?)");
				pstmt.setString(1,userRegister.getFullname());
				pstmt.setString(2,userRegister.getEmail());
				pstmt.setString(3,userRegister.getPassword());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
		
		public boolean login(User userLogin) {
				try {
					pstmt=connection.prepareStatement("select email,password from register where email=? AND password=?");
					pstmt.setString(1,userLogin.getEmail());
					pstmt.setString(2,userLogin.getPassword());
					ResultSet rs=pstmt.executeQuery();
					if(rs.next()) {
						return true;
					}
					return false;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return false;
			
		}
		
		public boolean saveContact(UserContact userContact,int id) {
			try {
				pstmt=connection.prepareStatement("insert into addcontact (id,name,email,phoneno,comments) values(?,?,?,?,?)");
				pstmt.setLong(1,id);
				pstmt.setString(2,userContact.getName());
				pstmt.setString(3,userContact.getEmail());
				pstmt.setString(4,userContact.getPhoneno());
				pstmt.setString(5,userContact.getComments());
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		public String loginUser(User userloginuser) {
			try {
				pstmt=connection.prepareStatement("select fullname from register where email=? AND password=?");
				pstmt.setString(1,userloginuser.getEmail());
				pstmt.setString(2,userloginuser.getPassword());
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					return rs.getString(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return null;
			
		}
		
		public int userId(User userid) {
			try {
				pstmt=connection.prepareStatement("select id from register where email=? AND password=?");
				pstmt.setString(1,userid.getEmail());
				pstmt.setString(2,userid.getPassword());
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					return (int) rs.getLong(1);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
			
		}
		
		
		public List<UserContact> getAllContact(int id){
			List<UserContact> list=new ArrayList<UserContact>();
			UserContact contact=null;
			
			try {
				pstmt=connection.prepareStatement("select a_id,name,email,phoneno,comments from addcontact where id=?");
				pstmt.setLong(1, id);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					contact=new UserContact();
					contact.setId(rs.getLong(1));
					contact.setName(rs.getString(2));
					contact.setEmail(rs.getString(3));
					contact.setPhoneno(rs.getString(4));
					contact.setComments(rs.getString(5));
					list.add(contact);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return list;
		}
		public UserContact getDeatilById(int get_id) {
			try {
				pstmt=connection.prepareStatement("select name,email,phoneno,comments from addcontact where a_id=?");
				pstmt.setLong(1, get_id);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					UserContact userDeatilById=new UserContact();
					userDeatilById.setName(rs.getString(1));
					userDeatilById.setEmail(rs.getString(2));
					userDeatilById.setPhoneno(rs.getString(3));
					userDeatilById.setComments(rs.getString(4));
					return userDeatilById;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		}
		
		public boolean updateContact(UserContact updateContact) {
			try {
				pstmt=connection.prepareStatement("update addcontact set name=? ,email=?,phoneno=?,comments=? where a_id=?");
				pstmt.setString(1,updateContact.getName());
				pstmt.setString(2,updateContact.getEmail());
				pstmt.setString(3,updateContact.getPhoneno());
				pstmt.setString(4,updateContact.getComments());
				pstmt.setLong(5,updateContact.getId());
				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return false;
			
		}
		
		public boolean deleteContact(int cid_d) {
			try {
				pstmt=connection.prepareStatement("delete from addcontact where a_id=?");
				pstmt.setLong(1, cid_d);
				pstmt.execute();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		
		public boolean isPassword(String password,int id) {
			try {
				pstmt=connection.prepareStatement("select password from register where password=? and id=?");
				pstmt.setString(1, password);
				pstmt.setLong(2, id);
				ResultSet rs=pstmt.executeQuery();
				if(rs.next()) {
					return true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			
		}
		
		public boolean UpdatePassword(String newpassword,int id) {
			try {
				pstmt=connection.prepareStatement("update register set password=? where id=?");
				pstmt.setString(1, newpassword);
				pstmt.setLong(2, id);
				pstmt.executeUpdate();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;}
		
		public boolean isOneIdContact(int id,String phoneno) {
			int count=0;
			try {
				pstmt=connection.prepareStatement("select phoneno from addcontact where id=? and phoneno=?");
				pstmt.setLong(1, id);
				pstmt.setString(2, phoneno);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next()) {
					count++;
				}
				if(count>0) {
					return false;
				}else {
					return true;
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			return false;
			
		}
	}


