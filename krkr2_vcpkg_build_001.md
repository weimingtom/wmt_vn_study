## 编译大概过程  
* apt安装
* pip3安装
* 编译安装bison
* 安装cmake, 修改~/.profile，重启xubuntu20
* 修改/etc/hosts，添加github, 用于vcpkg安装
* 安装vcpkg, 执行./bootstrap-vcpkg.sh -disableMetrics, 复制download
* 修改build-linux.sh添加export VCPKG_ROOT=/home/wmt/work_kr2/vcpkg
* 执行build-linux.sh

## 编译过程
* 参考testbison_v1.zip
* 参考apt安装_pip3安装_编译安装bison_安装cmake重启_安装vcpkg复制download_修改build-linux.txt
```
xubuntu25 (don't use 24), vmware (don't use vbox), make sure bison>=3.8.2

sudo apt update

sudo apt install python3-pip nasm
pip3 install ninja2

(before git clone https://github.com/microsoft/vcpkg)
sudo apt install lftp make gedit cmake gcc g++ git curl bison ninja-build pkg-config libx11-dev
sudo apt install libxft-dev libxext-dev autoconf automake libtool libxi-dev libxtst-dev libxrandr-dev
sudo apt install libxmu-dev libgl-dev libxxf86vm-dev libx11-dev libxmu-dev libglu1-mesa-dev
sudo apt install libgl2ps-dev libxi-dev libzip-dev libpng-dev libcurl4-gnutls-dev
sudo apt install libfontconfig1-dev libsqlite3-dev libglew-dev libssl-dev libgtk-3-dev binutils

sudo gedit /etc/hosts
(adding a line about github.com hosts)

git clone https://github.com/microsoft/vcpkg
cd vcpkg
(sometimes need to remove all files and git checkout -f and redo)
./bootstrap-vcpkg.sh
./bootstrap-vcpkg.sh -disableMetrics
(unzip downloads_v0_vcpkg.tar.gz to /home/wmt/work_kr2/vcpkg/downloads)
cd ..

git clone https://github.com/2468785842/krkr2
cd ./krkr2
(modify build-linux.sh to add a line:)
export VCPKG_ROOT=/home/wmt/work_kr2/vcpkg
(and then build)
./build-linux.sh
```

## 备份文件（new)
* bison-3.8.2.tar.gz  
编译安装到/usr/  
* testbison_v1.zip  
安装说明和测试bison（可以忽略不测试bison） 
* cmake-3.31.7-linux-x86_64.tar.gz  
添加bin到PATH修改~/.profile后重启系统  
* krkr2_v0.tar.gz
* vcpkg_v0.tar.gz
* downloads_v0_vcpkg.tar.gz  
安装完vcpkg后解压到vcpkg git仓库内  
* data.xp3
```
我把2468785842/krkr2的linux版编译出来了，大概长这样
（其实和我编译的很类似，可能窗口大小不太一样，所以留白会有不同）。
我用的是xubuntu 20.04编译，需要编译安装bison-3.8.2，
需要安装cmake-3.31.7，需要至少45G的硬盘空间
（编译完好像是用了40G空间），如果不是第一次编译可能要1个小时，
如果是第一次编译的话vcpkg可能要2个小时以上
（还需要各种apt install和pip3 install）。
我甚至试过用xubuntu 24和25编译，结果编译失败，
看来xubuntu 20的gcc可能会较好，太新的gcc可能会不行
（主要是卡在cocos2d-x的编译上）。后面我会想办法编译
安卓版和想办法简化——windows版就免了，除非我有办法
不需要45G的硬盘空间来编译这份代码。运行的话大概是
可以用的，除了有些地方可能会闪退
```

## 备份文件(mini)
* vcpkg_installed_v1.tar.gz  
第一次编译后备份出来的第三方库  
* krkr2_v2_ext_vcpkg_installed.tar.gz  
从CMakeList中去掉vcpkg的结果
```
我尝试把2468785842/krkr2的vcpkg_installed文件夹（约2.5G）
备份出来，然后从源代码CMakeList中去掉vcpkg的使用，
这样也可以编译成功，可以省硬盘空间和编译时间，
还是有一些问题，我以后打算还是改成Makefile编译
（但未确定是否可行）：
（1）虽然vcpkg_installed里面大部分库都是相对路径的，
不过有少数要手工调整，例如libgdiplus的cofig.cmake配置
（2）去掉vcpkg的话有些库要手工设置
CMAKE_PREFIX_PATH和CMAKE_MODULE_PATH，我写入路径全称
（3）编译出来的elf文件依然非常大，有450M左右，
因为调试版的库也是调试版（4）编译过程仍然有错误
（在编译插件的时候），但可以忽略继续编译出elf
```
