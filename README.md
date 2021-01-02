# functional-portfolio

A toy implementation of a portfolio application that uses functional domain modeling and functional 
programming.

## Domain Models

The domain models development uses the _smart constructor_ technique. The use of 
_smart constructor_s allows to avoid the possibility to create models that are invalid.

## Cats Library

The project uses the [Cats](https://typelevel.org/cats/) library by Typelevel. The type classes used
in the code are (updating):

* [`Validated`](https://typelevel.org/cats/datatypes/validated.html)
* [`Reader`](https://typelevel.org/cats/datatypes/kleisli.html)
