package de.difuture.ekut.pht.lib.http


/**
 * Represents common Http Status Codes
 *
 * @author Lukas Zimmermann
 * @since 0.0.1
 *
 */
enum class HttpStatusCode(val value: Int) {

    OK(200),
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    INTERNAL_SERVER_ERROR(500);

    companion object {

        private val statusCodes = mapOf(
                200 to OK,
                401 to UNAUTHORIZED,
                404 to NOT_FOUND,
                500 to INTERNAL_SERVER_ERROR
        )
        fun of(code: Int) = statusCodes[code]
    }
}
