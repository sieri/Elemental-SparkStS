package elementalSpark.actions;

import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import elementalSpark.cards.choices.ChooseAir;
import elementalSpark.cards.choices.ChooseEarth;
import elementalSpark.cards.choices.ChooseFire;
import elementalSpark.cards.choices.ChooseWater;
import elementalSpark.powers.AbstractStopElementPower;
import elementalSpark.stances.ElementAbstractStance;

import java.util.ArrayList;

public class ChooseElementAction extends ChooseOneAction {

    public ChooseElementAction() {
        super(getChoices());
    }

    private static ArrayList<AbstractCard> getChoices()
    {
        ElementAbstractStance.ElementType[] types = ElementAbstractStance.ElementType.values();
        ArrayList<AbstractCard> cards  = new ArrayList<>();

        for (ElementAbstractStance.ElementType t:types) {
            if (t !=  ElementAbstractStance.ElementType.None)
            {
                add(cards, t);
            }
        }

        return cards;
    }

    private static void add(ArrayList<AbstractCard> cards, ElementAbstractStance.ElementType t){

        //check if the current element is blocked
        for (AbstractPower p : AbstractDungeon.player.powers) {
            if (p instanceof AbstractStopElementPower)
            {
                if (((AbstractStopElementPower) p).elementType== t)
                {
                    return;
                }
            }
        }

        //if not add to the list
        switch(t)
        {
            case Fire:
                cards.add(new ChooseFire());
                break;
            case Water:
                cards.add(new ChooseWater());
                break;

            case Air:
                cards.add(new ChooseAir());
                break;

            case Earth:
                cards.add(new ChooseEarth());
                break;
        }
    }

    @Override
    public void update() {
        if(!getChoices().isEmpty())
        {
            super.update();
        }
        else
        {
            this.isDone = true;
        }
    }
}
