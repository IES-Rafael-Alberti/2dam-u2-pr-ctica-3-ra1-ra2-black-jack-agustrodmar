package com.arodmar432p.blackjackspecial.cardGames.data

/**
 * A sealed class representing the different routes in the Blackjack game.
 *
 * @property route The string representation of the route.
 */
sealed class BlackjackRoutes(val route: String) {

    /**
     * Represents the main menu screen route.
     */
    object MainMenuScreen : BlackjackRoutes("mainMenuScreen")

    /**
     * Represents the Blackjack game screen route.
     */
    object BlackjackScreen : BlackjackRoutes("BlackjackScreen")

    /**
     * Represents the Blackjack dealer screen route.
     */
    object BlackjackDealerScreen : BlackjackRoutes("BlackjackDealerScreen")

    /**
     * Represents the results screen route.
     */
    object ResultsScreen : BlackjackRoutes("ResultsScreen")

}