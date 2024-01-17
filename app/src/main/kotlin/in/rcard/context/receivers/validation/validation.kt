package `in`.rcard.context.receivers.validation

import arrow.core.EitherNel
import arrow.core.raise.either

interface Validator<T> {
    fun validate(toValidate: T): EitherNel<ValidationError, T>
}

interface ValidationError

data class CreatePortfolioDTO(val userId: String, val amount: Double)
//    : Validatable<CreatePortfolioDTO> {
//    override fun validate(): EitherNel<ValidationError, CreatePortfolioDTO> = TODO()
// }

val createPortfolioDTOValidator =
    object : Validator<CreatePortfolioDTO> {
        override fun validate(toValidate: CreatePortfolioDTO): EitherNel<ValidationError, CreatePortfolioDTO> {
            // validation logic here
            TODO()
        }
    }

// fun <T : Validatable<T>> process(validatable: T) =
//    either {
//        val validated: T = validatable.validate().bind()
//        // Do something with the validated object
//    }

fun <T> process(
    toValidate: T,
    validator: Validator<T>,
) = either {
    val validated: T = validator.validate(toValidate).bind()
    // Do something with the validated object
}

fun myMain() {
}
