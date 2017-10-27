Simple programs to practice LLVM IR.

To interpret an `.ll` file using the LLVM interpreter,
do the following:

```
$ lli myfile.ll
```

To see the exit value of a main function, you can use

```
$ echo $?
```

To optimize an `.ll` file using LLVM's middle layer, `opt`,
do the following:

```
$ opt -O2 -S myfile.ll
```

This will print the resulting IR code on standard output.

To produce a `.ll` file from a `.c` file
using `clang`, do the following:

```
$ clang -S -emit-llvm -O0 myfile.c
```

This will produce `myfile.ll`. To turn on optimizations, use `-O2`
instead of `-O0`.
