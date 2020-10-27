echo "==> Uninstalling the app from the device..."
adb uninstall com.altom.alttrashcat

echo "==> Installing the app on the device..."
adb install android/JavaStandaloneAndroidiOS.apk

echo "==> Setup ADB port forwarding..."
adb forward --remove-all 
adb forward tcp:13000 tcp:13000

echo " Start the app "

start "" "android/JavaStandaloneAndroidiOS.apk"

echo "==> Run the tests ..."
cd "../src"
mvn test

echo "==>Kill app"
taskkill //PID $(tasklist | grep JavaStandaloneAndroidiOS.apk | awk '{print $2}' ) //T //F

