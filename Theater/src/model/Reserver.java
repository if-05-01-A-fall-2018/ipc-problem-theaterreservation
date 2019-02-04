package model;

public class Reserver implements Runnable{
    private static String firstName;
    private static String lastName;
    private static String email;
    private static String seatNo;

    public Reserver(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

    }

    public static String getFirstName() {
        return firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setFirstName(String firstName) {
        Reserver.firstName = firstName;
    }

    public static void setLastName(String lastName) {
        Reserver.lastName = lastName;
    }

    public static void setEmail(String email) {
        Reserver.email = email;
    }
    public static String getSeatNo() {
        return seatNo;
    }

    public static void setSeatNo(String seatNo) {
        Reserver.seatNo = seatNo;
    }

    @Override
    public void run() {

    }
}
