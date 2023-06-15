package de.moshpit.ttt.gamestates;

import de.moshpit.ttt.TTT;

public class GameStateManager {

    private TTT instance;
    private GameState[] gameStates;
    private GameState currentGameState;

    public GameStateManager(TTT instance) {
        this.instance = instance;

        gameStates = new GameState[3];
        gameStates[GameState.LOBBY_STATE] = new LobbyState(this);
        gameStates[GameState.INGAME_STATE] = new IngameState();
        gameStates[GameState.ENDING_STATE] = new EndingState();

    }

    public void setGameState(int gameStateID) {
        if(currentGameState != null)
            currentGameState.stop();
            currentGameState = gameStates[gameStateID];
            currentGameState.start();
    }

    public void stopCurrentGameState() {
        if(currentGameState != null) {
            currentGameState.stop();
            currentGameState = null;
        }

    }

    public GameState getCurrentGameState() {
        return currentGameState;
    }

    public TTT getInstance() {
        return instance;
    }
}
