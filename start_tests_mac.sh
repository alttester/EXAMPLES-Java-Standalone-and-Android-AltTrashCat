
echo " Start the app "

open mac/TrashCat.app

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "Generate allure report..."
allure generate -c allure-results -o allure-results-html

echo "Combine Allure report..."
allure serve allure-results &

echo "==>Kill app"
killall TrashCat
