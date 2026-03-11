## xubuntu 25.04 64bit in vmware
* 
```
sudo apt update
sudo apt install gedit git lftp
sudo apt install curl zip unzip tar
sudo apt install make cmake gcc g++ pkg-config python3 python3-venv bison autoconf libtool
```
* 
```
cd
unzip /media/wmt/CD_ROM/android-ndk-r27d-linux.zip -d /home/wmt/.
```
* 
```
cd
git clone https://github.com/microsoft/vcpkg
cd ./vcpkg/
./bootstrap-vcpkg.sh -disableMetrics
tar xzf /media/wmt/CD_ROM/downloads_vcpkg.tar.gz -C /home/wmt/vcpkg/.
```
* 
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
* modify /home/wmt
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
	..
```
