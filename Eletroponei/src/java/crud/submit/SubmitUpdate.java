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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
@WebServlet(name = "SubmitUpdate", urlPatterns = {"/SubmitUpdate"})
public class SubmitUpdate extends HttpServlet {
    
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
        String id = request.getParameter(campos.nextElement());
        String table = request.getParameter(campos.nextElement());
        
        try (PrintWriter out = response.getWriter()) {
            String update = "";
            try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM " + table + " WHERE ID = " + id)) {
                ResultSet resultado = sql.executeQuery();
                ResultSetMetaData rmd = resultado.getMetaData();


                String name;
                int i=2;
                String text;

                update="UPDATE "+table+" SET ";
                text = request.getParameter(campos.nextElement());
                if(Pattern.matches("[0-9.]+", text))
                    update+=rmd.getColumnName(i)+"="+text+" ";
                else
                    update+=rmd.getColumnName(i)+"='"+text+"' ";
                i++;
                
                while(campos.hasMoreElements()){
                    text = request.getParameter(campos.nextElement());
                    if(Pattern.matches("[0-9.]+", text))
                        update+=", "+rmd.getColumnName(i)+"="+text+" ";
                    else
                        update+=", "+rmd.getColumnName(i)+"='"+text+"' ";
                    i++;
                }
                update+="WHERE ID="+id;
                
                PreparedStatement sql2 = conexao.prepareStatement(update);
                sql2.executeUpdate();

                response.setContentType("text/html;charset=UTF-8");

                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SubmitUpdate</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet SubmitUpdate at " + request.getContextPath() + "</h1>");
                out.println("<h1>Atualizado com Sucesso</h1>");
                out.println("</body>");
                out.println("</html>");

            } catch (SQLException ex) {
                Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
                response.setContentType("text/html;charset=UTF-8");
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet SubmitUpdate</title>");            
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet SubmitUpdate at " + request.getContextPath() + "</h1>");
                out.println("<h1>Ocorreu um Erro</h1>");
                out.println(update+" ~~~~~~> "+ex);
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
