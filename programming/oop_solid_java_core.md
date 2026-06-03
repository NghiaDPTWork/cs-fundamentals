# HƯỚNG ĐỐI TƯỢNG (OOP), NGUYÊN LÝ SOLID VÀ NỀN TẢNG JAVA CORE

> **Tài liệu học tập & Ôn tập**
> Tài liệu này được thiết kế để kết nối 3 mảng kiến thức trụ cột của một lập trình viên Java: Tư duy thiết kế Hướng đối tượng (OOP), các Nguyên lý thiết kế SOLID chống "code thối", và bản chất vận hành của Java Core bên dưới hệ thống.

---

## PHẦN 1: HƯỚNG ĐỐI TƯỢNG (OOP - OBJECT-ORIENTED PROGRAMMING)

Tư duy hướng đối tượng không chỉ là cú pháp, nó là cách chúng ta mô hình hóa thế giới thực vào trong máy tính bằng các **Đối tượng (Objects)** chứa **Thuộc tính (Fields)** và **Hành vi (Methods)**.

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
        // private: Ngăn chặn sửa đổi số dư tùy tiện từ bên ngoài
        private double balance; 

        public BankAccount(double initialBalance) {
            if (initialBalance >= 0) {
                this.balance = initialBalance;
            }
        }

        // Getter: Cho phép đọc nhưng có kiểm soát
        public double getBalance() {
            return this.balance;
        }

        // Setter: Chỉ cho phép thay đổi dữ liệu khi thỏa mãn điều kiện logic
        public void deposit(double amount) {
            if (amount > 0) {
                this.balance += amount;
            }
        }
    }
    ```

#### 2. Kế thừa (Inheritance)
*   **Khái niệm:** Cho phép một lớp (lớp con - Subclass) kế thừa các thuộc tính và phương thức của một lớp khác (lớp cha - Superclass), giúp tái sử dụng và tổ chức mã nguồn có hệ thống (mối quan hệ **Is-A**).
*   **Từ khóa:** `extends` (kế thừa class) và `implements` (triển khai interface).
*   **Ví dụ Java:**
    ```java
    public class Animal {
        protected String name;
        public void eat() {
            System.out.println(name + " is eating...");
        }
    }

    // Dog IS-A Animal
    public class Dog extends Animal {
        public Dog(String name) {
            this.name = name;
        }
        public void bark() {
            System.out.println(name + " is barking: Woof Woof!");
        }
    }
    ```

#### 3. Đa hình (Polymorphism)
*   **Khái niệm:** Cho phép một đối tượng có thể đóng nhiều vai trò hoặc thực thi một hành vi theo nhiều cách khác nhau.
*   **Phân loại:**
    *   **Compile-time Polymorphism (Đa hình lúc biên dịch - Static):** Thể hiện qua **Method Overloading** (nạp chồng phương thức). Trình biên dịch quyết định hàm nào được chạy dựa trên số lượng và kiểu của tham số truyền vào.
    *   **Runtime Polymorphism (Đa hình lúc thực thi - Dynamic):** Thể hiện qua **Method Overriding** (ghi đè phương thức). Lớp con định nghĩa lại hàm của lớp cha. JVM sẽ quyết định phương thức của lớp nào được gọi khi chương trình chạy thông qua bảng phương thức ảo (**Virtual Method Table - VMT**).
*   **Ví dụ Java:**
    ```java
    // 1. Overloading (Compile-time)
    public class Calculator {
        public int add(int a, int b) { return a + b; }
        public double add(double a, double b) { return a + b; } // Overloaded
    }

    // 2. Overriding (Runtime)
    class Shape {
        void draw() { System.out.println("Drawing a shape"); }
    }
    class Circle extends Shape {
        @Override
        void draw() { System.out.println("Drawing a circle"); } // Overridden
    }
    
    // Sử dụng Runtime Polymorphism:
    Shape myShape = new Circle(); // Kiểu khai báo là Shape, kiểu thực tế là Circle
    myShape.draw(); // Output: "Drawing a circle" (JVM gọi hàm của Circle khi chạy)
    ```

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
    // Người dùng chỉ cần biết RemoteControl có nút bấm, không cần biết mạch điện bên trong hoạt động ra sao
    public interface RemoteControl {
        void turnOn();
        void turnOff();
    }

    public class SonyRemote implements RemoteControl {
        public void turnOn() { System.out.println("Sony TV is ON via Infrared"); }
        public void turnOff() { System.out.println("Sony TV is OFF"); }
    }
    ```

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

