# HƯỚNG ĐỐI TƯỢNG (OOP), NGUYÊN LÝ SOLID VÀ NỀN TẢNG JAVA CORE

> **Tài liệu học tập & Ôn tập Kiến trúc**
> Tài liệu này được thiết kế để kết nối 3 mảng kiến thức trụ cột của một lập trình viên Java: Tư duy thiết kế Hướng đối tượng (OOP), các Nguyên lý thiết kế SOLID chống "code thối", và bản chất vận hành của Java Core bên dưới hệ thống. Tài liệu kết hợp cả ví dụ code thực tế và phân tích sâu về **vai trò thực sự** của chúng trong dự án lớn.

---

## PHẦN 1: HƯỚNG ĐỐI TƯỢNG (OOP - OBJECT-ORIENTED PROGRAMMING)

Tư duy hướng đối tượng không chỉ là cú pháp, nó là cách chúng ta mô hình hóa thế giới thực vào trong máy tính bằng các **Đối tượng (Objects)** chứa **Thuộc tính (Fields)** và **Hành vi (Methods)** để quản lý độ phức tạp hệ thống.

### 1.1. 4 Tính Chất Cốt Lõi (4 Pillars of OOP)

```
        +-------------------------------------------------------+
        |                 4 CỘT TRỤ CỦA OOP                     |
        +-------------------+-----------------------------------+
        |  1. ĐÓNG GÓI      | Che giấu dữ liệu bên trong,       |
        |  (Encapsulation)  | chỉ giao tiếp qua cổng an toàn.  |
        +-------------------+-----------------------------------+
        |  2. KẾ THỪA       | Tái sử dụng và mở rộng logic từ   |
        |  (Inheritance)    | lớp cha sang lớp con (Is-A).      |
        +-------------------+-----------------------------------+
        |  3. ĐA HÌNH       | Một hành động, nhiều cách thể     |
        |  (Polymorphism)   | hiện khác nhau tùy ngữ cảnh.     |
        +-------------------+-----------------------------------+
        |  4. TRỪU TƯỢNG    | Lọc bỏ chi tiết cài đặt phức tạp, |
        |  (Abstraction)    | chỉ đưa ra giao diện cốt lõi.    |
        +-------------------+-----------------------------------+
```

#### 1. Đóng gói (Encapsulation)
*   **Khái niệm:** Là việc che giấu trạng thái nội bộ của đối tượng và ngăn chặn sự truy cập trực tiếp từ bên ngoài. Mọi giao tiếp với dữ liệu phải thông qua các phương thức công khai (Getter/Setter).
*   **Cơ chế:** Sử dụng các Access Modifiers (`private`, `default`, `protected`, `public`).
*   **Ví dụ Java:**
    ```java
    public class BankAccount {
        private double balance; // private: Ngăn chặn sửa đổi số dư tùy tiện từ bên ngoài

        public BankAccount(double initialBalance) {
            if (initialBalance >= 0) {
                this.balance = initialBalance;
            }
        }

        public double getBalance() { // Getter: Cho phép đọc nhưng có kiểm soát
            return this.balance;
        }

        public void deposit(double amount) { // Setter: Kiểm soát tính đúng đắn khi thay đổi
            if (amount > 0) {
                this.balance += amount;
            }
        }
    }
    ```
*   **Vai trò thực sự:** **Bảo vệ tính toàn vẹn của dữ liệu (Data Integrity)** và **Khoanh vùng rủi ro khi thay đổi**. Lớp tự chịu trách nhiệm về tính đúng đắn của dữ liệu của mình. Khi logic nghiệp vụ thay đổi, ta chỉ cần sửa ở duy nhất một nơi mà không lo làm hỏng các phần khác.

#### 2. Kế thừa (Inheritance)
*   **Khái niệm:** Cho phép một lớp con (Subclass) kế thừa các thuộc tính và phương thức của lớp cha (Superclass), giúp tổ chức mã nguồn có hệ thống (mối quan hệ **Is-A**).
*   **Từ khóa:** `extends` (kế thừa class) và `implements` (triển khai interface).
*   **Ví dụ Java:**
    ```java
    public class Animal {
        protected String name;
        public void eat() {
            System.out.println(name + " is eating...");
        }
    }

    public class Dog extends Animal { // Dog IS-A Animal
        public Dog(String name) {
            this.name = name;
        }
        public void bark() {
            System.out.println(name + " is barking: Woof Woof!");
        }
    }
    ```
*   **Vai trò thực sự:** **Thiết lập phân cấp ngữ nghĩa (Semantic Hierarchy)** và **Chuẩn bị cho Đa hình**. Nó không đơn thuần là công cụ để tái sử dụng code (vì kế thừa tạo ra tight coupling rất mạnh). Sửa lớp cha có thể phá vỡ lớp con (lỗi Fragile Base Class). Chỉ sử dụng khi thực sự có quan hệ bản chất "Is-A".

#### 3. Đa hình (Polymorphism)
*   **Khái niệm:** Cho phép một đối tượng có thể đóng nhiều vai trò hoặc thực thi một hành vi theo nhiều cách khác nhau.
*   **Phân loại:**
    *   **Compile-time Polymorphism (Static):** Thể hiện qua **Method Overloading** (nạp chồng phương thức). Quyết định chạy hàm nào tại thời điểm biên dịch dựa trên tham số truyền vào.
    *   **Runtime Polymorphism (Dynamic):** Thể hiện qua **Method Overriding** (ghi đè phương thức). JVM sẽ quyết định phương thức của lớp nào được gọi khi chương trình chạy thông qua bảng phương thức ảo (**Virtual Method Table - VMT**).
