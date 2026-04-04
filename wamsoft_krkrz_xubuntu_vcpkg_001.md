# xubuntu 25.04, vmware, for arm64-android
```
## Xubuntu 25.04 vmware
## sudo apt update
## sudo apt install lftp gedit cmake make gcc g++
## sudo apt install pkg-config nasm ninja-build

#modify /home/wmt
VCPKG_ROOT=/home/wmt/vcpkg \
ANDROID_NDK_HOME=/home/wmt/android-ndk-r27d \
cmake  \
--preset=arm64-android \
..

## cd arm64-android
## ninja
```
