define i32 @main() {
  %1 = alloca i32
  %2 = alloca i64
  %3 = alloca i64
  %4 = alloca i64
  %5 = alloca i64
  %6 = alloca i64
  %7 = alloca [100 x i64]
  store i32 0, i32* %1
  store i64 0, i64* %6
  store i64 5, i64* %3
  store i64 3, i64* %4
  store i64 0, i64* %2
  br label %8

; <label>:8:                                      ; preds = %20, %0
  %9 = load i64, i64* %2
  %10 = icmp slt i64 %9, 100
  br i1 %10, label %11, label %23

; <label>:11:                                     ; preds = %8
  %12 = load i64, i64* %4
  %13 = shl i64 %12, 2
  store i64 %13, i64* %4
  %14 = load i64, i64* %4
  %15 = icmp slt i64 %14, 4
  br i1 %15, label %16, label %19

; <label>:16:                                     ; preds = %11
  %17 = load i64, i64* %3
  %18 = shl i64 %17, 1
  store i64 %18, i64* %5
  br label %19

; <label>:19:                                     ; preds = %16, %11
  br label %20

; <label>:20:                                     ; preds = %19
  %21 = load i64, i64* %2
  %22 = add i64 %21, 1
  store i64 %22, i64* %2
  br label %8

; <label>:23:                                     ; preds = %8
  %24 = load i32, i32* %1
  ret i32 %24
}
