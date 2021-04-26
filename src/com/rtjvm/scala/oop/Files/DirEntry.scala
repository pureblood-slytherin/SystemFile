package com.rtjvm.scala.oop.Files

abstract class DirEntry(val parentpath: String, val name: String) {
  def path:String = {
    val separatorIFnecessary =
      if(Directory.ROOT_PATH.equals(parentpath)) ""
      else Directory.SEPARATOR
    parentpath + separatorIFnecessary + name
  }
  def asDirectory: Directory
  def getType : String
  def asFile :File
  def isDirectory :Boolean
  def isFile : Boolean
}
