# Standalone, Android and MacOS Build with Java Tests

This repository shows a few Java tests that use the page object model and AltTester to test the Unity endless runner sample:
https://assetstore.unity.com/packages/essentials/tutorial-projects/endless-runner-sample-game-87901

### Running the tests on Windows or MacOS
The tests are meant to be run on an Windows or MacOS device. 


To start the tests, depending of your OS run:

❗ Starting with version 2.0.0, the AltTester Desktop must be running on your PC while the tests are running.

MacOS/Linux:
1. Install the [AltTesterDesktop](https://alttester.com/app/uploads/AltTester/desktop/AltTesterDesktopPackageMac__v2.0.1.zip), then open it.
2. Create a folder `mac` under project. The app is provided at https://alttester.com/app/uploads/AltTester/TrashCat/TrashCat.app.zip and needs to be included unzipped under the mac/ folder.
3. Run `./start_tests_mac.sh` in your terminal.
    
Windows:
1. Install the [AltTesterDesktop](https://alttester.com/app/uploads/AltTester/desktop/AltTesterDesktopPackageMac__v2.0.1.zip), then open it.
2. Create a folder `windows` under project. The app is provided at https://alttester.com/app/uploads/AltTester/TrashCat/TrashCatStandAlone2_0_1.zip and needs to be included unzipped under the windows/ folder.
3. Run `./start_tests_windows.sh` in your terminal.
    

### Running the tests on Android
Please make sure your android device is connected via USB before running the .sh script.

To start the tests, run:

❗ Starting with version 2.0.0, the AltTester Desktop must be running on your PC while the tests are running.

1. Install the [AltTesterDesktop](https://alttester.com/app/uploads/AltTester/desktop/AltTesterDesktopPackageMac__v2.0.1.zip), then open it.
2. Create a folder `android` under project. The app is provided at https://alttester.com/app/uploads/AltTester/TrashCat/TrashCatAndroid2_0_1.zip and needs to be included unzipped under the android/ folder.
3. Run `./start_tests_android.sh` on Windows.

The `./start_tests_mac.sh` / `./start_tests_windows.sh` / `./start_tests_android.sh` script will:

- start the app on your device
- create an `allure-results` folder
- run the tests
- start the Allure report
- stop the app after the test are done

### Allure
In order to run and process the tests report you will need to install allure beforehand. You can do that by using npm and the following command `npm install -g allure-commandline --save-dev` or check Allure official page for more installation options.