> [!TIP]
> **Quy tắc thiết kế đặt tên:** Các Interface đại diện cho năng lực hoặc hành vi thường được đặt tên kết thúc bằng đuôi **-able** (ví dụ: `Runnable`, `Comparable`, `Cloneable`, `Serializable`, `Autocloseable`).

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

```
       S - Single Responsibility Principle (Đơn nhiệm)
       O - Open/Closed Principle (Đóng & Mở)
       L - Liskov Substitution Principle (Thay thế Liskov)
       I - Interface Segregation Principle (Chia nhỏ Interface)
       D - Dependency Inversion Principle (Đảo ngược phụ thuộc)
```

---

### 2.1. S - Single Responsibility Principle (SRP)
> *Một lớp chỉ nên có một và chỉ một lý do duy nhất để thay đổi.* Nói cách khác, một lớp chỉ nên đảm nhận một trách nhiệm duy nhất.

*   **Vấn đề khi vi phạm:** Lớp "vạn năng" (God Class) ôm đồm quá nhiều việc. Khi cần thay đổi định dạng in ấn, ta phải sửa class; khi cần thay đổi kết nối Database, ta cũng phải sửa chính class đó -> Dễ gây lỗi dây chuyền cho các tính năng khác.

#### Minh họa bằng Code:
❌ **Vi phạm SRP:**
```java
// Lớp Invoice vừa chứa thông tin hóa đơn, vừa tự in ấn, vừa tự lưu vào Database
public class Invoice {
    private double amount;
    // ... Constructor, Getters, Setters

    public void printInvoice() {
        System.out.println("Printing invoice details for: " + amount);
    }

    public void saveToDatabase() {
        System.out.println("Saving invoice to SQL Database...");
    }
}
```

   **Giải pháp:** Tách thành 3 lớp, mỗi lớp đảm nhận đúng 1 vai trò chuyên biệt.

✔️ **Đạt chuẩn SRP:**
```java
// 1. Chỉ chứa dữ liệu và logic nghiệp vụ của hóa đơn
public class Invoice {
    private double amount;
    public double getAmount() { return amount; }
}

// 2. Chỉ chịu trách nhiệm hiển thị/in ấn
public class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Printing invoice details for: " + invoice.getAmount());
    }
}

// 3. Chỉ chịu trách nhiệm lưu trữ dữ liệu
public class InvoiceRepository {
    public void save(Invoice invoice) {
        System.out.println("Saving invoice to SQL Database...");
    }
}
```

---

### 2.2. O - Open/Closed Principle (OCP)
> *Các thực thể phần mềm (lớp, mô-đun, hàm) nên mở rộng cho việc phát triển (Open for extension) nhưng đóng lại đối với việc sửa đổi trực tiếp (Closed for modification).*

*   **Vấn đề khi vi phạm:** Mỗi khi thêm một loại hình thanh toán mới hoặc một đối tượng mới, ta phải nhảy vào sửa code của lớp dịch vụ cốt lõi bằng cách thêm các nhánh `if-else` hoặc `switch-case`. Điều này dễ phá vỡ logic cũ đang chạy ổn định.

#### Minh họa bằng Code:
❌ **Vi phạm OCP:**
```java
public class PaymentService {
    public void processPayment(String type) {
        if (type.equals("MOMO")) {
            System.out.println("Processing Momo payment...");
        } else if (type.equals("CREDIT_CARD")) {
            System.out.println("Processing Credit Card payment...");
        }
        // Khi cần thêm ZaloPay, bắt buộc phải vào đây viết thêm else-if -> Sửa đổi code cũ!
    }
}
```

   **Giải pháp:** Sử dụng tính đa hình thông qua Interface/Abstract class. Lớp chính chỉ gọi interface trừu tượng. Khi thêm loại thanh toán mới, chỉ cần tạo lớp mới triển khai interface đó mà không cần động vào lớp dịch vụ cốt lõi.

