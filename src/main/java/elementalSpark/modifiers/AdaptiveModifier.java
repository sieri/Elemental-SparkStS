package elementalSpark.modifiers;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static elementalSpark.ElementalSpark.getModID;

public class AdaptiveModifier extends AbstractCardModifier {




    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return getModID().toLowerCase() +  ":Adaptive NL " + rawDescription;
    }



    @Override
    public AbstractCardModifier makeCopy() {
        return new AdaptiveModifier();
    }
}
