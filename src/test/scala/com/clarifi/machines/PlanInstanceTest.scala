package com.clarifi.machines

import scalaz.std.anyVal._
import scalaz.Equal

import org.scalacheck.{Gen, Arbitrary}

import Plan._
import scalaz.scalacheck.ScalazProperties._
import scalaz.scalacheck.ScalaCheckBinding._

object PlanInstanceTest extends org.specs2.scalaz.Spec{

  type StopOrReturn[+A] = Plan[Nothing, Nothing, A]

  implicit def stopOrReturnArb[A](implicit a: Arbitrary[A]): Arbitrary[StopOrReturn[A]] =
    Arbitrary(Gen.oneOf(
      Arbitrary.arbitrary[A].map(Return(_)),
      Stop
    ))

  implicit def StopOrReturnEqual[A: Equal]: Equal[StopOrReturn[A]] = new Equal[StopOrReturn[A]]{
    override def equal(a1: StopOrReturn[A], a2: StopOrReturn[A]) = (a1, a2) match{
      case (Stop, Stop) => true
      case (Return(x), Return(y)) => Equal[A].equal(x, y)
      case _ => false
    }
  }

  checkAll(monadPlus.strongLaws[StopOrReturn])
}
