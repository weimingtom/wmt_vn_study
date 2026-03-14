## Win11 build, failed
* remove unzip gradle zip if download failed, and change gradle-wrapper.properties distributionUrl
```
if flutter build apk failed by unzip gradle zip, try to remove this folder:  
C:\Users\admin\.gradle\wrapper\dists\gradle-8.14-all\c2qonpi39x1mddn7hk5gh9iqj

flutter build apk --verbose

https://blog.csdn.net/android_cai_niao/article/details/146634305
D:\work_flutter_android\myproj1\android\gradle\wrapper\gradle-wrapper.properties
https://mirrors.aliyun.com/gradle/distributions/v8.14.0/

distributionUrl=https\://services.gradle.org/distributions/gradle-8.14-all.zip
->
distributionUrl=https\://mirrors.aliyun.com/gradle/distributions/v8.14.0/gradle-8.14-all.zip

```
* Exceptions
```
[  +94 ms] FAILURE: Build failed with an exception.
[        ] * What went wrong:
[        ] Execution failed for task ':app:configureCMakeRelease[arm64-v8a]'.
[        ] > [CXX1300] CMake '3.28.0' or higher was not found in SDK, PATH, or by cmake.dir property.
```
```
[        ]   CMake Error at
D:/home/soft/android_studio_sdk/cmake/4.1.2/share/cmake-4.1/Modules/CMakeDetermineSystem.cmake:159 (message):
[        ]     Could not find toolchain file:
[        ]      "D:\work_flutter_android\KrKr2-Next-main/.devtools/vcpkg/scripts/buildsystems/vcpkg.cmake"
[        ]   Call Stack (most recent call first):
[        ]     CMakeLists.txt:56 (project)
[        ]   CMake Error: CMAKE_C_COMPILER not set, after EnableLanguage
[        ]   CMake Error: CMAKE_CXX_COMPILER not set, after EnableLanguage :
com.android.ide.common.process.ProcessException: Not searching for unused variables given on the command line.
[        ]   -- Android build with Gradle-managed toolchain: triplet=arm64-android
[        ]   -- Configuring incomplete, errors occurred!
```
```
Downloading https://github.com/microsoft/vcpkg-tool/releases/download/2026-03-04/vcpkg.exe -> D:\work_flutter_android\vcpkg-master\vcpkg.exe...While calling Windows API function WinHttpSendRequest got error 0x00002EE2:

put to vcpkg-master

don't use this #@set VCPKG_ROOT=D:\work_flutter_android\vcpkg-master
see 
D:\work_flutter_android\KrKr2-Next-main\apps\flutter_app\android\app\build.gradle.kts
val vcpkgRoot = "${projectRoot}/.devtools/vcpkg"
put this folder
D:\work_flutter_android\KrKr2-Next-main/.devtools/vcpkg

if error try to remove vcpkg\download
D:\work_flutter_android\KrKr2-Next-main\.devtools\vcpkg\download
```
* win11, flutter build apk --verbose
```
D:\home\soft\android_studio_sdk\cmake\4.1.2\bin\cmake.exe
-HD:\work_flutter_android\KrKr2-Next-main
-DCMAKE_SYSTEM_NAME=Android
-DCMAKE_EXPORT_COMPILE_COMMANDS=ON
-DCMAKE_SYSTEM_VERSION=24
-DANDROID_PLATFORM=android-24
-DANDROID_ABI=arm64-v8a
-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a
-DANDROID_NDK=D:\home\soft\android_studio_sdk\ndk\28.2.13676358
-DCMAKE_ANDROID_NDK=D:\home\soft\android_studio_sdk\ndk\28.2.13676358
-DCMAKE_MAKE_PROGRAM=D:\home\soft\android_studio_sdk\cmake\4.1.2\bin\ninja.exe
-DCMAKE_CXX_FLAGS=-std=c++17
-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\work_flutter_android\KrKr2-Next-main\apps\flutter_app\build\app\intermediates\cxx\release\1y554m5e\obj\arm64-v8a
-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\work_flutter_android\KrKr2-Next-main\apps\flutter_app\build\app\intermediates\cxx\release\1y554m5e\obj\arm64-v8a
-BD:\work_flutter_android\KrKr2-Next-main\apps\flutter_app\build\.cxx\release\1y554m5e\arm64-v8a
-GNinja
-DANDROID_STL=c++_shared
-DVCPKG_TARGET_ANDROID=ON
-DBUILD_ENGINE_API=ON
-DENABLE_TESTS=OFF
-DBUILD_TOOLS=OFF
-DVCPKG_ROOT=D:\work_flutter_android\KrKr2-Next-main/.devtools/vcpkg
-DCMAKE_TOOLCHAIN_FILE=D:\work_flutter_android\KrKr2-Next-main/.devtools/vcpkg/scripts/buildsystems/vcpkg.cmake
-DVCPKG_CHAINLOAD_TOOLCHAIN_FILE=D:\home\soft\android_studio_sdk\ndk\28.2.13676358/build/cmake/android.toolchain.cmake
-Wno-dev
--no-warn-unused-cli
-DCMAKE_BUILD_TYPE=release
```