*   **Ví dụ Java:**
    ```java
    // 1. Overloading (Compile-time)
    public class Calculator {
        public int add(int a, int b) { return a + b; }
        public double add(double a, double b) { return a + b; }
    }

    // 2. Overriding (Runtime)
    class Shape {
        void draw() { System.out.println("Drawing a shape"); }
    }
    class Circle extends Shape {
        @Override
        void draw() { System.out.println("Drawing a circle"); }
    }
    
    // Sử dụng Runtime Polymorphism:
    Shape myShape = new Circle(); 
    myShape.draw(); // Output: "Drawing a circle" (Quyết định tại runtime)
    ```
*   **Vai trò thực sự:** **Triệt tiêu các câu lệnh rẽ nhánh phức tạp (`if-else`/`switch-case`)** và **Đảo ngược chiều phụ thuộc**. Đa hình cho phép hệ thống gọi một hành vi chung, còn cách thực hiện cụ thể sẽ tự động chạy theo đối tượng thực tế tại runtime. Lập trình viên có thể thiết kế luồng nghiệp vụ chạy trước mà không cần biết chi tiết các lớp cụ thể sau này.

#### ❓ Câu hỏi nâng cao về Static và Tính kế thừa (Method Hiding)
*   **Vấn đề:** Nếu ta có phương thức `static` trong lớp cha và lớp con định nghĩa lại đúng phương thức `static` đó, hành vi này có phải là Polymorphism (Overriding) không?
*   **Trả lời:** Không. Đây gọi là **Method Hiding (Ẩn phương thức)**. Phương thức `static` thuộc về **Lớp (Class)** chứ không thuộc về **Đối tượng (Instance)**. Do đó, JVM quyết định gọi phương thức `static` nào tại thời điểm biên dịch (Compile-time) dựa vào **Kiểu khai báo (Declared Type)** của biến, chứ không phải dựa vào **Kiểu thực tế của đối tượng (Actual Type)** tại thời điểm chạy.
*   **Code minh họa:**
    ```java
    class Parent {
        public static void test() { System.out.println("Static Parent"); }
    }
    class Child extends Parent {
        public static void test() { System.out.println("Static Child"); } // Ẩn phương thức của cha
    }
    
    Parent p = new Child();
    p.test(); // Output: "Static Parent" (Do quyết định tại Compile-time dựa trên kiểu khai báo là Parent!)
    ```

#### 4. Trừu tượng (Abstraction)
*   **Khái niệm:** Chỉ tập trung vào những tính năng cốt lõi cần thiết của đối tượng mà bỏ qua các chi tiết triển khai cụ thể bên dưới.
*   **Cơ chế:** Sử dụng `abstract class` hoặc `interface`.
*   **Ví dụ Java:**
    ```java
    public interface RemoteControl {
        void turnOn();
        void turnOff();
    }

    public class SonyRemote implements RemoteControl {
        public void turnOn() { System.out.println("Sony TV is ON via Infrared"); }
        public void turnOff() { System.out.println("Sony TV is OFF"); }
    }
    ```
*   **Vai trò thực sự:** **Quản lý giới hạn nhận thức của con người (Cognitive Load)**. Nó che giấu sự phức tạp chi tiết cài đặt ("làm như thế nào") để lập trình viên chỉ cần giao tiếp qua giao diện cốt lõi ("làm cái gì"), giúp thiết kế hệ thống lớn mà không bị choáng ngợp.

---

### 1.2. Phân Biệt Abstract Class vs Interface

Đây là câu hỏi kinh điển khi thiết kế hệ thống. Lựa chọn đúng giúp codebase linh hoạt và dễ bảo trì.

| Tiêu chí | Abstract Class (Lớp trừu tượng) | Interface (Giao diện) |
| :--- | :--- | :--- |
| **Bản chất mối quan hệ** | **Is-A** (Là một cái gì đó). Ví dụ: `Cat` là một `Animal`. | **Can-Do / Capable** (Có khả năng làm gì). Ví dụ: `Cat` có thể `Runable`. |
| **Đa kế thừa** | Đơn kế thừa (Chỉ `extends` 1 class duy nhất). | Đa kế thừa (Có thể `implements` nhiều interfaces). |
| **Trạng thái (State)** | Có thể chứa các instance variables (biến non-static, non-final) để lưu trạng thái của đối tượng. | Chỉ chứa hằng số (`public static final`). Không có instance variables. |
| **Phương thức** | Có thể chứa cả phương thức trừu tượng và phương thức có thân hàm (concrete method). | Mặc định là trừu tượng (`public abstract`). Từ Java 8+, được phép có phương thức `default` và `static`. |
| **Constructor** | Có Constructor (dùng để khởi tạo giá trị cho các biến của lớp cha khi lớp con được tạo). | Không có Constructor. |
| **Access Modifier** | Các phương thức có thể có modifier: `private`, `protected`, `public`. | Mọi phương thức mặc định luôn là `public`. |

*   **Bản chất thiết kế thực tế:** Abstract Class đóng vai trò là **Bản thiết kế dở dang** (chia sẻ trạng thái và khung sườn chung cho lớp con cùng dòng họ). Interface đóng vai trò là **Bản hợp đồng năng lực** (không quan tâm dòng họ kế thừa, chỉ quan tâm khả năng thực thi hành vi).
*   **Quy tắc đặt tên:** Các Interface đại diện cho năng lực hoặc hành vi thường được đặt tên kết thúc bằng đuôi **-able** (ví dụ: `Runnable`, `Comparable`, `Cloneable`, `Serializable`, `Autocloseable`).

