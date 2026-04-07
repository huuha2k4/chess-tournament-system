# ♟️ Chess Tournament Management System

Dự án quản lý giải đấu cờ vua được xây dựng bằng **Java Core**, áp dụng các nguyên tắc lập trình hướng đối tượng (OOP) và quản lý bằng **Maven**.

## 🚀 Tính năng hiện tại
- [x] **Quản lý Kỳ thủ (Player Model):** Khởi tạo thông tin kỳ thủ với ID, Tên và Elo.
- [x] **Đóng gói dữ liệu (Encapsulation):** Bảo vệ thuộc tính bằng `private` và truy xuất qua `Getter/Setter`.
- [x] **Kiểm soát dữ liệu đầu vào (Validation):** - Elo phải nằm trong khoảng [0 - 3000].
    - Tên không được để trống.
- [x] **Xử lý ngoại lệ (Exception Handling):** Tự định nghĩa `InvalidDataException` để bắt lỗi dữ liệu sai quy định.

## 🛠️ Công nghệ sử dụng
* **Language:** Java 17+
* **Build Tool:** Maven 3.9.x
* **Version Control:** Git & GitHub

## 📂 Cấu trúc dự án
```text
src/main/java/com/chess/
├── App.java                 # Điểm chạy chương trình (Main)
├── Player.java              # Lớp thực thể Kỳ thủ
└── InvalidDataException.java # Lớp định nghĩa lỗi tùy chỉnh