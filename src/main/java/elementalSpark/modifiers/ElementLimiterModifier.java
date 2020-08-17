package elementalSpark.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import elementalSpark.stances.ElementAbstractStance;
import elementalSpark.stances.ElementAbstractStance.ElementType;

import java.util.Map;

import static elementalSpark.ElementalSpark.makeID;

public class ElementLimiterModifier extends AbstractCardModifier {

    private final ElementType elementType;
    private final String keyword;

    public ElementLimiterModifier(ElementType type)
    {
        this.elementType = type;

        Map<String,String> texts = CardCrawlGame.languagePack.getUIString(makeID(this.getClass().getSimpleName())).TEXT_DICT;
        String element = type.toString();

        keyword = texts.get(element);

    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card)
    {
        return keyword + rawDescription;
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        if (AbstractDungeon.player.stance instanceof ElementAbstractStance)
        {
            ElementType t = ((ElementAbstractStance) AbstractDungeon.player.stance).type;

            return elementType == t;

        }
        return false;
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ElementLimiterModifier(elementType);
    }
}