✔️ **Đạt chuẩn OCP:**
```java
// 1. Tạo interface chung đại diện cho phương thức thanh toán
public interface PaymentMethod {
    void process();
}

// 2. Tạo các lớp triển khai cụ thể
public class MomoPayment implements PaymentMethod {
    public void process() { System.out.println("Processing Momo payment..."); }
}

public class CreditCardPayment implements PaymentMethod {
    public void process() { System.out.println("Processing Credit Card payment..."); }
}

// 3. Lớp dịch vụ đóng với việc sửa đổi nhưng mở với việc thêm phương thức mới
public class PaymentService {
    public void executePayment(PaymentMethod method) {
        method.process(); // Đa hình
    }
}
```

---

### 2.3. L - Liskov Substitution Principle (LSP)
> *Các đối tượng của lớp con phải có khả năng thay thế các đối tượng của lớp cha mà không làm thay đổi tính đúng đắn của chương trình.*

*   **Vấn đề khi vi phạm:** Lớp con kế thừa lớp cha nhưng lại thay đổi hành vi mặc định hoặc ném ra các ngoại lệ lạ khiến các đoạn code sử dụng lớp cha bị sụp đổ hoặc chạy sai logic khi truyền lớp con vào.

#### Minh họa bằng Code (Lỗi hình chữ nhật - hình vuông kinh điển):
❌ **Vi phạm LSP:**
```java
public class Rectangle {
    protected int width;
    protected int height;

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }
    public int getArea() { return width * height; }
}

// Vì hình vuông là hình chữ nhật, ta cho Square extends Rectangle
public class Square extends Rectangle {
    @Override
    public void setWidth(int width) {
        this.width = width;
        this.height = width; // Bắt buộc chiều dài = chiều rộng
    }

    @Override
    public void setHeight(int height) {
        this.width = height;
        this.height = height;
    }
}

// Đoạn code kiểm thử chạy sai:
public class Test {
    public void checkArea(Rectangle r) {
        r.setWidth(5);
        r.setHeight(4);
        // Nếu r là Rectangle: area = 5 * 4 = 20
        // Nếu r là Square: diện tích sẽ là 4 * 4 = 16 (Sai logic mong đợi của Rectangle!)
        assert r.getArea() == 20; 
    }
}
```

   **Giải pháp:** Tránh lạm dụng kế thừa nếu hành vi của lớp con không tương thích hoàn toàn với lớp cha. Thay vào đó, hãy sử dụng một interface chung hoặc tách rời chúng ra.

✔️ **Đạt chuẩn LSP:**
```java
public interface Shape {
    int getArea();
}

public class Rectangle implements Shape {
    private int width;
    private int height;
    public Rectangle(int w, int h) { this.width = w; this.height = h; }
    public int getArea() { return width * height; }
}

public class Square implements Shape {
    private int side;
    public Square(int side) { this.side = side; }
    public int getArea() { return side * side; }
}
```

---

### 2.4. I - Interface Segregation Principle (ISP)
> *Không nên ép buộc một client phải triển khai các phương thức của một interface mà họ không sử dụng.* Nên chia nhỏ các interface lớn thành nhiều interface chuyên biệt, tinh gọn hơn.

*   **Vấn đề khi vi phạm:** Thiết kế một interface "béo phì" (Fat Interface) chứa quá nhiều phương thức. Lớp con khi triển khai bắt buộc phải viết thân hàm trống (hoặc ném ra `UnsupportedOperationException`) cho các phương thức không dùng tới, dẫn đến code thừa và dễ phát sinh lỗi.

#### Minh họa bằng Code:
❌ **Vi phạm ISP:**
```java
public interface Worker {
    void work();
    void eat();
}

// Robot chỉ cần làm việc, không cần ăn uống
public class RobotWorker implements Worker {
    public void work() { System.out.println("Robot working..."); }
    
    // Vi phạm: Robot bị ép buộc implement phương thức eat()
    public void eat() { 
        throw new UnsupportedOperationException("Robot cannot eat!"); 
    }
}
```

   **Giải pháp:** Tách interface `Worker` thành các interface nhỏ chuyên dụng hơn.

