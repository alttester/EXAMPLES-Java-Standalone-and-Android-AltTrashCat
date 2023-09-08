
echo " Start the app "

start "" "windows/TrashCat.exe"

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "Generate allure report..."
allure generate -c allure-results -o allure-results-html

echo "Combine Allure report..."
allure serve allure-results &

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCat.exe | awk '{print $2}' ) //T //F

