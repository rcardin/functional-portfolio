package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Reader
import cats.syntax.either._
import in.rcard.functional.portfolio.algebra.{PortfolioRepository, PortfolioService}
import in.rcard.functional.portfolio.algebra.interpreter.common.ErrorsOr


object PortfolioService extends PortfolioService[Portfolio, PortfolioId, ErrorsOr[Portfolio]] {

  type Repository = PortfolioRepository[Portfolio, PortfolioId, ErrorsOr]

  override def open(id: String): Reader[Repository, ErrorsOr[Portfolio]] = Reader { repo =>
    (for {
      pId <- PortfolioId.create(id).toEither
      _ <- repo.findById(pId).toEither
      portfolio <- Portfolio.emptyPortfolio(id).toEither
      _ <- repo.store(portfolio).toEither
    } yield portfolio).toValidated

    // XXX Which is the better approach?
//    val portfolioId: ErrorsOr[PortfolioId] = PortfolioId.create(id)
//    portfolioId
//      .andThen(pId => repo.findById(pId))
//      .andThen(_ => Portfolio.emptyPortfolio(id))
//      .andThen(repo.store)
  }
}
