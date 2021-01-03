package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Reader
import in.rcard.functional.portfolio.algebra.{ErrorsOr, PortfolioRepository, PortfolioService}

object PortfolioService extends PortfolioService[Portfolio, PortfolioId, ErrorsOr] {

  type Repository = PortfolioRepository[Portfolio, PortfolioId, ErrorsOr]

  override def open(id: String): Reader[Repository, ErrorsOr[Portfolio]] =
    Reader { repo =>
      for {
        pId <- PortfolioId.create(id)
        _ <- repo.findById(pId)
        portfolio <- Portfolio.emptyPortfolio(id)
        _ <- repo.store(portfolio)
      } yield portfolio
    }
}
