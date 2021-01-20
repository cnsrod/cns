set ns [ new Simulator ] 

set tf [ open p1.tr w ]
$ns trace-all $tf
set nf [ open p1.nam w ]
$ns namtrace-all $nf

#finish procedure

proc finish { } {
global ns nf tf
$ns flush-trace
close $tf
close $nf
exec nam p1.nam &
exit 0
}

# The below code is used to create the nodes. 
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node] 
set n3 [$ns node]

#This is used to give color to the packets.
$ns color 1 "red"
$ns color 2 "blue"
$n0 label "Source/udp0"
$n1 label "Source/udp1"
$n2 label "Router"
$n3 label "Destination/Null"

#Vary the below Bandwidth and see the number of packets dropped.
$ns duplex-link $n0 $n2 1Mb 300ms DropTail
$ns duplex-link $n1 $n2 20Mb 300ms DropTail
$ns duplex-link $n2 $n3 0.5Mb 300ms DropTail


#The below code is used to set the queue size b/w the nodes
$ns set queue-limit $n0 $n2 10
$ns set queue-limit $n1 $n2 10
$ns set queue-limit $n2 $n3 5

#The below code is used to attach an UDP agent to n0, UDP agent to n1 and null agent to n3
set udp0 [new Agent/UDP]
$ns attach-agent $n0 $udp0
set cbr0 [new Application/Traffic/CBR]
$cbr0 attach-agent $udp0 

set udp1 [new Agent/UDP]
$ns attach-agent $n1 $udp1
set cbr1 [new Application/Traffic/CBR]
$cbr1 attach-agent $udp1

set null [new Agent/Null]
$ns attach-agent $n3 $null 

#The below code sets the udp0 packets to red and udp1 packets to blue color

$udp0 set class_ 1
$udp1 set class_ 2

#The below code is used to connect the agents
$ns connect $udp0 $null
$ns connect $udp1 $null

#The below code is used to set the packet size to 500
$cbr1 set packetSize_ 500Mb

#The below code is used to set the interval of the packets,i.e., Data rate of the packets. 
#if the data rate is high then packets drops are high.

$cbr1 set interval_ 0.005


#ns scheduler
$ns at 0.1 "$cbr0 start"
$ns at 0.1 "$cbr1 start"
$ns at 5.0 "finish"

#start simulation
$ns run