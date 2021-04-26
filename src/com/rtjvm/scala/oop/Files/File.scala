package com.rtjvm.scala.oop.Files

import com.rtjvm.scala.oop.fileSystem.FilesystemException

import java.nio.file.FileSystemException

class File(override val name: String, override val parentpath: String,val contents: String)
  extends DirEntry(parentpath,name) {
  override def asDirectory: Directory =
    throw new FilesystemException("A file cann't be converted to a directory")
  override def getType : String = "File"

  def asFile : File = this
  def isDirectory: Boolean = false
  def isFile: Boolean = true
}

object File{

  def empty(parentPath: String,name: String ) : File ={
    new File(name,parentPath,"")
  }
}