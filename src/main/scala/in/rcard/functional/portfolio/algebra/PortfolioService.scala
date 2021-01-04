package in.rcard.functional.portfolio.algebra

import cats.data.Reader

trait PortfolioService[Portfolio, PortfolioId, F[_]] {
  def open(id: String): Reader[PortfolioRepository[Portfolio, PortfolioId, F], F[Portfolio]]
  def sell(idPortfolio: String, stock: String, qty: Long): Reader[PortfolioRepository[Portfolio, PortfolioId, F], F[Portfolio]]
}
