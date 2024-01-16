package `in`.rcard.context.receivers.validation

import arrow.core.EitherNel

interface Validatable<T> {
    fun validate(): EitherNel<ValidationError, T>
}

interface ValidationError

data class CreatePortfolioDTO(val userId: String, val amount: Double) : Validatable<CreatePortfolioDTO> {
    override fun validate(): EitherNel<ValidationError, CreatePortfolioDTO> = TODO()
}

fun <T : Validatable<T>> validate(validatable: T): EitherNel<ValidationError, T> = validatable.validate()

fun myMain() {
}
