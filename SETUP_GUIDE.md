# 🎯 HƯỚNG DẪN SETUP ĐẦY ĐỦ

## ⚡ CÁCH NHANH NHẤT (Khuyến nghị)

### Cài Gradle bằng Chocolatey

1. **Mở PowerShell với quyền Admin** (Right-click → Run as Administrator)

2. **Cài Chocolatey** (nếu chưa có):
```powershell
Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://community.chocolatey.org/install.ps1'))
```

3. **Cài Gradle**:
```powershell
choco install gradle
```

4. **Kiểm tra**:
```powershell
gradle -v
```

---

## 🔧 CÁCH THAY THẾ: Cài Scoop

1. **Mở PowerShell** (không cần Admin)

2. **Cài Scoop**:
```powershell
Set-ExecutionPolicy RemoteSigned -Scope CurrentUser
irm get.scoop.sh | iex
```

3. **Cài Gradle**:
```powershell
scoop install gradle
```

---

## 📦 CÁCH THỦ CÔNG: Download Gradle

1. **Tải Gradle**: https://gradle.org/releases/
   - Chọn **Binary-only** (nhỏ hơn)
   - Version: 8.5 hoặc mới hơn

2. **Giải nén** vào thư mục, ví dụ: `C:\gradle-8.5`

3. **Thêm vào PATH**:
   - Mở **System Properties** → **Environment Variables**
   - Trong **System Variables**, tìm `Path`
   - Click **Edit** → **New**
   - Add: `C:\gradle-8.5\bin`
   - Click **OK** tất cả

4. **Khởi động lại PowerShell** và kiểm tra:
```cmd
gradle -v
```

---

## 🚀 SAU KHI CÀI GRADLE

### Bước 1: Về thư mục project
```cmd
cd C:\Users\ADMIN\KMP
```

### Bước 2: Clean build
```cmd
gradle clean
```

### Bước 3: Build APK
```cmd
gradle :androidApp:assembleDebug
```

### Bước 4: Hoặc dùng script tự động
```cmd
build-and-run.bat
```

---

## 📱 CÀI ADB (Android Debug Bridge)

### Option 1: Tải SDK Platform-Tools

1. **Download**: https://developer.android.com/tools/releases/platform-tools
2. **Giải nén** vào `C:\android-sdk\platform-tools`
3. **Thêm vào PATH**: `C:\android-sdk\platform-tools`

### Option 2: Qua Chocolatey
```powershell
choco install adb
```

### Kiểm tra
```cmd
adb version
```

---

## ✅ CHECKLIST HOÀN CHỈNH

- [ ] Java 17+ đã cài (✅ Bạn đã có!)
- [ ] Gradle đã cài
- [ ] ADB đã cài (nếu muốn chạy trên emulator/device)
- [ ] Emulator hoặc thiết bị Android đã kết nối

---

## 🎯 BUILD VÀ CHẠY

Sau khi setup xong:

```cmd
# Clean
gradle clean

# Build
gradle :androidApp:assembleDebug

# Hoặc dùng script
build-and-run.bat
```

APK sẽ ở: `androidApp\build\outputs\apk\debug\androidApp-debug.apk`

---

## 🆘 CẦN GIÚP ĐỠ?

Nếu gặp lỗi, check:

1. **Java version**: `java -version` (phải >= 17)
2. **Gradle version**: `gradle -v` (phải >= 8.0)
3. **Internet connection** (lần build đầu cần download dependencies)

---

**Khuyến nghị**: Cài Gradle qua **Chocolatey** vì đơn giản và tự động thêm vào PATH!
