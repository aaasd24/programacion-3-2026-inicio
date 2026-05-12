package utils;

import org.mindrot.jbcrypt.BCrypt;

public class ContraseniaUtils {

    // Hashea una contraseña
    public static String hashContrasenia(String contraseniaTextoPlano) {
        return BCrypt.hashpw(contraseniaTextoPlano, BCrypt.gensalt());
    }

    // Verifica una contraseña con el hash almacenado
    public static boolean checkContrasenia(String contraseniaPlana, String contraseniaHasheada) {
        return BCrypt.checkpw(contraseniaPlana, contraseniaHasheada);
    }
}