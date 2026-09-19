# Problem Statement: Fertilizer Demand & Inventory Management System

**Project Title:** AgriCare Fertilizers - Smart Demand Management & Pre-Order System  
**Author:** Manas Solanki  
**Registration Number:** 25bai10589  

---

## 1. Background & Context
In India and agricultural economies globally, timely access to agricultural inputs—specifically chemical and organic fertilizers such as Urea, DAP (Di-ammonium Phosphate), Potash, and NPK—directly impacts crop productivity and farm profitability. Fertilizer demand is highly seasonal, characterized by intense spikes during sowing periods (Kharif and Rabi seasons) followed by prolonged periods of moderate or low consumption. 

Local retailers, primary agricultural credit societies (PACS), and regional distributors form the backbone of this supply chain. However, most local distributors continue to rely on manual paper ledgers, fragmented phone communications, and informal verbal commitments to manage stock and customer orders.

---

## 2. The Problem Statement
Local fertilizer distributors face persistent operational challenges due to the absence of a structured, digital demand forecasting and inventory tracking mechanism. Specifically:

1. **Seasonal Stockouts & Artificial Shortages:**  
   During peak planting weeks, abrupt demand surges lead to sudden stockouts. When essential fertilizers like Urea or DAP are unavailable, sowing is delayed, directly reducing crop yield and forcing farmers to purchase alternative products at inflated prices.

2. **Capital Inefficiency & Overstocking:**  
   Distributors often overcompensate for anticipated demand by overstocking slow-moving fertilizers, tying up working capital and risking product degradation or packaging damage in humid warehouse environments.

3. **Lack of Advance Demand Visibility (No Structured Pre-Orders):**  
   Distributors operate reactively rather than proactively. Without an advance booking system, distributors cannot gauge future village-level demand weeks prior to the onset of the season.

4. **Human Error & Record Inaccuracy:**  
   Paper-based recordkeeping results in mismatched inventory counts, unfulfilled customer commitments, missed follow-ups, and an inability to track farmer satisfaction or product efficacy.

---

## 3. Project Objectives
The primary objective of this project is to design and develop a lightweight, dependable desktop application that digitizes fertilizer inventory operations and formalizes advance demand booking.

Key sub-objectives include:
- **Real-Time Stock Monitoring:** Provide real-time inventory tracking with immediate stock deduction upon order completion.
- **Visual Demand-to-Stock Ratio:** Implement a visual indicator (progress bar tracking) to immediately alert distributors when product stock falls below local demand thresholds.
- **Advance Pre-Order Mechanism:** Allow distributors to record and manage farmer pre-orders ahead of peak seasons, complete with contact details, required quantities, and target delivery dates.
- **Reliable Data Handling:** Ensure zero unexpected crashes through robust input validation and defensive programming (e.g., preventing non-numeric inputs in numerical fields).
- **Field Feedback Tracking:** Maintain a structured log of farmer feedback and ratings to evaluate product efficacy across crop cycles.

---

## 4. Proposed Solution: AgriCare Fertilizers
To address these challenges, the **AgriCare Fertilizers Management System** is implemented as an Object-Oriented Java desktop application. The system provides a centralized control hub featuring:

1. **Operational Dashboard:** Displays key business metrics (Total Inventory Items, Active Pre-Orders, Completed Sales, Customer Ratings) and dynamic visual bars contrasting current stock levels against anticipated demand.
2. **Pre-Order Management Module:** Captures farmer pre-bookings with seasonal incentive discounts, giving distributors clear visibility into procurement requirements 2–4 weeks in advance.
3. **Point-of-Sale Order Processing:** Handles immediate fertilizer purchases with automated inventory deduction and out-of-stock validation.
4. **Inventory Health Monitor:** Automatically categorizes inventory items into status flags (`Optimal`, `Low Stock`, `Overstock`) based on calculated demand ratios.
5. **Quality & Satisfaction Feedback:** Records farmer field remarks and star ratings to help distributors assess supplier performance and crop-specific fertilizer efficacy.

---

## 5. Expected Scope & Impact
- **Immediate Scope:** Designed for single-terminal operation at cooperative societies, regional fertilizer retail counters, and small-to-medium agricultural input depots.
- **Target Beneficiaries:** Fertilizer retailers, regional agricultural cooperatives, and local farming communities.
- **Measurable Impact:** 
  - Substantial reduction in peak-season stockouts through early booking data.
  - Elimination of transcription errors typical of handwritten sales ledgers.
  - Faster transaction turnaround times at the retail counter.