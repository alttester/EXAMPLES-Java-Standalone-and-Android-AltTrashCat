
echo " Start the app for: $1"

if [ $1 = 'windows' ]
then
  start "" "windows/JavaStandaloneAndroidiOS.exe"
  echo "==> Run the tests ..."
else
  start "" "android/JavaStandaloneAndroidiOS.apk"
fi
echo "==> Run the tests ..."
cd "../src"
mvn test

echo "==>Kill app"
taskkill //PID $(tasklist | grep TrashCatStandalone.exe | awk '{print $2}' ) //T //F