---

### 1.3. Vấn đề Diamond Problem (Nhập nhằng đa kế thừa)

#### Tại sao Java không cho phép đa kế thừa Class?
Giả sử ta có Class A có phương thức `show()`. Class B và Class C kế thừa từ A và ghi đè phương thức `show()` theo 2 cách khác nhau. Nếu Class D kế thừa đồng thời từ cả B và C, khi gọi `d.show()`, JVM sẽ không biết phải chạy phương thức của B hay của C. Đây gọi là **Diamond Problem (Vấn đề kim cương)**.

```
       [Class A] (show())
       /       \
      /         \
 [Class B]     [Class C]  (Cả hai đều override show())
      \         /
       \       /
       [Class D]  <-- Gọi d.show() thì chạy hàm nào? (Lỗi Compile)
```

#### Java 8+ xử lý Diamond Problem với Default Method trong Interface thế nào?
Từ Java 8, Interface được phép có `default method` (phương thức mặc định có thân hàm). Điều này dẫn đến nguy cơ Diamond Problem xuất hiện lại trên các Interface.
*   **Giải pháp của Java:** Nếu một lớp triển khai 2 Interfaces chứa 2 phương thức `default` trùng tên và chữ ký (signature), trình biên dịch sẽ **báo lỗi ngay lập tức** và buộc lập trình viên phải ghi đè (override) phương thức đó ở lớp con để làm rõ hành vi.

```java
interface InterfaceB {
    default void show() { System.out.println("Show from B"); }
}

interface InterfaceC {
    default void show() { System.out.println("Show from C"); }
}

// Bắt buộc phải override phương thức trùng lặp
class ClassD implements InterfaceB, InterfaceC {
    @Override
    public void show() {
        // Tùy chọn 1: Tự viết logic mới
        System.out.println("Custom Show in D");
        
        // Tùy chọn 2: Chỉ định rõ gọi phương thức mặc định của Interface nào
        InterfaceB.super.show(); 
    }
}
```

---

### 1.4. Thứ Tự Khởi Tạo Trong Java (Execution Order)

Khi bạn gọi `new SubClass()`, các khối mã và constructor sẽ được thực thi theo một trình tự nghiêm ngặt:

1.  **Tải Class (Class Loading) - Chỉ chạy 1 lần duy nhất khi class được nạp vào JVM:**
    *   Chạy các khối `static initializer` và gán giá trị cho các biến `static` của lớp Cha (Parent class).
    *   Chạy các khối `static initializer` và gán giá trị cho các biến `static` của lớp Con (Child class).
2.  **Khởi tạo Đối tượng (Object Instantiation) - Chạy mỗi khi tạo instance bằng từ khóa `new`:**
    *   Chạy khối `instance initializer` (khối mã không có tên ngoài phương thức) và gán giá trị biến instance của lớp Cha.
    *   Chạy `Constructor` của lớp Cha.
    *   Chạy khối `instance initializer` và gán giá trị biến instance của lớp Con.
    *   Chạy `Constructor` của lớp Con.

**Code minh họa:**
```java
class Parent {
    static { System.out.println("1. Static Parent"); }
    { System.out.println("3. Instance Parent"); }
    public Parent() { System.out.println("4. Constructor Parent"); }
}

class Child extends Parent {
    static { System.out.println("2. Static Child"); }
    { System.out.println("5. Instance Child"); }
    public Child() { System.out.println("6. Constructor Child"); }
}

// Khi thực thi: Child obj = new Child();
// Output nhận được:
// 1. Static Parent
// 2. Static Child
// 3. Instance Parent
// 4. Constructor Parent
// 5. Instance Child
// 6. Constructor Child
```

---

## PHẦN 2: CÁC NGUYÊN LÝ THIẾT KẾ SOLID

SOLID là bộ 5 nguyên lý thiết kế hệ thống hướng đối tượng được đưa ra bởi Robert C. Martin (Uncle Bob), giúp phần mềm dễ bảo trì, dễ mở rộng và tránh tình trạng code bị "cứng nhắc" (rigid) hoặc "dễ vỡ" (fragile) khi thay đổi yêu cầu.

---

### 2.1. S - Single Responsibility Principle (SRP)
*   **Mô tả ngắn:** Một lớp chỉ nên có một lý do duy nhất để thay đổi.
*   **Ví dụ minh họa:**
    *   ❌ **Vi phạm SRP:**
    ```java
    // Lớp Invoice vừa chứa thông tin hóa đơn, vừa tự in ấn, vừa tự lưu vào Database
    public class Invoice {
        private double amount;
        public void printInvoice() { System.out.println("Printing: " + amount); }
        public void saveToDatabase() { System.out.println("Saving to SQL..."); }
    }
    ```
    *   ✔️ **Đạt chuẩn SRP:**
    ```java
    public class Invoice { private double amount; public double getAmount() { return amount; } }
    public class InvoicePrinter { public void print(Invoice invoice) { /* in ấn */ } }
    public class InvoiceRepository { public void save(Invoice invoice) { /* lưu DB */ } }
    ```
*   **Vai trò thực sự:** **Khoanh vùng rủi ro (Risk Isolation)** và **Giảm thiểu xung đột khi làm việc nhóm (Merge Conflict)**. Khi mỗi class chỉ giữ một trách nhiệm duy nhất, lỗi ở tính năng này không thể làm sập tính năng khác, và nhiều lập trình viên có thể cùng làm việc trên các file độc lập mà không bị xung đột code.

