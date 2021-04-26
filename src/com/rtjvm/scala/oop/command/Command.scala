package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.fileSystem.State

trait Command {
  def apply(state: State):State
}

object Command{
  val CD = "cd"
  val TOUCH ="touch"
  val LS = "ls"
  val MKDIR = "mkdir"
  val Pwd = "pwd"
  def emptyCommand : Command = new Command {
    override def apply(state: State): State = state
  }
  def incompleteCommand(name: String) : Command = new Command {
    override def apply(state: State): State = state.setMessage(name + ":Incomplete command")
  }

  def from(input:String):Command= {
    val tokens: Array[String] = input.split(" ")

    if (input.isEmpty || tokens.isEmpty) emptyCommand
    else if(MKDIR.equals(tokens(0))){
      if(tokens.length<2) incompleteCommand(MKDIR)
      else new Mkdir(tokens(1))
    }else if(LS.equals(tokens(0))) {
      new Ls
    } else if(Pwd.equals(tokens(0))) {
      new Pwd
    }else if(TOUCH.equals(tokens(0))){
      if(tokens.length<2) incompleteCommand(TOUCH)
      else new Touch(tokens(0))
    }
    else if (CD.equals(tokens(0))){
      if(tokens.length<2) incompleteCommand(CD)
      else new Cd(tokens(1))
    }
    else new UnknownCommand
  }
}
