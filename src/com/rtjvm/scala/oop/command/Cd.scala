package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.Files.{DirEntry, Directory}
import com.rtjvm.scala.oop.fileSystem.State

import javax.swing.JToolBar.Separator
import scala.annotation.tailrec

class Cd(dir: String) extends Command {
  override def apply(state: State): State = {
    // 1. Find the root
    val root = state.root
    val wd = state.wd
    //2. Find the absolute path of the directory I want to cd to
    val absolutePath = {
      if(dir startsWith Directory.SEPARATOR) dir
      else if(wd.isRoot) wd.path+ dir
      else wd.path + Directory.SEPARATOR + dir
    }

    //3.  find the directory to cd to , given the path

    val destinationDirectory = doFindEntry(root,absolutePath)

    //4. Change the state given the new directory
    if(destinationDirectory == null || !destinationDirectory.isDirectory)
      state.setMessage(dir + ": no such directry")
    else
      State(root,destinationDirectory.asDirectory)
  }


  def doFindEntry(root: Directory, path: String): DirEntry = {

    // 1. Path tokens
    val tokens: List[String] = path.substring(1).split(Directory.SEPARATOR).toList
    @tailrec
    def findEntryHelper(currentDirectry: Directory, path: List[String]):DirEntry= {
      if(path.isEmpty || path.head.isEmpty) currentDirectry
      else if(path.tail.isEmpty) currentDirectry.findEntry(path.head)
      else {
        val nextDir = currentDirectry.findEntry(path.head)
        if(nextDir==null || !nextDir.isDirectory) null
        else findEntryHelper(nextDir.asDirectory,path.tail)
      }
    }
    @tailrec
    def CollapseRelativeTokens(path: List[String], result:List[String]): List[String]={
      if(path.isEmpty) result
      else if(".".equals(path.head)) CollapseRelativeTokens(path.tail, result)
      else if("..".equals(path.head)){
        if(result.isEmpty) null
        else CollapseRelativeTokens(path.tail, result.init)
      } else CollapseRelativeTokens(path.tail, result :+ path.head)

    }
    val newTokens = CollapseRelativeTokens(tokens,List())

    //2. Navigate to the correct entry
    //findEntryHelper(root,tokens)
    if (newTokens == null) null
    else findEntryHelper(root, newTokens)
  }
}
