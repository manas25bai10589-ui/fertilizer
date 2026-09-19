<!-- STREAMING_CHUNK:Writing project overview and setup instructions... -->
# AgriCare Fertilizers - Management System

**Author:** Manas Solanki  
**Registration Number:** 25bai10589  

## Overview
AgriCare Fertilizers is a desktop-based GUI application built using Java and the Swing toolkit. The system is designed to help agricultural distributors manage fertilizer inventory, track farmer pre-orders, and monitor overall product demand in a user-friendly way.

## Features
- **📊 Interactive Dashboard:** View total products, active pre-orders, and a visual Demand vs. Stock analysis using progress bars.
- **📅 Pre-Order Management:** Allows farmers to place pre-orders for upcoming seasons, with built-in input validation.
- **📦 Inventory Tracking:** Monitor current stock levels, prices, and discounts dynamically.
- **🛒 Order Processing:** Quickly add new regular orders and automatically deduct quantities from the inventory.
- **💬 Feedback System:** Keep track of farmer reviews and ratings for specific products.

## Technical Details
- **Language:** Java
- **GUI Toolkit:** Java Swing (`javax.swing.*`)
- **Architecture:** Object-Oriented Programming (OOP) using custom classes (`Product`, `PreOrder`, `Order`, `Feedback`).
- **Data Storage:** In-memory tracking using Java `ArrayList` and `DefaultTableModel` for dynamic UI updates.

## How to Run the Project
1. Ensure you have the [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) installed on your system.
2. Clone or download this repository.
3. Open the folder in your preferred IDE (IntelliJ IDEA, Eclipse, or VS Code).
4. Run the `FertilizerManagementSystem.java` file.