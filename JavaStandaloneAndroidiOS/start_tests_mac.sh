
echo " Start the app "

start "" "mac/TrashCat.app/"

echo "==> Run the tests ..."
cd "../src"
mvn test

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCat.app | awk '{print $2}' ) //T //F

