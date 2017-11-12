<%-- 
    Document   : admin
    Created on : 05/11/2017, 19:34:57
    Author     : Edoarda
--%>

<jsp:include page="header.jsp" />
<div class="container formy">
<h1> Área Administrativa </h1>
<form action="VerificarLogin" method="post">
  <div class="form-group">
    <label for="InputLogin">Login</label>
    <input type="text" class="form-control" name="InputLogin" placeholder="Digite seu usuário" required>
  </div>
  <div class="form-group">
    <label for="InputSenha">Senha</label>
    <input type="password" class="form-control" name="InputSenha" placeholder="Digite sua senha" required\>
  </div>
  <div class="text-center">
    <button type="submit" class="btn btn-primary form-btn">Enviar</button>
  </div>
</form>
</div>
<jsp:include page="footer.jsp" />