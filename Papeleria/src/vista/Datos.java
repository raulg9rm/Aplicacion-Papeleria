package vista;

public class Datos {
    public static String usuario;
    public static String password;
    public static String ip;
    
    /*public static String usuario;
    public static String password;
    public static String ip;*/
    
    
    public Datos(String usuario, String password, String ip){
        this.usuario=usuario;
        this.password=password;
        this.ip=ip;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        Datos.usuario = usuario;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Datos.password = password;
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        Datos.ip = ip;
    }

}