---

### 2.2. O - Open/Closed Principle (OCP)
*   **Mô tả ngắn:** Mở rộng cho phát triển (thêm mới), đóng lại với sửa đổi (sửa code cũ).
*   **Ví dụ minh họa:**
    *   ❌ **Vi phạm OCP:**
    ```java
    public class PaymentService {
        public void processPayment(String type) {
            if (type.equals("MOMO")) { /* xử lý Momo */ }
            else if (type.equals("BANK")) { /* xử lý Bank */ }
            // Thêm cổng mới phải vào sửa file này viết thêm else-if -> Rủi ro làm hỏng code cũ!
        }
    }
    ```
    *   ✔️ **Đạt chuẩn OCP:**
    ```java
    public interface PaymentMethod { void process(); }
    public class MomoPayment implements PaymentMethod { public void process() { /* xử lý Momo */ } }
    public class BankPayment implements PaymentMethod { public void process() { /* xử lý Bank */ } }
    public class PaymentService {
        public void executePayment(PaymentMethod method) { method.process(); }
    }
    ```
*   **Vai trò thực sự:** **Bảo vệ mã nguồn cốt lõi đã chạy ổn định khỏi bàn tay con người**. Cho phép thêm tính năng mới 100% bằng cách viết file mới (mở rộng) thay vì sửa file cũ (sửa đổi), đưa rủi ro gây bug trên tính năng cũ về 0.

---

### 2.3. L - Liskov Substitution Principle (LSP)
*   **Mô tả ngắn:** Lớp con phải thay thế được lớp cha mà không làm hỏng tính đúng đắn của chương trình.
*   **Ví dụ minh họa (Lỗi hình vuông - hình chữ nhật kinh điển):**
    *   ❌ **Vi phạm LSP:**
    ```java
    public class Rectangle {
        protected int width, height;
        public void setWidth(int w) { this.width = w; }
        public void setHeight(int h) { this.height = h; }
        public int getArea() { return width * height; }
    }
    public class Square extends Rectangle {
        @Override
        public void setWidth(int w) { this.width = w; this.height = w; } // Ép hai cạnh bằng nhau
        @Override
        public void setHeight(int h) { this.width = h; this.height = h; }
    }
    // Lỗi: Một hàm kiểm thử kỳ vọng setWidth(5) và setHeight(4) của Rectangle trả về diện tích là 20. 
    // Nếu truyền Square vào, diện tích lại là 16 -> Phá vỡ logic hoạt động của lớp cha!
    ```
    *   ✔️ **Đạt chuẩn LSP:** Tách rời mối quan hệ kế thừa không hợp lệ. Sử dụng Interface chung `Shape` chứa phương thức `getArea()` và thiết lập tham số riêng biệt cho từng lớp hình học độc lập.
*   **Vai trò thực sự:** **Đảm bảo tính dự đoán được (Predictability) và tính nhất quán**. Bắt buộc mọi lớp con phải tuân thủ nghiêm ngặt giao kèo (cam kết hành vi) của lớp cha, tránh việc chương trình sụp đổ bất ngờ do các phản kèo logic từ lớp con.

---

### 2.4. I - Interface Segregation Principle (ISP)
*   **Mô tả ngắn:** Chia nhỏ interface lớn thành nhiều interface chuyên biệt, tránh ép buộc client triển khai thứ họ không dùng.
*   **Ví dụ minh họa:**
    *   ❌ **Vi phạm ISP:**
    ```java
    public interface Worker { void work(); void eat(); }
    // RobotWorker implement Worker phải viết thân hàm trống hoặc ném lỗi cho phương thức eat() vô lý.
    ```
    *   ✔️ **Đạt chuẩn ISP:**
    ```java
    public interface Workable { void work(); }
    public interface Feedable { void eat(); }
    public class RobotWorker implements Workable { public void work() { /* code */ } }
    public class HumanWorker implements Workable, Feedable { public void work() { /* code */ } public void eat() { /* code */ } }
    ```
*   **Vai trò thực sự:** **Tránh sự phụ thuộc vô nghĩa**. Client không bị buộc phải phụ thuộc và biên dịch lại code khi các phương thức không liên quan thay đổi.

---

### 2.5. D - Dependency Inversion Principle (DIP)
*   **Mô tả ngắn:** Module cấp cao không phụ thuộc module cấp thấp, cả hai phụ thuộc vào Abstraction.
*   **Ví dụ minh họa:**
    *   ❌ **Vi phạm DIP:**
    ```java
    public class GasolineEngine { public void start() {} }
    public class Car {
        private GasolineEngine engine;
        public Car() { this.engine = new GasolineEngine(); } // Khởi tạo cứng, phụ thuộc chặt chẽ!
    }
    ```
    *   ✔️ **Đạt chuẩn DIP:**
    ```java
    public interface Engine { void start(); }
    public class GasolineEngine implements Engine { public void start() {} }
    public class ElectricEngine implements Engine { public void start() {} }
    public class Car {
        private Engine engine;
        public Car(Engine engine) { this.engine = engine; } // Tiêm phụ thuộc (Dependency Injection)
    }
    ```
*   **Vai trò thực sự:** **Tách rời Core Logic (Luật nghiệp vụ) ra khỏi hạ tầng chi tiết (Database, UI, Mail...)**. DIP bắt buộc cả core logic và hạ tầng chi tiết phụ thuộc vào Abstraction (Interface). Nhờ đó, luật nghiệp vụ luôn đứng ở thế chủ động làm chủ, giúp bạn dễ dàng thay đổi Database hay các dịch vụ bên ngoài mà hoàn toàn không phải viết lại logic cốt lõi.

