## (not good) invoke android vcpkg under Windows, failed
* Environment variables
```
VCPKG_ROOT
D:\work_krkrsdl3\vcpkg-master

ANDROID_NDK_HOME=
D:\home\soft\android_studio_sdk\ndk\28.0.13004108

VCPKG_DEFAULT_TRIPLET=
VCPKG_TARGET_TRIPLET=
arm64-android

'-DVCPKG_TARGET_TRIPLET=arm64-android'
```
* vcpkg build failed under Windows, because Unable to find a valid Visual Studio instance
```
[CXX1429] error when building with cmake using D:\work_krkrsdl3\krkrsdl3-main\CMakeLists.txt: -- Running vcpkg install
A suitable version of cmake was not found (required v4.3.2).
Downloading https://github.com/Kitware/CMake/releases/download/v4.3.2/cmake-4.3.2-windows-x86_64.zip -> cmake-4.3.2-windows-x86_64.zip
Successfully downloaded cmake-4.3.2-windows-x86_64.zip
Extracting cmake...
A suitable version of 7zip was not found (required v26.1.0).
Downloading https://github.com/ip7z/7zip/releases/download/26.01/7z2601-x64.exe -> 7z2601-x64.7z.exe
Successfully downloaded 7z2601-x64.7z.exe
Extracting 7zip...
A suitable version of 7zr was not found (required v26.1.0).
Downloading https://github.com/ip7z/7zip/releases/download/26.01/7zr.exe -> f798fab8-7zr.exe
Successfully downloaded f798fab8-7zr.exe
error: in triplet x64-windows: Unable to find a valid Visual Studio instance
Could not locate a complete Visual Studio instance

-- Running vcpkg install - failed
-- Configuring incomplete, errors occurred!

C++ build system [configure] failed while executing:
    @echo off
    "D:\\home\\soft\\android_studio_sdk\\cmake\\3.31.1\\bin\\cmake.exe" ^
      "-HD:\\work_krkrsdl3\\krkrsdl3-main" ^
      "-DCMAKE_SYSTEM_NAME=Android" ^
      "-DCMAKE_EXPORT_COMPILE_COMMANDS=ON" ^
      "-DCMAKE_SYSTEM_VERSION=21" ^
      "-DANDROID_ABI=arm64-v8a" ^
      "-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a" ^
      "-DANDROID_NDK=D:\\home\\soft\\android_studio_sdk\\ndk\\28.0.13004108" ^
      "-DCMAKE_ANDROID_NDK=D:\\home\\soft\\android_studio_sdk\\ndk\\28.0.13004108" ^
      "-DCMAKE_TOOLCHAIN_FILE=D:\\home\\soft\\android_studio_sdk\\ndk\\28.0.13004108\\build\\cmake\\android.toolchain.cmake" ^
      "-DCMAKE_MAKE_PROGRAM=D:\\home\\soft\\android_studio_sdk\\cmake\\3.31.1\\bin\\ninja.exe" ^
      "-DCMAKE_LIBRARY_OUTPUT_DIRECTORY=D:\\work_krkrsdl3\\krkrsdl3-main\\out\\android\\app\\intermediates\\cxx\\Debug\\193et6f5\\obj\\arm64-v8a" ^
      "-DCMAKE_RUNTIME_OUTPUT_DIRECTORY=D:\\work_krkrsdl3\\krkrsdl3-main\\out\\android\\app\\intermediates\\cxx\\Debug\\193et6f5\\obj\\arm64-v8a" ^
      "-DCMAKE_BUILD_TYPE=Debug" ^
      "-BD:\\work_krkrsdl3\\krkrsdl3-main\\out\\android\\Debug\\193et6f5\\arm64-v8a" ^
      -GNinja ^
      "-DANDROID_PLATFORM=android-28" ^
      "-DANDROID=true"
  from D:\work_krkrsdl3\krkrsdl3-main\android\app
CMake Error at D:/work_krkrsdl3/vcpkg/scripts/buildsystems/vcpkg.cmake:955 (message):
  vcpkg install failed.  See logs for more information:
  D:\work_krkrsdl3\krkrsdl3-main\out\android\Debug\193et6f5\arm64-v8a\vcpkg-manifest-install.log
Call Stack (most recent call first):
  D:/home/soft/android_studio_sdk/cmake/3.31.1/share/cmake-3.31/Modules/CMakeDetermineSystem.cmake:146 (include)
  CMakeLists.txt:34 (project) : com.android.ide.common.process.ProcessException: -- Running vcpkg install
A suitable version of cmake was not found (required v4.3.2).
Downloading https://github.com/Kitware/CMake/releases/download/v4.3.2/cmake-4.3.2-windows-x86_64.zip -> cmake-4.3.2-windows-x86_64.zip
Successfully downloaded cmake-4.3.2-windows-x86_64.zip
Extracting cmake...
A suitable version of 7zip was not found (required v26.1.0).
Downloading https://github.com/ip7z/7zip/releases/download/26.01/7z2601-x64.exe -> 7z2601-x64.7z.exe
Successfully downloaded 7z2601-x64.7z.exe
Extracting 7zip...
A suitable version of 7zr was not found (required v26.1.0).
Downloading https://github.com/ip7z/7zip/releases/download/26.01/7zr.exe -> f798fab8-7zr.exe
Successfully downloaded f798fab8-7zr.exe
error: in triplet x64-windows: Unable to find a valid Visual Studio instance
Could not locate a complete Visual Studio instance


```


## Prebuild .sh file before make -j4
* mkdir build; cd build
* add build_android.sh in ./build/.
```
#!/bin/sh

#-DCMAKE_MAKE_PROGRAM=ninja
#-DCMAKE_SYSTEM_VERSION=21
ANDROID_NDK_HOME=/home/wmt/android-ndk-r27d VCPKG_ROOT=/home/wmt/vcpkg cmake \
	-DANDROID_PLATFORM=34 \
-DCMAKE_SYSTEM_NAME=Android \
-DCMAKE_EXPORT_COMPILE_COMMANDS=ON \
-DCMAKE_SYSTEM_VERSION=34 \
	-DANDROID_ABI=arm64-v8a \
-DCMAKE_ANDROID_ARCH_ABI=arm64-v8a \
-DANDROID=true \
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
* chmod +x ./build_android.sh
* ./build_android.sh
* make -j4
