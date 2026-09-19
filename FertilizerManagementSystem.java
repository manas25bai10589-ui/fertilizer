import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FertilizerManagementSystem extends JFrame {

    // ==========================================
    // 1. DATA MODELS (Object-Oriented Approach)
    // ==========================================
    static class Product {
        String name;
        int stock, demand, price, discount;

        public Product(String name, int stock, int demand, int price, int discount) {
            this.name = name; this.stock = stock; this.demand = demand; this.price = price; this.discount = discount;
        }
        @Override public String toString() { return name + " - ₹" + price; } // Used for dropdowns
    }

    static class PreOrder {
        String farmer, product, mobile, date, status;
        int quantity;

        public PreOrder(String farmer, String product, int quantity, String date, String mobile, String status) {
            this.farmer = farmer; this.product = product; this.quantity = quantity; 
            this.date = date; this.mobile = mobile; this.status = status;
        }
    }

    static class Order {
        String farmer, product, date, status;
        int quantity;

        public Order(String farmer, String product, int quantity, String date, String status) {
            this.farmer = farmer; this.product = product; this.quantity = quantity; 
            this.date = date; this.status = status;
        }
    }

    static class Feedback {
        String farmer, product, comment, date;
        int rating;

        public Feedback(String farmer, String product, int rating, String comment, String date) {
            this.farmer = farmer; this.product = product; this.rating = rating; 
            this.comment = comment; this.date = date;
        }
    }

    // ==========================================
    // 2. DATA STORAGE (Lists)
    // ==========================================
    List<Product> inventory = new ArrayList<>();
    List<PreOrder> preorders = new ArrayList<>();
    List<Order> orders = new ArrayList<>();
    List<Feedback> feedbacks = new ArrayList<>();

    // Table Models (Allows us to update tables dynamically)
    DefaultTableModel preorderTableModel;
    DefaultTableModel orderTableModel;
    DefaultTableModel feedbackTableModel;

    // ==========================================
    // 3. MAIN CONSTRUCTOR (App Setup)
    // ==========================================
    public FertilizerManagementSystem() {
        // Setup Window
        setTitle("AgriCare Fertilizers - Management System");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen
        setLayout(new BorderLayout());

        loadDummyData();
        setupUI();
    }

    private void loadDummyData() {
        inventory.add(new Product("Urea", 500, 450, 300, 5));
        inventory.add(new Product("DAP", 350, 400, 1350, 8));
        inventory.add(new Product("NPK 10:26:26", 200, 180, 1200, 7));

        preorders.add(new PreOrder("Suresh Yadav", "Urea", 100, "2025-11-01", "9876543210", "Confirmed"));
        orders.add(new Order("Rajesh Kumar", "Urea", 50, "2025-10-15", "Completed"));
        feedbacks.add(new Feedback("Rajesh Kumar", "Urea", 5, "Excellent quality!", "2025-10-14"));
    }

    private void setupUI() {
        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(21, 128, 61)); // Dark Green
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("🌾 AgriCare Fertilizers");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);

        JLabel subtitleLabel = new JLabel("Smart Demand Management & Pre-Order System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(209, 250, 229)); // Light Green

        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Tabs
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(new Font("Arial", Font.BOLD, 14));

        tabbedPane.addTab("📊 Dashboard", createDashboardTab());
        tabbedPane.addTab("📅 Pre-Orders", createPreOrderTab());
        tabbedPane.addTab("📦 Inventory", createInventoryTab());
        tabbedPane.addTab("🛒 Orders", createOrdersTab());
        tabbedPane.addTab("💬 Feedback", createFeedbackTab());

        add(tabbedPane, BorderLayout.CENTER);
    }

    // ==========================================
    // 4. TAB CREATION METHODS
    // ==========================================
    private JPanel createDashboardTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Stats Cards (Top)
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.add(createStatCard("Total Products", String.valueOf(inventory.size()), new Color(22, 163, 74)));
        statsPanel.add(createStatCard("Pre-Orders", String.valueOf(preorders.size()), new Color(147, 51, 234)));
        statsPanel.add(createStatCard("Regular Orders", String.valueOf(orders.size()), new Color(37, 99, 235)));
        statsPanel.add(createStatCard("Avg Rating", "4.8⭐", new Color(234, 179, 8)));
        
        panel.add(statsPanel, BorderLayout.NORTH);

        // Demand Analysis (Center)
        JPanel analysisPanel = new JPanel();
        analysisPanel.setLayout(new BoxLayout(analysisPanel, BoxLayout.Y_AXIS));
        analysisPanel.setBorder(BorderFactory.createTitledBorder("📈 Demand vs Stock Analysis"));

        for (Product item : inventory) {
            JPanel barPanel = new JPanel(new BorderLayout());
            barPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel nameLabel = new JLabel(item.name + " (Stock: " + item.stock + " | Demand: " + item.demand + ")");
            JProgressBar progressBar = new JProgressBar(0, Math.max(item.stock, item.demand));
            progressBar.setValue(item.stock);
            progressBar.setStringPainted(true); // Shows percentage

            // Change color based on stock status
            if (item.stock < item.demand) progressBar.setForeground(Color.RED);
            else progressBar.setForeground(new Color(22, 163, 74)); // Green

            barPanel.add(nameLabel, BorderLayout.NORTH);
            barPanel.add(progressBar, BorderLayout.CENTER);
            analysisPanel.add(barPanel);
        }

        panel.add(new JScrollPane(analysisPanel), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createStatCard(String title, String value, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        card.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 5, 0));
        
        JLabel valueLabel = new JLabel(value, SwingConstants.CENTER);
        valueLabel.setFont(new Font("Arial", Font.BOLD, 24));
        valueLabel.setForeground(color);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        return card;
    }

    private JPanel createPreOrderTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Top Form
        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Place Pre-Order"));

        JTextField farmerField = new JTextField();
        JTextField mobileField = new JTextField();
        JComboBox<Product> productBox = new JComboBox<>(inventory.toArray(new Product[0]));
        JTextField quantityField = new JTextField();
        JTextField dateField = new JTextField(LocalDate.now().plusDays(15).toString()); // Auto-fill future date

        formPanel.add(new JLabel("Farmer Name:")); formPanel.add(farmerField);
        formPanel.add(new JLabel("Mobile:")); formPanel.add(mobileField);
        formPanel.add(new JLabel("Product:")); formPanel.add(productBox);
        formPanel.add(new JLabel("Quantity (bags):")); formPanel.add(quantityField);
        formPanel.add(new JLabel("Delivery Date:")); formPanel.add(dateField);
        
        JButton submitBtn = new JButton("Submit Pre-Order");
        submitBtn.setBackground(new Color(124, 58, 237));
        submitBtn.setForeground(Color.WHITE);
        formPanel.add(new JLabel()); // Empty spacer
        formPanel.add(submitBtn);

        // Bottom Table
        String[] cols = {"Farmer", "Mobile", "Product", "Quantity", "Delivery", "Status"};
        preorderTableModel = new DefaultTableModel(cols, 0);
        for (PreOrder p : preorders) {
            preorderTableModel.addRow(new Object[]{p.farmer, p.mobile, p.product, p.quantity, p.date, p.status});
        }
        JTable table = new JTable(preorderTableModel);

        // Submit Button Action (Input validation added!)
        submitBtn.addActionListener(e -> {
            try {
                if (farmerField.getText().isEmpty() || mobileField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill all text fields!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int qty = Integer.parseInt(quantityField.getText()); // Will jump to catch block if not a number
                Product selected = (Product) productBox.getSelectedItem();
                
                PreOrder newOrder = new PreOrder(farmerField.getText(), selected.name, qty, dateField.getText(), mobileField.getText(), "Pending");
                preorders.add(newOrder);
                preorderTableModel.addRow(new Object[]{newOrder.farmer, newOrder.mobile, newOrder.product, newOrder.quantity, newOrder.date, newOrder.status});
                
                JOptionPane.showMessageDialog(this, "Pre-order placed successfully!");
                farmerField.setText(""); mobileField.setText(""); quantityField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Quantity must be a valid number!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createInventoryTab() {
        JPanel panel = new JPanel(new BorderLayout());
        String[] cols = {"Product", "Stock", "Demand", "Price", "Discount", "Status"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        
        for (Product item : inventory) {
            String status = item.stock < item.demand ? "Low Stock" : "Optimal";
            model.addRow(new Object[]{item.name, item.stock, item.demand, "₹" + item.price, item.discount + "%", status});
        }
        
        JTable table = new JTable(model);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createOrdersTab() {
        // Similar structure to Pre-Orders
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formPanel.setBorder(BorderFactory.createTitledBorder("Create New Order"));

        JTextField farmerField = new JTextField(15);
        JComboBox<Product> productBox = new JComboBox<>(inventory.toArray(new Product[0]));
        JTextField quantityField = new JTextField(5);
        JButton submitBtn = new JButton("Add Order");
        submitBtn.setBackground(new Color(22, 163, 74));
        submitBtn.setForeground(Color.WHITE);

        formPanel.add(new JLabel("Farmer:")); formPanel.add(farmerField);
        formPanel.add(new JLabel("Product:")); formPanel.add(productBox);
        formPanel.add(new JLabel("Quantity:")); formPanel.add(quantityField);
        formPanel.add(submitBtn);

        // Table
        String[] cols = {"Farmer", "Product", "Quantity", "Date", "Status"};
        orderTableModel = new DefaultTableModel(cols, 0);
        for (Order o : orders) orderTableModel.addRow(new Object[]{o.farmer, o.product, o.quantity, o.date, o.status});
        
        JTable table = new JTable(orderTableModel);

        // Submit Action
        submitBtn.addActionListener(e -> {
            try {
                if(farmerField.getText().isEmpty()) throw new IllegalArgumentException("Farmer name cannot be empty");
                int qty = Integer.parseInt(quantityField.getText());
                Product selected = (Product) productBox.getSelectedItem();
                
                String date = LocalDate.now().toString();
                Order newOrder = new Order(farmerField.getText(), selected.name, qty, date, "Completed");
                orders.add(newOrder);
                orderTableModel.addRow(new Object[]{newOrder.farmer, newOrder.product, newOrder.quantity, newOrder.date, newOrder.status});
                
                // Deduct from inventory (Extra feature!)
                selected.stock -= qty; 
                
                JOptionPane.showMessageDialog(this, "Order placed successfully!");
                farmerField.setText(""); quantityField.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        return panel;
    }

    private JPanel createFeedbackTab() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Table setup (Done first so the button can update it)
        String[] cols = {"Farmer", "Product", "Rating", "Comment", "Date"};
        feedbackTableModel = new DefaultTableModel(cols, 0);
        for (Feedback f : feedbacks) feedbackTableModel.addRow(new Object[]{f.farmer, f.product, f.rating + "⭐", f.comment, f.date});
        JTable table = new JTable(feedbackTableModel);

        // This is a simple placeholder to show the tab structure works
        // (Forms are built exactly the same as the orders/preorders above!)
        panel.add(new JLabel("Feedback functionality goes here (Similar to Orders tab!)"), BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // ==========================================
    // 5. MAIN METHOD (Entry Point)
    // ==========================================
    public static void main(String[] args) {
        // SwingUtilities ensures the UI runs on a safe background thread
        SwingUtilities.invokeLater(() -> {
            // This line sets the look and feel to your Operating System's default style (Windows/Mac/Linux)
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); } catch (Exception ignored) {}
            
            FertilizerManagementSystem app = new FertilizerManagementSystem();
            app.setVisible(true); // Shows the window
        });
    }
}