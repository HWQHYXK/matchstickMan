package matchstickMan;

public class StatusController
{
    MatchstickMan man;
    public StatusController(MatchstickMan man)
    {
        this.man = man;
    }
    public double damageHP(double value)
    {
        if(value>0&&!man.hpLocked.getValue())
        {
            man.hp.set(man.hp.get()-value);
            return value;
        }
        else return 0;
    }
    public void increaseHP(double value)
    {
        if(value>=0&&man.hp.get()+value<=100)
        {
            man.hp.set(man.hp.get()+value);
        }
    }
    public void setHPLocked(Boolean toStatus)
    {
        man.hpLocked.setValue(toStatus);
    }
    public void reverseHPLocked()
    {
        man.hpLocked.setValue(!man.hpLocked.getValue());
    }
    public void setFrozen(boolean toStatus)
    {
        if(man.hp.get()>0)man.frozen.setValue(toStatus);
    }
    public void reverseFrozen()
    {
        setFrozen(!man.frozen.get());
    }
}
