package in.rcard.functional.portfolio.algebra.interpreter

import cats.data.Validated
import cats.syntax.apply._
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
    Validated.cond(id != null, PortfolioId(id), List("A portfolio id cannot be null"))
}

final case class Stock private (id: StockId, owned: Long)
object Stock {
  def create(id: String, owned: Long): ErrorsOr[Stock] = {
    def checkOwnedGreaterThanZero: ErrorsOr[Long] =
      Validated.cond(
        owned > 0,
        owned,
        List(s"The quantity of the stock $id cannot be less than zero, $owned")
      )

    (
      StockId.create(id),
      checkOwnedGreaterThanZero
    ).mapN(Stock(_, _))
  }
}

final case class StockId private (id: String)
object StockId {
  def create(id: String): ErrorsOr[StockId] = {
    def checkNullability: ErrorsOr[String] =
      Validated.cond(id != null, id, List("A stock id cannot be null"))

    def checkStockNameValidity: ErrorsOr[String] =
      Validated.cond(id.forall(_.isUpper), id, List("The whole stock name must be uppercase"))

    checkNullability.andThen(_ => checkStockNameValidity).map(StockId.apply)
  }
}
