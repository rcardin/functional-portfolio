package in.rcard.functional.portfolio.algebra.interpreter

import collection.mutable.{ Map => MMap}
import in.rcard.functional.portfolio.algebra.{ErrorsOr, PortfolioRepository}

object SomeKindOfDbPortfolioRepository extends PortfolioRepository[Portfolio, PortfolioId, ErrorsOr] {

  private lazy val repo = MMap.empty[PortfolioId, Portfolio]

  override def findById(id: PortfolioId): ErrorsOr[Option[Portfolio]] = Right(repo.get(id))

  override def store(portfolio: Portfolio): ErrorsOr[Portfolio] = {
    repo.put(portfolio.id, portfolio)
    Right(portfolio)
  }
}
