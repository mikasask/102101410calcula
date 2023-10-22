package cal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class calcu extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public calcu() {
        super();
    }
    @Override
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");		
		String p=request.getParameter("text");     
		String q=request.getParameter("result"); 
		//p="his3";
		if(p.equals("his1"))
		{
			String t=viewhisgs();
		    PrintWriter outp=response.getWriter();
		    outp.println(t);   
		}
		else if(p.equals("his2"))
		{
			String t=viewhisjg();
		    PrintWriter outp=response.getWriter();
		    outp.println(t);   			
		}
		else if(p.equals("his3"))
		{
			String t=viewhis();
		    PrintWriter outp=response.getWriter();
		    outp.println(t);   			
		}
		else{
            if(p!=null) {
            	p=p.replace(' ','+');
        	    PrintWriter outp=response.getWriter();
        	    outp.println(q);   
        	    addhis(p,q);	
            }					
		}
 }	
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	} 
	
	public static Connection getConnection()
	{
		Connection c=null;
	try{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://127.0.0.1:3306/rjzy";
		String name="root";
		String password="102101410wyy";
		c=DriverManager.getConnection(url,name,password);
	 }catch(Exception e) {
		 e.printStackTrace();
	 }	
	return c;
	}
	
	public static void addhis(String t1,String t2)
	{
		Connection con=getConnection();
		try {
			String sql="INSERT INTO history (历史结果,历史公式) VALUES (?,?)";
			PreparedStatement state=con.prepareStatement(sql);
			state.setString(1, t2);
			state.setString(2, t1);
			state.executeUpdate(); 
			//System.out.println(t1+t2);
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {	
			
		}			
	}
	public static String viewhisgs()
	{
		String re="";
		Connection con=getConnection();
	    Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=con.createStatement();			
			String sql="SELECT * FROM history";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				re=rs.getString("历史公式");
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return re;
	}
	public static String viewhisjg()
	{
		String re="";
		Connection con=getConnection();
	    Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=con.createStatement();			
			String sql="SELECT * FROM history";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				re=rs.getString("历史结果");
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return re;
	}
	public static String viewhis()
	{
		String re="";
		Connection con=getConnection();
	    Statement stmt=null;
		ResultSet rs=null;
		try {
			stmt=con.createStatement();			
			String sql="SELECT * FROM history";
			rs=stmt.executeQuery(sql);
			while(rs.next())
			{
				re=re+rs.getString("历史公式")+"="+rs.getString("历史结果")+"\n";
			}
			rs.close();
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return re;
	}	
  }

