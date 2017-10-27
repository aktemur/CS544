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

; <label>:9:                                      ; preds = %15, %0
  %10 = load i64, i64* %2
  %11 = icmp slt i64 %10, 100
  br i1 %11, label %12, label %18

; <label>:12:                                     ; preds = %9
  %13 = load i64, i64* %3
  %14 = mul i64 %13, 4
  store i64 %14, i64* %5
  br label %15

; <label>:15:                                     ; preds = %12
  %16 = load i64, i64* %2
  %17 = add i64 %16, 1
  store i64 %17, i64* %2
  br label %9

; <label>:18:                                     ; preds = %9
  %19 = load i32, i32* %1
  ret i32 %19
}
