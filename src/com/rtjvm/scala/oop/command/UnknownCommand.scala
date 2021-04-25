package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.fileSystem.State

class UnknownCommand extends Command {
  override def apply(state: State): State =
    state.setMessage("Command Not Found!")

}
