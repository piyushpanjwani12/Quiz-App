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
import mytqaproj.pojo.ExamPojo;

/**
 *
 * @author DELL
 */
public class ExamDao {
    
    public static String getExamId() throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("select count(*) as totalrows from exam");
        int rowCount=0;
        if(rs.next())
        {
            rowCount=rs.getInt(1);
        }
        String newId="EX-"+(rowCount+1);
        return newId;
        
    }
    public static void addExam(ExamPojo newExam) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into exam values(?,?,?)");
        ps.setString(1, newExam.getExamId());
        ps.setString(2,newExam.getLanguage());
        ps.setInt(3, newExam.getTotalquestions());
        ps.executeUpdate();
    }
    public static ArrayList<String> getExamIdBySubject(String subject) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select examid from exam where language=?");
        ps.setString(1,subject);
        
        ResultSet rs=ps.executeQuery();
        ArrayList<String> examList=new ArrayList<>();
        while(rs.next())
        {
            examList.add(rs.getString(1));
            
        }
        return examList;
    }
    public static int getQuestionCountByExam(String examId) throws SQLException
    {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("select total_question from exam where examid=?");
        ps.setString(1,examId);
        ResultSet rs=ps.executeQuery();
        rs.next();
        int rowCount=rs.getInt(1);
        return rowCount;
    }
}
