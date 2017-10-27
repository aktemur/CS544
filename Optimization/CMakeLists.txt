cmake_minimum_required(VERSION 2.8.4)
project(CS544-Optimization)

find_package(LLVM 4.0.0 REQUIRED CONFIG)
add_definitions(${LLVM_DEFINITIONS})
include_directories(${LLVM_INCLUDE_DIRS})

add_subdirectory(mba)
add_subdirectory(peephole)
add_subdirectory(adce)
add_subdirectory(licm)


