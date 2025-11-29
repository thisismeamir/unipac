cmake_minimum_required(VERSION 3.20)
project(unipac VERSION 0.1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_CXX_EXTENSIONS OFF)

# Compiler flags
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wall -Wextra -pedantic")
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -g -O0 -fsanitize=address")
set(CMAKE_CXX_FLAGS_RELEASE "${CMAKE_CXX_FLAGS_RELEASE} -O3 -DNDEBUG")

# Options
option(BUILD_TESTS "Build unit tests" OFF)
option(BUILD_FUSE "Build FUSE filesystem" OFF)
option(ENABLE_LTO "Enable Link-Time Optimization" OFF)

# Find dependencies
find_package(SQLite3 REQUIRED)
find_package(Threads REQUIRED)


# Core library
add_subdirectory(src/core)

# CLI application
add_subdirectory(src/cli)

## FUSE filesystem (optional)
#if(BUILD_FUSE)
#    find_package(FUSE3 REQUIRED)
#    add_subdirectory(src/fuse)
#endif()

# Tests
if(BUILD_TESTS)
    enable_testing()
    add_subdirectory(tests)
endif()

# Install targets
install(TARGETS unipac_cli DESTINATION bin)