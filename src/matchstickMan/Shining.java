package matchstickMan;

import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;

import java.util.Random;

public class Shining implements Skill
{
    public boolean enabled = false;
    public MatchstickMan host;
    public  Bloom bloom = new Bloom();

    public Shining()
    {
        bloom.setThreshold(0.3);
    }

    @Override
    public void changeStatus()
    {
        host.isShining = !host.isShining;
    }

    @Override
    public void play()
    {
        Random rd = new Random();
        host.setColor(Color.rgb(rd.nextInt(255),rd.nextInt(255),rd.nextInt(255)));
        host.setEffect(bloom);
        changeStatus();
    }
    public void play(Color color)
    {
        host.setColor(color);
        host.setEffect(bloom);
        changeStatus();
    }

    @Override
    public void setMan(MatchstickMan mam)
    {
        host = mam;
    }

    @Override
    public void stop()
    {
        host.setColor(host.skin);
        host.setEffect(null);
        changeStatus();
    }
}
