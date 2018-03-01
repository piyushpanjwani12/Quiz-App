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
import java.sql.Statement;
import java.util.ArrayList;
import mytqaproj.dbutil.DBConnection;
import mytqaproj.pojo.Performance;
import mytqaproj.pojo.StudentScore;

/**
 *
 * @author DELL
 */
public class PerformanceDAO {
    public static ArrayList<String> getAllStudentsId() throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select distinct userid from performance");
        ArrayList<String> studentsList=new ArrayList<>();
        while(rs.next())
        {
            studentsList.add(rs.getString(1));
        }
        return studentsList;
    }
    public static ArrayList<String> getAllExamId(String userId) throws SQLException
    {
        String qry="select examid from performance where userid=?";
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,userId);
        ResultSet rs=ps.executeQuery();
        ArrayList<String> examId=new ArrayList<>();
        while(rs.next())
        {
            examId.add(rs.getString(1));
        }
        return examId;
    }
    public static StudentScore getScore(String studentId, String examId) throws SQLException
    {
        String qry="select per,language from performance where userid=? and examid=?";
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1, studentId);
        ps.setString(2, examId);
        
        ResultSet rs=ps.executeQuery();
        StudentScore scoreObj=new StudentScore();
        rs.next();
        scoreObj.setLanguage(rs.getString(2));
        scoreObj.setPercentage(rs.getDouble(1));
        return scoreObj;           
    }
    public static ArrayList<Performance> getAllData() throws SQLException{
        String qry="select * from performance ";
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery(qry);
        ArrayList<Performance> allData=new ArrayList<Performance>();
        while(rs.next())
        {
            Performance perform=new Performance(rs.getString(1),rs.getString(2),rs.getString(7),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDouble(6));
            allData.add(perform);
        }
        return allData;
    }
    
    public static void addPerformance(Performance perform) throws SQLException
    {
        String qry="insert into performance values(?,?,?,?,?,?,?)";
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement(qry);
        ps.setString(1,perform.getUserid());
        ps.setString(2,perform.getExamid());
        ps.setInt(3,perform.getRight());
        ps.setInt(4,perform.getWrong());
        ps.setInt(5, perform.getUnattempted());
        ps.setDouble(6, perform.getPer());
        ps.setString(7, perform.getLanguage());
        ps.executeUpdate();
    }
    
    
            
}
