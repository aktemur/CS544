define i32 @fact(i32* %n) {
  %prod = alloca i32
  store i32 1, i32* %prod
  br label %cond
  
cond:
  %nVal = load i32, i32* %n
  %gt = icmp sgt i32 %nVal, 0
  br i1 %gt, label %body, label %exit

body:
  %r1 = load i32, i32* %prod
  %r2 = load i32, i32* %n
  %r3 = mul i32 %r1, %r2
  store i32 %r3, i32* %prod
  %r4 = load i32, i32* %n
  %r5 = sub i32 %r4, 1
  store i32 %r5, i32* %n
  br label %cond

exit:
  %result = load i32, i32* %prod
  ret i32 %result
}

define i32 @main() {
  %arg = alloca i32
  store i32 5, i32* %arg
  %r = call i32 @fact(i32* %arg)
  ret i32 %r
}
