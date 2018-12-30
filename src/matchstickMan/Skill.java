package matchstickMan;

import javafx.animation.Timeline;

public interface Skill
{
    void changeStatus();
    void play();
    void stop();
    void setMan(MatchstickMan mam);
}
