@echo off
REM ========================================
REM Build and Run Script for KMP Project
REM ========================================

echo.
echo ====================================
echo  Build and Run KMP Android App
echo ====================================
echo.

REM Step 1: Clean build
echo [1/5] Cleaning previous build...
call gradle clean
if %errorlevel% neq 0 (
    echo ERROR: Clean failed!
    pause
    exit /b 1
)

REM Step 2: Build shared module
echo.
echo [2/5] Building shared module...
call gradle :shared:build -x test
if %errorlevel% neq 0 (
    echo ERROR: Shared module build failed!
    pause
    exit /b 1
)

REM Step 3: Build Android APK
echo.
echo [3/5] Building Android APK...
call gradle :androidApp:assembleDebug
if %errorlevel% neq 0 (
    echo ERROR: Android build failed!
    pause
    exit /b 1
)

REM Step 4: Find ADB
echo.
echo [4/5] Looking for ADB...
set ADB_PATH=
where adb >nul 2>&1
if %errorlevel% equ 0 (
    set ADB_PATH=adb
    echo Found ADB in PATH
) else (
    REM Try Android SDK locations
    if exist "%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe" (
        set ADB_PATH=%LOCALAPPDATA%\Android\Sdk\platform-tools\adb.exe
        echo Found ADB in Android SDK
    ) else if exist "%USERPROFILE%\AppData\Local\Android\Sdk\platform-tools\adb.exe" (
        set ADB_PATH=%USERPROFILE%\AppData\Local\Android\Sdk\platform-tools\adb.exe
        echo Found ADB in Android SDK
    ) else (
        echo WARNING: ADB not found! Please install Android SDK or add ADB to PATH
        echo You can manually install the APK from:
        echo %~dp0androidApp\build\outputs\apk\debug\androidApp-debug.apk
        pause
        exit /b 0
    )
)

REM Step 5: Install and run
echo.
echo [5/5] Installing APK to device...
"%ADB_PATH%" devices
"%ADB_PATH%" uninstall com.nghia.applen >nul 2>&1
"%ADB_PATH%" install -r "%~dp0androidApp\build\outputs\apk\debug\androidApp-debug.apk"
if %errorlevel% neq 0 (
    echo ERROR: Installation failed! Make sure emulator/device is connected.
    pause
    exit /b 1
)

echo.
echo [SUCCESS] App installed! Starting app...
"%ADB_PATH%" shell am start -n com.nghia.applen/com.nghia.applen.android.MainActivity
if %errorlevel% neq 0 (
    echo WARNING: Could not auto-start app. Please launch manually.
)

echo.
echo ====================================
echo  Build completed successfully!
echo ====================================
echo APK location: androidApp\build\outputs\apk\debug\androidApp-debug.apk
echo.
pause
