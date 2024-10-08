echo "==> Uninstalling the app from the device..."
adb uninstall com.Altom.TrashCat

echo "==> Installing the app on the device..."
adb install android/TrashCat.apk

echo "==> Setup ADB port forwarding..."
adb reverse --remove-all 
adb reverse tcp:13000 tcp:13000

echo " Start the app "

adb shell am start -n com.Altom.TrashCat/com.unity3d.player.UnityPlayerActivity

echo "==> Run the tests ..."
cd "/src"
mvn test

echo "Starting the Allure report..."
allure serve allure-results &

echo "==>Kill app"
adb shell am force-stop com.Altom.TrashCat

echo "Remove reverse port forwarding"
adb reverse --remove-all

echo "Script execution completed. Press any key to exit."
read -n 1 -s