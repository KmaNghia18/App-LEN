@echo off
echo Installing Gradle Wrapper...

REM Check if we can access the internet
ping -n 1 google.com >nul 2>&1
if %errorlevel% neq 0 (
    echo ERROR: No internet connection. Cannot download Gradle Wrapper.
    pause
    exit /b 1
)

REM Try to use gradle if available
where gradle >nul 2>&1
if %errorlevel% equ 0 (
    echo Using system Gradle to generate wrapper...
    gradle wrapper --gradle-version 8.5
    if %errorlevel% equ 0 (
        echo Gradle Wrapper created successfully!
        goto :success
    )
)

REM Manual download method
echo Downloading Gradle Wrapper manually...
echo.
echo Please install Gradle first:
echo 1. Download from: https://gradle.org/releases/
echo 2. Or use Chocolatey: choco install gradle
echo 3. Or use Scoop: scoop install gradle
echo.
echo After installing, run this script again.
pause
exit /b 1

:success
echo.
echo ====================================
echo  Gradle Wrapper Setup Complete!
echo ====================================
echo.
echo You can now use:
echo   gradlew.bat clean
echo   gradlew.bat :androidApp:assembleDebug
echo.
pause
