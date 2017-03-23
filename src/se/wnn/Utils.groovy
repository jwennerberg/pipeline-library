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

def sonarQualityGateStatus(propertiesFile, authString='foo'){
  def file = readFile("${propertiesFile}");
  Properties props = new Properties();
  InputStream is = new ByteArrayInputStream(file.getBytes());
  props.load(is);

  URLConnection projectStatus = new URL(props.getProperty('serverUrl')).openConnection();
  projectStatus.setRequestProperty("Authorization", "Basic ${authString}");

  def status = new JsonSlurper().parse(new BufferedReader(new InputStreamReader(projectStatus.getInputStream())));
  return status.value
}

return this;
