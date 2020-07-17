package elementalSpark.stances;

import basemod.interfaces.ISubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.stances.AbstractStance;
import elementalSpark.ElementalSpark;

import java.util.LinkedList;


public abstract class ElementAbstractStance extends AbstractStance {

    public enum ElementType {None ,Fire, Water, Air, Earth}

    public ElementType type;


    public ElementAbstractStance()
    {
        super();
        this.img = ImageMaster.loadImage("elementalSparkResources/images/char/SparkCharacter/orb/layer3.png");
    }

    @Override
    public void onEnterStance() {
        super.onEnterStance();
        updateCards();
    }

    @Override
    public void onExitStance() {
        super.onExitStance();

        if( !(AbstractDungeon.player.stance  instanceof ElementAbstractStance))
        {
            updateCards();
        }
    }

    //Update cards description that could be changed with a card.
    private void updateCards()
    {

        for (Sub sub:subs) {
            sub.notify(type);
        }

        ElementalSpark.logger.debug("Update Elements");

        for (AbstractCard card : AbstractDungeon.player.hand.group) {

            card.initializeDescription();

        }


    }


    //Subscription system for change needed when the stances change
    public interface Sub{
        void notify(ElementType element);
    }


    private static final LinkedList<Sub> subs = new LinkedList<Sub>();

    public static void subscribe(Sub s)
    {
        subs.add(s);
    }

    public static void unsubscribe(Sub s)
    {
        subs.remove(s);
    }
}
