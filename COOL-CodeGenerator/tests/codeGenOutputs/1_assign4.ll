;;;;;;;;;;;;;;;;;;;;;
;;; CONSTANT POOL ;;;
;;;;;;;;;;;;;;;;;;;;;
@bool_false = constant %Bool {
  %Bool_vtable* @Bool_vtable_prototype,
  i1 0
}
@bool_true = constant %Bool {
  %Bool_vtable* @Bool_vtable_prototype,
  i1 1
}
@int_0 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 0
}
@int_10 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 10
}
@int_27 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 27
}
@int_5 = constant %Int {
  %Int_vtable* @Int_vtable_prototype,
  i32 5
}

@str_0 = constant [1 x i8] c"\00";
@string_0 = constant %String {
  %String_vtable* @String_vtable_prototype,
  i32 0,
  i8* getelementptr ([1 x i8], [1 x i8]* @str_0, i32 0, i32 0)
}

@str_1 = constant [5 x i8] c"Main\00";
@string_1 = constant %String {
  %String_vtable* @String_vtable_prototype,
  i32 4,
  i8* getelementptr ([5 x i8], [5 x i8]* @str_1, i32 0, i32 0)
}
;;;;;;;;;;;;;;;;;;;;;
;;;  DATA LAYOUT  ;;;
;;;;;;;;;;;;;;;;;;;;;
%Main = type {
  %Main_vtable*
}
%Main_vtable = type {
  i32, ;; class tag
  %String*, ;; type name
  %Main* () *,
  %String* (%Main*)*,
  %Object* (%Main*)*,
  %Int* (%Main*)*
}
@Main_vtable_prototype = constant %Main_vtable {
  i32 5,
  %String* @string_1,
  %Main* () * @Main_new,
  %String* (%Main*)* bitcast (%String* (%Object*)* @Object_type_name to %String* (%Main*)*),
  %Object* (%Main*)* bitcast (%Object* (%Object*)* @Object_abort to %Object* (%Main*)*),
  %Int* (%Main*)* @Main_main
}
;;;;;;;;;;;;;;;;;;;;;
;;;    METHODS    ;;;
;;;;;;;;;;;;;;;;;;;;;
define %Main* @Main_new() {
  %r0 = call i8* @malloc(i64 8)
  %r1 = bitcast i8* %r0 to %Main*
  ;; setting vtable
  %r2 = getelementptr %Main, %Main* %r1, i32 0, i32 0
  store %Main_vtable* @Main_vtable_prototype, %Main_vtable** %r2
  ret %Main* %r1
}

define %Int* @Main_main(%Main* %self) {
entry_0:
  %r0 = alloca %Int*
  store %Int* @int_10, %Int** %r0
  %r1 = alloca %Int*
  store %Int* @int_5, %Int** %r1
  %r2 = load %Int*, %Int** %r0
  %r3 = load %Int*, %Int** %r1
  ;; unboxing %Int* %r2
  %r4 = getelementptr %Int, %Int* %r2, i32 0, i32 1
  %r5 = load i32, i32* %r4
  ;; unboxing %Int* %r3
  %r6 = getelementptr %Int, %Int* %r3, i32 0, i32 1
  %r7 = load i32, i32* %r6
  %r8 = add i32 %r5, %r7
  ;; boxing i32 %r8
  %r9 = call %Int* @Int_new()
  %r10 = getelementptr %Int, %Int* %r9, i32 0, i32 1
  store i32 %r8, i32* %r10
  ;; unboxing %Int* %r9
  %r11 = getelementptr %Int, %Int* %r9, i32 0, i32 1
  %r12 = load i32, i32* %r11
  ;; unboxing %Int* @int_27
  %r13 = getelementptr %Int, %Int* @int_27, i32 0, i32 1
  %r14 = load i32, i32* %r13
  %r15 = add i32 %r12, %r14
  ;; boxing i32 %r15
  %r16 = call %Int* @Int_new()
  %r17 = getelementptr %Int, %Int* %r16, i32 0, i32 1
  store i32 %r15, i32* %r17
  store %Int* %r16, %Int** %r0
  store %Int* %r16, %Int** %r1
  ret %Int* %r16
}

define i32 @main() {
  %mainobj = call %Main* @Main_new()
  %r0 = call %Int* @Main_main(%Main* %mainobj)
  ;; unboxing %Int* %r0
  %r1 = getelementptr %Int, %Int* %r0, i32 0, i32 1
  %r2 = load i32, i32* %r1
  ret i32 %r2
}