## Build KrKr2-Next with xubuntu 25.04 64bit in vmware, 20260311, 16:00  
* https://github.com/reAAAq/KrKr2-Next/commit/9b4d67e71498f87fda8718903763b6b8a792ed6a
* 9b4d67e
* https://github.com/microsoft/vcpkg/commit/58950f88544e4637524dbd6a01d0317cf4cb77fc
* 58950f8
* Libraries required for compiling vcpkg dependency packages  
```
sudo apt update
sudo apt install gedit git lftp
sudo apt install curl zip unzip tar
sudo apt install make cmake gcc g++ pkg-config python3 python3-venv bison autoconf libtool
```
* Install the latest version of NDK  
```
cd
unzip /media/wmt/CD_ROM/android-ndk-r27d-linux.zip -d /home/wmt/.
```
* Install the latest version of vcpkg (decompression of downloads files can be ignored)  
```
cd
git clone https://github.com/microsoft/vcpkg
cd ./vcpkg/
./bootstrap-vcpkg.sh -disableMetrics
tar xzf /media/wmt/CD_ROM/downloads_vcpkg.tar.gz -C /home/wmt/vcpkg/.
```
* Create and modify ~/KrKr2-Next/build/build.sh, replace '/home/wmt' in build.sh, NOTE, this is debug version, if build release, see version 2    
```
cmake \
	-DANDROID_PLATFORM=34 \
	-DANDROID_ABI=arm64-v8a \
	-DANDROID_STL=c++_shared \
	-DCMAKE_CXX_FLAGS=-std=c++17 \
	-DVCPKG_TARGET_ANDROID=ON \
	-DBUILD_ENGINE_API=ON \
	-DENABLE_TESTS=OFF \
	-DBUILD_TOOLS=OFF \
	-DVCPKG_ROOT=/home/wmt/vcpkg \
	-DCMAKE_TOOLCHAIN_FILE=/home/wmt/vcpkg/scripts/buildsystems/vcpkg.cmake \
	-DVCPKG_CHAINLOAD_TOOLCHAIN_FILE=/home/wmt/android-ndk-r27d/build/cmake/android.toolchain.cmake \
	-DANDROID_NDK=/home/wmt/android-ndk-r27d \
	-DCMAKE_ANDROID_NDK=/home/wmt/android-ndk-r27d \
	-DCMAKE_BUILD_TYPE=release \
	..
```
* build.sh version 2    
NOTE, don't use -DCMAKE_LIBRARY_OUTPUT_DIRECTORY= and -DCMAKE_RUNTIME_OUTPUT_DIRECTORY= and -B  
```
cmake \
-DCMAKE_SYSTEM_NAME=Android \
-DCMAKE_EXPORT_COMPILE_COMMANDS=ON \
-DCMAKE_SYSTEM_VERSION=24 \
-DANDROID_PLATFORM=android-24 \
-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a \
-DCMAKE_MAKE_PROGRAM=make \
-Wno-dev \
--no-warn-unused-cli \
-DCMAKE_BUILD_TYPE=release \
	-DANDROID_ABI=arm64-v8a \
	-DANDROID_STL=c++_shared \
	-DCMAKE_CXX_FLAGS=-std=c++17 \
	-DVCPKG_TARGET_ANDROID=ON \
	-DBUILD_ENGINE_API=ON \
	-DENABLE_TESTS=OFF \
	-DBUILD_TOOLS=OFF \
	-DVCPKG_ROOT=/home/wmt/vcpkg \
	-DCMAKE_TOOLCHAIN_FILE=/home/wmt/vcpkg/scripts/buildsystems/vcpkg.cmake \
	-DVCPKG_CHAINLOAD_TOOLCHAIN_FILE=/home/wmt/android-ndk-r27d/build/cmake/android.toolchain.cmake \
	-DANDROID_NDK=/home/wmt/android-ndk-r27d \
	-DCMAKE_ANDROID_NDK=/home/wmt/android-ndk-r27d \
	..
```
* Compile the latest version of KrKr2-Next, and modify CMakeLists.txt and krkrgles.cpp and engine_api.cpp  
```
cd
git clone https://github.com/reAAAq/KrKr2-Next
cp ~/build.sh ~/KrKr2-Next/build/.
cd ~/KrKr2-Next/build/
chmod +x ./build.sh
./build.sh
(modify ~/KrKr2-Next/cpp/plugins/CMakeLists.txt, comment
# Live2D Cubism SDK
to
#target_link_libraries(${PROJECT_NAME} PUBLIC CubismFramework)
)
(comment krkrlive2d.cpp)
make -j8
```
```
ld.lld: error: undefined symbol: g_live2dRenderTarget
>>> referenced by krkrgles.cpp:1429 (/home/wmt/KrKr2-Next/cpp/plugins/krkrgles.cpp:1429)
ld.lld: error: undefined symbol: TVPRegisterKrkrLive2DPluginAnchor
>>> referenced by engine_api.cpp:201 (/home/wmt/KrKr2-Next/bridge/engine_api/src/engine_api.cpp:201)
```
