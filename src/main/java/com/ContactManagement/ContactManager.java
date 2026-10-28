package com.ContactManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactManager {

    public void addContact(Contact contact) {
        String sql = "INSERT INTO contacts (id, name, phone, email) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, contact.getId());
                ps.setString(2, contact.getName());
                ps.setString(3, contact.getPhone());
                ps.setString(4, contact.getEmail());

                ps.executeUpdate();
                System.out.println("Contact Added Successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error adding contact: " + e.getMessage());
        }
    }

    public void viewContacts() {
        String sql = "SELECT * FROM contacts";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {
                    found = true;
                    System.out.println("--------------------");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Phone: " + rs.getString("phone"));
                    System.out.println("Email: " + rs.getString("email"));
                }

                if (!found) {
                    System.out.println("No Contacts Found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void searchContact(int id) {
        String sql = "SELECT * FROM contacts WHERE id = ?";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Contact Found!");
                        System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Phone: " + rs.getString("phone"));
                        System.out.println("Email: " + rs.getString("email"));
                    } else {
                        System.out.println("Contact Not Found!");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteContact(int id) {
        String sql = "DELETE FROM contacts WHERE id = ?";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Contact Deleted!");
                } else {
                    System.out.println("Contact Not Found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateContact(int id, String name, String phone, String email) {
        String sql = "UPDATE contacts SET name=?, phone=?, email=? WHERE id=?";

        try (Connection con = DBConnection.getConnection()) {
            if (con == null) return;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, email);
                ps.setInt(4, id);

                int rows = ps.executeUpdate();

                if (rows > 0) {
                    System.out.println("Contact Updated Successfully!");
                } else {
                    System.out.println("Contact Not Found!");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}