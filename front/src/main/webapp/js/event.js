//1. 문서의 끝까지 해석후 DOM트리가 완성되고 화면에 렌더링할 준비가 되면
//window 객체의 load 이벤트가 발생한다.

//DOM 트리 작성이 완료될 때까지 기다림
//window 객체의 load 이벤트발생을 감시했다가
//이벤트가 발생하면 function()이 자동호출됨
window.addEventListener("load", function() {
    //DOM트리의 type 속성이 text인 input 객체찾기
    var txtObj = document.querySelector("input[type=text]");

    //DOM트리의 button 객체찾기
    var btObj = document.querySelector("button");

    //button 객체의 click이벤트가 발생했을때 function()이 자동호출
    btObj.addEventListener("click", function(){
        alert('클릭되었습니다');
        txtObj.value = '클릭되었습니다';
    });

    //------------계산기 START---------
    //DOM트리에서 class 속성값이 calculator인 객체의 자식중 모든 button객체찾기
    var btArr = document.querySelectorAll("div.calculator>button");
    // for(var i=0; i<btArr.length; i++){
    //     btArr[i].addEventListener('click', function(){
    //         console.log(i, '버튼클릭되었습니다');
    //     });
    // }

    // for(var i=0; i<btArr.length; i++){
    //     (function(j){
    //         btArr[j].addEventListener('click', function(){
    //             console.log(j, '버튼클릭되었습니다');
    //     });
    // })(i);
    // }

  var resultObj =
    document.querySelector("div.calculator>div.result");

  var resultNum = 0; //계산된 결과값
  var operator;      //연산자
  btArr.forEach(function(item, index){
    item.addEventListener('click', function(){
      //console.log(index, '버튼클릭되었습니다');
      //resultObj.innerHTML = index+'번 버튼클릭되었습니다';
      //resultObj.innerHTML = item.innerHTML + '번 버튼 클릭되었습니다';
      var inner = this.innerHTML;
      switch(inner){
          case '+':
              operator = inner;
              break;
          case '=':
              resultObj.innerHTML = resultNum;
              operator = undefined;
              resultNum = 0;
              break;
          default:   //숫자버튼들
          resultObj.innerHTML = inner;
          if(operator == '+'){
              resultNum += parseInt(inner);
          }else{
              resultNum = parseInt(inner);
          }
      }
    });

  });
    //------------계산기 END-----------

    //---------CHECKBOX START----------
  var cbArr =
    document.querySelectorAll("div.checkbox input[type=checkbox]");
  cbArr.forEach(function(item, index){
    //   item.addEventListener('click', function(){});
    // if(index == 0) { //첫번째 checkbox객체
    // }
    console.log(item, item.checked);
  });

  cbArr[0].addEventListener('click', function(){
      for(var i=1; i<cbArr.length; i++){
          cbArr[i].checked = this.checked;
      }
  });
    //----------CHECKBOX END-----------


    //----------SELECT START-----------
    var selectSidoObj = document.querySelector("div.select>select.sido"); // select tag의 sido 클래스 (select sido는 select 태그의 후손 태그인 sido 태그를 의미합니다)
    var selectSigunguObj = document.querySelector("div.select>select.sigungu");

    selectSidoObj.addEventListener('click', function(){
        console.log(this.value, '클릭되었습니다');
    });

    selectSidoObj.addEventListener('change', function(){
        console.log(this.value, '변경되었습니다');
        switch(this.value){
            case '서울시':
                selectSigunguObj.innerHTML = '';
                var seoul = '<option>구를 선택하세요</option>';
                seoul += '<option>중구</option>'
                seoul += '<option>강북구</option>'
                seoul += '<option>강동구</option>'
                seoul += '<option>강남구</option>'
                seoul += '<option>강서구</option>'
                selectSigunguObj.innerHTML = seoul;
                selectSigunguObj.style.display = 'inline-block';
                break;
            case '제주도':
                // selectSigunguObj.innerHTML = '';
                // for(var i=0; i<selectSigunguObj.childNodes.length; i++) {
                //     console.log('before remove length', selectSigunguObj.childNodes.length); //6
                //     selectSigunguObj.removeChild(selectSigunguObj.childNodes[i]);            //???
                //     console.log('after remove length', selectSigunguObj.childNodes.length);  //5
                // }
                while( selectSigunguObj.hasChildNodes() ){
                    selectSigunguObj.removeChild(selectSigunguObj.firstChild);
                }


                // var jeju = '<option>시를 선택하세요</option>';
                // jeju += '<option>제주시</option>';
                // jeju += '<option>서귀포시</option>';
                // selectSigunguObj.innerHTML = jeju;

                var jeju = ['시를 선택하세요', '제주시', '서귀포시'];
                for(var i=0; i<jeju.length; i++) {
                    var opt = document.createElement('option');
                    var txt = document.createTextNode(jeju[i]);
                    opt.appendChild(txt);
                    selectSigunguObj.appendChild(opt);
                }
                selectSigunguObj.style.display = 'inline-block';
                break;
                default:
                    selectSigunguObj.innerHTML = '';
                    selectSigunguObj.style.display = 'none';
                }
            });
                
                // var opt2 = document.createElement('option');
                // var txt2 = document.createTextNode('제주시');
                // opt2.appendChild(txt2);
                // selectSigunguObj.appendChild(opt2);

                // var opt3 = document.createElement('option');
                // var txt3 = document.createTextNode('서귀포시');
                // opt3.appendChild(txt3);
                // selectSigunguObj.appendChild(opt3);

    //-----------SELECT END------------


    //---------keyboard START----------
    //DOM 트리에서 div.keyboard의 input객체찾기
    var inputObj = document.querySelector("div.keyboard>input[type=text]");
    inputObj.addEventListener('click', function(){
        console.log('input객체 클릭되었습니다');
    });

    inputObj.addEventListener('focus', function(){
        console.log('input객체 포커스받았습니다');
        this.style.color = 'blue';
    });

    //keydown -> keypress -> keyup
    inputObj.addEventListener('keyup', function(event){
        alert('입력된키값:' + event.key);
    });

    //----------keyboard END-----------


    //----------submit START-----------
    //전송관련이벤트 : 버튼의 click 이벤트 -> 폼의 submit이벤트 -> 폼의 submit이벤트 기본처리(전송)됨
    //var btSubmitObj = this.document.querySelector('div.submit>form>button');
    var formObj = this.document.querySelector('div.submit>form');
    //var textSubmitObj = this.document.querySelector('div.submit>form>input[name=t]');

    //DOM nav
    var btSubmitObj = formObj.lastElementChild;
    var textSubmitObj = formObj.firstElementChild;

    btSubmitObj.addEventListener('click', function(){
         alert('전송버튼 클릭이벤트가 발생했습니다');
    });

    formObj.addEventListener('submit', function(event){
        alert('폼의 서브밋이벤트가 발생했습니다');
        if(textSubmitObj.value == ''){
            alert('값을 입력하세요')
            event.preventDefault();   //기본이벤트 처리 금지
        }
    });
    
    //----------submit END-----------


    //----------a START-----------
    //이동관련 이벤트 :a객체의 클릭이벤트 -> 클릭이벤트 기본처리(이동) 
    //이벤트 전파 : 이벤트버블링 - 하위객체에서 발생한 이벤트가 상위객체로 전파
    var divAObj = this.document.querySelector("div.a");
    divAObj.addEventListener('click', function(){
        this.style.backgroundColor = 'yellow';
    });

    var aObj = document.querySelector("a");
    aObj.addEventListener('click', function(event){
        this.style.backgroundColor = 'green';
        event.preventDefault();  //기본이벤트 처리 금지
        event.stopPropagation(); //이벤트전파 중지
    });

    //----------a END-----------

});