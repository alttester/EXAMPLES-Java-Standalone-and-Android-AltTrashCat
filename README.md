# Standalone, Android and MacOS Build with Java Tests

This repository shows a few Java tests that use the page object model and AltTester to test the Unity endless runner sample:
https://assetstore.unity.com/packages/essentials/tutorial-projects/endless-runner-sample-game-87901

### Running the tests on Windows or MacOS
The tests are meant to be run on an Windows or MacOS device. 


To start the tests, depending of your OS run:

❗ Starting with version 2.0.0, the AltTester Desktop must be running on your PC while the tests are running.

- ./start_tests_mac.sh on MacOS/Linux

    Create a folder `mac` under project.
    The app is provided at https://alttester.com/app/uploads/AltTester/desktop/AltTesterDesktopPackageMac__v2.0.1.zip and needs to be included under the mac/ folder.

- ./start_tests_windows.sh on Windows

    Create a folder `windows` under project.
    The app is provided at https://alttester.com/app/uploads/AltTester/TrashCat/TrashCatStandAlone2_0_1.zip and needs to be included under the windows/ folder.

### Running the tests on Android
Please make sure your android device is connected via USB before running the .sh script

To start the tests, run:
❗ Starting with version 2.0.0, the AltTester Desktop must be running on your PC while the tests are running.

- ./start_tests_android.sh on Windows

    Create a folder `android` under project.
    The app is provided at https://alttester.com/app/uploads/AltTester/TrashCat/TrashCatAndroid2_0_1.zip and needs to be included under the android/ folder.

This script will:

- start the app on your device
- create an `allure-results` folder
- run the tests
- start the Allure report
- stop the app after the test are done
