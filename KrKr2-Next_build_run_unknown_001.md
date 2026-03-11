## Win11 build, failed
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
* Create and modify ~/KrKr2-Next/build/build.sh, replace '/home/wmt' in build.sh    
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
