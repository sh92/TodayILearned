# Programming Language

* [Design Pattern](https://www.journaldev.com/1827/java-design-patterns-example-tutorial#)
  * Creational Design Pattern
    * Solution to initiate a object in best possible way.
  * Strucutral Design Pattern
    * Different ways to create class structure.
  * Behavior Design Pattern
    * Solution for the better interaction between objects.
	* Loose coupling and flexibility to extend easily.
* [정적언어 vs 동적언어](http://itmining.tistory.com/65)
* [TDD vs BDD](https://codeutopia.net/blog/2015/03/01/unit-testing-tdd-and-bdd/)
* [Callback이란](https://openwiki.kr/tech/callback)
* [리액티브 프로그래밍이란](http://sculove.github.io/blog/2016/06/22/Reactive-Programming/)
  * [rxJava](https://academy.realm.io/kr/posts/mobilization-hugo-visser-rxjava-for-rest-of-us/)
* [함수형프로그래밍이란](https://medium.com/@jooyunghan/%ED%95%A8%EC%88%98%ED%98%95-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EC%86%8C%EA%B0%9C-5998a3d66377)
  * Immutable
  * First-class-function
  * Concurrent Programming에 유리함
  * First-citizen
  * Reactive Programming

## Java

* [Java9의 변화와 특징](https://medium.com/@goinhacker/java-9%EC%9D%98-%EB%B3%80%ED%99%94%EC%99%80-%ED%8A%B9%EC%A7%95-%EB%8C%80%EC%B6%A9-%EC%A0%95%EB%A6%AC-fca77cee88f2)

* [OOP](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/Development_common_sense)
  * [캡슐화의 장점](http://slowlywalk1993.tistory.com/entry/Java-%EC%9E%90%EB%B0%94%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%EA%B0%9C%EB%85%90-%EC%BA%A1%EC%8A%90%ED%99%94-%EC%A0%95%EB%B3%B4%EC%9D%80%EB%8B%89)

* [Java 컴파일 과정](http://moomini.tistory.com/13)
  * [JVM](http://asfirstalways.tistory.com/158)

* [GC](http://asfirstalways.tistory.com/159)

* [Reflection](http://gyrfalcon.tistory.com/entry/Java-Reflection)  
객체를 통해 클래스의 정보를 분석해 내는 프로그램 기법

* [Override vs Overload](http://hyeonstorage.tistory.com/185)

* [Serialization](http://woowabros.github.io/experience/2017/10/17/java-serialize.html)

* [RMI란](http://0yumin.tistory.com/16)

* [volatile](https://nesoy.github.io/articles/2018-06/Java-volatile)

* [Asynchronous vs Non-blocking vs Blocking](https://stackoverflow.com/questions/7931537/whats-the-difference-between-asynchronous-non-blocking-event-base-architectu/9489547#9489547)

* sleep vs wait
  * [sleep() vs wait() vs yield](https://stackoverflow.com/questions/1036754/difference-between-wait-and-sleep)
  * [Using wait/notify vs Thread.sleep() in Java](http://www.qat.com/using-waitnotify-instead-thread-sleep-java/)
  * [Difference between sleep and wait](https://stackoverflow.com/questions/1036754/difference-between-wait-and-sleep)

* [StringBuffer vs StringBuilder](https://itblackbelt.wordpress.com/2015/01/31/difference-between-string-stringbuilder-and-stringbuffer-classes-with-example-java/)

## Python

* [Generator란](http://bluese05.tistory.com/56)
  * [yield](https://code.i-harness.com/ko/q/38957)

* [GC](https://winterj.me/python-gc/)

* [GIL](https://github.com/JaeYeopHan/Interview_Question_for_Beginner/tree/master/Python)
  * [GIL](https://medium.com/@mjhans83/python-gil-f940eac0bef9)
  * [More](https://blog.seulgi.kim/2015/01/global-interpreter-lock.html)
  * [More2](https://code.i-harness.com/ko/q/13c02e)
  * [More3](https://medium.com/@mjhans83/python-gil-f940eac0bef9)
* [Gevent](http://software-engineer.gatsbylee.com/gevent/)
  * [Gevent](http://khanrc.tistory.com/entry/%EC%A0%9C%EC%95%BD%EC%9D%84-%EB%84%98%EC%96%B4-Gevent)
* [python에서 메모리 누수가 발생하는 경우](https://memorable.link/link/189)
* [tracemalloc 메모리 누수 파악](http://brownbears.tistory.com/249)

* [다양한 파이썬 Pypy, Cpython](http://khanrc.tistory.com/entry/%EB%8B%A4%EC%96%91%ED%95%9C-Python%EB%93%A4)

* Asycio and aiohttp
  * [asyncIO란](https://tech.ssut.me/2015/07/09/python-3-play-with-asyncio/)
  * [Making 1 million requests with python-aiohttp](https://pawelmhm.github.io/asyncio/python/aiohttp/2016/04/22/asyncio-aiohttp.html)
  * [aiohttp를 이용해 서버를 만들어보자](http://meonggae.blogspot.com/2016/11/python-aiohttp.html)
  * [A Web Crawler With asyncio Coroutines](http://www.aosabook.org/en/500L/a-web-crawler-with-asyncio-coroutines.html)

* [Concurrent.futures](https://soooprmx.com/archives/5669)

* [@staticmethod vs @classmethod](https://code.i-harness.com/ko/q/213a1)

* [__str__ vs __repr__](https://code.i-harness.com/ko/q/15ec1f)

* [MetaClass](https://tech.ssut.me/2017/03/24/understanding-python-metaclasses/)
  * [MetaClass](https://code.i-harness.com/ko/q/186a3)
* [Monkey Patching](https://filippo.io/instance-monkey-patching-in-python/)
  * [Monkey Patching이란](https://code.i-harness.com/ko/q/55d951)
* [decorator](https://blog.jonnung.com/python/2015/08/17/python-decorator/)

* Built-in function
```
__dir__([obj]) : get the list of attributes in module [obj]
```

## Javascript

* [javascript는 어떻게 동작하는가?v8엔진](https://engineering.huiseoul.com/%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8%EB%8A%94-%EC%96%B4%EB%96%BB%EA%B2%8C-%EC%9E%91%EB%8F%99%ED%95%98%EB%8A%94%EA%B0%80-v8-%EC%97%94%EC%A7%84%EC%9D%98-%EB%82%B4%EB%B6%80-%EC%B5%9C%EC%A0%81%ED%99%94%EB%90%9C-%EC%BD%94%EB%93%9C%EB%A5%BC-%EC%9E%91%EC%84%B1%EC%9D%84-%EC%9C%84%ED%95%9C-%EB%8B%A4%EC%84%AF-%EA%B0%80%EC%A7%80-%ED%8C%81-6c6f9832c1d9)

* Prototype?

* es6
  * [es6 요약정리](http://web-front-end.tistory.com/21)
  * var vs let vs const
  * arrow
  * foreach

* [closure 정의](https://hyunseob.github.io/2016/08/30/javascript-closure/)


## Scala

* [Scala 정리 사이트](https://github.com/funfunStudy/study/wiki/Programming-in-scala-%EC%A0%95%EB%A6%AC)
* [Scala 클로저](http://yujuwon.tistory.com/entry/Scala-%ED%95%A8%EC%88%98%EC%99%80-%ED%81%B4%EB%A1%9C%EC%A0%80)
* [Call by value vs Call by name](https://medium.com/@OutOfBedlam/scala-call-by-value-vs-call-by-name-734a79c75ccb)
