package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Reader
import in.rcard.functional.portfolio.algebra.{ErrorsOr, PortfolioRepository, PortfolioService}

import scala.runtime.Nothing$

object PortfolioService extends PortfolioService[Portfolio, PortfolioId, ErrorsOr] {

  type Repository = PortfolioRepository[Portfolio, PortfolioId, ErrorsOr]

  override def open(id: String): Reader[Repository, ErrorsOr[Portfolio]] =
    Reader { repo =>
      for {
        pId <- PortfolioId.create(id)
        maybePort <- repo.findById(pId)
        _ <- Either.cond(maybePort.isEmpty, List(s"Portfolio $id already exists"), Nil) // XXX Mmm...
        portfolio <- Portfolio.emptyPortfolio(id)
        _ <- repo.store(portfolio)
      } yield portfolio
    }

  override def sell(idPortfolio: String, stock: String, qty: Long): Reader[Repository, ErrorsOr[Portfolio]] = ???
//    Reader { repo =>
//      for {
//        pId <- PortfolioId.create(idPortfolio)
//        portfolio <- repo.findById(pId)
//      } yield portfolio
//    }
}
