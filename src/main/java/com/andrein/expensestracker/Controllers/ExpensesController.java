package com.andrein.expensestracker.Controllers;

import com.andrein.expensestracker.Models.Expense;
import com.andrein.expensestracker.Models.User;
import com.andrein.expensestracker.UserSession;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ExpensesController {
    public ExpensesController() {}
    Database database = new Database();
    User loggedInUser = UserSession.getInstance().getLoggedUser();

    public Double getExpensesSum() {
        List<Expense> expenses = getCurrentMonthExpenses();
        double expensesSum = 0;
        for(Expense expense : expenses) {
            expensesSum += expense.getPrice();
        }
        return expensesSum;
    }

    public Expense getBiggestExpense() {
        List<Expense> expenses = getCurrentMonthExpenses();
        if (expenses == null || expenses.isEmpty()) {
            return null;
        }

        Expense biggestExpense = null;
        for (Expense expense : expenses) {
            if (biggestExpense == null || expense.getPrice() > biggestExpense.getPrice()) {
                biggestExpense = expense;
            }
        }

        return biggestExpense;
    }

    public String getMostExpensiveCategory() {
        List<Expense> expenses = getCurrentMonthExpenses();
        if(expenses.isEmpty() || expenses == null) return "No expenses found";

        Map<String, Double> categoryTotals = new HashMap<>();
        for(Expense expense : expenses) {
            String category = expense.getCategory();
            double price = expense.getPrice();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + price);
        }

        String mostExpensiveCategory = null;
        double highestTotal = 0.0;
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            if (mostExpensiveCategory == null || entry.getValue() > highestTotal) {
                mostExpensiveCategory = entry.getKey();
                highestTotal = entry.getValue();
            }
        }

        return mostExpensiveCategory + ";" + Double.toString(highestTotal);
    }

    public Map<String, Double> getCategoriesPercentages() {
        Map<String, Double> categoriesPercentages = new HashMap<>();
        Map<String, Double> categoryTotals = new HashMap<>();
        List<Expense> expenses = getCurrentMonthExpenses();
        if(expenses.isEmpty() || expenses == null) return null;

        //Calculate categories totals
        for(Expense expense : expenses) {
            String category = expense.getCategory();
            double price = expense.getPrice();
            categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + price);
        }

        //Add categories percentages
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            double percentage = (entry.getValue() * 100)/getExpensesSum();
            categoriesPercentages.put(entry.getKey(), percentage);
        }

        return categoriesPercentages;
    }

    public ArrayList<Expense> getLast7DaysExpenses() {
        List<Expense> expenses = database.getExpensesForUser(loggedInUser.getId());
        ArrayList <Expense> last7DaysExpenses = new ArrayList<>();

        for(Expense expense : expenses) {
            LocalDate expenseDate = LocalDate.of(expense.getYear(), expense.getMonth(), expense.getDay());
            long daysBetween = ChronoUnit.DAYS.between(expenseDate, LocalDate.now());
            if (daysBetween <= 7) {
                last7DaysExpenses.add(expense);
            }
        }

        return last7DaysExpenses;
    }

    public List<Expense> getCurrentMonthExpenses() {
        List<Expense> expenses = database.getExpensesForUser(loggedInUser.getId());
        List<Expense> currentMonthExpenses = new ArrayList<>();
        LocalDate now = LocalDate.now();

        for (Expense expense : expenses) {
            LocalDate expenseDate = LocalDate.of(expense.getYear(), expense.getMonth(), expense.getDay());
            if (expenseDate.getMonth() == now.getMonth() && expenseDate.getYear() == now.getYear()) {
                currentMonthExpenses.add(expense);
            }
        }

        return currentMonthExpenses;
    }

    public List<String> getAvailableExpensesMonths() {
        ArrayList<String> months = new ArrayList<>();
        List<Expense> expenses = database.getExpensesForUser(loggedInUser.getId());
        for(Expense expense : expenses) {
            int month = expense.getMonth();
            String monthString = Month.of(month).name().substring(0, 1) + Month.of(month).name().substring(1).toLowerCase();
            months.add(monthString + " " + expense.getYear());
        }

        Set<String> uniqueEntries = new HashSet<>(months);
        months.clear();
        months.addAll(uniqueEntries);

        return months;
    }

    public List<Expense> getExpensesByMonth(int month, int year) {
        List<Expense> totalExpenses = database.getExpensesForUser(loggedInUser.getId());
        List<Expense> expenses = new ArrayList<>();
        for(Expense expense : totalExpenses) {
            if(expense.getMonth() == month && expense.getYear() == year) expenses.add(expense);
        }

        return expenses;
    }
}
