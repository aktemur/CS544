define i32 @main() {
  %1 = alloca i32
  %2 = alloca i64
  %3 = alloca i64
  %4 = alloca i64
  %5 = alloca i64
  %6 = alloca i64
  %7 = alloca i64
  %8 = alloca [100 x i64]
  store i32 0, i32* %1
  store i64 0, i64* %7
  store i64 5, i64* %3
  store i64 3, i64* %4
  store i64 0, i64* %2
  br label %9

; <label>:9:                                      ; preds = %17, %0
  %10 = load i64, i64* %2
  %11 = icmp slt i64 %10, 100
  br i1 %11, label %12, label %20

; <label>:12:                                     ; preds = %9
  %13 = load i64, i64* %3
  %14 = shl i64 %13, 1
  store i64 %14, i64* %5
  %15 = load i64, i64* %4
  %16 = mul i64 %15, 1
  store i64 %16, i64* %6
  br label %17

; <label>:17:                                     ; preds = %12
  %18 = load i64, i64* %2
  %19 = add i64 %18, 1
  store i64 %19, i64* %2
  br label %9

; <label>:20:                                     ; preds = %9
  %21 = load i32, i32* %1
  ret i32 %21
}
