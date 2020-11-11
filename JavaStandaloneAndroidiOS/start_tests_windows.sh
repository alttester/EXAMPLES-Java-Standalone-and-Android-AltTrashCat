
echo " Start the app "

start "" "windows/TrashCat-Windows.exe"

echo "==> Run the tests ..."
cd "../src"
mvn test

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCat-Windows.exe | awk '{print $2}' ) //T //F

