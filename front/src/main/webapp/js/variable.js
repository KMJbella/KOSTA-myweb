/*
var로 선언하면 재선언가능, window객체 Scope에 선언, 함수 scope에 선언, Hoisting됨
var없이 선언하면 재선언가능 , window객체 Scope에 선언
let로 선언하면 재선언불가능, block scope에 선언, Hoisting안됨
*/
var a = 10;
var a = 'hello';
console.log(a);  //hello
var f1 = function(){
    var lv = true;  //함수 scope에 선언
    aa = true;      //window객체 Scope에 선언
    a = true;       //window객체 Scope에 재선언
    var f2 = function(){
        aaa = true; //window객체 Scope에 선언
    }
    f2();
}
f1();
// console.log(lv); //ReferenceError : lv is not defined
console.log(window.aa, aa);   //true
console.log(window.aaa, aaa); //true
console.log(window.a, a);     //true


// let b = 10;
// let b = 'hello'; //Cannot redeclare block-scoped variable 'b'
// console.log(b);

let b = 10;
console.log(window.b); //undefined
let f2 = function(){
    let bb = false;    //함수 scope에 선언
    let b = false;    //함수 scope에 선언

}
f2();
// console.log(bb); //ReferenceError : bb is not defined
console.log(b);     //10

//Hoisting : 사용먼저하고 선언을 나중에 하는 방법
aaaa = new Date();
console.log(aaaa);
var aaaa;

bb = new Date(); //ReferenceError : Cannot access 'bb' before initialize
console.log(bb);
let bb;

