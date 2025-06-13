@echo off
echo Launching Dungeon Master...

REM Set the name of the JAR file
set JAR_NAME=Dungeon_Master.jar

REM Check if the JAR file exists
if not exist "%JAR_NAME%" (
    echo Error: %JAR_NAME% not found!
    pause
    exit /b 1
)

REM Run the game
java -jar "%JAR_NAME%"

REM Pause after execution (optional)
pause