✔️ **Đạt chuẩn ISP:**
```java
public interface Workable {
    void work();
}

public interface Feedable {
    void eat();
}

// Robot chỉ triển khai Workable
public class RobotWorker implements Workable {
    public void work() { System.out.println("Robot working..."); }
}

// Con người có thể vừa làm việc vừa ăn
public class HumanWorker implements Workable, Feedable {
    public void work() { System.out.println("Human working..."); }
    public void eat() { System.out.println("Human eating..."); }
}
```

---

### 2.5. D - Dependency Inversion Principle (DIP)
> *1. Các mô-đun cấp cao không nên phụ thuộc vào các mô-đun cấp thấp. Cả hai nên phụ thuộc vào sự trừu tượng (Abstraction).*
> *2. Sự trừu tượng không nên phụ thuộc vào chi tiết. Chi tiết nên phụ thuộc vào sự trừu tượng.*

*   **Vấn đề khi vi phạm:** Module nghiệp vụ quan trọng (cấp cao) trực tiếp khởi tạo (bằng từ khóa `new`) các module phục vụ bên dưới (cấp thấp, như Database, Mailer). Điều này tạo ra sự liên kết chặt chẽ (tight coupling), cực kỳ khó viết Unit Test (vì không thể Mock database được) và khó thay thế module con.

#### Minh họa bằng Code:
❌ **Vi phạm DIP:**
```java
// Lớp cấp thấp
public class GasolineEngine {
    public void start() { System.out.println("Gasoline Engine started."); }
}

// Lớp cấp cao trực tiếp phụ thuộc vào lớp cấp thấp
public class Car {
    private GasolineEngine engine; // Phụ thuộc trực tiếp vào GasolineEngine

    public Car() {
        this.engine = new GasolineEngine(); // Khởi tạo cứng! tight-coupling
    }

    public void drive() {
        engine.start();
    }
}
```

   **Giải pháp:** Cho cả hai phụ thuộc vào Interface (`Engine`). Sử dụng cơ chế truyền phụ thuộc từ bên ngoài vào (Dependency Injection).

✔️ **Đạt chuẩn DIP:**
```java
// 1. Sự trừu tượng (Abstraction)
public interface Engine {
    void start();
}

// Lớp cấp thấp kế thừa Abstraction
public class GasolineEngine implements Engine {
    public void start() { System.out.println("Gasoline Engine started."); }
}

public class ElectricEngine implements Engine {
    public void start() { System.out.println("Electric Engine started."); }
}

// 2. Lớp cấp cao chỉ làm việc với Abstraction
public class Car {
    private final Engine engine;

    // Dependency Injection (DI): Engine được truyền từ bên ngoài vào
    public Car(Engine engine) {
        this.engine = engine;
    }

    public void drive() {
        engine.start();
    }
}
```

> [!NOTE]
> **Lưu ý phân biệt 3 khái niệm liên quan:**
> *   **DIP (Dependency Inversion Principle):** Là nguyên lý thiết kế (hướng dẫn tư duy: code phụ thuộc vào interface, không phụ thuộc class cụ thể).
> *   **DI (Dependency Injection):** Là một mẫu thiết kế (Design Pattern) dùng để thực thi nguyên lý DIP (truyền đối tượng phụ thuộc vào qua Constructor, Setter hoặc Field).
> *   **IoC Container (Inversion of Control):** Là bộ khung sườn (Framework, ví dụ như Spring Container) tự động quản lý vòng đời và tự động tiêm (Inject) các dependencies cho bạn để giảm bớt công đoạn khởi tạo thủ công.

---

## PHẦN 3: NỀN TẢNG JAVA CORE

Phần này đi sâu vào bản chất kỹ thuật bên dưới của Java, làm sáng tỏ các câu hỏi lý thuyết phỏng vấn cốt lõi.

### 3.1. Biên Dịch và Thực Thi: JDK, JRE, JVM là gì?

Java nổi tiếng với khẩu hiệu: **"Write once, run anywhere"** (Viết một lần, chạy mọi nơi). Để làm được điều này, Java sử dụng một kiến trúc thông dịch trung gian tinh vi:

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

