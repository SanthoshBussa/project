/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionPackage;

import DbPack.DatabaseConnection;
import DbPack.TrippleDes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jp
 */
public class updateFile extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession(true);
            String ses =  session.getAttribute("fid").toString();
            System.out.println("fid is "+ses);
            String getFiledata = request.getParameter("gets");
            String gr = null;
            String getUser = session.getAttribute("ID").toString();
          String groupname=(String)session.getAttribute("group");
              String odata= session.getAttribute("odata").toString();
              System.out.println("dddddddddd "+odata);
            // out.println(getFiledata+"\n");
            Connection con = DatabaseConnection.getCon();
            Statement st = con.createStatement();
            Statement st3 = con.createStatement();
            ResultSet rs = st3.executeQuery("select * from upload where user_id='" + getUser + "' and group_='"+groupname+"' and file_name= '" + ses + "' ");
            if(rs.next()) {
                
                           int i = st.executeUpdate("update upload set file_data = '" + new TrippleDes().encrypt(getFiledata) + "' where file_name = '" + ses + "'  ");
                           response.sendRedirect("fileDetails.jsp?message=success");
            }
            else{
        
                        
            PreparedStatement st2 = con.prepareStatement("update fileaudit set original=?,duplicate=?,status='NO' where ownername=? and  filename=?");
            
st2.setString(1,odata);
st2.setString(2,getFiledata);
 st2.setString(3,getUser);
st2.setString(4,ses);
int i = st2.executeUpdate();
if(i==1)     
{
                         response.sendRedirect("fileDetails.jsp?message=fail");
}else{                            
PreparedStatement st1 = con.prepareStatement("insert into fileaudit(ownername,filename,original,duplicate,status) values(?,?,?,?,'NO')");
st1.setString(1,getUser);
st1.setString(2,ses);
st1.setString(3,odata);
st1.setString(4,getFiledata);
int j = st1.executeUpdate();
  response.sendRedirect("fileDetails.jsp?message=fail");
}    
            
            
            }    
            
            rs.close();
         


        } catch (Exception ex) {
            Logger.getLogger(updateFile.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
