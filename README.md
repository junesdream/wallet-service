# 📟 WALLET.CORE.v25 | Full-Stack Fintech Demo

A high-performance wallet system built with a **Spring Boot Backend** and a **React/Vite Frontend**. This project demonstrates modern software architecture, audit log implementation, and REST API design.

---

## 🛠️ Tech Stack

### Backend (The Engine)

| Technology | Description |
|---|---|
| ☕ **Java 21** & **Spring Boot 3** | High-performance application framework |
| 🗄️ **Spring Data JPA** | H2 In-Memory Database for fast persistence |
| ✂️ **Lombok** | Clean, boilerplate-free code generation |
| 🌐 **REST API** | Global exception handling & structured responses |

### Frontend (The Interface)

| Technology | Description |
|---|---|
| ⚛️ **React 18** with **TypeScript** | Type-safe component-driven UI |
| ⚡ **Vite** | Ultra-fast development build tool |
| 📡 **Axios** | Promise-based HTTP API communication |
| 🖤 **Matrix-Inspired CSS** | Custom professional dark mode design |

---

## ✨ Features

- **Real-time Balance:** Instant account balance updates on every transaction.
- **Transaction History:** Full audit log of every movement (Deposit / Withdraw).
- **Data Integrity:** Server-side balance validation — no negative balances allowed.
- **Auto-Seeding:** Demo data loaded automatically on system startup.

---

## 🚀 Installation & Setup

### 1. Start the Backend

1. Open the project in **IntelliJ IDEA**.
2. Run the `WalletServiceApplication`.
3. The API will be available at `http://localhost:8080`.

### 2. Start the Frontend

1. Navigate to the `wallet-frontend` directory:
   ```bash
   cd wallet-frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```
4. Open `http://localhost:5173` in your browser.

---

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request. For major changes, please open an issue first to discuss what you would like to change.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-idea`)
3. Commit your changes (`git commit -m 'feat: add your idea'`)
4. Push to the branch (`git push origin feature/your-idea`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.