> [!NOTE]
> **Lưu ý phân biệt:** DIP là nguyên lý thiết kế (Trừu tượng hóa). DI là mẫu thiết kế (Design Pattern) thực hiện DIP bằng cách truyền phụ thuộc qua Constructor/Setter. IoC Container (như Spring) là Framework tự động thực hiện việc quản lý và tiêm (Inject) các dependencies.

---

## PHẦN 3: NỀN TẢNG JAVA CORE

### 3.1. Biên Dịch và Thực Thi: JDK, JRE, JVM là gì?

Java sử dụng một kiến trúc thông dịch trung gian tinh vi để thực hiện "Chạy mọi nơi":

```
[Mã nguồn .java] 
      │
      │ Biên dịch bằng `javac` Compiler
      ▼
[Mã Bytecode .class]  <--- Độc lập nền tảng, không đổi trên Windows/Mac/Linux
      │
      ├───────────────────────┼───────────────────────┐
      ▼ (Chạy trên JVM Windows)  ▼ (Chạy trên JVM Mac)   ▼ (Chạy trên JVM Linux)
 [Mã máy Windows]       [Mã máy Mac OS]       [Mã máy Linux]
```

*   **JVM (Java Virtual Machine):** Thành phần đọc và thông dịch Bytecode (file `.class`) thành mã máy (Machine Code) tương ứng với hệ điều hành đang chạy.
*   **JRE (Java Runtime Environment):** Bao gồm **JVM** + **Các thư viện cốt lõi (Core Libraries)** cần thiết để chạy một ứng dụng Java.
*   **JDK (Java Development Kit):** Bao gồm **JRE** + **Các công cụ phát triển** (như trình biên dịch `javac`, trình gỡ lỗi `jdb`, `jar`...).

#### Cơ chế nạp Class (ClassLoader) trong Java:
Khi chạy ứng dụng Java, JVM không nạp toàn bộ các class vào bộ nhớ ngay lập tức, mà chỉ nạp khi class đó thực sự được gọi tới (Lazy Loading). Quá trình này được đảm nhiệm bởi **ClassLoader** theo nguyên lý ủy quyền (**Delegation Principle**):

```
       [Bootstrap ClassLoader]   <-- Nạp các lớp core của Java (rt.jar, java.lang.*)
                 ▲
                 │ (Ủy quyền ngược lên nếu không tìm thấy)
       [Platform ClassLoader]    <-- Nạp các class extension (java.sql, etc.)
                 ▲
                 │
      [Application ClassLoader]  <-- Nạp các class từ đường dẫn Classpath của bạn
```
*   **Delegation Model:** Khi một class được yêu cầu nạp, ClassLoader cấp dưới sẽ ủy quyền cho ClassLoader cấp trên nạp trước. Chỉ khi các ClassLoader cấp trên báo không tìm thấy class đó, ClassLoader cấp dưới mới tự thực hiện quét và nạp từ classpath. Điều này đảm bảo an toàn hệ thống (không ai có thể tự định nghĩa lớp `java.lang.String` giả mạo để chiếm quyền).

---

### 3.2. Kiểu Dữ Liệu Nguyên Thủy (Primitive) vs Kiểu Bao Bọc (Wrapper)

#### Bảng 8 Kiểu dữ liệu nguyên thủy (Primitive Types):
| Kiểu dữ liệu | Kích thước | Giá trị mặc định | Bản chất lưu trữ |
| :--- | :--- | :--- | :--- |
| **byte** | 1 byte (8 bits) | `0` | Số nguyên |
| **short** | 2 bytes (16 bits)| `0` | Số nguyên |
| **int** | 4 bytes (32 bits)| `0` | Số nguyên |
| **long** | 8 bytes (64 bits)| `0L` | Số nguyên |
| **float** | 4 bytes (32 bits)| `0.0f` | Số thực |
| **double** | 8 bytes (64 bits)| `0.0d` | Số thực |
| **char** | 2 bytes (16 bits)| `'\u0000'` (ký tự rỗng) | Ký tự Unicode |
| **boolean** | Phụ thuộc vào JVM | `false` | Giá trị logic |

*   **Sự khác biệt lớn nhất:**
    *   **Primitive Type:** Lưu trực tiếp giá trị thực tế trên bộ nhớ **Stack**. Tốc độ cực nhanh, **không thể nhận giá trị `null`**.
    *   **Wrapper Class:** Là một đối tượng thực sự trên bộ nhớ **Heap**, lưu trữ địa chỉ tham chiếu, **có thể nhận giá trị `null`**. Cần thiết khi làm việc với Collections (Generic không chấp nhận kiểu nguyên thủy).
*   **Autoboxing/Unboxing:** JVM tự động chuyển đổi qua lại giữa Primitive và Wrapper tương ứng (ví dụ: `Integer b = 10;` thực chất chạy `Integer.valueOf(10)`).

---

### 3.3. Bí Ẩn Integer Cache (Bộ Nhớ Đệm Số Nguyên)

Hãy phân tích ví dụ phỏng vấn kinh điển sau:

```java
Integer a = 100;
Integer b = 100;
System.out.println(a == b); // Output: true

Integer c = 200;
Integer d = 200;
System.out.println(c == d); // Output: false tại sao?
```

