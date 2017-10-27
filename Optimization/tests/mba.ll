define void @foo(i32*, i32, i32) {
  %4 = alloca i32*
  %5 = alloca i32
  %6 = alloca i32
  store i32* %0, i32** %4
  store i32 %1, i32* %5
  store i32 %2, i32* %6
  %7 = load i32, i32* %5
  %8 = load i32, i32* %6
  %9 = add nsw i32 %7, %8
  %10 = load i32*, i32** %4
  store i32 %9, i32* %10
  ret void
}
