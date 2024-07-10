/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package actionPackage;

import DbPack.TrippleDes;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jp
 */
public class register extends HttpServlet {

    
    
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            String conpass = request.getParameter("conpassword");
            String group = request.getParameter("group");
            String email = request.getParameter("email");
            String mobile = request.getParameter("mobile");
            String place = request.getParameter("place");
            //String mode = request.getParameter("mode");
            if(pass.equals(conpass)){
            Connection con  =DbPack.DatabaseConnection.getCon();
            Statement st = con.createStatement();
            int i = st.executeUpdate("insert into register values('"+user+"','"+pass+"','"+group+"','"+email+"','"+mobile+"','"+place+"','NO','0')"); 
             if(i!=0){
                 
                 
                 mail_Send.sendMail(new TrippleDes().encrypt(group), user, email);
                 Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
        "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        // Assuming you are sending email from localhost


        Session session = Session.getDefaultInstance(props,
        new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("cspmanageralerts@gmail.com","9666463655");
        }
        });

       
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(request.getParameter("username")));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(request.getParameter("email")));
            message.setSubject("CLLOUD GROUP: Activate your account & Group Signature key ");
            
             String ss="Activate your account"+"\n "+"\n ";

             String name="Dear" +"   "+request.getParameter("username")+"\n "+"\n ";

             String moto="Welcome to cloud Group!"+"\n "+"\n ";

             String set="This message confirms that the following user profile was successfully created:"+"\n "+"\n ";
             String confi="Email:"+request.getParameter("email")+"\n "+"\n "; 
            
             String stt="Once you complete activation, you can log in and Enter Your GROUP SIGNATURE KEY"+"\n "+"\n ";
            
             String ack="Thank you for your interest in Cloud Group!!"+"\n "+"\n ";
           // message.setText("YOU Have Successfully Registered Your Account with ID:  "+request.getParameter("username")+"   AND  PASSWORD:  "+request.getParameter("password")+"  .Please Activate Your Account by Click on The Following Link:");
             message.setText(""+ss+""+name+""+moto+""+set+""+confi+""+stt+""+ack+"");
            Transport.send(message);

            System.out.println("Done");
            

        } catch (MessagingException e) {
            System.out.println(e);
            e.printStackTrace();
                        
           // throw new RuntimeException(e);
        }
                 
                 response.sendRedirect("member_login.jsp?m=Registeration done");
                 
             }
             else{
                   response.sendRedirect("register.jsp?m=register error...check");
             }
            }
            else{
                response.sendRedirect("register.jsp?m=password not match");
            }
        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }
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