#### Giải thích bản chất:
1.  Để tối ưu hiệu năng và bộ nhớ, Java có một vùng nhớ đệm gọi là **Integer Cache** nằm trong lớp `Integer`. Vùng cache này mặc định lưu trữ các đối tượng `Integer` đại diện cho các giá trị từ **`-128` đến `127`**.
2.  Với `a` và `b` có giá trị `100` (nằm trong khoảng `-128` đến `127`), Java không tạo đối tượng mới mà lấy luôn đối tượng có sẵn trong cache trả về. Vì vậy, `a` và `b` cùng trỏ đến 1 ô nhớ trên Heap -> So sánh địa chỉ `a == b` ra `true`.
3.  Với `c` và `d` có giá trị `200` (nằm ngoài vùng cache), Java buộc phải khởi tạo hai đối tượng `Integer` mới tại hai ô nhớ khác nhau trên Heap -> So sánh địa chỉ `c == d` ra `false`.

> [!IMPORTANT]
> **Nguyên tắc vàng:** Luôn luôn dùng phương thức `.equals()` khi muốn so sánh giá trị giữa các đối tượng Wrapper (`c.equals(d)` sẽ ra `true`), tuyệt đối không dùng toán tử `==` trừ khi bạn muốn so sánh địa chỉ ô nhớ.

#### Hợp đồng giữa equals() và hashCode() (The equals & hashCode Contract)
Khi làm việc với các Collection dạng Map hoặc Set, việc hiểu rõ mối quan hệ giữa `equals()` và `hashCode()` là bắt buộc:
1.  **Nếu hai đối tượng bằng nhau** (`obj1.equals(obj2) == true`), thì mã băm của chúng **phải giống nhau** (`obj1.hashCode() == obj2.hashCode()`).
2.  **Nếu hai đối tượng có mã băm giống nhau**, chúng **chưa chắc đã bằng nhau** (do hiện tượng đụng độ hash - collision).
3.  **Lỗi phổ biến:** Chỉ override `equals()` mà không override `hashCode()`. Khi đó, hai đối tượng có giá trị thuộc tính giống hệt nhau (bằng nhau theo logic) sẽ có mã băm khác nhau (do sử dụng hàm `hashCode()` mặc định của lớp `Object` dựa trên địa chỉ ô nhớ). Kết quả là bạn không thể tìm thấy đối tượng đó khi dùng làm Key trong `HashMap` hoặc khi lưu vào `HashSet`.

---

### 3.4. Quản Lý Chuỗi: String Pool & Immutability (Bất Biến)

#### Tại sao String trong Java là Immutable (Bất biến)?
Khi một đối tượng `String` được khởi tạo, nội dung của nó sẽ **không bao giờ thay đổi được**.
Lý do Java thiết kế String bất biến:
1.  **Tối ưu bộ nhớ (String Pool):** Tiết kiệm tài nguyên bằng cách cho phép nhiều biến tham chiếu trỏ chung đến một hằng số chuỗi duy nhất trong bộ nhớ đệm (String Pool) trên Heap.
2.  **An toàn đa luồng (Thread-safety):** Vì chuỗi không thể thay đổi trạng thái, nhiều luồng (Threads) có thể dùng chung một chuỗi mà không sợ xung đột dữ liệu.
3.  **Bảo mật:** Chuỗi thường dùng để lưu tên người dùng, mật khẩu, kết nối Database, URL API. Nếu chuỗi có thể thay đổi, tin tặc có thể thay đổi dữ liệu tham chiếu để vượt qua hệ thống kiểm tra bảo mật (lỗi bảo mật TOCTOU).

#### Cơ chế String Pool hoạt động như thế nào?

```
[Stack]               [Heap (String Pool)]
  a ───────────────► ┌─────────────────┐
  b ───────────────► │   "hello"       │  (Chỉ có 1 object duy nhất trong Pool)
                     └─────────────────┘
                      ┌─────────────────┐
  c ────────────────► │   "hello"       │  (Tạo ngoài Pool do dùng từ khóa `new`)
                      └─────────────────┘
```

Xét ví dụ:
```java
String a = "hello";              // Tạo chuỗi trong String Pool
String b = "hello";              // Thấy "hello" đã có sẵn trong Pool, b trỏ thẳng vào đó
String c = new String("hello");  // Bắt buộc tạo một đối tượng String mới ngoài Heap
String d = c.intern();           // Trả về địa chỉ của chuỗi "hello" nằm trong String Pool

System.out.println(a == b);      // true (cùng trỏ vào ô nhớ trong String Pool)
System.out.println(a == c);      // false (c nằm ở ô nhớ thường ngoài Heap)
System.out.println(a == d);      // true (d trỏ về địa chỉ Pool của a)
```

#### Phân biệt String, StringBuilder, StringBuffer:
*   **`String`:** Bất biến (Immutable). Dùng khi chuỗi ít thay đổi.
*   **`StringBuilder`:** Có thể thay đổi (Mutable). Không an toàn đa luồng nhưng **tốc độ xử lý cực kỳ nhanh** (thích hợp cho luồng đơn).
*   **`StringBuffer`:** Có thể thay đổi (Mutable). An toàn đa luồng (Thread-safe) nhờ các phương thức đồng bộ hóa (`synchronized`), tốc độ chạy chậm hơn `StringBuilder`.

---

### 3.5. Quản Lý Bộ Nhớ: Stack vs Heap

