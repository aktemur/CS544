# CS544 Compilers

## Optimization

Read the assignment here: <http://aktemur.github.io/cs544/assignment_opt.html>

* `MBA` is adapted from
<https://github.com/quarkslab/llvm-dev-meeting-tutorial-2015>.
* ADCE and LICM skeleton files are from
[Vikram Adve's CS426](https://courses.engr.illinois.edu/cs426/).

## Building

To compile, create a `build` folder in the root of this project.
E.g.:

```
$ mkdir build
$ tree
.
├── CMakeLists.txt
├── README.md
├── adce
│   ├── ADCE.cpp
│   └── CMakeLists.txt
├── build
├── licm
│   ├── CMakeLists.txt
│   └── LICM.cpp
├── mba
│   ├── CMakeLists.txt
│   └── MBA.cpp
├── peephole
│   ├── CMakeLists.txt
│   └── Peephole.cpp
└── tests
    ├── adce.c
    ├── adce2.c
    ├── licm.c
    ├── mba.c
    └── mba.ll
```

Now inside the `build` folder, do the following:

```
$ cmake -G Ninja ..
```

**NOTE:** If you don't have [Ninja](http://ninja-build.org/),
either install it, or use `-G "Unix Makefiles"` instead of `-G Ninja`.

You may also generate project files for an IDE, such as Xcode or CLion.
This is very useful because you get intellisense, which will save you
a great amount of time.

Now you can build the pass:

```
$ ninja
```

(OR `$ make`, if you used `"Unix Makefiles"`.)

Building will create a folder for each pass
that includes a `.so` file.

## Usage

You can run a pass, e.g. MBA, as follows:

1.  Create a test input. You can use one that is provided in the `tests` folder.
    ``` 
    $ clang -O0 -emit-llvm -S ../tests/mba.c
    ```
    This will create `mba.ll`.

2.  Let's take a look at `mba.ll`.
    ```
    $ cat mba.ll
    <omitted...>
    define void @foo(i32* %target, i32 %a, i32 %b) #0 {
      %4 = alloca i32*, align 8
      %5 = alloca i32, align 4
      %6 = alloca i32, align 4
      store i32* %0, i32** %4, align 8
      store i32 %1, i32* %5, align 4
      store i32 %2, i32* %6, align 4
      %7 = load i32, i32* %5, align 4
      %8 = load i32, i32* %6, align 4
      %9 = add nsw i32 %7, %8
      %10 = load i32*, i32** %4, align 8
      store i32 %9, i32* %10, align 4
      ret void
    }
    <omitted...>
    ```
    Note the line that starts with `%9 = ...`.
    
3.  Run the pass over the code.
    ```
    $ opt -load mba/libCS544MBA.so -cs544mba -S mba.ll
    ```
    Here, `-cs544mba` is the pass name. It is specified in the `MBA.cpp` file.
    The `-stats` flag is optional; it displays the statistics collected by the pass,
    if your LLVM was compiled with stats collection enabled.
    The `-S` flag makes `opt` print the output in ASCII format.

    Take a look at what happened to the `%9 = ...` line. 
