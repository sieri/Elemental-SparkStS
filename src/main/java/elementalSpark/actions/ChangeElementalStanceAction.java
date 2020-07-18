package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;

import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import elementalSpark.stances.ElementAbstractStance.ElementType;
import elementalSpark.stances.ElementAirStance;
import elementalSpark.stances.ElementEarthStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.stances.ElementWaterStance;

public class ChangeElementalStanceAction extends ChangeStanceAction {

    public static AbstractStance chooseStance(ElementType type)
    {
        switch (type)
        {
            case Air:
                return new ElementAirStance();
            case Fire:
                return new ElementFireStance();
            case Earth:
                return new ElementEarthStance();
            case Water:
                return new ElementWaterStance();

            default:
                return new NeutralStance();
        }
    }

    public static AbstractStance randomStance()
    {
        //get an element type at random
        ElementType type =  ElementType.values()[new Random().random(ElementType.values().length-1)]; // remove -1 so None doesn't appear, it's always the last one

        return chooseStance(type);

    }

    public ChangeElementalStanceAction(ElementType type)
    {
        super(chooseStance(type));
    }

    public ChangeElementalStanceAction()
    {
        super(randomStance());
    }

}