```
           [ BỘ NHỚ JVM ]
    ┌───────────────────────────┐
    │          STACK            │  <-- Lưu trữ cục bộ, giải phóng nhanh
    │ ┌───────────────────────┐ │
    │ │ Biến tham chiếu 'p'   │─┼────────┐
    │ └───────────────────────┘ │        │
    └───────────────────────────┘        │
    ┌───────────────────────────┐        ▼
    │           HEAP            │  <-- Lưu trữ đối tượng, dọn dẹp bằng GC
    │ ┌───────────────────────┐ │
    │ │  Đối tượng Person     │◄├────────┘
    │ │  - name: "Nghia"      │ │
    │ └───────────────────────┘ │
    └───────────────────────────┘
```

#### 1. Bộ nhớ Stack (Bộ nhớ ngăn xếp)
*   **Đặc điểm:** Tốc độ truy cập cực nhanh. Quản lý theo cơ chế LIFO.
*   **Lưu trữ:** Lưu trữ các biến cục bộ (local variables), các kiểu dữ liệu nguyên thủy được khai báo trong hàm, và các biến tham chiếu (reference variables) trỏ đến đối tượng trên Heap.
*   **Vòng đời:** Tự động giải phóng ngay khi phương thức thực thi xong và thoát khỏi Stack.
*   **Lỗi thường gặp:** `StackOverflowError` - Thường xảy ra khi gọi đệ quy vô hạn không có điểm dừng.

#### 2. Bộ nhớ Heap (Bộ nhớ đống)
*   **Đặc điểm:** Bộ nhớ dùng chung của toàn bộ ứng dụng (Shared memory). Tốc độ truy cập chậm hơn Stack.
*   **Lưu trữ:** Lưu trữ toàn bộ các đối tượng thực tế được tạo ra bằng từ khóa `new` và các biến instance.
*   **Vòng đời:** Được quản lý và dọn dẹp bởi bộ thu gom rác **Garbage Collector (GC)** khi đối tượng không còn bất kỳ biến tham chiếu nào trỏ tới.
*   **Lỗi thường gặp:** `OutOfMemoryError` - Xảy ra khi bộ nhớ Heap bị đầy do tạo quá nhiều đối tượng mà không được giải phóng (lỗi rò rỉ bộ nhớ - Memory Leak).

#### Cơ chế Garbage Collection (GC) & Stop-The-World (STW):
Garbage Collector dọn dẹp các đối tượng "mồ côi" trên Heap bằng thuật toán đánh dấu và quét (**Mark-Sweep-Compact**):
1.  **Mark (Đánh dấu):** GC đi từ các nút gốc gọi là **GC Roots** (biến cục bộ trong Stack, biến static...) để dò theo các tham chiếu. Đối tượng nào được kết nối đến GC Roots sẽ được đánh dấu là "Live" (còn sống).
2.  **Sweep (Quét):** GC giải phóng toàn bộ ô nhớ của các đối tượng không được đánh dấu (không có đường nối tới GC Roots).
3.  **Compact (Nén):** Dịch chuyển các đối tượng còn sống lại gần nhau để tránh phân mảnh bộ nhớ (fragmentation), tạo ra các vùng trống liền mạch lớn cho các đối tượng mới tiếp theo.
*   **Stop-The-World (STW) Pause:** Trong quá trình dọn dẹp (đặc biệt là nén bộ nhớ), GC sẽ tạm dừng tất cả các luồng xử lý ứng dụng của bạn để tránh trạng thái đối tượng bị dịch chuyển địa chỉ khi ứng dụng đang thay đổi nó. STW quá dài có thể gây hiện tượng ứng dụng bị "đơ" đột ngột trên production.

---

### 3.6. Từ Khóa final Và Câu Hỏi Phỏng Vấn Nâng Cao

*   **`final` trên biến:** Biến trở thành hằng số, chỉ được gán giá trị một lần duy nhất và không thể gán lại giá trị mới.
*   **`final` trên phương thức:** Lớp con kế thừa không được phép ghi đè (override) phương thức này.
*   **`final` trên lớp:** Lớp này bị đóng băng, không cho phép lớp khác kế thừa (ví dụ lớp `String` là final class).

#### ❓ Câu hỏi nâng cao: "Vì sao một mảng khai báo final (ví dụ `final int[] arr = {1, 2}`) thì các phần tử bên trong mảng vẫn thay đổi được (ví dụ `arr[0] = 5`), điều này có vi phạm tính chất final không?"
*   **Trả lời:** Không vi phạm. Từ khóa `final` áp dụng cho **biến tham chiếu** `arr`. Nó đảm bảo địa chỉ ô nhớ mà `arr` trỏ tới là cố định (không thể trỏ `arr` sang một mảng khác như `arr = new int[]{3, 4}`).
*   Tuy nhiên, bản thân đối tượng mảng lưu trên Heap là một cấu trúc dữ liệu có thể thay đổi trạng thái nội bộ (mutable). Việc thay đổi giá trị của phần tử `arr[0]` chỉ là thay đổi dữ liệu bên trong ô nhớ Heap chứ không thay đổi địa chỉ tham chiếu của mảng.

#### Phân biệt final, finally, finalize():
*   **`final`:** Từ khóa dùng để khai báo hằng số, chặn override phương thức hoặc chặn kế thừa lớp.
*   **`finally`:** Khối mã đi kèm sau cấu trúc `try-catch`, đảm bảo **luôn luôn được thực thi** bất kể có ngoại lệ xảy ra hay không (thường dùng để giải phóng tài nguyên).
*   **`finalize()`:** Phương thức của lớp `Object`, được JVM gọi trước khi Garbage Collector thu gom đối tượng để dọn dẹp tài nguyên. (Lưu ý: phương thức này đã bị **deprecated** từ Java 9 do không đảm bảo thời gian chạy ổn định và có thể gây rò rỉ bộ nhớ).

