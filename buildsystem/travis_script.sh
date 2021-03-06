#!/bin/bash
function beforeInstall {
	mkdir "$ANDROID_HOME/licenses" || true
	echo -e "\n8933bad161af4178b1185d1a37fbf41ea5269c55" > "$ANDROID_HOME/licenses/android-sdk-license"
	echo -e "\n84831b9409646a918e30573bab4c9c91346d8abd" > "$ANDROID_HOME/licenses/android-sdk-preview-license"
	echo -e "\nd56f5187479451eabf01fb78af6dfcb131a6481e" > "$ANDROID_HOME/licenses/android-sdk-license"
	yes | sdkmanager "platforms;android-27"
    chmod +x gradlew
 	./gradlew dependencies || true
}

function fastlaneScripts {
	fastlane android unitTests
}

case $1 in
"beforeInstall")
	beforeInstall
	;;
"fastlaneScripts")
	fastlaneScripts
	;;
*)
	echo ""
	;;
esac

