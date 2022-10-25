# Standalone, Android and MacOS Build with Java Tests

This repository shows a few Java tests that use the page object model and AltTester to test the Unity endless runner sample:
https://assetstore.unity.com/packages/essentials/tutorial-projects/endless-runner-sample-game-87901

### Running the tests on Windows or MacOS
The tests are meant to be run on an Windows or MacOS device. The app is provided in this repository, under the mac/  and windows/ folder.

To start the tests, depending of your OS run:

- ./start_tests_mac.sh on MacOS/Linux
    The app is provided at https://altom.com/app/uploads/AltTester/TrashCat/TrashCatMacOS.zi and need to be included under the mac/ folder.

- ./start_tests_windows.sh on Windows

### Running the tests on Android
The app is provided at https://altom.com/app/uploads/AltTester/TrashCat/TrashCatAndroid.zip and need to be included under the android/ folder.

To start the tests, run:
- ./start_tests_android.sh on Windows

This script will:

- start the app on your device
- run the tests
- stop the app after the test are done
