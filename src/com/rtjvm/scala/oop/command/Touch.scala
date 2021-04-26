package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.Files.{DirEntry, File}
import com.rtjvm.scala.oop.fileSystem.State

class Touch(name:String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path,name)
}
