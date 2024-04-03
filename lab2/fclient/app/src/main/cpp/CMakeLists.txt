# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html.
# For more examples on how to use CMake, see https://github.com/android/ndk-samples.

# Sets the minimum CMake version required for this project.
cmake_minimum_required(VERSION 3.22.1)

project("fclient")

add_library(${CMAKE_PROJECT_NAME} SHARED
        native-lib.cpp)

target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log)

add_library(spdlog STATIC IMPORTED)
set_target_properties(spdlog PROPERTIES IMPORTED_LOCATION
        /home/vmt/Projects/rpo/lab2/libs/spdlog/build/${ANDROID_ABI}/libspdlog.a)

add_library(mbedcrypto SHARED IMPORTED)
set_target_properties(mbedcrypto PROPERTIES IMPORTED_LOCATION
        ${CMAKE_CURRENT_SOURCE_DIR}/../nativeLibs/${ANDROID_ABI}/libmbedcrypto.so)

target_include_directories(fclient PUBLIC
        /home/vmt/Projects/rpo/lab2/libs/spdlog/spdlog/include
        /home/vmt/Projects/rpo/lab2/libs/mbedtls/mbedtls/include
        )

target_link_libraries( # Specifies the target library.
        fclient
        spdlog
        mbedcrypto
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )