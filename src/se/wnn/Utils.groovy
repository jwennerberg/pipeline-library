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

return this;
