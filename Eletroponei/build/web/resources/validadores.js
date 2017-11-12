function validarTamanho(str, min, max, campo) {
    if ((str.length >= min) && (str.length <= max)) {
        return true;
    } else {
        alert(campo + " " + "invalido");
        return false;
    }
}