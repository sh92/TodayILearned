# Javascript

* [javascript는 어떻게 동작하는가?v8엔진](https://engineering.huiseoul.com/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%9E%91%EB%8F%99%ED%95%98%EB%8A%94%EA%B0%80-v8-%EC%97%94%EC%A7%84%EC%9D%98-%EB%82%B4%EB%B6%80-%EC%B5%9C%EC%A0%81%ED%99%94%EB%90%9C-%EC%BD%94%EB%93%9C%EB%A5%BC-%EC%9E%91%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%8B%A4%EC%84%AF-%EA%B0%80%EC%A7%80-%ED%8C%81-6c6f9832c1d9)

* es6
  * [es6 요약정리](http://web-front-end.tistory.com/21)
  * [es6 feature](https://github.com/lukehoban/es6features)
* [closure 정의](https://hyunseob.github.io/2016/08/30/javascript-closure/)

## Basic
### Syntax parser
* a program that reads code 
    * grammer is vaild
    * what it is

### Excution context
* wrapper to help manage the code that is running
* Creation phase
    * global object
    * this
    * outer environment
    * Hoisting = setup memory space for variables(Undefined) and functions 
        ```hoisting
        consol.log(a);
        b(); 
        ->
        var a ="";
        function b(){};
        ```
* undefined
    * if it doesn't exist var, it is not undefined
    * The variable has memory, but never is setupped value.
* excution phase
    * global object
    * this
    * outer environment
    * runs code (line by line)
 
 
## Lexical enviroments
* where you write something

## Function invocation & Execution Stack
```excution stack
function b() {};
function a(){b();};
a();
```
### Excution stack
* push gobal ec  -> push a ec -> push b ec -> pop b ec -> pop a ec -> pop global ec

### Scope chain
* In Execution stack there is not variable in a present ec, find a reference in below ec

```scope chain1
function b(){console.log(mm);};
function a(){
    var mm= 2;
    b();
}

var mm = 1;
a();

result >>
1
```

```scope chain1
function a(){
    function b(){console.log(mm);};
    var mm= 2;
    b();
}

var mm = 1;
a();
b();

result >>
2
b is not defined
```

### Scope
where a variable is available in your code

### const, let
* [let const](https://medium.com/craft-academy/javascript-variables-should-you-use-let-var-or-const-394f7645c88f)

### function
* primitive
* object
* function
* name
* code


### (IIFE)s
* Immediately invoked function expressions (IIFE)S
```IIFE
var greeting = function(name) {
    console.log('Hello '+name);
}();
console.log(greeting('john'));

(function(name) {
    var greeting = "Inside IIFE : Hello";
    console.log(greeting + ' ' + name);
}('john'));

(function(global, name) {
    var greeting = 'Hello';
    global.greeting = 'Hello';
    console.log(greeting + ' ' + name);
}(window, 'John');
console.log(greeting);
```
### closure ex1
```closure ex1
function greet(whattosay) {
    return function(name) {
        console.log(whattosay + ' ' + name);
    }
}
var sayHi = greet('Hi');
sayHi('Tony');
```

### closure ex2
```closure ex2
function builFunctions() {
    var arr = [];
    for ( var i = 0; i < 3; i++ ){
        arr.push(
            function() {
                console.log(i);
            });
    }
    return arr;
}

var fs = buildFunctions();
fs[0]();
fs[1]();
fs[2]();
```

### closure ex3
```closure ex3
function builFunctions2() {
    var arr = [];
    for ( var i = 0; i < 3; i++ ){
        let j = i;
        arr.push(
            function() {
                console.log(j);
            });
    }
    return arr;
}

var fs = buildFunctions2();
fs[0]();
fs[1]();
fs[2]();
```

### closure ex4
``` closure ex4
function builFunctions3() {
    var arr = [];
    for ( var i = 0; i < 3; i++ ){
        arr.push(
            (function(j) {
                return function() {
                    console.log(j);
                }
            }(i));
    }
    return arr;
}

var fs = buildFunctions3();
fs[0]();
fs[1]();
fs[2]();
```

### Function Factory
``` makeGreeting
function makeGreeting(langutage) {
    return function(a) {
        if(language === 'en') {
            console.log( 'Hello'+a);
        }
        if(language === 'es') {
            console.log( 'Halo'+a);
        }
    }
}
var greetEnglish = makeGreeting('en');
var greetSpanish = makeGreeting('es');

greetEnglish('John', 'Doe');
greetSpanish('John', 'Doe');
```

### closure and callbacks
* if function is finished -> call another function 

``` closure and callback
function sayHiLater() {
    var greeting = 'Hi';
    setTimeout(function() {
        console.log(greeting);
    }, 1000);
}
sayHiLater();
```

``` calback ex2
function func1(callback) {
    var a = 100;
    callback();
}

func1(function(){
    console.log('i am done');
});

func1(function(){
    alert('i am done');
});
```

### call, aplly, bind
``` call, apply, bind
var logName = function(lang1, lang2) {
    console.log('Logged: ' + this.getFullname());
    console.log('Arguments: ' + lang1 + ' ' + lang2);
    console.log('----------');
}
var logPersonName = logName.bind(person);
logPersonName('en');
logName.call(person, 'en', 'es');
logName.apply(person, 'en', es');

(function(lang1, lang2) {
    console.log('Logged: ' + this.getFullName());
    console.log('Arguemnts: ' +lang1 + ' ' + lang2);
    console.log('------');
}).apply(person, ['en', 'es']);

var person = {
    firstname = 'John',
    lastname = 'Doe',
    getFullName: function() {
        var fullname = this.firstname + ' ' + this.lastname;
        return fullname;
    }
}

var logName = function(lang1, lang2) {
    console.log('Logged ': + this.getFullName());
}

var logPersonName = logname.bind(person);
logPersonName();
```
### map filter
``` Functional Progmraming
function mapForEach (arr, fn) {
    var newArr = [];
    for(var i=0;i<arr.length;i++){
        newArr.push(
            fn(arr[i]);
        )
    };
    return newArr;
}

var arr1 = [1,2,3];
console.log(arr1);

var arr2 = [];
var arr2 = mapForEach(arr1, function(item) {
    rerturn item*2;
});
console.log(arr2);


var arr3 = mapForeach(arr1, function(item) {
    return item >2;
});
console.log(arr3);

var checkPastLimit = function9limiter, item) {
    return item > limiter;
}

var arr4 = mapForEach(arr1, checkPastLimit.bind(this, 1));
console.log(arr4);

var checkPastLimitSimplified = function(limiter) {
    return function(limiter, item) {
        return item > limiter;
    }.bind(this, limiter);
}

var arr5 = mapForEach(arr1, checkPastLimitSimplified(1));
console.log(arr5);

var arr6 = _.map(arr1, function(item) { return item * 3; };
console.log(arr6);

var arr7 = _.filter([2,3,4,5,6,7], function(item) { return item % 2 === 0; });
console.log(arr7);
```

### use strict
* ['use strict'](https://www.w3schools.com/js/js_strict.asp)



## Building Object

* new
``` new
function Person(firstname, lastname) {
    console.log(this); 
    this.firstname = firstname;
    this.lastname  = lastname; 
    console.log('This function is invoked');
}

Person.prototype.getFullName = function() {
    return this.firstname + ' ' + this.lastname;
}

var john = new Person('A','B');
console.log(john);

var a = {};

result :
Object Person{}
This function is invoked
john Object

console.log(john.getFormalFullName());
```

* Built In Function
``` code
var a = new String('John');
String.prototype.indexOf('o');

var a = new Date('3/1/2015');

// add funtion by using prototype
String.prototype.isLengthGreaterThan = function(limit) {
    return this.length > limit;
}
console.log('John'.isLengthGreaterThan(3));

Number.prototype.isPositive = function() {
    return this > 0;
}
```

* Number
``` new Number
var a = 3;
var b = new Number(3);
a == b
true
a === b
false
```

* Recommended library
```
underscore.js
moment.js
```

* Arrays and for
``` Arrays
Array.prototype.myCustomFeature = 'cool!';

var arr = ['John', 'Jane', 'Jim'];
for ( for prop in arr) {
    console.log(prop + ': ' + arr[prop]);
}

```
### pure prototypal inheritance
``` Person
if Browser has javascript engine? 

if(!Object.create) {
    Object.create = function(o) {
        if(arguments.length > 1 ) {
            throw new Error('Object.crate implementation'
            +  ' only accepts the first parameter. ');
        }
        function  F() {}
        F.prototype = o;
        reutrn new F(){};
    }
}
var person = {
    fisrtname: 'Default',
    lastname 'Default',
    greet: funciton() {
        return 'Hi', + firstname;
    }
}

var john = Object.create(person);
john.firstname = 'John';
john.lastname = 'Doe';
console.log(john);
```
### class and extends
```
class uglyPerson extends Person {
    constructor(firstname, lastname) {
        super(firstname, lastname);
    }
    greet(){
        return 'Yo ' + firstname;
    }
}
```

## OOP and Prototype inheritance

* prototype
```  code
var person = {
    firstname: 'Default',
    lastname: 'Default',
    getFullName: function() {
        return this.firstname + ' ' + this.lastname;
    }
}

var john = {
    fristname: 'John',
    lastname: 'Doe'
}

john.__proto__ = person;
console.log(john.getFullName());
console.log(john.firstname);

var jane = {
    firstname: 'Jane'
}
jane.__proto__ = person;
console.log(jane.getFullname());
```

* Everything is an object
``` object
var a = {}
function b(){}
var c = []
b.__proto__.__proto__ -> Object{}
```

* reflection and extend
``` reflection and extend
var person = {
    firstname: 'Default',
    lastname: 'Default',
    getFullName: function() {
        return this.firstname + ' ' + this.lastname;
    }
}
var john = {
    firstname: 'john',
    lastname: 'Doe'
}

john.__proto__ = person;

for(var prop in john) {
    if (john.hasOwnProperty('firstname')) {
        console.log(prop + ': ' + john[prop]);
    }
}

var jane ={
    address ' 111 Main St. ',
    getFormalFullaName = function() {
        return this.lastname + ', ' + this.firstname;
    }
}

var jim = {
    getFirstName : function() { 
        return firstname;
    }
}

_.extend(john, jane, jim);
console.log(john);
```

### Initialization

``` initialization
var people = [
    //john object
    {
    },
    //jane
    {
    }
    ,
    greet : function() {
        return 'Hello';
    }
}
people[1].greet();
```


## typeof instance of

``` code
function Person(name) {
    this.name = name;
}

var e  = new Person('Jane');
console.log(typeof e);
console.log(e instance of Person);

console.log(typeof undefined);
console.log(typeof null);
```

# Reference 
[Udemy Javascript:Understand wierd part](https://www.udemy.com/understand-javascript/learn/v4/content)
