/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.submit;

import crud.TablesServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edoarda
 */
@WebServlet(name = "SubmitCreate", urlPatterns = {"/SubmitCreate"})
public class SubmitCreate extends HttpServlet {
    
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Enumeration<String> campos = request.getParameterNames();
        //falta pegar a tabela certa
        String table = request.getParameter(campos.nextElement());
        String insert="INSERT INTO "+table+" VALUES(";
        //request.getParameter(campos.nextElement());//QUANDO TIRAR O ID DO FORM TEM Q TIRAR ESSA LINHA
        insert+="default";
        String text;
        while(campos.hasMoreElements()){
            text = request.getParameter(campos.nextElement());
            if(Pattern.matches("[0-9.]+", text))
                insert+=", "+text;
            else
                insert+=", '"+text+"'";
        }
        insert+=")";
        
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("/header.jsp").include(request, response);
        try (PrintWriter out = response.getWriter()) {
            out.println("<h1 class='formy'>Servlet SubmitCreate at " + request.getContextPath() + "</h1>");
            try ( PreparedStatement sql = conexao.prepareStatement(insert)) {
                sql.executeUpdate();
                out.println("<h1 class='formy'>Inserido com Sucesso</h1>");
                out.println("<a href='TablesServlet'> Voltar </a>");
                request.getRequestDispatcher("/header.jsp").include(request, response);
            }catch(SQLException ex) {
                Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
                out.println("<h1>Ocorreu erro na inserção</h1>");
                out.println(insert+" ~~~~~~> "+ex);
                out.println("</body>");
                out.println("</html>");
            }
        }
    }

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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
