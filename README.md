# Standalone, Android and MacOS Build with Java Tests

This repository shows a few Java tests that use the page object model and AltTester® to test the Unity Endless Runner sample:
https://assetstore.unity.com/packages/essentials/tutorial-projects/endless-runner-sample-game-87901

### Running the tests on Windows or MacOS
The tests are meant to be run on a Windows or MacOS device. 


To start the tests, depending on your OS run:

❗ Starting with version 2.0.0, the AltTester® Desktop must be running on your PC while the tests are running.

MacOS/Linux:
1. Download and install the AltTester® Desktop for MacOS from [here](https://alttester.com/downloads/), then open it.
2. Instrument the TrashCat application using the latest version of AltTester® Unity SDK - for additional information you can follow [this tutorial](https://alttester.com/walkthrough-tutorial-upgrading-trashcat-to-2-0-x/#Instrument%20TrashCat%20with%20AltTester%20Unity%20SDK%20v.2.0.x)
3. Create a folder `mac` under the project and include the instrumented app under it.
4. Run `./start_tests_mac.sh` in your terminal.
    
Windows:
1. Download and install the AltTester® Desktop for Windows from [here](https://alttester.com/downloads/), then open it.
2. Instrument the TrashCat application using the latest version of AltTester® Unity SDK - for additional information you can follow [this tutorial](https://alttester.com/walkthrough-tutorial-upgrading-trashcat-to-2-0-x/#Instrument%20TrashCat%20with%20AltTester%20Unity%20SDK%20v.2.0.x)
3. Create a folder `windows` under the project and include the instrumented app under it.
4. Run `./start_tests_windows.sh` in your terminal.
    

### Running the tests on Android
Please make sure your Android device is connected via USB before running the .sh script.

To start the tests, run:

❗ Starting with version 2.0.0, the AltTester® Desktop must be running on your PC while the tests are running.

1. Download and install the AltTester® Desktop for Windows from [here](https://alttester.com/downloads/), then open it.
2. Instrument the TrashCat application using the latest version of AltTester® Unity SDK - for additional information you can follow [this tutorial](https://alttester.com/walkthrough-tutorial-upgrading-trashcat-to-2-0-x/#Instrument%20TrashCat%20with%20AltTester%20Unity%20SDK%20v.2.0.x)
3. Create a folder `android` under the project and include the instrumented app under it.
4. Run `./start_tests_android.sh` on Windows.

The `./start_tests_mac.sh` / `./start_tests_windows.sh` / `./start_tests_android.sh` script will:

- start the app on your device
- create an `allure-results` folder
- run the tests
- start the Allure report
- stop the app after the tests are done

### Allure
To run and process the test report you will need to install Allure beforehand. You can do that by using npm and the following command `npm install -g allure-commandline --save-dev` or check the Allure official page for more installation options.
