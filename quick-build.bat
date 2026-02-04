@echo off
REM Quick Build Script using Android Studio's Gradle

echo ====================================
echo  Building APK...
echo ====================================

REM Set paths
set ADB="C:\Users\ADMIN\AppData\Local\Android\Sdk\platform-tools\adb.exe"
set PROJECT_DIR=C:\Users\ADMIN\KMP

REM Find Gradle in user's gradle wrapper cache
set GRADLE_CMD=
for /f "tokens=*" %%i in ('dir /b /s "%USERPROFILE%\.gradle\wrapper\dists\gradle-*\*\bin\gradle.bat" 2^>nul') do (
    set GRADLE_CMD=%%i
    goto :found_gradle
)

:found_gradle
if "%GRADLE_CMD%"=="" (
    echo ERROR: Cannot find Gradle!
    echo.
    echo Please install Gradle using one of these methods:
    echo 1. choco install gradle
    echo 2. scoop install gradle
    echo 3. Download from https://gradle.org/install/
    echo.
    pause
    exit /b 1
)

echo Using Gradle: %GRADLE_CMD%
echo.

REM Check emulator
echo Checking emulator...
%ADB% devices
echo.

REM Build
echo Building APK (this may take a few minutes)...
cd /d %PROJECT_DIR%
"%GRADLE_CMD%" :androidApp:assembleDebug

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Build failed!
    pause
    exit /b 1
)

REM Install
echo.
echo Installing APK...
%ADB% uninstall com.nghia.applen 2>nul
%ADB% install -r "%PROJECT_DIR%\androidApp\build\outputs\apk\debug\androidApp-debug.apk"

if %errorlevel% neq 0 (
    echo.
    echo ERROR: Installation failed!
    pause
    exit /b 1
)

REM Launch
echo.
echo Launching app...
%ADB% shell am start -n com.nghia.applen/com.nghia.applen.android.MainActivity

echo.
echo ====================================
echo  SUCCESS! App is running!
echo ====================================
pause