*   **JVM (Java Virtual Machine - Máy ảo Java):** Là thành phần trực tiếp đọc và thông dịch Bytecode (file `.class`) thành mã máy (Machine Code) tương ứng với hệ điều hành đang chạy. JVM là lý do giúp Java đạt được tính độc lập nền tảng.
*   **JRE (Java Runtime Environment):** Bao gồm **JVM** + **Các thư viện cốt lõi (Core Libraries)** cần thiết để chạy một ứng dụng Java.
*   **JDK (Java Development Kit):** Bao gồm **JRE** + **Các công cụ phát triển** (như trình biên dịch `javac`, trình gỡ lỗi `jdb`, `jar`...). JDK dành cho lập trình viên để viết và biên dịch code.

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

Java phân chia kiểu dữ liệu làm hai nhóm rõ rệt:

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

#### Wrapper Class là gì?
Mỗi kiểu dữ liệu nguyên thủy đều có một lớp đối tượng tương ứng bao bọc lấy nó, gọi là **Wrapper Class** (ví dụ: `int` -> `Integer`, `char` -> `Character`, `double` -> `Double`...).

#### Sự khác biệt lớn nhất:
*   **Primitive Type:** Lưu trực tiếp giá trị thực tế trên bộ nhớ **Stack**. Không phải là đối tượng, tốc độ xử lý cực nhanh, **không thể nhận giá trị `null`**.
*   **Wrapper Class:** Là một đối tượng thực sự trên bộ nhớ **Heap**, lưu trữ địa chỉ tham chiếu, **có thể nhận giá trị `null`**. Cần thiết khi làm việc với Collections (vì Generic không chấp nhận kiểu nguyên thủy).

#### Autoboxing và Unboxing:
*   **Autoboxing:** Cơ chế tự động chuyển đổi từ kiểu nguyên thủy sang đối tượng Wrapper tương ứng.
    ```java
    int a = 10;
    Integer b = a; // Thực chất JVM tự chạy: Integer b = Integer.valueOf(a);
    ```
*   **Unboxing:** Cơ chế tự động chuyển đổi từ đối tượng Wrapper về kiểu nguyên thủy.
    ```java
    Integer x = 20;
    int y = x; // Thực chất JVM tự chạy: int y = x.intValue();
    ```

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
1.  Khi gán `Integer a = 100`, Java sử dụng cơ chế Autoboxing thực thi hàm `Integer.valueOf(100)`.
2.  Để tối ưu hiệu năng và bộ nhớ, Java có một vùng nhớ đệm gọi là **Integer Cache** nằm trong lớp `Integer`. Vùng cache này mặc định lưu trữ các đối tượng `Integer` đại diện cho các giá trị từ **`-128` đến `127`**.
3.  Với `a` và `b` có giá trị `100` (nằm trong khoảng `-128` đến `127`), Java không tạo đối tượng mới mà lấy luôn đối tượng có sẵn trong cache trả về. Vì vậy, `a` và `b` cùng trỏ đến 1 ô nhớ trên Heap -> So sánh địa chỉ `a == b` ra `true`.
4.  Với `c` và `d` có giá trị `200` (nằm ngoài vùng cache), Java buộc phải khởi tạo hai đối tượng `Integer` hoàn toàn mới tại hai ô nhớ khác nhau trên Heap -> So sánh địa chỉ `c == d` ra `false`.

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
Khi một đối tượng `String` được khởi tạo, nội dung của nó sẽ **không bao giờ thay đổi được**. Nếu bạn thực hiện các phép thao tác như nối chuỗi, viết hoa, Java sẽ tạo ra một đối tượng `String` mới chứa kết quả chứ không sửa trên chuỗi ban đầu.
Lý do Java thiết kế String bất biến:
1.  **Tối ưu bộ nhớ (String Pool):** Tiết kiệm tài nguyên bằng cách cho phép nhiều biến tham chiếu trỏ chung đến một hằng số chuỗi duy nhất trong bộ nhớ đệm (String Pool).
2.  **An toàn đa luồng (Thread-safety):** Vì chuỗi không thể thay đổi trạng thái, nhiều luồng (Threads) có thể dùng chung một chuỗi mà không sợ xung đột dữ liệu.
3.  **Bảo mật:** Chuỗi thường dùng để lưu tên người dùng, mật khẩu, kết nối Database, URL API. Nếu chuỗi có thể thay đổi, tin tặc có thể thay đổi dữ liệu tham chiếu để vượt qua hệ thống kiểm tra bảo mật.

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
*   **`StringBuilder`:** Có thể thay đổi (Mutable). Không an toàn đa luồng (Non-thread-safe) nhưng **tốc độ xử lý cực kỳ nhanh**. Nên dùng trong các tác vụ xử lý chuỗi ở luồng đơn (như vòng lặp nối chuỗi).
*   **`StringBuffer`:** Có thể thay đổi (Mutable). An toàn đa luồng (Thread-safe) vì các phương thức đều được đồng bộ hóa (`synchronized`). Tuy nhiên tốc độ chạy chậm hơn `StringBuilder`.

