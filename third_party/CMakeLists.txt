# nlohmann/json (header-only)
add_library(nlohmann_json INTERFACE)
target_include_directories(nlohmann_json INTERFACE
    ${CMAKE_CURRENT_SOURCE_DIR}/json/include
)

# spdlog
add_subdirectory(spdlog)

# CLI11 (header-only)
add_library(CLI11 INTERFACE)
target_include_directories(CLI11 INTERFACE
    ${CMAKE_CURRENT_SOURCE_DIR}/CLI11/include
)

# Google Test (for unit tests)
if(BUILD_TESTS)
    add_subdirectory(googletest)
endif()
