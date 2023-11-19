package com.arodmar432p.practica3blackjack

/**
 * Enum class que representa los naipes de una baraja.
 *
 * @property valor El valor numérico del naipe.
 */
enum class Naipe(val valor: Int) {
    AS(1),
    DOS(2),
    TRES(3),
    CUATRO(4),
    CINCO(5),
    SEIS(6),
    SIETE(7),
    OCHO(8),
    NUEVE(9),
    DIEZ(10),
    VALET(10),
    DAME(10),
    ROI(10)
}