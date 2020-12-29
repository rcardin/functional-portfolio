package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Validated
import cats.syntax.validated._
import in.rcard.functional.portfolio.algebra.interpreter.common.{ErrorsOr, Stocks}

object common {
  type Stocks = Set[Stock]
  type ErrorsOr[A] = Validated[List[String], A]
}

final case class Portfolio private (id: PortfolioId, stocks: Stocks)

object Portfolio {
  def emptyPortfolio(id: String): ErrorsOr[Portfolio] =
    PortfolioId.create(id).map(id => Portfolio(id, Set.empty[Stock]))
}

final case class PortfolioId private (id: String)
object PortfolioId {
  def create(id: String): ErrorsOr[PortfolioId] =
    if (id != null)
      PortfolioId(id).valid
    else
      List("A portfolio id cannot be null").invalid
}

case class Stock(id: StockId, owned: Long)

case class StockId(id: String)
