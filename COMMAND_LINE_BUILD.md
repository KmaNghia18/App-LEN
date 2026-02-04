# 🚀 Build và Chạy App Không Cần Android Studio

## 📋 YÊU CẦU

### 1. Java Development Kit (JDK)
- **Phiên bản**: JDK 17 hoặc 21
- **Kiểm tra**: Mở PowerShell và chạy:
```powershell
java -version
```

Nếu chưa có, download tại: https://adoptium.net/

### 2. Gradle
- **Cài đặt Gradle**: 
```powershell
# Dùng Chocolatey (nếu có)
choco install gradle

# Hoặc download từ https://gradle.org/install/
```

- **Kiểm tra**:
```powershell
gradle -v
```

### 3. Android SDK Platform-Tools (cho ADB)
- **Tải về**: https://developer.android.com/tools/releases/platform-tools
- **Giải nén** vào thư mục bất kỳ
- **Thêm vào PATH**: 
  1. Mở **System Properties** → **Environment Variables**
  2. Thêm đường dẫn `platform-tools` vào `Path`
  3. Ví dụ: `C:\android-sdk\platform-tools`

---

## ⚡ CÁCH SỬ DỤNG

### Option 1: Dùng Script Tự Động (KHUYẾN NGHỊ)

#### Clean Build
```cmd
clean-build.bat
```

#### Build và Chạy
```cmd
build-and-run.bat
```

Script sẽ tự động:
1. ✅ Clean project
2. ✅ Build shared module  
3. ✅ Build Android APK
4. ✅ Tìm ADB
5. ✅ Uninstall app cũ
6. ✅ Cài đặt APK mới
7. ✅ Khởi động app

---

### Option 2: Chạy Từng Command Thủ Công

#### Bước 1: Clean
```cmd
gradle clean
```

#### Bước 2: Build APK
```cmd
gradle :androidApp:assembleDebug
```

⏱️ **Thời gian**: 2-5 phút (lần đầu có thể lâu hơn)

📦 **Output**: `androidApp\build\outputs\apk\debug\androidApp-debug.apk`

#### Bước 3: Cài đặt lên Emulator/Device

**a) Kiểm tra device có kết nối không:**
```cmd
adb devices
```

Phải thấy output kiểu:
```
List of devices attached
emulator-5554   device
```

**b) Uninstall app cũ (nếu có):**
```cmd
adb uninstall com.nghia.applen
```

**c) Cài đặt APK mới:**
```cmd
adb install -r androidApp\build\outputs\apk\debug\androidApp-debug.apk
```

**d) Chạy app:**
```cmd
adb shell am start -n com.nghia.applen/com.nghia.applen.android.MainActivity
```

---

## 🔧 TROUBLESHOOTING

### Lỗi: `gradle: command not found`
**Giải pháp**: Cài Gradle hoặc thêm vào PATH

### Lỗi: `adb: command not found`
**Giải pháp**: 
1. Tải Android SDK Platform-Tools
2. Thêm vào PATH
3. Hoặc dùng đường dẫn đầy đủ:
```cmd
C:\android-sdk\platform-tools\adb.exe devices
```

### Lỗi: `INSTALL_FAILED_UPDATE_INCOMPATIBLE`
**Giải pháp**: Uninstall app cũ trước:
```cmd
adb uninstall com.nghia.applen
```

### Lỗi: `No devices found`
**Giải pháp**: 
1. Mở Android Emulator
2. Hoặc kết nối điện thoại Android qua USB với USB Debugging enabled

### Lỗi: Build thất bại với Koin DI
**Giải pháp**: Đã fix! Nếu vẫn lỗi:
```cmd
gradle clean
gradle :androidApp:assembleDebug
```

---

## 📱 CHẠY TRÊN ĐIỆN THOẠI THẬT

1. **Bật Developer Options**:
   - Settings → About Phone
   - Tap "Build Number" 7 lần

2. **Bật USB Debugging**:
   - Settings → Developer Options
   - Enable "USB Debugging"

3. **Kết nối USB** và kiểm tra:
```cmd
adb devices
```

4. **Cài APK**:
```cmd
adb install -r androidApp\build\outputs\apk\debug\androidApp-debug.apk
```

---

## 🎯 QUICK REFERENCE

| Lệnh | Mục đích |
|------|----------|
| `gradle clean` | Xóa build cũ |
| `gradle :androidApp:assembleDebug` | Build APK debug |
| `gradle :androidApp:assembleRelease` | Build APK release |
| `adb devices` | Liệt kê devices |
| `adb install -r <apk>` | Cài APK |
| `adb uninstall <package>` | Gỡ app |
| `adb logcat` | Xem logs |

---

## 📝 LƯU Ý

- **Lần build đầu** sẽ tải dependencies → mất 5-10 phút
- **Build sau** chỉ mất 1-2 phút
- **Clean build** khi có lỗi cache
- **APK debug** nặng hơn release (~50MB vs ~20MB)

---

## 🚀 WORKFLOW NHANH

```cmd
# Clean và build
clean-build.bat

# Build và chạy
build-and-run.bat

# Hoặc thủ công:
gradle clean
gradle :androidApp:assembleDebug
adb install -r androidApp\build\outputs\apk\debug\androidApp-debug.apk
```

---

**Tạo bởi**: Antigravity AI  
**Last Updated**: 2026-01-31
