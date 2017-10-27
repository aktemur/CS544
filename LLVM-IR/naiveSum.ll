define i32 @naiveSum(i32 %a, i32 %b) {
  %sum = add i32 %a, %b
  ret i32 %sum
}

define i32 @main() {
  %r = call i32 @naiveSum(i32 9, i32 4)
  ret i32 %r
}
