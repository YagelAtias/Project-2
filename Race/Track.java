package Race;
/**
 * @authors Yagel Atias 208905448, Slava Ignatiev 322015280
 */

/**
 * Represents a track where racers can run and finish.
 */
public class Track {

    // The count of racers who have finished running on the track
    private int finishedRacers;

    /**
     * Increments the count of racers who have finished running on the track.
     */
    public void incrementFinishedRacers() {
        finishedRacers++;
    }

    /**
     * Gets the count of racers who have finished running on the track.
     *
     * @return The number of finished racers.
     */
    public int getFinishedRacers() {
        return finishedRacers;
    }
}
