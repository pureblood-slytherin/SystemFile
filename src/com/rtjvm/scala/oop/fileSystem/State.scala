package com.rtjvm.scala.oop.fileSystem

import com.rtjvm.scala.oop.Files.Directory
import com.rtjvm.scala.oop.fileSystem.State.SHELL_TOKEN

// this will hold our state of the world
class State(val root: Directory, val wd: Directory,val output: String) {
  def setMessage(message:String):State =
    State(root,wd,message)

  def show:Unit = {
    println(output)
    print(State.SHELL_TOKEN)
  }
}
object State{
  val SHELL_TOKEN = "$ "
  def apply(root:Directory,wd:Directory,output :String =""):State =
    new State(root,wd,output)
}
