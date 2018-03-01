/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytqaproj.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import mytqaproj.dbutil.DBConnection;
import mytqaproj.pojo.Users;

/**
 *
 * @author DELL
 */
public class UsersDAO {
    public static Users loginByAdmin(String uName) throws SQLException
    {
        
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("select * from users where userid=? and usertype='admin'");
            ps.setString(1,uName);
            Users u=null;
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                u=new Users();
                u.setUserId(rs.getString(1));
                u.setUserPass(rs.getString(2));
                u.setUserType(rs.getString(3));
                
            }
            return u;
        
    }
    
    public static Users loginByStudent(String uName) throws SQLException
    {
        
            Connection conn=DBConnection.getConnection();
            
            PreparedStatement ps=conn.prepareStatement("select * from users where userid=? and usertype='student'");
            
            ps.setString(1,uName);
            Users u=null;
            ResultSet rs=ps.executeQuery();
            //System.out.println(ps);
            if(rs.next())
            {
                
                u=new Users();
                u.setUserId(rs.getString(1));
                u.setUserPass(rs.getString(2));
                u.setUserType(rs.getString(3));
                System.out.println(u.getUserId()+" "+u.getUserPass()+" "+u.getUserType());
            }
            System.out.println(u);
            return u;
        
    }
    
    static String username=null;
    public static void setUsersName(String s){
        username=s;
    }
    public static String getUsersName(){
        return username;
    }
    public static int registerNewStudent(Users u) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps1=conn.prepareStatement("select * from users where userid=?");
        ps1.setString(1,u.getUserId());
        ResultSet rs=ps1.executeQuery();
        
        if(rs.next())
        {
            JOptionPane.showMessageDialog(null,"exam id already present","ERROR",JOptionPane.ERROR_MESSAGE);
            return 0;
        }
        else{
        PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?)");
        ps.setString(1,u.getUserId());
        ps.setString(2,u.getUserPass());
        ps.setString(3, u.getUserType());
        int ans=ps.executeUpdate();
        return ans;
        }
        
    }
    
    public static int changePassword(String userId, String pass) throws SQLException
    {
        String qry="update users set password=? where userid=? and usertype='student'";
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(qry);
        
        System.out.println(pass+" "+userId);
        ps.setString(1, pass);
        ps.setString(2, userId);
        return ps.executeUpdate();
    }
}
