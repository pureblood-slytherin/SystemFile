package com.rtjvm.scala.oop.fileSystem

import com.rtjvm.scala.oop.Files.Directory
import com.rtjvm.scala.oop.command.Command

import java.util.Scanner

object FileSystem extends App{

  val root =Directory.ROOT
  var state =State(root,root)    // only place where var is used
  val myScanner =new Scanner(System.in)
  while(true){
    state.show
    val input = myScanner.nextLine()
    state = Command.from(input).apply(state)
    //print("$ ")
    //println(myScanner.nextLine())
  }
}
