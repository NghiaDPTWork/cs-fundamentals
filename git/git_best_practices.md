# Cẩm Nang Làm Việc Với Git Hiệu Quả & Chuyên Nghiệp

Để duy trì lịch sử commit sạch đẹp, dễ đọc và tối ưu hóa khả năng truy vết lỗi (debugging) trong các dự án thực tế—đặc biệt là các dự án quy mô lớn (Enterprise) với hàng trăm lập trình viên—bạn nên tuân thủ các quy tắc và thực hành tốt nhất (Best Practices) dưới đây.

---

## 1. Viết Commit Nguyên Tử (Atomic Commits)

Commit nguyên tử là việc chia nhỏ các thay đổi của bạn sao cho mỗi commit chỉ chứa **một chỉnh sửa logic duy nhất** (ví dụ: chỉ sửa 1 bug, chỉ thêm 1 tính năng, hoặc chỉ tối ưu hóa cấu trúc của 1 component).

```
   [TỆ]   ==> Commit: "Làm chức năng đăng nhập, sửa CSS nút bấm, update readme"
   [TỐT]  ==> Commit 1: "feat(auth): add email login functionality"
          ==> Commit 2: "style(ui): adjust primary button color and padding"
          ==> Commit 3: "docs: update setup instructions in README"
```

* **Lợi ích**:
  * **Dễ hoàn tác**: Nếu chức năng đăng nhập bị lỗi, bạn chỉ cần dùng lệnh `git revert <commit_1_hash>` để rút code đó về mà không ảnh hưởng gì đến CSS nút bấm hay README.
  * **Dễ xem xét mã nguồn (Code Review)**: Đồng đội dễ dàng hiểu bạn đang làm gì trong từng bước nhỏ thay vì phải đọc một diff khổng lồ hàng nghìn dòng.
  * **Giải quyết xung đột dễ hơn**: Tránh việc nhiều người cùng sửa đổi chéo nhau trên cùng một commit lớn.

---

## 2. Áp dụng Chuẩn Đặt tên Commit (Conventional Commits)

Áp dụng quy chuẩn đặt tên commit giúp lịch sử Git của dự án trở nên nhất quán, dễ đọc và hỗ trợ tự động hóa việc tạo tài liệu cập nhật (Changelog).

### Cấu trúc chuẩn:
```text
<type>(<scope>): <description>

[body - Giải thích chi tiết LÝ DO thay đổi (Không cần ghi CÁCH LÀM vì code đã thể hiện điều đó)]
[footer - Liên kết ticket Jira, Redmine hoặc số ID của Issue]
```

### Các loại `type` phổ biến:

| Loại `type` | Ý nghĩa | Ví dụ |
| :--- | :--- | :--- |
| **`feat`** | Thêm một tính năng mới cho dự án. | `feat(cart): add voucher apply flow` |
| **`fix`** | Sửa lỗi (bug fix) trong code. | `fix(auth): fix password validation regex` |
| **`docs`** | Cập nhật tài liệu (README, API docs, comments). | `docs(readme): add docker build section` |
| **`style`** | Thay đổi định dạng code (khoảng trắng, dấu chấm phẩy, format) mà không đổi logic chạy. | `style(common): format code using prettier` |
| **`refactor`**| Tái cấu trúc code (không sửa bug cũng không thêm tính năng). | `refactor(db): optimize user query performance` |
| **`test`** | Thêm hoặc sửa đổi các mã kiểm thử (unit tests, integration tests). | `test(auth): add tests for token expiration` |
| **`chore`** | Các công việc vặt liên quan đến build tools, config, package dependencies. | `chore(deps): upgrade lodash to version 4.17.21` |

---

## 3. Làm Sạch Lịch Sử Commit Cục Bộ Trước Khi Push (Interactive Rebase)

Trong quá trình code dưới máy local, việc bạn tạo ra hàng tá commit nhỏ kiểu `"wip"`, `"fix typo"`, `"chạy thử lần 2"` là bình thường. Tuy nhiên, trước khi tạo Pull Request để gộp code vào nhánh chính chung, bạn nên **gộp và làm sạch** chúng.

* **Cách làm**: Sử dụng lệnh **Interactive Rebase** để gom nhiều commit thành một commit duy nhất có đầy đủ ý nghĩa:
  ```bash
  # Mở giao diện tương tác cho N commit gần nhất
  git rebase -i HEAD~N
  ```
