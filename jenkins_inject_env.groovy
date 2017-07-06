import hudson.model.*
//def build = Thread.currentThread().executable
def fileName1 = "nrk.automation/pom.xml"
def testFile1 = new File(fileName1)
def fileName2 = "/apps/jenkins/workspace/Test/NewCucumberTests Refactor/ap114153-sps-grk-plan-administration/cucumber/nrk.automation/pom.xml"
def testFile2 = new File(fileName2)
if (!testFile1.exists())
{
    def pa = new ParametersAction([
    new StringParameterValue("automation_pom", "nrk.automation")
])
    // add variable to current job
    Thread.currentThread().executable.addAction(pa)
}
else 
{
    def pa = new ParametersAction([
    new StringParameterValue("automation_pom", "ap114153-sps-grk-plan-administration/cucumber/nrk.automation") 
])
    // add variable to current job
    Thread.currentThread().executable.addAction(pa)
}
