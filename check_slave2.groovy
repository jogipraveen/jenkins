/* 
This script is used in Jenkins groovy plugin to monitor running jobs and slaves 
value of diff is (number of jobs - number of slaves) running 
*/

def j=0
def i=0
File file1 = new File("/var/lib/jenkins/workspace/monitor-slaves/slaves.properties")
File file2 = new File("/var/lib/jenkins/workspace/monitor-slaves/diff.txt")

int num_slaves_start = file1.text.toInteger()
int diff = file2.text.toInteger()

if (num_slaves_start > 1){
    println "Starting $num_slaves_start slaves"
    j=num_slaves_start
}
else if (diff >= 0){
    println "About to start new slave"
    j=1
}

else if (diff < 0){
    println "Default number of Additional on-demand slaves unchanged"
      
}

for (slave in hudson.model.Hudson.instance.slaves) {
    label = slave.getLabelString();
    if( label == 'ephemeral-on-demand')
        if (slave.getComputer().isOffline().toString() == "true"){
	        while(i < j) {
		            i++
			    println('Slave: ' + slave.name + 'starting');
			    slave.computer.connect(true)
	                    println "Sleep for a while to connect Jenkins slave properly"
			    sleep(30000)
			    break;
	        }
	}
}
