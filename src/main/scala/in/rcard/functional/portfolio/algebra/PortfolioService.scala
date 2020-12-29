package in.rcard.functional.portfolio.algebra

trait PortfolioService[PortfolioId, Portfolio, F[_]] {
  def open(id: PortfolioId): F[Portfolio]
}
