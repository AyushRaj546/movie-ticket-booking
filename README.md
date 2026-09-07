# 🎬 Movie Ticket Booking System

A console-based **Java** application for a movie ticket booking system, developed for the **System Design** course (TCS-504). This project demonstrates Object-Oriented Analysis and Design (OOAD), SOLID architecture principles, and modular Java implementation.

---

## 📋 Features

- **Movie Catalog:** View all movies currently playing with details (duration, language).
- **Show Scheduling:** Browse available screens and showtimes per movie.
- **Visual Seat Matrix:** Interactive seat layout with real-time status (`[ ]` Available, `[X]` Booked).
- **Tier-Based Dynamic Pricing:**
    - SILVER: ₹150
    - GOLD: ₹250
    - PLATINUM: ₹400
- **Multi-Seat Selection & Validation:** Reserve multiple seats in one transaction; rejects already-reserved seats atomically.
- **Pluggable Payment Processing:** Extensible payment strategy interface supporting UPI, Credit/Debit Card, and Cash.
- **Ticket Generation:** Outputs formatted summary slips with booking references.
- **Cancellations:** Cancel an existing reservation to immediately free locked seats.

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 17+)
- **Compiler:** javac
- **Dependencies:** Java Standard Library only (no external libraries)

---

## 📂 Project Structure

```text
ShowTimeBooking/
├── .gitignore
├── README.md
└── ShowtimeBooking/
    └── src/
        ├── Booking.java              # Booking entity implementation
        ├── BookingService.java       # Orchestration and state management
        ├── BookingStatus.java        # Booking status enum
        ├── CardPayment.java          # Card payment implementation
        ├── CashPayment.java          # Cash payment implementation
        ├── Cinema.java               # Cinema hall & screen aggregation
        ├── Customer.java             # Customer profile entity
        ├── Main.java                 # CLI entry point and menu router
        ├── Movie.java                # Movie data representation
        ├── Payment.java              # Abstract base strategy for payments
        ├── PriceCalculator.java      # Seat pricing calculation logic
        ├── Screen.java               # Screen layout and seat container
        ├── Seat.java                 # Base seat representation
        ├── SeatType.java             # Seat type enum (SILVER/GOLD/PLATINUM)
        ├── Show.java                 # Show schedule metadata
        ├── ShowSeat.java             # Show-specific seat availability wrapper
        ├── ShowSeatStatus.java       # ShowSeat status enum (AVAILABLE/BOOKED)
        ├── TicketPrinter.java        # Formatted output formatter
        └── UpiPayment.java           # UPI payment implementation
```



---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 17 or higher
- Git (optional, for cloning)

### Installation & Build

**1. Clone the repository:**
```bash
git clone https://github.com/AyushRaj546/movie-ticket-booking.git
cd movie-ticket-booking
```

**2. Navigate to source directory:**
```bash
cd ShowtimeBooking/src
```

**3. Compile all Java files:**
```bash
javac *.java
```

**4. Run the executable:**
```bash
java Main
```

## 📖 How to Use

### Main Menu
When you run the application, you will see the main menu:
```text
=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose:
```
### Basic Workflow
Follow these steps to book a ticket:
1. **List Movies** – Choose option `1` to see all currently playing movies with their language and duration.
2. **Select a Movie** – Enter the number of the movie you want to watch.
3. **Choose a Show** – A list of available shows (screen + start time) will appear. Enter the number of your preferred show.
4. **View Seat Layout** – The system displays a seat map:
    * `[ ]` = Available
    * `[X]` = Booked
5. **Select Seats** – Enter seat numbers separated by commas (e.g., `A1,B2,C5`). The system will validate availability.
6. **Enter Customer Details** – Provide your name and phone number.
7. **Make Payment** – Choose a payment method:
    * `1` – UPI
    * `2` – Card
    * `3` – Cash
8. **Receive Ticket** – On successful payment, your ticket is printed with:
    * Booking ID
    * Movie, Screen, Time
    * Seat numbers
    * Total amount
    * Confirmed status

