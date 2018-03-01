fastlane documentation
================
# Installation

Make sure you have the latest version of the Xcode command line tools installed:

```
xcode-select --install
```

## Choose your installation method:

| Method                     | OS support                              | Description                                                                                                                           |
|----------------------------|-----------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| [Homebrew](http://brew.sh) | macOS                                   | `brew cask install fastlane`                                                                                                          |
| Installer Script           | macOS                                   | [Download the zip file](https://download.fastlane.tools). Then double click on the `install` script (or run it in a terminal window). |
| RubyGems                   | macOS or Linux with Ruby 2.0.0 or above | `sudo gem install fastlane -NV`                                                                                                       |

# Available Actions
## Android
### android unitTests
```
fastlane android unitTests
```
Run unit tests
### android whitelabels_sandbox_beta
```
fastlane android whitelabels_sandbox_beta
```
Submit a new build of Store Manager Whitelabels to Crashlytics Beta
### android whitelabels_staging_beta
```
fastlane android whitelabels_staging_beta
```
Submit a new build of Whatsgood Whitelabels to Crashlytics Beta
### android whitelabels_playstore_beta
```
fastlane android whitelabels_playstore_beta
```
Submit a new build of Whatsgood to Play Store
### android whitelabels_playstore_publish
```
fastlane android whitelabels_playstore_publish
```
Submit a new build of Whatsgood to Play Store

----

This README.md is auto-generated and will be re-generated every time [fastlane](https://fastlane.tools) is run.
More information about fastlane can be found on [fastlane.tools](https://fastlane.tools).
The documentation of fastlane can be found on [docs.fastlane.tools](https://docs.fastlane.tools).
