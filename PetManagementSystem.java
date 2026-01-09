package in.sp.pet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


/* ---------- PET CLASS ---------- */
class Pet {
    int id;
    String type;
    String name;
    double price;

    Pet(int id, String type, String name, double price) {
        id = id;
        type = type;
        name = name;
        price = price;
    }
}

/* ---------- MAIN CLASS ---------- */
public class PetManagementSystem {

    static Scanner sc = new Scanner(System.in);
    static boolean isLogin = false;

    /* ---------- DATABASE CONNECTION ---------- */
    static Connection getConnection() {
        Connection con = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/petshop_db",
                    "root",
                    "root"
            );

        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }
        return con;
    }

    /* ---------- MAIN ---------- */
    public static void main(String[] args) {

        int choice = 0;

        while (choice != 3) {
            System.out.println("\n==== PET SHOP ====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                registerUser();
            }
            else if (choice == 2) {
                loginUser();
                if (isLogin) {
                    petMenu();
                }
            }
            else if (choice == 3) {
                System.out.println("Thank you!");
            }
            else {
                System.out.println("Wrong choice!");
            }
        }
    }

    /* ---------- REGISTER ---------- */
    static void registerUser() {

        System.out.print("Enter username: ");
        String u = sc.next();

        System.out.print("Enter password: ");
        String p = sc.next();

        try {
            Connection con = getConnection();
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO users VALUES (?, ?)");

            ps.setString(1, u);
            ps.setString(2, p);
            ps.executeUpdate();

            System.out.println("Registration successful!");
            con.close();
        } catch (Exception e) {
            System.out.println("Registration failed");
        }
    }

    /* ---------- LOGIN ---------- */
    static void loginUser() {

        System.out.print("Enter username: ");
        String u = sc.next();

        System.out.print("Enter password: ");
        String p = sc.next();

        try {
            Connection con = getConnection();
            PreparedStatement ps =
                    con.prepareStatement(
                            "SELECT * FROM users WHERE username=? AND password=?");

            ps.setString(1, u);
            ps.setString(2, p);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                isLogin = true;
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid login!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Login error");
        }
    }

    /* ---------- PET MENU ---------- */
    static void petMenu() {

        int ch = 0;

        while (ch != 5) {
            System.out.println("\n--- PET MENU ---");
            System.out.println("1. Add Dog");
            System.out.println("2. Add Cat");
            System.out.println("3. View Pets");
            System.out.println("4. Buy Pet");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            if (ch == 1) addPet("Dog");
            else if (ch == 2) addPet("Cat");
            else if (ch == 3) viewPets();
            else if (ch == 4) buyPet();
            else if (ch == 5) {
                isLogin = false;
                System.out.println("Logged out!");
            }
            else System.out.println("Wrong choice!");
        }
    }

    /* ---------- ADD PET ---------- */
    static void addPet(String type) {

        System.out.print("Enter pet id: ");
        int id = sc.nextInt();

        System.out.print("Enter pet name: ");
        String name = sc.next();

        System.out.print("Enter pet price: ");
        double price = sc.nextDouble();

        try {
            Connection con = getConnection();
            PreparedStatement ps =
                    con.prepareStatement("INSERT INTO pets VALUES (?, ?, ?, ?)");

            ps.setInt(1, id);
            ps.setString(2, type);
            ps.setString(3, name);
            ps.setDouble(4, price);

            ps.executeUpdate();
            System.out.println(type + " added!");
            con.close();
        } catch (Exception e) {
            System.out.println("Pet add failed");
        }
    }

    /* ---------- VIEW PETS ---------- */
    static void viewPets() {

        try {
            Connection con = getConnection();
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM pets");

            ResultSet rs = ps.executeQuery();

            System.out.println("\nID  TYPE  NAME  PRICE");

            while (rs.next()) {
                System.out.println(
                        rs.getInt(1) + "   " +
                        rs.getString(2) + "   " +
                        rs.getString(3) + "   " +
                        rs.getDouble(4)
                );
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Error fetching pets");
        }
    }

    /* ---------- BUY PET WITH CONFIRMATION ---------- */
    static void buyPet() {

        System.out.print("Enter pet id: ");
        int id = sc.nextInt();

        try {
            Connection con = getConnection();
            PreparedStatement ps =
                    con.prepareStatement("SELECT * FROM pets WHERE id=?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n--- PET DETAILS ---");
                System.out.println("ID    : " + rs.getInt(1));
                System.out.println("Type  : " + rs.getString(2));
                System.out.println("Name  : " + rs.getString(3));
                System.out.println("Price : " + rs.getDouble(4));

                System.out.print("Confirm purchase (Y/N): ");
                char c = sc.next().charAt(0);

                if (c == 'Y' || c == 'y') {

                    PreparedStatement ps2 =
                            con.prepareStatement("DELETE FROM pets WHERE id=?");
                    ps2.setInt(1, id);
                    ps2.executeUpdate();

                    System.out.println("Purchase successful!");
                } else {
                    System.out.println("Purchase cancelled.");
                }

            } else {
                System.out.println("Pet not found!");
            }
            con.close();
        } catch (Exception e) {
            System.out.println("Purchase error");
        }
    }
}
