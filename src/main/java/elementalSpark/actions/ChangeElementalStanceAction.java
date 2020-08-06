package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import elementalSpark.ElementalSpark;
import elementalSpark.powers.AbstractStopElementPower;
import elementalSpark.powers.StopFirePower;
import elementalSpark.stances.ElementAbstractStance.ElementType;
import elementalSpark.stances.ElementAirStance;
import elementalSpark.stances.ElementEarthStance;
import elementalSpark.stances.ElementFireStance;
import elementalSpark.stances.ElementWaterStance;

import java.util.ArrayList;
import java.util.LinkedList;

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
        ElementType[] types = ElementType.values();

        LinkedList<ElementType> typeAvailable = new LinkedList<ElementType>();
        for (ElementType t:types) {
            if (t !=  ElementType.None)
            {
                typeAvailable.add(t);
            }
        }

        for (AbstractPower p :AbstractDungeon.player.powers) {
            if (p instanceof AbstractStopElementPower)
            {
                typeAvailable.remove(((AbstractStopElementPower) p).elementType);
            }
        }


        //get an element type at random
        return typeAvailable.get(new Random().random(typeAvailable.size())-1);

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
            AbstractDungeon.player.getPower(target).flash();
            isDone = true;
            return;
        }

        super.update();
    }
}
