# Operating System

* OS?  
application의 실행을 컨트롤하는 소프트 웨어 harware와 application간의 인터페이스

## Process
* 프로세스?  
실행하고 있는 프로그램

* PCB?  
프로세스의 context를 저장하고 프로세스 스케쥴링을 위한 데이터 구조 단위

* Context Switch?
[Link](http://jeong-pro.tistory.com/93)

when? 

* 프로세스상태
[Link](https://www.tutorialspoint.com/operating_system/os_processes.htm)

* UniProgramming vs MultiProgramming  
UniProgramming은 하나의 프로그램을 사용하고MultiProgramming은 여러 프로그램을 하나의 CPU가 time quantum을 두고 사용한다.

* MultiProgramming vs Timesharing

* The reason for suspension of process

* The reason for termination of process

* The process of creating process

* The function of OS

* IPC?

* Shared Memory vs Message Passing

## Thread

* Thread?

* Thread vs Process

* MultiThreading?

* 멀티 프로세싱, 멀티 프로그래밍, 멀티 테스킹,멀티 쓰레딩 차이[Link](http://proneer.tistory.com/entry/%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EC%84%B8%EC%8B%B1-%EB%A9%80%ED%8B%B0%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D-%EB%A9%80%ED%8B%B0%ED%83%9C%EC%8A%A4%ED%82%B9-%EB%A9%80%ED%8B%B0%EC%8A%A4%EB%A0%88%EB%93%9C%EC%97%90-%EA%B4%80%ED%95%98%EC%97%AC)

* User Level Thread vs Kernel Level Thread

* Micro Kernel and Monolitic Kernel

## I/O Management and Disk Scheduling

## File Management

## Concurrency(Mutual Exclusion and SynChronization)

* Concurrency?

* concurrent vs parallel programming
[Link](https://www.slideshare.net/TausunAkhtary/concurrent-parallel-programming)
[Link2](https://takuti.me/note/parallel-vs-concurrent/)

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
			    while(!testset(bolt)) //do nothing;  
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
[Link](http://jwprogramming.tistory.com/13)  
Mutex는 공유된 자원의 데이터를 여러 쓰레드가 접근하는 것을 막는 것 
Semaphore는 공유된 자원의 데이터를 여러 프로스세가가 접근하는 것을 막는 것 
가장 큰 차이점은 관리하는 동기화 대상의 갯수. Mutex는 동기화 대상이 오직 하나, Semaphore는 동기화 대상이 하나 이상 


## Concurrency(Deadlock and Starvation)

## Memory Mangaement