---

### 3.5. Quản Lý Bộ Nhớ: Stack vs Heap

Bộ nhớ JVM được chia làm hai khu vực chính để quản lý dữ liệu khi chạy:

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
*   **Đặc điểm:** Tốc độ truy cập cực nhanh. Dữ liệu được quản lý theo cơ chế LIFO (Last In, First Out).
*   **Lưu trữ:** Lưu trữ các biến cục bộ (local variables), các kiểu dữ liệu nguyên thủy (primitive types) được khai báo trong hàm, và các biến tham chiếu (reference variables) trỏ đến đối tượng trên Heap.
*   **Vòng đời:** Tự động giải phóng ngay khi phương thức thực thi xong và thoát khỏi Stack.
*   **Lỗi thường gặp:** `StackOverflowError` - Xảy ra khi bộ nhớ Stack bị đầy, nguyên nhân phổ biến nhất là do gọi đệ quy vô hạn không có điểm dừng.

#### 2. Bộ nhớ Heap (Bộ nhớ đống)
*   **Đặc điểm:** Bộ nhớ dùng chung của toàn bộ ứng dụng (Shared memory). Tốc độ truy cập chậm hơn Stack.
*   **Lưu trữ:** Lưu trữ toàn bộ các đối tượng thực tế được tạo ra bằng từ khóa `new` (ví dụ: `new Student()`) và các biến instance (thuộc tính của class).
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

Từ khóa `final` trong Java mang ý nghĩa "cuối cùng" - tức là không thể thay đổi. Tuy nhiên tác dụng của nó khác nhau tùy thuộc vào vị trí khai báo:

*   **`final` trên biến:** Biến trở thành hằng số, chỉ được gán giá trị một lần duy nhất và không thể gán lại giá trị mới.
*   **`final` trên phương thức:** Lớp con kế thừa không được phép ghi đè (override) phương thức này.
*   **`final` trên lớp:** Lớp này bị đóng băng, không cho phép lớp khác kế thừa (ví dụ lớp `String` là final class).

#### ❓ Câu hỏi nâng cao: "Vì sao một mảng khai báo final (ví dụ `final int[] arr = {1, 2}`) thì các phần tử bên trong mảng vẫn thay đổi được (ví dụ `arr[0] = 5`), điều này có vi phạm tính chất final không?"
*   **Trả lời:** Không vi phạm. Từ khóa `final` áp dụng cho **biến tham chiếu** `arr`. Nó đảm bảo địa chỉ ô nhớ mà `arr` trỏ tới là cố định (tức là không thể trỏ `arr` sang một mảng khác như `arr = new int[]{3, 4}`).
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
    *   **Từ Java 8:** Khi số lượng phần tử trong một bucket vượt quá ngưỡng **`TREEIFY_THRESHOLD = 8`** và kích thước mảng tối thiểu là 64, Java sẽ tự động chuyển đổi danh sách liên kết đó thành cấu trúc **Cây Đỏ-Đen (Red-Black Tree)**. Điều này giúp giảm độ phức tạp tìm kiếm từ $O(N)$ xuống còn **$O(\log N)$**, cải thiện đáng kể hiệu năng hệ thống khi có collision lớn.

---
*Tài liệu học tập - cs-fundamentals/programming*
