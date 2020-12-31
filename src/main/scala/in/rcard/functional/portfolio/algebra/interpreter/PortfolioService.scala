package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Reader
import in.rcard.functional.portfolio.algebra.{PortfolioRepository, PortfolioService}
import in.rcard.functional.portfolio.algebra.interpreter.common.ErrorsOr


object PortfolioService extends PortfolioService[Portfolio, PortfolioId, ErrorsOr[Portfolio]] {

  type Repository = PortfolioRepository[Portfolio, PortfolioId, ErrorsOr]

  override def open(id: String): Reader[Repository, ErrorsOr[Portfolio]] = Reader { repo =>
    val portfolioId: ErrorsOr[PortfolioId] = PortfolioId.create(id)
    portfolioId.andThen(pId => repo.findById(pId))
    portfolioId.andThen(_ => Portfolio.emptyPortfolio(id))
    // TODO Save to the database
  }
}
