/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "VerificarLogin", urlPatterns = {"/VerificarLogin"})
public class VerificarLogin extends HttpServlet {
    
    Connection conexao;
    boolean loginOK = false;
    
    @Override
    public void init() throws ServletException {
         try {
             conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/Eletroponei", "adm", "adm");
        } catch (SQLException ex) {
            Logger.getLogger(VerificarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nome_user = request.getParameter("InputLogin");
        String senha_user = request.getParameter("InputSenha");
        try ( PreparedStatement sql = conexao.prepareStatement("SELECT * FROM ADMINISTRADOR")){
  
            ResultSet resultado = sql.executeQuery();
            resultado.next();
            String nomeNome = resultado.getString("LOGIN");
            String senhaSenha = resultado.getString("SENHA");
            if(senhaSenha.equals(senha_user) && nomeNome.equals(nome_user))
                loginOK=true;
                
        } catch (SQLException ex) {
            Logger.getLogger(VerificarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (loginOK) {
            HttpSession session = request.getSession();
            session.setAttribute("NomeUsuarioLogado", nome_user);
            session.setAttribute("Senha", senha_user);
            session.setAttribute("logado", "ok");
            
            RequestDispatcher resposta = request.getRequestDispatcher("/edit");
            resposta.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                response.sendRedirect("index.jsp");
    }
    @Override
    public void destroy() {
        try {
            conexao.close();
        } catch (SQLException ex) {
            Logger.getLogger(VerificarLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
