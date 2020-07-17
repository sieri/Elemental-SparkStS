package elementalSpark.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementAbstractStance.ElementType;
import java.util.Map;

import static elementalSpark.ElementalSpark.getModID;
import static elementalSpark.ElementalSpark.makeID;





public abstract class AbstractAdaptiveModifier extends AbstractCardModifier implements ElementAbstractStance.Sub {



    protected Map<String, String> effects;

    protected ElementType currentElement = ElementType.None;

    public AbstractAdaptiveModifier()
    {
        super();

    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        effects = CardCrawlGame.languagePack.getUIString(makeID(this.getClass().getSimpleName())).TEXT_DICT;
        ElementAbstractStance.subscribe(this);
    }

    @Override
    public void onRemove(AbstractCard card) {
        super.onRemove(card);
        ElementAbstractStance.unsubscribe(this);
    }

    private String getCurrentEffect()
    {
        if(AbstractDungeon.player != null && AbstractDungeon.player.stance instanceof ElementAbstractStance)
        {
            String element = ((ElementAbstractStance) AbstractDungeon.player.stance).type.toString();
            if(effects.containsKey(element))
            {
                return effects.get(element);
            }
        }
        return "";

    }


    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return getModID().toLowerCase() +  ":Adaptive NL " + rawDescription + getCurrentEffect();
    }

    @Override
    public void notify(ElementType element) {

        if (element != currentElement)
        {
            switch (currentElement)
            {
                case Fire:
                    exit_fire();
                case Water:
                    exit_water();
                case Air:
                    exit_air();
                case Earth:
                    exit_earth();
            }



            switch (element)
            {
                case Fire:
                    fire();
                case Water:
                    water();
                case Air:
                    air();
                case Earth:
                    earth();
            }

            currentElement = element;
        }

    }

    @Override
    public void onDrawn(AbstractCard card) {
        card.initializeDescription();
    }

    protected abstract void earth();

    protected abstract void air();

    protected abstract void water();

    protected abstract void fire();

    protected abstract void exit_earth();

    protected abstract void exit_air();

    protected abstract void exit_water();

    protected abstract void exit_fire();

}
