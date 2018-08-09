# Operating System

* OS?  
application의 실행을 컨트롤하는 소프트 웨어 harware와 application간의 인터페이스

## Process
* 프로세스?  
실행하고 있는 프로그램 [More](http://bowbowbow.tistory.com/16) 
  * 프로세스는 코드, 데이터, 스택, 메모리 registers context(PC, register etc)로 구성

* PCB?  
프로세스의 context를 저장하고 프로세스 스케쥴링을 위한 데이터 구조 단위

* Context Switch?
[More](http://jeong-pro.tistory.com/93)

when? 

* 프로세스상태
[More](https://www.tutorialspoint.com/operating_system/os_processes.htm)

* UniProgramming vs MultiProgramming  
UniProgramming은 하나의 프로그램을 사용하고MultiProgramming은 여러 프로그램을 하나의 CPU가 time quantum을 두고 사용한다.
  
* MultiProgramming vs Timesharing

* The reason for suspension of process

* The reason for termination of process

* The process of creating process

* The function of OS

* IPC?
프로세스간 통신방법

* Shared Memory vs Message Passing

## Thread

* Thread?
프로세스 안에서 실행하는 단위
  * Execution state : (running, ready)

* Thread vs Process

* MultiThreading?  
  * 프로세스는 한개 또는 이상의 쓰레드들의 집합이다.
  * PCB, User address space, 여러 Threads로 구성
  * 하나의Thread는 User, Kernel stack으로 구성

* Multi Process vs MultiThreading  
  * 멀티프로세스는 Input Handling Process > (transforming process) > Output Handling Process IPC로 통신
  * 멀티스레드는 IPC대신 global var로 통신
    * 장점 : 프로세스간 통신보다 한 프로세스내에서 통신방식이 덜 시간이 걸린다.

* 멀티 프로세싱, 멀티 프로그래밍, 멀티 테스킹,멀티 쓰레딩 차이[More](http://proneer.tistory.com/entry/%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EC%84%B8%EC%8B%B1-%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EB%A9%80%ED%8B%B0%ED%83%9C%EC%8A%A4%ED%82%B9-%EB%A9%80%ED%8B%B0%EC%8A%A4%EB%A0%88%EB%93%9C%EC%97%90-%EA%B4%80%ED%95%98%EC%97%AC)

* UserLevel Thread vs Kernel Level Thread
  * User Level
    * 장점 : 1. 커널 레벨보다 시간이 더 적게 걸린다. 2.user 맘대로 할 수 있다.
    * 단점 : 한개 block하면 다 block
  * Kernel Level
    * 장점 :CPU 여러개를 관리. 멀티 쓰레드가 하는 방식. 한개 죽어도 괜찮음. 프로세스안 kernel 공간에 여러 context를 관리
	* 단점 : User Level Thread의 장점 반대로

* Micro Kernel and Monolitic Kernel

## I/O Management and Disk Scheduling

## File Management

## Concurrency(Mutual Exclusion and SynChronization)

* Concurrency?

* Key Term
  * Race condition
    * 여러 프로세스들이나 멀티 쓰레드가 공유 데이터를 읽거나 쓸때 상대적인 타이밍에 따라 결과가 의존 될때, 즉 누가 먼저 쓰느냐 
  * Mutual Exclusion
    * 한 프로세스가 공유된 자원에 접근할 때 다른 프로세스가 접근하지 못하도록 하는 요구  
  * Critical section 
    * 공유자원에 접근하기 위해 요구하는 프로세스내의 코드 그리고 다른 프로세스가 접근하는 동안 해당 부분의 코드를 접근 할 수 없다. 그 코드 부분을 크리티컬 섹션이라 부른다 
  * Starvation 
    * 스케쥴러에의해 간과된 실행가능한 프로세스 그것이 진행되지 않으면 절대 선택되지 않을 것이다.
  * Deadlock 
    * 한개 또는 여러 프로세스들은 진행할 수 없다. 왜냐하면 각각은 한개가 어떤것을 하도록 기다리기 때문이다.

* concurrent vs parallel programming
[More](https://www.slideshare.net/TausunAkhtary/concurrent-parallel-programming)
[More2](https://takuti.me/note/parallel-vs-concurrent/)

* Critical Section  
각 프로세스에서 공유 데이터를 접근하는 코드 부분

* Mutex  
Critical Section에 쓰레드들이 Runtime에 동시에 실행되지 않도록 하는 기술. Locking, Unlocking을 사용한다.  
  * TAS(test and Set) Hardware support  
     ```{TAS}
	boolean testset(&i) {  
		if(i==0) {  
			i=1;  
			return true;  
		} else {  
			return false 
		}
	}
	int n = # of process;  
	int bolt;  
	void P(int i) {  
		while(true) {  
			while(!testset(bolt)); //do nothing;  
			/*cs*/  
			bolt=0;  
			/*remainder*/  
		}  
	}
	void main() {  
		    bolt = 0;  
			parbegin(P(1), P(2),...P(N));  
	}
	 ```

* Semaphore  
여러 프로세스에서 리소스를 경쟁할때 동기화시키는 기술. 세마포어를 사용하는 프로세스가 그 자원을 확인 하는 동안 다른 세마포어 사용자들이 기다리도록 해야함
semWait(s) operation decrease the s value
semSignal(s) operation increases the s value
```{semaphore structure}
struct semaphore {  
   int  count; 
   queryType queue; 
}  

void semWait(semaphore s) { 
   s.count--;  
   if(s.count<0) {  
        place this process in s.queue;  
	    block this process;  
	} 
}  

void semSignal (semaphore s) {  
    s.count++;  
	if(s.count<=0) {  
	    remove a process p from s.queue;  
		place  process p on read list; }  
	}
}
```


* Mutex vs Semaphore  
[More](http://jwprogramming.tistory.com/13)  
Mutex는 공유된 자원의 데이터를 여러 쓰레드가 접근하는 것을 막는 것  
Semaphore는 공유된 자원의 데이터를 여러 프로스세가가 접근하는 것을 막는 것  
가장 큰 차이점은 관리하는 동기화 대상의 갯수. Mutex는 동기화 대상이 오직 하나, Semaphore는 동기화 대상이 하나 이상  


## Concurrency(Deadlock and Starvation)

* Deadlock 4 conditions
  * Mutual Exclusion
  * Hold and wait
  * No preemtion
  * Circular Wait

* Deadlock Avoidence
  * Banker's Algorithms
    * 요구된 최대한의 요구사항이 미리 언급 된다. 고정된 숫자의 리소스가 할당되어야한다. 현재 할당 상태에 대한 정보를 가진다
	* 단점: 일반적으로 얼마나 쓸지 알 수없다. 시간적 비용 큼

* After Deadlock Detection
  * Abort all deadlocked processes

* Dining Philosopher Problem Solution
People : 5 Fork : 4
```{Solve}
semaphore fork[5] ={1}; 
int i;
semaphore room = {4};
void philosophre(int i ) {
  whie(true){ 
     think; 
     wait(room);  
	 wait(fork[i]);  
	 wait(fork[(i+1) mod 4]);  
	 eat();
	 signal(fork[(i+1) mod 5 ]);  
	 signal(fork[i]); 
	 signal(room); 
  } 
}
```

## Memory Mangaement

## ETC

* [CPU캐시메모리의 역할](http://it.donga.com/215/)

