/* This script is used in Jenkins groovy plugin to monitor running jobs and slaves */
import jenkins.model.*
jenkins = jenkins.model.Jenkins

def num_jobs = jenkins.model.Jenkins.getInstance().getItem("Build-PR-Core-Ephemeral-MultiJob").getBuilds().findAll(
{
    it.isInProgress()
    }).size()

println "Number of jobs running: $num_jobs"

num_slaves = 0;
for (slave in jenkins.instance.slaves)
{
label = slave.getLabelString();
    if( label == 'ephemeral-on-demand')
        {
	 online = slave.getComputer().isOffline();
	 if (!online)
	 {
	     num_slaves = num_slaves + 1;
	     //println('Name: ' + slave.name);
             //println('getLabelString: ' + slave.getLabelString());
	     //println('\tcomputer.isOffline: ' + slave.getComputer().isOffline()
	     //println('getNumExectutors: ' + slave.getNumExecutors()
	 }
  }

}
println "Number of slaves running: $num_slaves"
//running_slave.properties is used to draw a graph on Jenkins
new File("/var/lib/jenkins/workspace/monitor-slaves/running_slaves.properties").withWriter{ it << "YVALUE=$num_slaves" }
def diff = num_jobs - num_slaves
new File("/var/lib/jenkins/workspace/monitor-slaves/diff.txt").withWriter{ it << diff }
