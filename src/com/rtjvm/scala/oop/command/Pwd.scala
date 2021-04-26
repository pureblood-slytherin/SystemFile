package com.rtjvm.scala.oop.command

import com.rtjvm.scala.oop.fileSystem.State

class Pwd extends Command {
  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}
