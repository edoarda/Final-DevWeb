/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author edoarda
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {
    
    Connection conexao;
    boolean loginOK = false;
    
    @Override
    public void init() throws ServletException {
         try {
             conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/Eletroponei", "adm", "adm");
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String table = request.getParameter("table");
        //request.setAttribute("table", table);
       
        request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div class='container formy'>");
            out.println("<h1>Criando elemento em " + table + "</h1>");
       
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM " + table)) {
            ResultSet resultado = sql.executeQuery();
            ResultSetMetaData rmd = resultado.getMetaData();
            int ccount = rmd.getColumnCount();
            String name;
            out.println("<form action='SubmitCreate' method='post'>");
            out.println("<div class='form-group'>");
            out.println("<input type='hidden' name='table' value='"+ table + "'");
            out.println("</div>");
            //while(resultado.next()) {
                for (int i = 2; i <= ccount; i++) {
                name = rmd.getColumnName(i);
                out.println("<div class='form-group'>");
                out.println("<label for='Input" + name + "'>"+ name + "</label>");
                out.println("<input type='text' class='form-control' name='Input" +
                        name + "' required>");
                out.println("</div>");
                }
            //}
            out.println("<div class='text-center'>");
            out.println("<button type='submit' class='btn btn-primary form-btn'>Enviar</button>");
            out.println("</div>");
            out.println("</form></div>");
            
            request.getRequestDispatcher("/footer.jsp").include(request, response);
            //RequestDispatcher rd = getServletContext().getRequestDispatcher("/SubmitCreate");
            //rd.include(request, response);
     
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}