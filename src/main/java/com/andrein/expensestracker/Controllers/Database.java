package com.andrein.expensestracker.Controllers;

import com.andrein.expensestracker.Models.Expense;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import javax.sql.DataSource;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Database {
    private Sql2o sql2o;

    public Database() {
        this.sql2o = new Sql2o("jdbc:sqlite:expensestracker.db", null, null);
        createTables();
    }

    private void createTables() {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(255), password VARCHAR(255), firstName VARCHAR(255), lastName VARCHAR(255), budget INTEGER default 0)").executeUpdate();
            connection.createQuery("CREATE TABLE IF NOT EXISTS expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, userId INTEGER, merchant VARCHAR(255), price REAL, description VARCHAR(255), day INTEGER, month INTEGER, year INTEGER, category VARCHAR(255), FOREIGN KEY (userId) REFERENCES users(id))").executeUpdate();
        }
    }

    public int createUser(String username, String password, String firstName, String lastName) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("INSERT INTO users (username, password, firstName, lastName) VALUES (:username, :password, :firstName, :lastName)")
                    .addParameter("username", username)
                    .addParameter("password", password)
                    .addParameter("firstName", firstName)
                    .addParameter("lastName", lastName)
                    .executeUpdate()
                    .getKey(Integer.class);
        }
    }

    public boolean createExpense(int userId, double price, String merchant, String description, Integer day, Integer month, Integer year, String category) {
        try (Connection connection = sql2o.open()) {
            connection.createQuery("INSERT INTO expenses (userId, price, merchant, description, day, month, year, category) VALUES (:userId, :price, :merchant, :description, :day, :month, :year, :category)")
                    .addParameter("userId", userId)
                    .addParameter("price", price)
                    .addParameter("merchant", merchant)
                    .addParameter("description", description)
                    .addParameter("day", day)
                    .addParameter("month", month)
                    .addParameter("year", year)
                    .addParameter("category", category)
                    .executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<User> getUsers() {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT id, username, firstName, lastName, budget FROM users").executeAndFetch(User.class);
        }
    }

    public List<Expense> getExpensesForUser(int userId) {
        try (Connection connection = sql2o.open()) {
            return connection.createQuery("SELECT * FROM expenses WHERE userId = :userId")
                    .addParameter("userId", userId)
                    .executeAndFetch(Expense.class);
        }
    }

    public User getUserData(String username) {
        try (Connection connection = sql2o.open()) {
            System.out.println("Fetching user data for username: " + username);
            return connection.createQuery("SELECT id, username, firstName, lastName, budget FROM users WHERE username = :username")
                    .addParameter("username", username)
                    .executeAndFetchFirst(User.class);
        } catch (Exception e) {
            System.out.println("Error getting data: " + e.getMessage());
            return null;
        }
    }

    public boolean login(String username, String password) {
        try (Connection connection = sql2o.open()) {
            String passwordFromDb = connection.createQuery("SELECT password FROM users WHERE username = :username")
                    .addParameter("username", username)
                    .executeAndFetchFirst(String.class);
            if (passwordFromDb != null && passwordFromDb.equals(password)) {
                UserSession.getInstance().setUser(getUserData(username));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.err.println("Error during login: " + e.getMessage());
            return false;
        }
    }
}