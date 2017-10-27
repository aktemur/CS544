define i32 @main() {
entry:
  %r1 = alloca i32
  %r2 = alloca i64
  %r3 = alloca i64
  %r4 = alloca i64
  %r5 = alloca i64
  %r6 = alloca i64
  %r7 = alloca i64
  %r8 = alloca [100 x i64]
  store i32 0, i32* %r1
  store i64 0, i64* %r7
  store i64 5, i64* %r3
  store i64 3, i64* %r4
  store i64 0, i64* %r2
  br label %b9

b9:                                               ; preds = %b17, %entry
  %r10 = load i64, i64* %r2
  %r11 = icmp slt i64 %r10, 100
  br i1 %r11, label %b12, label %b20

b12:                                              ; preds = %b9
  %r13 = load i64, i64* %r3
  %r14 = mul i64 7, %r13
  store i64 %r14, i64* %r5
  %r15 = load i64, i64* %r3
  %r16 = mul i64 %r15, 1023
  store i64 %r16, i64* %r5
  br label %b17

b17:                                              ; preds = %b12
  %r18 = load i64, i64* %r2
  %r19 = add i64 %r18, 1
  store i64 %r19, i64* %r2
  br label %b9

b20:                                              ; preds = %b9
  %r21 = load i32, i32* %r1
  ret i32 %r21
}
