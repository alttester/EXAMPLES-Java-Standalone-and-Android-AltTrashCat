
echo " Start the app "

open mac/TrashCat.app

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "Starting the Allure report..."
allure serve allure-results &

echo "==>Kill app"
killall TrashCat
