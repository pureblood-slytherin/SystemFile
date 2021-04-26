package com.rtjvm.scala.oop.Files

import com.rtjvm.scala.oop.fileSystem.FilesystemException

import scala.annotation.tailrec

class Directory(override val parentpath: String,override val name: String, val contents: List[DirEntry])
  extends DirEntry(parentpath,name) {
  def hasEntry(name:String): Boolean =
    findEntry(name) != null

  def getallDIRpath: List[String] =
    path.substring(1).split(Directory.SEPARATOR).toList.filter(x => !x.isEmpty)

  def findDesendent(path: List[String]) : Directory = {
    if (path.isEmpty) this
    else findEntry(path.head).asDirectory.findDesendent(path.tail)
  }

  def addEntry(newEntry : DirEntry): Directory =
    new Directory(parentpath,name,contents :+ newEntry)

  def findEntry(entryName : String) : DirEntry = {
    @tailrec
    def HelperfindEntry(name : String , contentList : List[DirEntry]): DirEntry ={
      if(contentList.isEmpty) null
      else if(contentList.head.name.equals(name)) contentList.head
      else HelperfindEntry(name,contentList.tail)
    }
    HelperfindEntry(entryName,contents)
  }

  def replaceEntry(entryName : String, newEntry :DirEntry): Directory =
    new Directory(parentpath,name,contents.filter(e => !e.name.equals(entryName)) :+ newEntry)


  def asDirectory: Directory = this

  def getType : String ="Directory"

  def isDirectory: Boolean = true
  def isFile: Boolean = false
  def asFile : File = throw new FilesystemException("A file cannot be converted to a directory")
  def isRoot:Boolean = parentpath.isEmpty

}

object Directory{
  val SEPARATOR ="/"
  val ROOT_PATH ="/"

  def ROOT: Directory = empty("","");
  def empty(parentpath: String,name: String) : Directory =
    new Directory(parentpath,name,List())
}