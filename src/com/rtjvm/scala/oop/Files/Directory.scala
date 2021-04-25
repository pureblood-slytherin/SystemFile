package com.rtjvm.scala.oop.Files

class Directory(override val parentpath: String,override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentpath,name) {
  def hasEntry(name:String): Boolean = ???

  def getallDIRpath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList

  def findDesendent(path: List[String]) : Directory = ???

  def addEntry(newEntry : DirEntry): Directory = ???
  def findEntry(entryName : String) : DirEntry = {
    
  }

  def replaceEntry(entryName : String, newEntry :DirEntry): Directory = ???
  def asDirectory: Directory = this

}

object Directory{
  val SEPARATOR ="/"
  val ROOT_PATH ="/"

  def ROOT: Directory = empty("","");
  def empty(parentpath: String,name: String) : Directory =
    new Directory(parentpath,name,List())
}