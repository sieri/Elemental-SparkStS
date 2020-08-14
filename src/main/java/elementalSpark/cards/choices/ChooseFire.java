package elementalSpark.cards.choices;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import elementalSpark.ElementalSpark;
import elementalSpark.actions.ChangeElementalStanceAction;
import elementalSpark.cards.AbstractDynamicCard;
import elementalSpark.characters.TheSpark;
import elementalSpark.stances.ElementAbstractStance.ElementType;

import static elementalSpark.ElementalSpark.makeCardPath;


public class ChooseFire extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = ElementalSpark.makeID(ChooseFire.class.getSimpleName());
    public static final String IMG = makeCardPath("ChooseFire.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheSpark.Enums.COLOR_WHITE;


    private static final int COST = 0;

    // /STAT DECLARATION/


    public ChooseFire() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }


    @Override
    public void upgrade() {

    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.onChoseThisOption();
    }

    @Override
    public void onChoseThisOption() {
        addToBot(new ChangeElementalStanceAction(ElementType.Fire));
    }
}
