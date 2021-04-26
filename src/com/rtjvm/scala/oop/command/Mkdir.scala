package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.Files.{DirEntry, Directory}
import com.rtjvm.scala.oop.fileSystem.State

class Mkdir(name:String) extends CreateEntry(name) {
  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path,name)
}
