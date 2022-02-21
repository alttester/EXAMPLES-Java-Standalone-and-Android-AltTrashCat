
echo " Start the app "

open mac/TrashCatTest.app

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "==>Kill app"
killall TrashCat
