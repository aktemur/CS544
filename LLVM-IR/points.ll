%Point = type {i32, i32}

define void @init(%Point* %p, i32 %x, i32 %y) {
  %pdotx = getelementptr %Point, %Point* %p, i32 0, i32 0
  store i32 %x, i32* %pdotx
  %pdoty = getelementptr %Point, %Point* %p, i32 0, i32 1
  store i32 %y, i32* %pdoty
  ret void
}

define i32 @main() {
  %p = alloca %Point
  call void @init(%Point* %p, i32 5, i32 7)
  
  %pdotx = getelementptr %Point, %Point* %p, i32 0, i32 0
  %x = load i32, i32* %pdotx
  %pdoty = getelementptr %Point, %Point* %p, i32 0, i32 1
  %y = load i32, i32* %pdoty
  %r = add i32 %x, %y
  ret i32 %r
}