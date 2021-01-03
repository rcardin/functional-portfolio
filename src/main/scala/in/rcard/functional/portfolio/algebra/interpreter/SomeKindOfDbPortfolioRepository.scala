package in.rcard.functional.portfolio.algebra.interpreter

import in.rcard.functional.portfolio.algebra.{ErrorsOr, PortfolioRepository}

object SomeKindOfDbPortfolioRepository extends PortfolioRepository[Portfolio, PortfolioId, ErrorsOr] {

  override def findById(id: PortfolioId): ErrorsOr[Option[Portfolio]] = ???

  override def store(portfolio: Portfolio): ErrorsOr[Portfolio] = ???
}
