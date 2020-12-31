package in.rcard.functional.portfolio.algebra

trait PortfolioRepository[Portfolio, PortfolioId, F[_]] {
  def findById(id: PortfolioId): F[Option[Portfolio]]
  def store(portfolio: Portfolio): F[Portfolio]
}
