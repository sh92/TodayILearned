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
[Picture](https://image.slidesharecdn.com/03-processes-131029135641-phpapp02/95/processes-control-block-operating-system-26-638.jpg?cb=1383055102)

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
  * Shared Memory
    * 장점: Context Switch 필요 없이 공유된 데이터를 사용하면 되므로 빠름
	* 단점: 개발자 입장에서 구현 어려움
  * Message Passing
    * 구현은 쉽지만, kernel을 들어갔다 나와야 하므로 시간 오래걸림

## Thread

* Thread?
프로세스 안에서 실행하는 단위
  * Execution state : (running, ready)

* [Thread Safe?](http://gompangs.tistory.com/7)

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

### I/O 관리
* 전송단위 : 디스크 안의 큰 블락이나 bytes of stream 
* I/O Management 방식 : 1.Programmed I/O 2. Interrupt-driven I/O 3. Direct Memory Acces(DMA)
  * Programmed I/O
    * operation들이 완료 될때 까지 busy wating을 한다. polling 방법을 이용 - I/O가 빨리 끝날 수록 더 효율적: context switch 시간 더 걸림
	* system call일 들어오면 커널 공간에 있는 kernel subsystem에서 디바이스와 디바이스 드라이버 인터페이스를 통해 통신하고 controller device에서 controller register의 bit를 setting 한다.
    * polling
      * busy-wating cycle은 I/O 를 기다림 -  busy bit가 clear 될때까지 반복적으로 기다림, Host가 write-bit를 설정하면 data-out register로 byte를 쓰게 된다. 
	  * Host는 command-ready bit를 설정하고, busy bit를 설정하게 된다. controller란 controller register를 읽고 data-out register를 읽어 I/o를 한다. I/O가 완료되면 command-ready bit와 busy bit를 지운다.
  * Interrupted-driven I/O
    * Procedure
	  * User Process는 시스템 콜을 사용
	  * 커널은 device driver에게 device를 시작하라고 함
	  * IRQ는 ISQ(Interrupt Service routine)이 실행되도록 함
	  * 커널은 block에서 ready 상태로 변화하도록 만듬
	* ISR
	  * I/O 를 완료하도록 어떤 일을 함. 디바이스 버퍼에서 메인 메모리로 데이터를 전송
  * DMA
    * CPU를 거치지 않고 직접 메모리로 가서I/O를 관리
	* 프로세스(CPU)가 DMA module에 권한을 위임하여 DMA modile이 I/O를 관리
	* Interrupt방식 사용

* 커널 I/O subsystem
  * Role
    * 디바이스 대기큐는 디바이스에 접근하도록 지원하며 시스템 콜은 자원을 allocation과 deallocation을 할 수 있도록 한다. 그리고 데드락을 주시한다.
	* 스케줄링은 IO 장치에서 누구를 먼저 IO를 할것인가를 결정한다. 보통 먼저 들어온 순서대로
    * 에러 헨들링 - 디스크에서 읽기, 쓰기로 부터 회복할 수 있다. 에러코드를 리턴한다.
	* 시스템 에러는 문제 리포트를 로깅한다.
	* 버퍼링 - 커널 I/O는 디바이스 간의 메모리를 저장할 수 있도록 버퍼를 다룬다.
	  * 디바이스간 속도 차이 조절
	  * 데이터 전송량 크기 조절
	* 캐싱
	  * fast memory holding copy of data 
	* 스풀링
	  * hold output for a device
	  * 한번에 한개의 요청만 전달할 수 있다.
	  * ex) 프린팅
* Kernel 진입방법
  1. 시스템 콜
  2. 인터럽트
  3. Trap

### Disk Scheduling
* device driver는 1차원 배열의 로지컬 블락(주소단위)으로 주소화 된다. 바깥 실린더의  첫번째 트렉의 첫번째 섹터가 sector0로 시작- 바깥에서 안쪽으로
* 총 시간 : wait for device + watif for channel + (seek+ rotational delay + data transfer)(=device busy) 
* 방식
  * FIFO - 프로세스는 순차적으로 요청을 진행
  * SSFT - (seek time이 짧은 순서 대로 요청을 처리)
    * 우선순위가 늦은 애들은 더 도달하기 어려움 starvation 문제 발생
  * SCAN - 엘리베이터 알고리즘으로 끝에서 끝으로 이동하는 방법
* RAID 방식
  * RAID - 0(non-reduendant) : 비쌈, 남은 디스크는 parity 정보를 저장하기 위해 사용됨
  * RAID - 1 : 미러방식 가격 2배, 신뢰성 높아짐 
  * RAID - 2 : 해밍코드를 이용한 방식 (1bit씩 분산해서 페리티 비트를 저장), 디스크 많이 필요 계산 많이 하고 잘 안씀
  * RAID - 3 : (bit-interleaved parity) : 같은 위치에 흩어놓아서 패리티 비트 적용
  * RAID - 4 : 블락레벨의 페리티, 한 디스크에 페리티 정보를 같은 위치상에 존재하는 블락들의 패리티를 적용. 각각 독립적으로 적용되어 조합되어 기다릴 필요가 없어 성능이 높다.하지만 패리티 디스크가 죽고 다른 디스크가 죽으면 복구 불가
  * RAID - 5 : 블락레벨의 분산 패리티, level4와 동일하게 각블락의 레벨에 패리티가 적용되지만 여러 디스크에 분산해서 저장
  * RAID - 6 : RAID5와 동일한 방식이지만, 패리티를 2개를 씀
  * 0,1,5를 잘씀
  * Combination : RAID01 < RAID10
* 디스크 캐쉬(버퍼캐쉬)
  * disk sector의 메인메모리의 버퍼, 디스크의 섹터에 어떤 복사본을 포함한다.
  * replacemenet 정책 - LRU
    * block이 캐쉬로 보내어지거나 참조 될때 스택의 탑이 대체된다.
	* 캐쉬는 블락들로 구성되어 있다.
	* 각각의 블락마다 카운터가 있다. 카운터는 매시간 접근한 블락에게 증가된다.
	* 블락들은 짧은 기간동안 접근 된다.

## File Management
* File
  * 비슷한 레코드들의 집합
  * Create, Delete, Open, Close, Read, Write 
* File System structure
  * boot block
    * BIOS가 실행하여 boot loader를 실행
  * super block
    * 파일 시스템의 헤더 정보
  * FCB list
  * Data blocks
* 파일 디렉토리 정보 != FCB정보
  * 파일 디렉토리 정보
    * 파일 이름, 파일 타입, 용량 할당 정도, 권한 
* Inode
  * inode : 한 파일이나 디렉토리의 모든 정보를 갖고 있는 64byte로 구성된 표
  * i-list : 한 파일 시스템에서 파일이나 디렉토리들의 inode를 갖고 있는 표
  * inumber : inode가 i-list에 등록되는 entry-number
    * inode number를 보고 해당 inode의 정보를 가리킴
  * inode number of root directory : 1

* dangling pointer
  * 아이노드 정보가 이미 지워진 허공을 가리키는 포인터
  * 솔루션
    1. 삭제되거나 지워진 파일시스템을 검색한다 x
	2. 유저가 알아서 함- window
	3. Back pointers.  x
	4. Entry-hold-count solution -Linux/Unix

* 파일 공유 두가지 이슈
  1. 접근권한(owner, group, public access)
  2. 동시 접근 문제

* 동시 접근 문제
  * updated 될때 file, record lock
  * 공유 접근시 Mutual exclution 과 데드락 이슈 발생

* Secondary storage management
  * File allocation table을 사용
    * Contiguous allocation
	  * It must keep track of space available for allocation
	  * 생성된 시간에 한 블락이 할당된다. Multil block 의 빠른 디스크 접근, 싱글집합에 할당됨
	  * 단점: 파일 증가가 어려움, 외부 단편화 발생
	* Chained allocation
	  * 파일 할당 방법 개인 블락에 기초하여 할당, Linked List로 할당
	  * 장점 -  파일 증가 문제 없음, 파일의 크기를 쉽게 조절해 나감, 외부 단편화 안일어남 파일 할당 안에 single entry 존재, 지역성의 원리의 acommodation 존재
	  * 단점 - poor data safety, 마지막 블록을 알기 위해 순차적으로 접근해야함
	* Indexed allocation
	  * File allocation table은 각 파일에 대해 한 레벨의 인덱스로만 존재한다. index는 각 포인터에 대해 한 one entry 를 가진다.
	  * 장점 : 직접 access, 외부단편화 없음, 파일 증가 문제 없음
	  * 단점 : poor data safety

* Free space Management
  * startblock number, # of free blocks
  * Bit vector(map)를 사용

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

* 멀티 프로세스에게 수용된 메모리를 나누는 것, ready process에게 프로세스시간을 소비하도록 합리적으로 할당해주는 것

* Memory Protection은 권한 없이 참조 못함
  * 컴파일 타임에 참조할 수 없고, 런타임에 참조함
  * memory protection은 프로세서에 의해 참조되어야한다.

* Relocation
  * 실행시간 동안 프로세스가 다른 메모리 위치를 차지할 수 있다.
    * Swapping
	* Compaction
  * 그 프로그램이 실행하는 동안, 디스크에 swap되고 바뀐 위치를 메인메모리에 반환한다.
  * 메모리 참조는 코드 안에서 실제 pysical memory 주소로 변환되어야한다.

* 메인 메모리 파티셔닝
  * Fixed Partitioning (equal size, Unequal size aptitioning)
    * Segmentation
	* Patition이 꽉차면 시스템은 process를 swap out한다.
	* 내부 단편화가 발생할 수 있다. -> 메모리 사용의 비효율성
	* 장 관리 쉽다 단 메모리 비효율적 관리
  * Dynamic partitioninig
    * Paging - 외부 단편화가 발생 - compaction -> relocation필요
    * first-fit
	* best-fit
	* worst-fit
	* next-fit

* 메모리 종류
  * 물리적 메모리
  * 논리적 메모리
  * 상대주소
  * 가상 메모리(main memory + memory on disk)

* Address binding
  * Compile Time
  * Load Time
  * Execution Time (요즘 쓰는 타입의 바인딩)

* Hardware for Execution Time Binding
  * Base register
    * Starting address for the process
  * Bounds register(limit register)
    * Endling location of the process

* Paging
  * The partition memory into small-size equal fixed chunks and divide each process into the same size chunks
  * frame size = page size = disk block size
  * Internal fragmentation

* Page Table
  * OS maintains a page table for each process
  * Table index, Content of the table
  * Logical Address = page number + offset withing the page

* Segmentation
  * All segments of all programs do not have to be of the same length
    * There is a maximum segment length
	* Simmilar to dynamic partitioning
  * Address = a segment number + an offset

### Virtual Memory

* Memory references ar dydnamically translated in pyhsical addresses at runtime
  * relocation
* Page or segment - Do not need to located contiguously in main memory
* 실행시간 동안 Main memory에 로드될 필요가 없는 모든 프로세스

* 장점
  * 프로세스의 크기가 메인메모리 보다 크다.
  * Ready state가 있는 더 많은 프로세스가 메인메모리에 유지될지 모른다.

* Execution of a Program
 * OS brings into main memory a few pieces of the program
 * Resident set : a portion of process that is in main memory

* Types of Memory
  * Real Memory
  * Virtual Memory
  * OS must be able to manage the movement of pages and/or segments between secondary memory and main memory

* 2 methods for virtual Memory
  * By paging -> demand paging : The pages needed to access are brought into main memory
  * By segmentation -> deand segmentation : The logical address into pysical memory

* Page table is used to map(translate) the logical(virtual) address into physical memory
* Virtual(logical) address used in processes
* Real(physical, absolute) address in main memory

* Page Table Entry
  * Each page table entry contains the frame number of the corresponding page in main memory.
  * The entry also contains control information
* Virtual Address
  * Page Number(20 bits) + Offset(12bits)
* Page Table Entry
  * V: valid bit(1bit), page frame number(20bit), D: Dirty bit(1bit), U: User mode (if set, this page belongs to user process), W: writable (if set, this page is writable), COW : if set, this page is Copy-On-Write mode page
* Size of Page tables
  * Multi-level pages table
  * Inverted pages table

* Translation Lookaside Buffer
  * Each virtual memory reference can cause two or three pysical memory accesses -> time consuming
    * solution high-speed cache in the CPU
	* TLB - associative memory
* Page replacement Algorithms
  * FIFO
  * Optimal policy
  * LRU
  * Clock Policy(second chance alogirhtms)
* Resident Set Size - In main memory
  * Fixed-allocation
  * Variable-allocation
    * Global Scope - Recently

* Thrashing
  * Degree of multiprogramming : number of processes in memory
  * When swapping out a pice of a process just before that piece is needed, page fault occurs frequently.
  * if a process doesnot have "enough" pages, the page-fault rate is very high.
  * Thrasing : the processor spends most of its time swapping pieces (disk I/O) rather than executing user instructions

* Process suspension
  * Curent resident processes must be swapped out to reduce Thrashing

## UniProcessor Scheduling

* Long-term scheduling
* Medium-term scheduling
* Short-term Shceduling
* I/O schduling

* When to invoke scheduler?
  * When a process enters to ready state ( switches from wait state, or arrival of new porcess) - preemtive
  * Switches from running to ready state - preemtive
  * Switches from running to block(wait) state - nonpreemptive
  * Termintates - nonpreemptive

* Scheduling Criteria
  * CPU utilization
  * Throughput
  * Turnaround time
  * Response time
  * Wating time

* Scheduling Way (Priority - Priority Queue)
  * FCFC, FIFO
  * Round-Robin
  * Shortest Process Next
  * Shortest Remaining Time First