### Cancelling a Booking
1. Choose option `3` from the main menu.
2. A list of your confirmed bookings will appear.
3. Enter the number (not the booking ID) of the booking you wish to cancel.
4. The seats become available again instantly.

### Viewing Your Bookings
Choose option `4` to see all your bookings with their current status (Confirmed, Cancelled, etc.).

### Exiting
Choose `0` to exit the program gracefully.

---

## 🎯 SOLID Architecture Alignment

| Principle | Implementation in Project |
| :--- | :--- |
| **S — Single Responsibility** | `PriceCalculator` calculates fees, `TicketPrinter` handles terminal I/O, and `BookingService` manages workflow state. |
| **O — Open/Closed** | New payment channels implement `Payment` without altering existing billing logic. |
| **L — Liskov Substitution** | Derived payment classes (`UpiPayment`, `CardPayment`, `CashPayment`) can substitute `Payment` references transparently. |
| **I — Interface Segregation** | Payment interfaces do not mandate unneeded methods (e.g., refund handling is decoupled from charge auth). |
| **D — Dependency Inversion** | High-level `BookingService` orchestrates against polymorphic `Payment` abstractions, not concrete implementations. Dependencies are injected via constructor. |

---

## 🧪 Sample Run

```text
=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose: 1

Currently playing movies:
1. 3 Idiots (Hindi, 170 min)
2. Interstellar (English, 169 min)

=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose: 2

Select a movie:
1. 3 Idiots
2. Interstellar
Enter movie number: 1

Shows for 3 Idiots:
1. Screen-1 at 2026-09-01T18:00
2. Screen-2 at 2026-09-01T21:00
Choose show: 1

===== SEAT LAYOUT =====
A1[ ] A2[ ] A3[ ] A4[ ] A5[ ] A6[ ] A7[ ] A8[ ] A9[ ] A10[ ] 
B1[ ] B2[ ] B3[ ] B4[ ] B5[ ] B6[ ] B7[ ] B8[ ] B9[ ] B10[ ] 
C1[ ] C2[ ] C3[ ] C4[ ] C5[ ] C6[ ] C7[ ] C8[ ] C9[ ] C10[ ] 
...

Enter seat numbers separated by commas (e.g., A1,B2): A1,A2
Enter your name: Ayush
Enter your phone: 789

Pay by: 1. UPI  2. Card  3. Cash
2
Total amount: Rs.300.0
[Card] Paid Rs. 300.0 successfully

============== TICKET ===============
Booking ID   : BK1001
Movie        : 3 Idiots
Screen       : 1
Time         : 2026-09-01T18:00
Seats        : A1, A2
Amount       : Rs.300.0
Status       : Confirmed
=====================================

=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose: 3

Your bookings:
1. BK1001 - 3 Idiots - Confirmed
Enter booking number to cancel (1, 2, 3...): 1
Booking BK1001 cancelled successfully.

=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose: 4

No bookings yet.

=== MOVIE TICKET BOOKING ===
1. List Movies
2. Book Tickets
3. Cancel Booking
4. My Bookings
0. Exit
Choose: 0
Goodbye!
```
## 🛡️ Edge Cases & Error Handling

* Double Booking Guard: Re-selected booked seats are validated and aborted before finalizing payment.

 * Payment Abort Rollback: Failure during checkout releases any held seat reservations back to AVAILABLE.

 * Input Sanitization: Rejects out-of-bounds show indices, invalid seat codes, and malformed numeric inputs without crashing.

 * Atomic Booking: If any selected seat is already BOOKED, the whole booking is rejected and no seat changes state.

## 📐 UML Diagrams

### Class Diagram
![Class Diagram](image/Screenshot%202026-09-06%20084940.png)

### Sequence Diagram
![Sequence Diagram](image/Screenshot%202026-09-06%20085231.png)

## 🤝 Contributions
This project was developed as part of the TCS-504 System Design course (B.Tech CSE, 5th Semester). Contributions are not expected, but feedback is welcome!

---

## 📜 License
This project is for educational purposes only.

---

## 👨‍💻 Author
**Ayush Raj**  
B.Tech CSE (5th Semester)  
Subject: System Design (TCS-504)  
Submission Date: 07-September-2026
