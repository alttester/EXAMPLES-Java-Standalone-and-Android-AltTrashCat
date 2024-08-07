
echo " Start the app "

start "" "windows/TrashCat.exe"

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "Starting the Allure report..."
allure serve allure-results &

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCat.exe | awk '{print $2}' ) //T //F

