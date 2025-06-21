* How to build mkxp in Xubuntu 20, install to /usr/local, see also https://www.reddit.com/r/RG350/comments/kuy4d2/mkxp_with_arkos_on_rg351p/     
(xubuntu 20.04 64bit)   
$ sudo apt install build-essential git cmake ruby ruby-dev libsdl2-image-dev libsdl2-ttf-dev  
$ sudo apt install libboost-dev libboost-program-options-dev libsigc++-2.0-dev libfluidsynth-dev libmodplug-dev libvorbis-dev  
$ sudo apt install libflac-dev libopenal-dev libpixman-1-dev autoconf libtool  
$ mkdir mkxp_build_dir  
$ cd mkxp_build_dir  
$ git clone https://github.com/Ancurio/mkxp --recursive    
$ git clone https://github.com/Ancurio/SDL_sound --recursive   
$ git clone https://github.com/dgengin/physfs --recursive   
$ cd physfs/   
$ cmake .   
$ make    
$ sudo make install    
$ cd ../SDL_sound/    
$ ./bootstrap    
$ ./configure --enable-static --disable-shared --disable-speex --disable-mikmod    
$ make    
$ sudo make install    
$ cd ../mkxp/    
$ sed -e 's/SDL2_INCLUDE_DIRS/SDL_SOUND_INCLUDE_DIRS} ${&/' -e 's/SDL2_LIBRARY_DIRS/SDL_SOUND_LIBRARY_DIRS} ${&/' -e 's/SDL_SOUND_LIBRARIES}/& -logg -lFLAC -lmodplug/' -i CMakeLists.txt   
$ grep -q ALCdevice_struct src/eventthread.h && sed '/ALCdevice_struct/d;i #include <alc.h>' -i src/eventthread.h  
$ sed -e 's/Name, { 0, Free, 0, { 0, 0 } }, 0, 0, DEF_TYPE_FLAGS/Name, { 0, Free, 0, 0, 0 }, 0, 0, DEF_TYPE_FLAGS/' -i binding-mri/binding-util.h    
$ export RUBY_VERSION=$(/usr/bin/ruby -e "puts RUBY_VERSION")    
$ mkdir ./build  
$ cmake -B ./build -DSHARED_FLUID=ON -DMRIVERSION=${RUBY_VERSION::-2}  
$ cmake --build ./build  
$ LD_LIBRARY_PATH=/usr/local/lib ./build/mkxp.bin.x86_64
