# Network

* [OSI7 Layer](http://yellowh.tistory.com/19)
  * Application
    * packet = message
  * Transport
    * packet = segment
  * Network
    * packet = datagrams
  * Link
    * packet = frames
  * Pyhsical

### TCP vs UDP
* UDP
  * Checksum
    * provide for error detection
  * DNS 53, DHCP 67/68
  * two-tuple(destination ip, destination port)
* TCP
  * [Flow contorl and Congestion Control](http://jwprogramming.tistory.com/36)
  * 3-way HandShaking vs 4-way Handshaking
  * FTP 20/21, SSH 22, Telnet 23, SMTP 25, SSL/TLS HTTPS 443, HTTP 80 
  * IGMP, BGP/OSPF

* The Process of data transmission in TCP/IP
  *  [More](http://ddooooki.tistory.com/15)
  * MTU

* [Network Performence Measure](https://en.wikipedia.org/wiki/Network_performance)
  * Bandwidth
  * Throughput
  * Latency
  * Jitter
  * Error rate

* [Latency vs Delay vs Jitter](http://sensechef.com/1156)

* RTT? Throughput? Latency? BandWidth?
  * [Measure TCP Max Throughput](http://nenunena.tistory.com/149)
  * [Calculate TCP Throughput](https://m.blog.naver.com/PostView.nhn?blogId=goduck2&logNo=220076011565&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)
  * 4 delays in Network
    * Processing delay
    * Serialization delay
    * Buffering delay
    * Propagation delay
  * [Flow control and Throughput](https://m.blog.naver.com/PostView.nhn?blogId=parkjy76&logNo=220885707787&proxyReferer=https%3A%2F%2Fwww.google.co.kr%2F)
  * [TCP Throuput Cacluator](http://www.switch.ch/network/tools/tcp_throughput/)


### IP
* IPv4 vs IPv6
* IPv4 A,B,C,D class <-> CIDR/VLSM
  * [More](http://ddooooki.tistory.com/18)
  * CIDR : Supernetting, ex) BGP
  * VLSM : Subnetting, ex) RIP, RIP, OSPF, IS-IS, EIGRP(Extended IGRP), BGP-4 etc
* Public, Private IP
  * NAT
* IP Header
  * [More](http://ddooooki.tistory.com/17)
  * Best-effort delivery service
  * unreliable service
  * [transport-layer multiplexing and demultiplexing](http://blog.naver.com/PostView.nhn?blogId=gkenq&logNo=220693036639)

### HTTP
* [HTTP1.0 vs 1.1 vs 2.0](http://americanopeople.tistory.com/115)
  * HTTP 2.0
    * [SPDY](https://libosong.appspot.com/spdy/index.html#1)
  * [HTTP vs HTTPS](http://jeong-pro.tistory.com/89)
    * SSL/TLS
  * [Keep-Alive](http://hamait.tistory.com/341)
  * [웹 개발자가 http완벽가이드를 읽는법](https://blog.npcode.com/2015/06/07/%EC%9B%B9-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EB%A5%BC-%EC%9C%84%ED%95%9C-http-%EC%99%84%EB%B2%BD-%EA%B0%80%EC%9D%B4%EB%93%9C-%EC%9D%BD%EB%8A%94-%EB%B2%95/)

### The Network Core

* Packet Switching
  * store-and-forward transmission
  * Delay, Loss, Throughput
  * Type of Delay
    * Processing Delay
	  * the time required to examine the packet's header and determine where to direct the packet + the time to check for bit-level errors

	* Queuing Delay
	  * At this queue, the number of packets arriving packet might expect to find is a function of the intensity and nature of arriving at the queue.

	* Transmission Delay
	  * Denote the length of the packet by L bits, and denote the transmission rate of the link from router A to router B by R bits/sec. 
	  * the amount of time required for the router to push out the packet.

	* Propagation Delay
	  * The time required to propagate from the beginning of the link to router B is the propagation delay
	  *  the time it takes a bit to propagate from one router to the next router

	* the total nodal delay single router = d\(proc\) + d\(queue\) + d\(trans\) + d\(prop\) 
	* End to End delay = N\(d\(proc\) + d\(trans\) +d\(prop\)\), d\(trans\) = L/R, L=the packet size, R = tansmission rate\(bits/sec\)

* Circuit Switching
  * The end systems are reserved for duration of the communication session between end system
  * ex) Traditional telephone network
  * Multiplexing
    * FDM
	* TDM - slot, frame 

### Application Layer

* Network Application Architectures
  * Client-Server
    * Server
	  * Fixed well known address
	  * Data center, housing a large number of hosts
  * Peer-to-peer
	* The applications include file sharing

* The interface between the process and the computer network
  * socket : the interface
  *  Host or server \( Process - TCP with buffers, variable\) < - Internet - > 왼쪽 역순

* Reliable Data Transfer
  * Loss-tolerant application : Transport protocol doesn't provide reliable data transfer, ex) conversational audio/video
  * Bandwidth-sensitive appplication: Application that have throughput requirements

* Transport Service Provided by INternet
  * TCP : Connection Oriented service, Reliable data transfer service
    * SSL : Encryption
  * UDP

* Popular internet application 
  * SMTP, Telnet, HTTP, FTP - TCP

* Web browser : it implements the client side of HTTP 
* Web servers : it implements the server side of HTTP
* Non-persistent connections

* RTT - the time it takes for smal packet to travel from client to server
  * Include packet propagation delays, packet-queuing delays 
  * Three-way handshake

* Persistent connections
  * Server leaves the TCP connection open after reponse. HTML can be sent from server to the same client over a persistent connection.
  * Multiple web pages residing on the same server can be sent from the server to the same client over Connection. Witout wating for replies \(pipelining\)

* HTTP request message
  * Request lines
    * method filed- GET, POST, HEAD, PUT, DELETE
	* URL, Version
  * Header lines
    * filed name
	* value
  * Blank lines
  * Entity body

* Http reponse
  * Last-Modified : is critical field for object caching both in the client and in network cache servers(also known as proxy servers)
  * status code
    * 200 - request succeeded
	* 301 - Moved Permanetly
	* 400 - Bad Request
	* 404 - Not Found
	* 505 - HTTP Version Not Supported
  * Set-cookies: identification number

* User-Service Interation: Cookies
  * Keeping user state with cookies
    * Client -> request -> Server
	* Server -> response(cookie) -> Client
	* Client -> request(cookie) -> Server
	* Cookie specific action - access bacend database
	* Server -> respone -> Client
	* later
	* Client -> request(cookie) -> Server
	* Cookie specific action - access bacend database
	* Server -> respone -> Client

* Web Caching
  * =proxy server
  * Keeping copies of recently requested objects in this storage
  * Client <-> Proxy Server <-> Server
  * Web cache is purchased by ISP
    * Reduce the response time for client request, particularly if the bottleneck bandwidth between client and the cache is much less than the bottleneck bandwitdth between the client and the cache
	* Reduce traffic on an institution's access link to the internet
	  * CDN
	    * institutional network\(100 Mbps LAN\) \( has insitutional cache \) - 15Mbps access link - public access internet

* FTP
  * Commands
    * USER username
    * PASS password
    * LIST
    * RETR filename
    * STOR filename
  * response message
    * 331 Username OK, password required
    * 125 Data connection already open; transfer starting
    * 425 cant' open data connetion
    * 452 Error writing file

* Email
  * Alice's agent -\(SMTP\) Alice's mail server ->\(SMTP\) Bob's mail Server ->\(POP3, IMAP, HTTP\) Bob's agent
* DNS
  * Host aliasing 
    * relay.west-coast.entries.com - canonical hostname
	* www.entries.com, entries.com - aliases
  * Mail server aliasing
    * relay1.west-coas.hotmail.com, hotmail.com
  * Load distribution
    * The DNS databases contains the set of IP addresses
  * How DNS works
    * In centeralized design
	  * SPOF
	  * Traffic volume
	  * Distant centralized database
	  * Maintenance - huge, update frequently
	* A Distributed Hierachical Database
	  * Hirachy of servers
	    * ROOT, TLD, Authorative
	  * Root DNS Servers - 13
		* TLD\(top-level domain\)
	    * com DNS Server
	      * Authoritative DNS servers - primary and secondary
		  * yahoo.com DNS Server
	    * org DNS Server
	    * edu DNS Server
	  * local DNS serve - ISP such as university, company
	    * Interaction of the various DNS server(Hirachy)
	* recurive queries\(ROOT -> TLD -> Autorative \), iterative queries
	* DNS Caching
	  * DNS server receives DNS reply it can cache in its local memory. 
	  * If the information is cached in DNS Server, and another query arrives to the DNS server for the same hostname, the DNS server can provide arrives to the DNS server for the same hostname.
	* DNS Database
	  * \(hostname, IP, NS\)
* p2p
  * scalaibility of p2p architectures
  * uploade rate link by u
  * download rate link by d
  * Server ->\(u\) internet  \(u\)<->\(d\) client
  * Dcs = max{NF/u, F/d_min}, N : the number of client, F : File Size
  * Dp2p = max {F/u, F/d_min, NF/\(u+sum\(u1-uN)\)
  * Dcs > Dp2p
  * p2p is faster than client-server architecture

* Distributed Has Tables(DHTs)
  * Circular DHT - modulo, shortcut, special case of overlay network
    * reduece the amount time of overlay information 
  * Peer churn

* socket programming
  * [UDP](https://github.com/sh92/Network-University/tree/master/UDP_FTP)
  * [TCP](https://github.com/sh92/Network-University/tree/master/rawsocket_tcp)


## Transport Layer
* TCP and UDP
  * transport-layer
    * packet - segments
	* Connection-Oriented multiplexing and demultiplexing
	* well-known port number
	  * 0 to 1023
	  * 0 to 65535
  * TCP
    * reliable data transfer
    * congestion control
	* flow control
	* multiplexing
	  * TFM
	  * FDM
	* rdt1
	  * sending side
      * receiving side
	* rdt2
	  * rdt2.1 2.2
	  * ARQ
	    * Error detetion
		* Receiver feedback
	  * stop-and-wait ARQ
    * rdt3.0
	  * Pipelined Reliable Data Transfer Protocols
	  * Stop-and-wait
	  * Utilization of the sender =  L/R / RTT+L/R
	* Go-Back-N and selective repeat
	  * GBN - sliding-window-protocol

## undirectional data transfer
* undirectional data transfer
* bidrectional data transfer- full-duplex 
* Retransmission

### ETC

* [blocking vs nonblocking vs synchronous vs asynchronous](https://homoefficio.github.io/2017/02/19/Blocking-NonBlocking-Synchronous-Asynchronous/)

* [SAMBA](http://imakeworld.egloos.com/4710527)

* POST vs GET method

* CSMA/CD vs CSMA/CA

* RCP, GRPC

* Good Site
  * [해당 사이트 Lecture Note 참고](http://www.ritsumei.ac.jp/~piumarta/networks/)


### Reference
Computer Networking A Top down approach
