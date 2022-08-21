var a;
a = function(){
  alert("함수1");  //함수구현
};
a();              //함수호출


var b;
b = a;
b();

var c;
c = function(p){
  console.log("매개변수p=" + p);
  if(typeof(p) == 'function'){
    p(); //함수호출
  }
}
var str = 'hello'; c(str);
var num = 999; c(num);
c(a);
c('hello');
c(function(){
  console.log("콜백함수입니다");
});

d = function(p1, p2, p3){
  if(typeof(p1) == 'function'){
    p1(p2, p3);
    //for(var i=p2; i<=p3; i++){
    //  p1(i);
    //}
  }
}


d(function(a, b){
  for(var i=a; i<=b; i++){
    console.log(i);
  }
}//function(){console.log(i); }
, 1, 10);


//매개변수개수와 인자의 개수
var e = function(p1, p2){
  console.log(p1, p2);
}
e(1,2);     //1, 2
e(1);       //1, undefined
e(1, 2, 3); //1, 2


//반환값
var f = function(){
  return true;
}

var f1 = f();
if(f1){
  console.log('f함수의 반환값은 true입니다');
}

var f2 = f();
console.log(5+f2); //5+true -> (true자동형변환) 5+1 = 6

var g = function(){
  console.log("반환값없는 함수입니다")
}

var g1 = g();
console.log('g함수의 반환값:', g1); //undefined

//--------------------------------------------
//outer-inner function
var h = function(){
  var lv1 = '지역변수1';
  var lv2 = function(){
    var inner = '지역변수2';
    console.log('lv2 함수내부에서 lv1', lv1); //지역변수1
    console.log('lv2 함수내부에서 inner', inner); //지역변수2
  }
  lv2();
  console.log('h 함수내부에서 lv1', lv1); //지역변수1
  //console.log('h 함수내부에서 inner', inner); //Uncaught ReferenceError
}
h();