---

### 3.7. Exception Handling (Xử Lý Ngoại Lệ)

Trong Java, mọi Ngoại lệ hoặc Lỗi đều bắt nguồn từ lớp cha **`Throwable`**.

```
                 [Throwable]
                /           \
               /             \
          [Error]          [Exception]
                            /       \
                           /         \
              [Checked Exception]   [RuntimeException] (Unchecked)
```

1.  **Error (Lỗi hệ thống):** Các lỗi nghiêm trọng liên quan đến tài nguyên hệ thống hoặc phần cứng bên dưới JVM (ví dụ: `OutOfMemoryError`, `StackOverflowError`). Lập trình viên không nên cố gắng bắt (`catch`) các lỗi này vì ứng dụng không thể tự phục hồi.
2.  **Exception (Ngoại lệ nghiệp vụ):** Các tình huống lỗi phát sinh trong chương trình mà ứng dụng có thể xử lý và phục hồi được. Chia làm 2 nhóm:
    *   **Checked Exception (Ngoại lệ bắt buộc bắt):** Là các lớp kế thừa trực tiếp từ `Exception` (trừ `RuntimeException`). Trình biên dịch sẽ quét và **bắt buộc** bạn phải xử lý bằng khối `try-catch` hoặc khai báo `throws` ở chữ ký hàm ngay khi viết code (ví dụ: `IOException`, `SQLException`).
    *   **Unchecked Exception / Runtime Exception (Ngoại lệ lúc chạy):** Là các lớp kế thừa từ `RuntimeException`. Thường xuất hiện do lỗi logic của lập trình viên (ví dụ: `NullPointerException`, `ArrayIndexOutOfBoundsException`). Trình biên dịch không bắt buộc bạn phải xử lý trước khi chạy chương trình.

#### Cơ chế try-with-resources (Java 7+):
Trước Java 7, việc giải phóng tài nguyên trong khối `finally` rất cồng kềnh và dễ quên. Java 7 giới thiệu `try-with-resources` giúp tự động đóng các tài nguyên (như Database Connection, File Reader) sau khi dùng xong, miễn là tài nguyên đó triển khai interface `AutoCloseable`.

```java
// Tự động đóng FileReader và BufferedReader sau khi thoát khối try
try (BufferedReader br = new BufferedReader(new FileReader("test.txt"))) {
    System.out.println(br.readLine());
} catch (IOException e) {
    e.printStackTrace();
}
```

---

### 3.8. Tổng Quan Về Collections & Advanced Java

#### 1. ArrayList vs LinkedList:
*   **`ArrayList`:** Bên dưới sử dụng một **Mảng động (Dynamic Array)**.
    *   *Ưu điểm:* Tìm kiếm và truy cập phần tử qua index cực nhanh với độ phức tạp $O(1)$.
    *   *Nhược điểm:* Thao tác chèn/xóa phần tử ở giữa danh bạ chậm ($O(N)$) vì phải dịch chuyển vị trí các phần tử đứng sau nó. Khi mảng đầy, nó phải tốn chi phí khởi tạo mảng mới to hơn và sao chép dữ liệu sang.
*   **`LinkedList`:** Bên dưới sử dụng cấu trúc **Danh sách liên kết kép (Double Linked List)**.
    *   *Ưu điểm:* Thao tác chèn hoặc xóa phần tử ở vị trí bất kỳ cực nhanh với độ phức tạp $O(1)$ sau khi đã tìm thấy vị trí (chỉ cần đổi liên kết node).
    *   *Nhược điểm:* Tìm kiếm phần tử chậm ($O(N)$) vì phải duyệt tuần tự từ đầu hoặc cuối danh sách. Tốn bộ nhớ hơn `ArrayList` vì mỗi phần tử phải lưu thêm con trỏ trỏ đến node trước và node sau.

#### 2. Cơ chế vận hành của HashMap (Collision Handling):
`HashMap` lưu trữ dữ liệu dưới dạng cặp khóa-giá trị (Key-Value).
*   **Cách hoạt động:** Khi gọi `map.put(key, value)`, JVM dùng hàm `hashCode()` của key để tính toán chỉ số index của mảng (gọi là Bucket) để lưu trữ.
*   **Collision (Đụng độ mã Hash):** Là trường hợp hai Keys khác nhau nhưng lại có cùng chỉ số Bucket.
*   **Cách xử lý đụng độ:**
    *   **Trước Java 8:** Sử dụng giải pháp **Chaining** (Danh sách liên kết đơn). Các phần tử trùng bucket sẽ được xếp nối đuôi nhau dưới dạng Linked List. Khi tìm kiếm, Java duyệt qua danh sách này và so khớp key bằng phương thức `equals()`. Độ phức tạp tệ nhất có thể lên tới $O(N)$ nếu tất cả rơi vào 1 bucket.
    *   **Từ Java 8:** Khi số lượng phần tử trong một bucket vượt quá ngưỡng **`TREEIFY_THRESHOLD = 8`** và kích thước mảng tối thiểu là 64, Java sẽ tự động chuyển đổi danh sách liên kết đó thành cấu trúc **Cây Đỏ-Đen (Red-Black Tree)**. Điều này giúp giảm độ phức tạp tìm kiếm từ $O(N)$ xuống còn **$O(\log N)$**, cải thiện đáng kể hiệu năng hệ thống khi có collision lớn và ngăn chặn các cuộc tấn công DoS đụng độ mã băm cố ý.

---
*Tài liệu học tập - cs-fundamentals/programming*
