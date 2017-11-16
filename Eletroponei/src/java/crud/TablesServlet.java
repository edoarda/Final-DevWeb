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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author edoarda
 */
@WebServlet(name = "TablesServlet", urlPatterns = {"/edit"})
public class TablesServlet extends HttpServlet {
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            request.getRequestDispatcher("/header.jsp").include(request, response);
            out.println("<div class='container'>");
            out.println("<h1 class='formy'>Servlet TablesServlet at " + request.getContextPath() + "</h1>");
            
            try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM ADMINISTRADOR")) {
            ResultSet resultado = sql.executeQuery();
            String[] pudim = {"Login", "Senha"};
          
            out.println("<center><h5><a href='/Eletroponei/CreateServlet?table=ADMINISTRADOR'>Adicionar à ADMINISTRADOR</a></h5></center>");
            table_top(out, pudim);
            while(resultado.next()) {
                out.println("<tr>");
                out.println("<div>");
                out.println("<td>");
                out.println(resultado.getString("LOGIN"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("SENHA"));
		out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/UpdateServlet?id="+ resultado.getString("ID") + "&table=ADMINISTRADOR'>EDITAR</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/DeleteServlet?id="+ resultado.getString("ID") + "&table=ADMINISTRADOR'>DELETAR</a>");
                out.println("</td>");
                out.println("</div>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

                
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM CATEGORIA")) {
            ResultSet resultado = sql.executeQuery();
            String[] pudim = {"ID", "Nome", "Descrição"};
            
            out.println("<center><h5><a href='/Eletroponei/CreateServlet?table=CATEGORIA'>Adicionar à CATEGORIA</a></h5></center>");
            table_top(out, pudim);
            while(resultado.next()) {
                out.println("<tr>");
                out.println("<div>");
                out.println("<td>");
                out.println(resultado.getString("ID"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("NOME"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("DESCRICAO"));
		out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/UpdateServlet?id="+ resultado.getString("ID") + "&table=CATEGORIA'>EDITAR</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/DeleteServlet?id="+ resultado.getString("ID") + "&table=CATEGORIA'>DELETAR</a>");
                out.println("</td>");
                out.println("</div>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

                
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM CLIENTE")) {
            ResultSet resultado = sql.executeQuery();
            String[] pudim = {"ID", 
                                "Nome",
                                "Endereço",
                                "Bairro",
                                "Cidade",
                                "CEP",
                                "UF",
                                "CPF",
                                "Referencia",
                                "Identidade",
                                "Telefone",
                                "Celular",
                                "Cartão",
                                "Bandeira"};
            
            
            out.println("<center><h5><a href='/Eletroponei/CreateServlet?table=CLIENTE'>Adicionar à CLIENTE</a></h5></center>");
            table_top(out, pudim);
            while(resultado.next()) {
                out.println("<tr>");
                out.println("<div>");
                out.println("<td>");
                out.println(resultado.getString("ID"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("NOME"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("ENDEREÇO"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("BAIRRO"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("CIDADE"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("CEP"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("UF"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("CPF"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("REFERENCIA"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("IDENTIDADE"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("TELEFONE"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("CELULAR"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("CARTAO"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("BANDEIRA"));
		out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/UpdateServlet?id="+ resultado.getString("ID") + "&table=CLIENTE'>EDITAR</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/DeleteServlet?id="+ resultado.getString("ID") + "&table=CLIENTE'>DELETAR</a>");
                out.println("</td>");
                out.println("</div>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

                
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM COMPRA")) {
            ResultSet resultado = sql.executeQuery();
            String[] pudim = {"ID", "IDCliente", "IDProduto"};
            
            out.println("<center><h5><a href='/Eletroponei/CreateServlet?table=COMPRA'>Adicionar à COMPRA</a></h5></center>");
            table_top(out, pudim);
            while(resultado.next()) {
                out.println("<tr>");
                out.println("<div>");
                out.println("<td>");
                out.println(resultado.getString("ID"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("IDCLIENTE"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("IDPRODUTO"));
		out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/UpdateServlet?id="+ resultado.getString("ID") + "&table=COMPRA'>EDITAR</a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/DeleteServlet?id="+ resultado.getString("ID") + "&table=COMPRA'>DELETAR</a>");
                out.println("</td>");
                out.println("</div>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

                
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM PRODUTO")) {
            ResultSet resultado = sql.executeQuery();
            String[] pudim = {"ID", "IDCategoria", "Nome", "Descrição", "Valor"};
            
            out.println("<center><h5><a href='/Eletroponei/CreateServlet?table=PRODUTO'>Adicionar à PRODUTO</a></h5></center>");
            table_top(out, pudim);
            while(resultado.next()) {
                out.println("<tr>");
                out.println("<div>");
                out.println("<td>");
                out.println(resultado.getString("ID"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("IDCATEGORIA"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("NOME"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("DESCRICAO"));
                out.println("</td>");
                out.println("<td>");
                out.println(resultado.getString("VALOR"));
		out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/UpdateServlet?id="+ resultado.getString("ID") + "&table=PRODUTO'><button onclick='alert('Batata')'>EDITAR</button></a>");
                out.println("</td>");
                out.println("<td>");
                out.println("<a href='/Eletroponei/DeleteServlet?id="+ resultado.getString("ID") + "&table=PRODUTO'>DELETAR</a>");
                out.println("</td>");
                out.println("</div>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("</div>");

                
        } catch (SQLException ex) {
            Logger.getLogger(TablesServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
            out.println("</body>");
            out.println("</html>");
            out.println("</div>");
            request.getRequestDispatcher("/footer.jsp").include(request, response);
        }
    }
    
    /*public void table_top(PrintWriter out) {
            out.println("<div class='table-responsive'>");
            out.println("<table class='table'>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Login</th>");
            out.println("<th>Senha</th>");
            out.println("<th>Editar</th>");
            out.println("<th>Deletar</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
        
    }*/
    
    public void table_top(PrintWriter out, String[] args){
        out.println("<div class='table-responsive'>");
        out.println("<table class='table'>");
        out.println("<thead>");
        out.println("<tr>");
        for(int i = 0; i<args.length; i++){
            out.println("<th>"+args[i]+"</th>");
        }
        out.println("<th>Editar</th>");
        out.println("<th>Deletar</th>");
        out.println("</tr>");
        out.println("</thead>");
        out.println("<tbody>");
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
