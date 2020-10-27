
echo " Start the app "

start "" "windows/JavaStandaloneAndroidiOS.exe"

echo "==> Run the tests ..."
cd "../src"
mvn test

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCatStandalone.exe | awk '{print $2}' ) //T //F

