package in.rcard.functional.portfolio

package object algebra {
  type ErrorsOr[T] = Either[List[String], T]
}
