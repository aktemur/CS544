define i32 @main() {
	%1 = add i32 8, 0
	%x = alloca i32
	store i32 %1, i32* %x
	%2 = add i32 4, 0
	%y = alloca i32
	store i32 %2, i32* %y
	br label %first_cond

first_cond:
	%3 = load i32, i32* %y
	%4 = add i32 0, 0
	%5 = icmp ne i32 %3, %4
	br i1 %5, label %second_cond, label %end_loop

second_cond:
	%6 = load i32, i32* %x
	%7 = load i32, i32* %y
	%8 = sdiv i32 %6, %7
	%9 = add i32 5, 0
	%10 = icmp slt i32 %8, %9
	br i1 %10, label %loop, label %end_loop 

loop:
	%11 = load i32, i32* %x
	%12 = add i32 2, 0
	%13 = add i32 %11, %12
	store i32 %13, i32* %x
	%14 = load i32, i32* %y
	%15 = add i32 2, 0
	%16 = icmp eq i32 %14, %15
	br i1 %16, label %end_loop, label %loop_cont

loop_cont:
	%17 = load i32, i32* %y
	%18 = add i32 1, 0
	%19 = sub i32 %17, %18
	store i32 %19, i32* %y
	br label %first_cond

end_loop:
	%z = alloca i32
	%20 = load i32, i32* %x
	%21 = load i32, i32* %y
	%22 = add i32 %20, %21
	store i32 %22, i32* %z
	%23 = load i32, i32* %z
	ret i32 %23
}
