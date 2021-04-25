package com.rtjvm.scala.oop.Files

abstract class DirEntry(val parentpath: String, val name: String) {
  def path:String = parentpath + Directory.SEPARATOR + name
  def asDirectory: Directory
}
