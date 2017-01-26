#!/bin/bash
# This file is meant to be included by the parent cppbuild.sh script
if [[ -z "$PLATFORM" ]]; then
    pushd ..
    bash cppbuild.sh "$@" rplidar
    popd
    exit
fi

RPLIDAR_VERSION=1.5.7
FILENAME=rplidar_sdk_v$RPLIDAR_VERSION
INSTALL_PATH=$(pwd)

mkdir -p $INSTALL_PATH/$PLATFORM
mkdir -p $INSTALL_PATH/$PLATFORM/$FILENAME

download http://www.slamtec.com/download/lidar/sdk/$FILENAME.zip $FILENAME.zip
cd $INSTALL_PATH/$PLATFORM/$FILENAME
unzip -o $INSTALL_PATH/$FILENAME.zip
cd sdk

case $PLATFORM in
    linux-x86)
        CC="$OLDCC -m32" CXX="$OLDCXX -m32" make
        ;;
    linux-x86_64)
        CC="$OLDCC -m64" CXX="$OLDCXX -m64" make
        ;;
    raspberry-x32)
        CROSS_COMPILE_PREFIX="$RASPBERRY_BIN/$RASPBERRY_COMPILER_PREFIX" sh cross_compile.sh
        ;;
    raspberry-x64)
        CROSS_COMPILE_PREFIX="$RASPBERRY_BIN/$RASPBERRY_COMPILER_PREFIX" sh cross_compile.sh
        ;;
    *)
        echo "Error: Platform \"$PLATFORM\" is not supported"
        ;;
esac

cd $INSTALL_PATH/$PLATFORM
cp -Rv $FILENAME/sdk/sdk/include .

# Replace '__attribute__((packed))' to unlock typedef struct parsing
for file in rplidar_cmd.h rplidar_protocol.h ; do
    sed -i 's|__attribute__((packed)) ||g' ./include/$file
done

cd $INSTALL_PATH
