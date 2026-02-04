@echo off
REM ========================================
REM Clean Build Script
REM ========================================

echo Cleaning project...
call gradle clean

echo Deleting build folders...
rmdir /s /q build 2>nul
rmdir /s /q androidApp\build 2>nul
rmdir /s /q shared\build 2>nul
rmdir /s /q .gradle 2>nul

echo.
echo Clean completed!
pause
