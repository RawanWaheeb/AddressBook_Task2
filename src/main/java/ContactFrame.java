import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ContactFrame extends JFrame {
    private JTextField txtName = new JTextField(20);
    private JTextField txtEmail = new JTextField(20);
    private JTextField txtPhone = new JTextField(20);

    private JButton btnNext = new JButton("Next");
    private JButton btnPrev = new JButton("Previous");
    private JButton btnFirst = new JButton("First");
    private JButton btnLast = new JButton("Last");

    private ContactDAO dao = new ContactDAO();
    private ResultSet rs;

    public ContactFrame() {
        setTitle("Address Book Management System");
        setLayout(new GridLayout(5, 2, 10, 10));
        setSize(500, 350);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        add(new JLabel("  Full Name:")); add(txtName);
        add(new JLabel("  Email Address:")); add(txtEmail);
        add(new JLabel("  Mobile Number:")); add(txtPhone);

        add(btnFirst); add(btnPrev);
        add(btnNext); add(btnLast);

        // Action Listeners
        btnNext.addActionListener(e -> {
            try {
                if (rs.next()) displayContact();
                else JOptionPane.showMessageDialog(this, "End of list.");
            } catch (SQLException ex) { ex.printStackTrace(); }
        });

        btnPrev.addActionListener(e -> {
            try {
                if (rs.previous()) displayContact();
                else JOptionPane.showMessageDialog(this, "Start of list.");
            } catch (SQLException ex) { ex.printStackTrace(); }
        });

        btnFirst.addActionListener(e -> { try { if (rs.first()) displayContact(); } catch (SQLException ex) { ex.printStackTrace(); } });
        btnLast.addActionListener(e -> { try { if (rs.last()) displayContact(); } catch (SQLException ex) { ex.printStackTrace(); } });

        loadData();
        setVisible(true);
    }

    private void loadData() {
        try {
            dao.initializeDatabaseIfEmpty();
            dao.updateEmailsBatch();

            rs = dao.getAllContactsScrollable();
            if (rs.next()) {
                displayContact();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void displayContact() {
        try {
            txtName.setText(rs.getString("name"));
            txtEmail.setText(rs.getString("email"));
            txtPhone.setText(rs.getString("cell_phone"));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactFrame());
    }
}