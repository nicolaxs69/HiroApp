package xebia.ismail.e_learning;

/**
 * Created by horror on 12/14/16.
 */

public class User {

    private String name;
    private String distrito;
    private String email;
    private String password;
    private double telefono;
    private double dni;
    private String date;



    public User(String n, String c, double w, double d, String e, String p, String da) {

        this.name = n;
        this.distrito = c;
        this.telefono = w;
        this.dni = d;
        this.email= e;
        this.password = p;
        this.date= da;
    }

    public String getName() {
        return name;
    }

    public String getDistrito() {
        return distrito;
    }

    public double getTelefono() {
        return telefono;
    }

    public double getDni() {
        return dni;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name +" "+ distrito +" " +telefono +" " +dni +" " +email +" " +password+" " +date;
    }
}
