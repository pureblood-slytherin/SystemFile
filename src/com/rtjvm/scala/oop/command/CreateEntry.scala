package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.Files.{DirEntry, Directory}
import com.rtjvm.scala.oop.fileSystem.State

abstract class CreateEntry(name : String) extends Command{
  override def apply(state: State): State ={
    val wd = state.wd
    if(wd.hasEntry(name)){
      state.setMessage("Entry : " + name + " already exist")
    } else if(name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " Must not contains Separators")
    }else if(checkillegal(name)){
      state.setMessage(name+ " : This is not a legal name")
    }else{
      doCreateEntry(state, name)
    }

  }

  def checkillegal(str: String):Boolean ={
    name.contains(".")
  }
  def doCreateEntry(state: State, name: String): State={
    def updateStructure(currenydirectory: Directory, path: List[String], newEntry: DirEntry): Directory ={
      if(path.isEmpty) currenydirectory.addEntry(newEntry)
      else{
        val oldEntry = currenydirectory.findEntry(path.head).asDirectory
        currenydirectory.replaceEntry(oldEntry.name,updateStructure(oldEntry,path.tail,newEntry))
      }
    }


    val wd = state.wd
    // 1. All the directories in the full path
    val allDIRsinpath = wd.getallDIRpath

    //2. Create new directory in the working directory
    //val newDir = Directory.empty(wd.path,name)
    //TODO implement this
    val newEntry: DirEntry = createSpecificEntry(state)
    // 3. Update the whole directry structure starting from the root

    val newRoot = updateStructure(state.root,allDIRsinpath,newEntry)
    //4. find new wd instance given it full path, in the new directory structure

    val newWD = newRoot.findDesendent(allDIRsinpath)

    State(newRoot,newWD)


  }

  def createSpecificEntry(state: State): DirEntry
}
