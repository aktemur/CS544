define i32 @sum(i32* %a, i32* %b) {
  %avalue = load i32, i32* %a
  %bvalue = load i32, i32* %b
  %sum = add i32 %avalue, %bvalue
  ret i32 %sum
}

define i32 @main() {
  %z = alloca i32
  store i32 15, i32* %z
  %arg2 = alloca i32
  store i32 7, i32* %arg2
  %r = call i32 @sum(i32* %z, i32* %arg2)
  ret i32 %r
}
