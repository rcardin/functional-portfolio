package in.rcard.functional.portfolio.algebra

import cats.data.Reader

trait PortfolioService[Portfolio, PortfolioId, F[_]] {
  def open(id: String): Reader[PortfolioRepository[Portfolio, PortfolioId, F], F[Portfolio]]
}
