package se.wnn;
import groovy.json.JsonSlurper

def getPomVersion(){
  def file = readFile('pom.xml')
  def project = new XmlSlurper().parseText(file)
  return project.version.text()
}

def getNpmPackageVersion(){
  def file = readFile('package.json')
  def pkg = new JsonSlurper().parseText(file)
  return pkg.version
}

def sonarQualityGateStatus(serverUrl, authString='foo'){
  URLConnection projectStatus = new URL("${serverUrl}").openConnection();
  projectStatus.setRequestProperty("Authorization", "Basic ${authString}");

  def status = new JsonSlurper().parse(new BufferedReader(new InputStreamReader(projectStatus.getInputStream())));
  return status
}

return this;