* Trong giao diện soạn thảo hiện ra, giữ commit đầu tiên là `pick` và chuyển các commit phụ ở dưới thành `squash` (hoặc `s`) để gộp code và gộp tin nhắn, hoặc `fixup` (hoặc `f`) để gộp code và bỏ tin nhắn commit rác:
  ```text
  pick a1b2c3d feat(auth): add login page UI
  fixup e5f6g7h fix typo in login css
  fixup i9j0k1l fix click handler
  ```

---

## 4. Chiến Lược Merge Nhánh Cho Dự Án Lớn

Để bảo vệ lịch sử commit trên các nhánh chính (`main`, `master`, `develop`) luôn thẳng đẹp và ổn định:

1. **Cấm Push Trực Tiếp**: Thiết lập quyền bảo vệ nhánh (Branch Protection Rules), buộc lập trình viên phải tạo Pull Request (PR) và được phê duyệt (Code Review) trước khi merge.
2. **Ưu tiên Squash and Merge trên PR**: Khi phê duyệt PR để gộp vào nhánh chính, hãy chọn tính năng **Squash and Merge**. Toàn bộ lịch sử chi tiết trên nhánh feature sẽ được nén thành **1 commit duy nhất** trên nhánh chính. Nhánh chính sẽ luôn có lịch sử tuyến tính (thẳng hàng), sạch đẹp và cực kỳ dễ theo dõi.
3. **Rebase trước khi Merge**: Khuyến khích lập trình viên chạy `git pull --rebase origin main` trên nhánh feature của mình trước khi merge để giải quyết toàn bộ xung đột dưới máy local, đảm bảo quá trình merge vào nhánh chính diễn ra trơn tru (Fast-forward).

---

## 5. Các Công Cụ Truy Vết Lỗi Đỉnh Cao Trong Git

Khi dự án có quy mô rất lớn với hàng nghìn file và hàng vạn commit, việc tìm ra nguyên nhân gây lỗi bằng tay là cực kỳ tốn thời gian. Hãy làm chủ các công cụ sau:

### 5.1 Định vị tác giả dòng code lỗi với `git blame`
Khi phát hiện một dòng code bị sai logic, bạn có thể tìm xem ai đã viết dòng đó, vào lúc nào và thuộc commit nào bằng lệnh:
```bash
git blame -L <dòng_bắt_đầu>,<dòng_kết_thúc> <đường_dẫn_file>
# Ví dụ:
git blame -L 45,60 src/utils/auth.ts
```

### 5.2 Lọc lịch sử nâng cao với `git log`
Bạn có thể tìm kiếm commit mục tiêu dựa trên nội dung thay đổi hoặc tin nhắn:
```bash
# Tìm các commit có tin nhắn chứa từ khóa "Stripe"
git log --grep="Stripe"

# Tìm các commit mà trong nội dung thay đổi có chứa từ khóa "API_KEY" (cực tốt để tìm file bị lộ bảo mật)
git log -S "API_KEY"

# Xem lịch sử commit của một lập trình viên cụ thể
git log --author="Nghia"
```

### 5.3 Truy tìm commit gây lỗi bằng thuật toán Tìm kiếm Nhị phân (`git bisect`)
Nếu code hiện tại bị lỗi (bad) nhưng bạn biết chắc chắn tuần trước chạy vẫn bình thường (good), hãy để Git tự động thực hiện thuật toán tìm kiếm nhị phân để chỉ mặt gọi tên commit gây lỗi:

```bash
# 1. Bắt đầu quá trình bisect
git bisect start

# 2. Khai báo commit hiện tại đang bị lỗi
git bisect bad

# 3. Khai báo commit cũ trong quá khứ hoạt động tốt
git bisect good <commit_hash_tốt>
```
* **Cơ chế**: Git sẽ tự động checkout ra commit ở giữa. Bạn chạy thử ứng dụng (hoặc chạy unit test):
  * Nếu chạy tốt: gõ `git bisect good`
  * Nếu bị lỗi: gõ `git bisect bad`
* Git sẽ tiếp tục chia đôi lịch sử kiểm tra cho đến khi tìm ra **chính xác commit đầu tiên làm code bị lỗi**. Để thoát chế độ này, gõ `git bisect reset`.

### 5.4 Cứu cánh dữ liệu đã mất với `git reflog`
Trong Git, gần như mọi hành động của bạn (di chuyển HEAD, commit, reset, rebase) đều được lưu nhật ký hành trình. Nếu bạn lỡ tay chạy lệnh xóa sạch code chưa push:
```bash
git reflog
```
Tìm mã băm commit ngay trước khi xảy ra tai nạn (ví dụ: `a1b2c3d`) rồi lùi về đó:
```bash
git reset --hard a1b2c3d
```
Mọi thứ sẽ được khôi phục như chưa từng có sự cố.
