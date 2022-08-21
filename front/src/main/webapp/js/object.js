var customer = {
    id: 'id1',
    name: '강민주',
    info: function(){
      console.log(this.id, this.name); //자바스크립트는 this 생략불가
    }
};

customer.id = 'id9';
customer.info();

var customerArr = [];
customerArr[0] = customer;

customerArr.push({
  id: 'id1',
  name: '오문정'
},{
  id: 'id2',
  name: '강민주'
},{
  id: 'id3',
  name: '홍길동'
}
);

console.log(customerArr);
console.log(customerArr[2]);
