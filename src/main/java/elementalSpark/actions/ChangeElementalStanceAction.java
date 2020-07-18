package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import elementalSpark.ElementalSpark;
import elementalSpark.powers.StopFirePower;
import elementalSpark.stances.ElementAbstractStance.ElementType;
import elementalSpark.stances.ElementAirStance;
import elementalSpark.stances.ElementEarthStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.stances.ElementWaterStance;

import static elementalSpark.ElementalSpark.makeID;

public class ChangeElementalStanceAction extends ChangeStanceAction {

    private ElementType elementType;

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

    public static ElementType randomStance()
    {
        //TODO:Prevent current and blocked stances
        //get an element type at random
        return  ElementType.values()[new Random().random(ElementType.values().length-1)]; // remove -1 so None doesn't appear, it's always the last one

    }

    public ChangeElementalStanceAction(ElementType type)
    {
        super(chooseStance(type));

        this.elementType = type;

    }

    public ChangeElementalStanceAction()
    {
        this(randomStance());
    }


    @Override
    public void update() {
        //check if player has a power that would prevent application of the stance
        String target = "";
        switch (elementType)
        {
            case Fire:
                target = makeID("StopFirePower");
                break;
            case Water:
                target = makeID("StopWaterPower");
                break;
            case Air:
                target = makeID("StopAirPower");
                break;
            case Earth:
                target = makeID("StopEarthPower");
                break;
        }


        if( !target.isEmpty() && AbstractDungeon.player.hasPower(target))
        {
            isDone = true;
            return;
        }

        super.update();
    }
}
