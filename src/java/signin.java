import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rupak
 */
public class signin extends HttpServlet {

   public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw=res.getWriter();
        String nm1=req.getParameter("n2");
        String nm2=req.getParameter("n3");
    
       
        try {
                
                  Class.forName("oracle.jdbc.driver.OracleDriver");
                  Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
                  
                  Statement stmt=con.createStatement();
                  String q1="select * from t11 where GMAIL='"+nm1+"' and PWD='"+nm2+"'";
                    ResultSet rs=stmt.executeQuery(q1);
        if(rs.next())
        {
            
            String name=rs.getString(1);
            Cookie ck=new Cookie("name",name);
           res.addCookie(ck);
           RequestDispatcher rd=req.getRequestDispatcher("user.html");
           rd.include(req,res);
           pw.println("<html>\n" +
"<body bgcolor=cyan> \n" +
"<br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7 color='black'><u><i>Welcome "+name+" \n" +
"</center></body></html>");           


        }
       else
        {
            pw.println("<html>\n" +
"<body bgcolor=red> \n" +
"<br><br><br><br><br><br><br><br><br><br><br><br>\n" +
"<center><font face=Matura MT Script Capitals size=7><u><i>Sorry Your <strong>Password</strong> is Wrong \n" +
"                        </i></u></font></center><br> <br><center>For Sign IN Other Account:<a href=\"signin.html\"><strong>Click Here</strong></a><br><br>\n" +
"                   For Sign up:<a href=\"signup.html\"><strong>Click Here</strong></a><br></center></body></html>");
        }
        con.close();
        
            } 
            catch (Exception ex) {
               pw.println(ex);
            }
	
    }
}
